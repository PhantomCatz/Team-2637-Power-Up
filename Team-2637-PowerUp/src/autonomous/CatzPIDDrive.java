package autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzPIDDrive {


	/****************************************************************************
	 * 
	 * PID Drive Constants
	 * 
	 ****************************************************************************/
	/****************************************************************************
	 * PID Drive Timeout Values - In work - not ready for use
	 ****************************************************************************/
	private final static double PID_DRIVE_SHORT_DIST  =  30.0; // 2.5 ft
	private final static double PID_DRIVE_MEDIUM_DIST = 60.0;  // 6.0 ft

	private final static double PID_DRIVE_TIMEOUT_SHORT_DIST = 0.8;
	private final static double PID_DRIVE_TIMEOUT_MED_DIST   = 1.5;
	private final static double PID_DRIVE_MAX_TIMEOUT        = 3.5;  //We travel 204" (17 ft) in ~2.5 sec

	/****************************************************************************
	 * 
	 ****************************************************************************/
	final static double PID_DRIVE_ERROR_THRESHOLD = 1.0; // Stop within 1 inch


	/****************************************************************************
	 *  PID Parameters for Heading Correction
	 ****************************************************************************/
	private final static double PID_DRIVE_KP = 0.200; //TRY 0.260 up to 0.300
	private final static double PID_DRIVE_KD = 0.008; //TRY 0.004 

	private final static double PID_DRIVE_FILTER_CONSTANT = .5;


	/****************************************************************************
	 *  Braking values are for user specified power - assumes power of 0.5??? 
	 *  May work at 0.6???
	 ****************************************************************************/
	static private double PID_DRIVE_BRAKE_POWER = 0.43;
	private final static double PID_DRIVE_BRAKE_TIME = 0.25;

	
	/****************************************************************************
	 * PID Drive Variables
	 ****************************************************************************/
	private static Timer functionTimer;
	private static Timer loopTimer;

	private static boolean debugMode = false;
	private static boolean done = false;

	private static double power;

	private static double deltaTimeSec;
	private static double currentHeading;
	private static double turnValue;
	private static double derivative;
	private static double previousDerivative;
	private static double deltaAngleError;
	private static double previousAngleError;

	private static double encoderPulseCountL;
	private static double encoderPulseCountR;
	private static double prevEncoderPulseCountL;
	private static double prevEncoderPulseCountR;

	private static double driftError;
	private static double cumulativeDriftError;

	private static double distanceAbs;
	private static double plannedTravelDistance;
	private static double distanceTraveledL;
	private static double distanceTraveledR;
	private static double actualDistanceTraveled;
	private static double totalDistanceTraveled;
	private static double distanceError;
	private static double driftnewHeadingAngle;

	private static double timeout = 0.0;
	
	private static double deltaError;
	private static double totalAngleError;
	private static double refHeading;

	
	//Turbo Constants
	final static public double PID_DRIVE_PWR_FILTER_CONSTANT = 0.3;

	static public double PID_DRIVE_PWR_KP = 0.04;
	static public double PID_DRIVE_PWR_KI = 0.0;
	static public double PID_DRIVE_PWR_KD = 0.010;
	
	//TurboVariables
	private static double currentDistance;
	private static double drivePower;
	private static double deltaDistanceError;
	private static double previousDistanceError;
	private static double powerDerivative;
	private static double previousPowerDerivative;
	
	
	//pass 0 for defaultPower to let PID loop set Pwr Values
	public static void PIDDriveNoTrig(double defaultPower, double distance, double timeoutSeconds) {
		
		functionTimer = new Timer();
		loopTimer     = new Timer();
		boolean firstTimePwr = true;

		double lastHeading;
		
		done = false;

		functionTimer.stop();
		functionTimer.reset();
		functionTimer.start();

		distanceAbs = Math.abs(distance);

		calculatePWRKD(distanceAbs);
		
		CatzRobotMap.wheelEncoderL.reset();
		//CatzRobotMap.wheelEncoderR.reset();

//		CatzRobotMap.navx.reset();
//		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);
		refHeading     = CatzRobotMap.navx.getAngle();
		lastHeading    = refHeading;
		
		previousAngleError = 0.0;
		previousDerivative = 0.0;
		previousDistanceError = 0.0;

		printPwrDebugHeader();

		while (done == false) {
			
			deltaTimeSec = loopTimer.get();
			
			currentHeading   = CatzRobotMap.navx.getAngle();

			loopTimer.stop();
			loopTimer.reset();
			loopTimer.start();

			currentDistance = Math.abs(CatzRobotMap.wheelEncoderL.getDistance());
			distanceError = distanceAbs - currentDistance;
			

			if (distanceError < PID_DRIVE_ERROR_THRESHOLD) {
				done = true;
			} else {
				if (functionTimer.get() > timeoutSeconds) {
					done = true;
				} else {

					deltaError      = currentHeading - lastHeading;
					totalAngleError = currentHeading - refHeading;
					
					/**************************************************************
					 * Calculate Heading Derivative Term
					 **************************************************************/
					derivative = (PID_DRIVE_FILTER_CONSTANT * previousDerivative)
							   + ((1 - PID_DRIVE_FILTER_CONSTANT) * (deltaError / deltaTimeSec));
					
					// FILTER OUT INVALID VALUES
					//if(derivative == 0.0 || deltaTimeSec == 0.0) {
					//	derivative = previousDerivative;
					}

					previousDerivative = derivative;

					turnValue = (-PID_DRIVE_KP * totalAngleError) + (PID_DRIVE_KD * derivative);

					/**************************************************************
					 * Calculate Drive Power
					 **************************************************************/
					deltaDistanceError = distanceError - previousDistanceError;
					
					if (firstTimePwr == false)
					{
						powerDerivative = PID_DRIVE_PWR_FILTER_CONSTANT * previousPowerDerivative + 
								((1-PID_DRIVE_PWR_FILTER_CONSTANT) * (deltaDistanceError / deltaTimeSec));
					} else {
						firstTimePwr = false;
						powerDerivative = 0.0;
					}
					
					previousDistanceError = distanceError;
					previousPowerDerivative = powerDerivative;
					
					if (defaultPower == 0.0) {
						// calculates drivetrain power
						drivePower = ((PID_DRIVE_PWR_KP * distanceError) + (PID_DRIVE_PWR_KD * powerDerivative));
						
						// Verify we have not exceeded max power when turning right or left
						if (drivePower > CatzConstants.DRIVE_MAX_POS_POWER)
							drivePower = CatzConstants.DRIVE_MAX_POS_POWER;

						if (drivePower < CatzConstants.DRIVE_MAX_NEG_POWER)
							drivePower = CatzConstants.DRIVE_MAX_NEG_POWER;
						else {
						drivePower = defaultPower;
					}

					/**************************************************************
					 * 
					 **************************************************************/
					CatzRobotMap.drive.arcadeDrive(drivePower, turnValue);

					lastHeading = currentHeading;
					
					printPwrDebugData();
				}
			}
		}
		
		printPwrDebugData();


		/*************************************************************************
		 * Brake using motors 
		 *************************************************************************/
		if (defaultPower < 0.0) {
			CatzRobotMap.drive.tankDrive(PID_DRIVE_BRAKE_POWER, PID_DRIVE_BRAKE_POWER);
		} else if (defaultPower > 0.0) {
			CatzRobotMap.drive.tankDrive(-PID_DRIVE_BRAKE_POWER, -PID_DRIVE_BRAKE_POWER);
		}
		else {
			
			CatzRobotMap.drive.tankDrive(0.0, 0.0);
		}


}


	
	

	
	
	
	
	

	

	
	
	

	
	public static void PIDDrive(double drivePower, double distance, double timeoutSeconds) {
		
		boolean firstTime;
		double deltaPulseCount;
		
		functionTimer = new Timer();
		loopTimer     = new Timer();

		functionTimer.stop();
		functionTimer.reset();
		functionTimer.start();


		distanceAbs = Math.abs(distance);
		power = drivePower;

		CatzRobotMap.wheelEncoderL.reset();
		//CatzRobotMap.wheelEncoderR.reset();
		
		CatzRobotMap.navx.reset();
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);

		previousAngleError = 0.0;
		previousDerivative = 0.0;
		prevEncoderPulseCountL = CatzRobotMap.wheelEncoderL.get();
		//prevEncoderPulseCountR = CatzRobotMap.wheelEncoderR.get();
		cumulativeDriftError = 0.0;
		totalDistanceTraveled = 0.0;

		calcTimeout(power, distance, timeoutSeconds);

		done = false;
		firstTime = true;

		printDebugInit();
		printDebugHeader();
		printPwrDebugHeader();
		Timer.delay(0.015);

		loopTimer.reset();
		loopTimer.start();
		

		while (done == false) {
			/**********************************************************************
			 * Get Data Samples
			 **********************************************************************/
			encoderPulseCountL = CatzRobotMap.wheelEncoderL.get();
			//encoderPulseCountR = CatzRobotMap.wheelEncoderR.getDistance();
			currentHeading     = CatzRobotMap.navx.getAngle();

			deltaTimeSec = loopTimer.get();

			loopTimer.stop();
			loopTimer.reset();
			loopTimer.start();

			/**********************************************************************
			 * Add code to deal with dead encoder???
			 **********************************************************************/

			/**********************************************************************
			 * Calc Drift Values
			 **********************************************************************/
			deltaPulseCount = encoderPulseCountL - prevEncoderPulseCountL;
			distanceTraveledL = deltaPulseCount * CatzConstants.DRIVE_ENCODER_INCHES_PER_PULSE;

			deltaPulseCount = encoderPulseCountR - prevEncoderPulseCountR;
			distanceTraveledR = deltaPulseCount * CatzConstants.DRIVE_ENCODER_INCHES_PER_PULSE;

			prevEncoderPulseCountL = encoderPulseCountL;
			prevEncoderPulseCountR = encoderPulseCountR;
			
			driftError = Math.sin(currentHeading * CatzConstants.DEG_TO_RAD) * CatzConstants.RAD_TO_DEG;
			driftError = driftError * distanceTraveledL; //calculate error using left encoder only
			
			cumulativeDriftError = cumulativeDriftError + driftError;

			/**********************************************************************
			 * Calc Distance Traveled
			 **********************************************************************/
			if (currentHeading == 0.0) {
				totalDistanceTraveled += distanceTraveledL;
			} else {
				actualDistanceTraveled = Math.abs(driftError / (Math.tan(currentHeading * CatzConstants.DEG_TO_RAD) * CatzConstants.RAD_TO_DEG) );

				totalDistanceTraveled += actualDistanceTraveled;
			}

			distanceError = distanceAbs - totalDistanceTraveled;

			if (distanceError < PID_DRIVE_ERROR_THRESHOLD) {
				done = true;
			} else {
				if (functionTimer.get() > timeout) {
					done = true;
				} else {
					/**************************************************************
					 * Add drif newHeading angle to account for drift
					 **************************************************************/
					if (driftError == 0.0) {
						driftnewHeadingAngle = 0.0;
					} else {
						plannedTravelDistance = distanceTraveledL; // FIGURE OUT HOW TO CALCULATE LATER
						// FOR NOW ASSUME IT WILL BE SAME AS LAST TIME SINCE SPEED IS CONSTANT

						if (plannedTravelDistance == 0.0) {
							driftnewHeadingAngle = 0.0;
						} else {
							driftnewHeadingAngle = Math.asin( (cumulativeDriftError / plannedTravelDistance) * CatzConstants.DEG_TO_RAD);
							driftnewHeadingAngle = driftnewHeadingAngle * CatzConstants.RAD_TO_DEG;
						}
					}

					/**************************************************************
					 * Calculate Heading Proportional Term Add driftnewHeadingAngle 
					 * to delta Error to compensate for driftError
					 **************************************************************/
					deltaAngleError = (currentHeading - previousAngleError) + driftnewHeadingAngle;
					previousAngleError = deltaAngleError;

					/**************************************************************
					 * Calculate Heading Derivative Term
					 **************************************************************/
					derivative = (PID_DRIVE_FILTER_CONSTANT * previousDerivative)
							+ ((1 - PID_DRIVE_FILTER_CONSTANT) * (deltaAngleError / deltaTimeSec));
					
					// FILTER OUT INVALID VALUES
					if(derivative == 0.0) {
						derivative = previousDerivative;
					}

					previousDerivative = derivative;

					/**************************************************************
					 * Calculate new heading & Update drive settings.  newHeading
					 * is a value between -1.0 (90 deg left) and +1.0 (90 deg right).
					 * A value of 0.0 means go straight.
					 **************************************************************/
					turnValue = ((-PID_DRIVE_KP * deltaAngleError) + (PID_DRIVE_KD * derivative))/90.0;
					
					CatzRobotMap.drive.arcadeDrive(power, turnValue);

//					printDebugData();
					printPwrDebugData();
					//Timer.delay(0.015);
				}
			}
		}

		/*************************************************************************
		 * Brake using motors 
		 *************************************************************************/
		
		if (power < 0.0) {
			CatzRobotMap.drive.tankDrive(PID_DRIVE_BRAKE_POWER, PID_DRIVE_BRAKE_POWER);
		} else {
			CatzRobotMap.drive.tankDrive(-PID_DRIVE_BRAKE_POWER, -PID_DRIVE_BRAKE_POWER);
		}

		Timer.delay(PID_DRIVE_BRAKE_TIME);
		CatzRobotMap.drive.tankDrive(0.0, 0.0);
	}
	
	
	private static void calculatePWRKD(double distance)
	{
		//Use different KD for different distances Hard coded, tried and tested
		if(distance <= 12)
		{
			distance +=  3.5;
			PID_DRIVE_PWR_KD = 0.002;
		}
		else if(distance <= 24)
		{
			distance += 1;
			PID_DRIVE_PWR_KD = 0.0020;
		}
		else if(distance == 36)
		{
			PID_DRIVE_PWR_KD = 0.0059;
		}
		else if(distance == 48)
		{
			PID_DRIVE_PWR_KD = 0.006;
		}
		else if(distance == 60)
		{
			PID_DRIVE_PWR_KD = 0.0068;
		}
		else if(distance == 72)
		{
			PID_DRIVE_PWR_KD = 0.00694;
		}
		else if(distance == 84)
		{
			PID_DRIVE_PWR_KD = 0.0072;
		}
		else if(distance == 96)
		{
			PID_DRIVE_PWR_KD = 0.0078;
		}
		else if(distance == 120)
		{
			PID_DRIVE_PWR_KD = 0.008;
		}
		else if(distance == 144)
		{
			PID_DRIVE_PWR_KD = 0.0082;
		}
		else if(distance == 160.5)
		{
			PID_DRIVE_PWR_KD = 0.0087;
		}
		else if(distance == 180)
		{
			PID_DRIVE_PWR_KD = 0.0087;
		}
		else if(distance == 192)
		{
			PID_DRIVE_PWR_KD = 0.0088;
		}
		else if(distance == 204)
		{
			PID_DRIVE_PWR_KD = 0.0091;
		}
		else if(distance == 210)
		{
			PID_DRIVE_PWR_KD = 0.00915;
		}
		else 
		{
			//calculate KD ------- EXPERAMENTAL ------- Need to tune
			PID_DRIVE_PWR_KD = -0.000792367+0.001834892*Math.log(distance);
		}
			
	}


	

	public static void setDebugModeEnabled(boolean enabled) {
		debugMode = enabled;
	}

	public static void calcTimeout(double power, double distance, double timeoutSeconds) {
		double distanceAbs;
		
		distanceAbs = Math.abs(distance);
		
		/***************************************************************************
		 * Calculate Timeout timeoutSeconds: <0 : PIDDrive() will calculate timeout
		 * based on degreesToTurn 0 : Max Timeout >0 : # of seconds before aborting
		 ***************************************************************************/
		if (timeoutSeconds < 0.0) {
			if (distanceAbs < PID_DRIVE_SHORT_DIST) {
				timeout = PID_DRIVE_TIMEOUT_SHORT_DIST;
			} else if (distanceAbs < PID_DRIVE_MEDIUM_DIST) {
				timeout = PID_DRIVE_TIMEOUT_MED_DIST;
			} else {
				timeout = PID_DRIVE_MAX_TIMEOUT;
			}
		} else {
			if (timeoutSeconds == 0.0) {
				timeout = PID_DRIVE_MAX_TIMEOUT;
			} else {
				timeout = timeoutSeconds;
			}
		}
	}


	public static void printDebugInit() {
		if (debugMode == true) {

			System.out.printf("power,         %.3f, INCHES/PULSE, %.4f\n", power, CatzConstants.DRIVE_ENCODER_INCHES_PER_PULSE);
			System.out.printf("distanceAbs,   %.3f, timeout,      %.3f\n", distanceAbs, timeout);
			System.out.printf("BrakePower,    %.3f, BrakeTime,    %.3f\n", PID_DRIVE_BRAKE_POWER, PID_DRIVE_BRAKE_TIME);
			System.out.printf("distErrThresh, %.3f\n", PID_DRIVE_ERROR_THRESHOLD);
			System.out.printf("kP,            %.3f\n", PID_DRIVE_KP);
			System.out.printf("kD,            %.3f\n", PID_DRIVE_KD);
			System.out.printf("DrvFiltConst,  %.3f\n", PID_DRIVE_FILTER_CONSTANT);
		}
	}


	public static void printDebugHeader() {
		if (debugMode == true) {
			System.out.print("encoderStraightDrive debug data\n");
			System.out.print("timestamp,deltaTimeSec,encoderPulseCountL,encoderPulseCountR," +  
					"distanceTraveledL, distanceTraveledR, actualDistanceTraveled, totalDistanceTraveled, distanceError," +
					"driftError, cumulativeDriftError,driftnewHeadingAngle," + 
					"deltaAngleError, derivative," +
					"plannedTravelDistance,currentHeading, turnValue, power\n");
		}
	}


	public static void printDebugData() {
		if (debugMode == true) {

 			System.out.printf("%.3f, %.3f, %f, %f, ", 
 					functionTimer.get(), deltaTimeSec, encoderPulseCountL, encoderPulseCountR);

 			System.out.printf("%.3f, %.3f, %.3f, %.3f, %.3f, ", 
 					distanceTraveledL, distanceTraveledR, actualDistanceTraveled, totalDistanceTraveled, distanceError);

 			System.out.printf("%.6f, %.6f, %.6f, %.6f, %.3f, ", 
 					driftError, cumulativeDriftError, driftnewHeadingAngle, deltaAngleError, derivative);

 			System.out.printf("%.3f, %.6f, %.6f, %.3f\n", 
 					plannedTravelDistance, currentHeading, turnValue, power);

			// printDatainSmartDashboard();
		}
	}


	public static void printPwrDebugHeader() {
		if (debugMode == true) {
			System.out.print("timestamp,deltaTimeSec,currentDistance,distanceError,deltaDistanceError," +  
					"currentHeading,deltaAngleError,totalAngleError,derivative,powerDerivative," +
					"drivePower,turnValue\n");
		}
	}

	public static void printPwrDebugData() {
		if (debugMode == true) {
 
 			System.out.printf("%.3f, %.3f, %.3f, %.3f, %.3f, ", 
 					functionTimer.get(), deltaTimeSec, currentDistance, distanceError, deltaDistanceError);

 			System.out.printf("%.3f, %.3f, %.3f, %.3f,  %.3f, %.3f, %.3f\n", 
 					currentHeading, deltaError, totalAngleError, derivative, powerDerivative, drivePower, turnValue);
		}
	}
	
	
	public static void printDatainSmartDashboard() {
		SmartDashboard.putNumber("timestamp", functionTimer.get());
		SmartDashboard.putNumber("deltaTimeMillis", deltaTimeSec);
		SmartDashboard.putNumber("currentAngleDegrees", currentHeading);
		SmartDashboard.putNumber("currentErrorDegrees", deltaAngleError);
		SmartDashboard.putNumber("derivative", derivative);

	}
}