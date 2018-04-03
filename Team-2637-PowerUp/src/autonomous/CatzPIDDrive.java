package autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzPIDDrive {


	/****************************************************************************
	 * PID Drive Constants
	 ****************************************************************************/
	        final static double PID_DRIVE_ERROR_THRESHOLD_HI =  1.0; // Stop within 1 inch
	        final static double PID_DRIVE_ERROR_THRESHOLD_LO = -1.0; // Stop within 1 inch

	private final static double PID_DRIVE_KP = 0.260;
	private final static double PID_DRIVE_KD = 0.008;

	private       static double PID_DRIVE_BRAKE_POWER = 0.43;
	private final static double PID_DRIVE_BRAKE_TIME  = 0.25;
	
	private final static double PID_DRIVE_FILTER_CONSTANT = .5;

	
	/****************************************************************************
	 * PID Drive Variables
	 ****************************************************************************/
	private static Timer functionTimer;
	private static Timer loopTimer;

	private static boolean debugMode = false;
	private static boolean done      = false;

	private static double power;

	private static double deltaTimeSec;
	private static double currentHeading;
	private static double turnValue;
	private static double derivative;
	private static double previousDerivative;
	private static double deltaAngleError;
	private static double previousAngleError;

	private static double totalAngleError;
	private static double refHeading;

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
	
	
	//Turbo Constants
	final static public double PID_DRIVE_PWR_FILTER_CONSTANT = 0.3;

	static public double PID_DRIVE_PWR_KP = 0.04;
	static public double PID_DRIVE_PWR_KI = 0.0;
	static public double PID_DRIVE_PWR_KD = 0.008;
	
	//TurboVariables
	private static double currentDistance;
	private static double drivePower;
	private static double deltaDistanceError;
	private static double previousDistanceError;
	private static double powerDerivative;
	private static double previousPowerDerivative;

	
	
	//pass 0 for drive power to use calculated value

	public static void PIDDriveNoTrig(double defaultPower, double distance, double timeoutSeconds) {
		
		loopTimer     = new Timer();
		functionTimer = new Timer();
		functionTimer.reset();
		functionTimer.start();

		boolean firstTimePwr = true;
		double  lastHeading  = 0.0;
		double  direction    = 1.0;
		
		done = false;

		if (distance < 0.0) {
			direction = -1.0;
		} else {
			direction = 1.0;
		}
		
		distanceAbs = Math.abs(distance);
		calculatePwrPidValues(distanceAbs);

		//attempt to make it work backwards
//		distanceToTravel = distance;

		CatzRobotMap.wheelEncoderL.reset();
		//CatzRobotMap.wheelEncoderR.reset();

		CatzRobotMap.navx.reset();
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);

		previousAngleError    = 0.0;
		previousDerivative    = 0.0;
		previousDistanceError = 0.0;

		printDebugInit();
		printPwrDebugHeader();

		while (done == false) {

			currentHeading = CatzRobotMap.navx.getAngle();
			deltaTimeSec   = loopTimer.get();
			
			loopTimer.stop();
			loopTimer.reset();
			loopTimer.start();
			
			currentDistance = Math.abs(CatzRobotMap.wheelEncoderL.getDistance());			
			distanceError   = distanceAbs - currentDistance;

			//Check if we are close enough
			if (distanceError < PID_DRIVE_ERROR_THRESHOLD_HI) {
				done = true;
			} else {
				if (functionTimer.get() > timeoutSeconds) {
					done = true;
				} else {

					deltaAngleError = currentHeading - lastHeading;
					totalAngleError = currentHeading - refHeading;
					
					/**************************************************************
					 * Calculate Heading Derivative Term
					 * The first time through the loop, deltaTimeSec will be zero
					 * so we will set derivative to zero.
					 **************************************************************/
					if (deltaTimeSec == 0.0) {
						derivative = 0.0;
					} else {
						derivative = (PID_DRIVE_FILTER_CONSTANT * previousDerivative)
								   + ((1 - PID_DRIVE_FILTER_CONSTANT) * (deltaAngleError / deltaTimeSec));						
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
					
					previousDistanceError   = distanceError;
					previousPowerDerivative = powerDerivative;
					
					if (defaultPower == 0.0) {
						// calculates drivetrain power
						drivePower = ((PID_DRIVE_PWR_KP * distanceError) + (PID_DRIVE_PWR_KD * powerDerivative));
						drivePower = drivePower * direction;

						// Verify we have not exceeded max power when turning right or left
						if (drivePower > CatzConstants.DRIVE_MAX_POS_POWER)
							drivePower = CatzConstants.DRIVE_MAX_POS_POWER;

						if (drivePower < CatzConstants.DRIVE_MAX_NEG_POWER)
							drivePower = CatzConstants.DRIVE_MAX_NEG_POWER;
					} else {
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
			Timer.delay(PID_DRIVE_BRAKE_TIME);
		} else if (defaultPower > 0.0) {
			CatzRobotMap.drive.tankDrive(-PID_DRIVE_BRAKE_POWER, -PID_DRIVE_BRAKE_POWER);
			Timer.delay(PID_DRIVE_BRAKE_TIME);
		}
		

		CatzRobotMap.drive.tankDrive(0.0, 0.0);    //Stop the robot

	}
	

	
	
	
	
	

	

	
	
	

	
	
	
	private static void calculatePwrPidValues(double distance)
	{
		//Use different KD for different distances Hard coded, tried and tested
		if(distance <= 12)
		{
			PID_DRIVE_PWR_KP = 0.094;
			PID_DRIVE_PWR_KD = 0.008;
		}
		else if(distance <= 24)
		{
			PID_DRIVE_PWR_KP = 0.100;
			PID_DRIVE_PWR_KD = 0.010;
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
			PID_DRIVE_PWR_KP = 0.090;
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



	public static void printDebugInit() {
		if (debugMode == true) {

//			System.out.printf("power,         %.3f, INCHES/PULSE, %.4f\n", power, CatzConstants.DRIVE_ENCODER_INCHES_PER_PULSE);
//			System.out.printf("distanceAbs,   %.3f, timeout,      %.3f\n", distanceAbs, timeout);
//			System.out.printf("BrakePower,    %.3f, BrakeTime,    %.3f\n", PID_DRIVE_BRAKE_POWER, PID_DRIVE_BRAKE_TIME);
			System.out.printf("kP, %.3f,  kD, %.3f\n", PID_DRIVE_KP, PID_DRIVE_KD);
			System.out.printf("distErrThresh, %.3f\n", PID_DRIVE_ERROR_THRESHOLD_HI);
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
					"currentHeading,deltaAngleError,derivative,powerDerivative," +
					"drivePower,turnValue\n");
		}
	}

	public static void printPwrDebugData() {
		if (debugMode == true) {
 
 			System.out.printf("%.3f, %.3f, %.3f, %.3f, %.3f, ", 
 					functionTimer.get(), deltaTimeSec, currentDistance, distanceError, deltaDistanceError);

 			System.out.printf("%.3f, %.3f, %.3f, %.3f, %.3f, %.3f\n", 
 					currentHeading, deltaAngleError, derivative, powerDerivative, drivePower, turnValue);
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