package autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzPIDDrive {


	/****************************************************************************
	 * PID Drive Constants
	 ****************************************************************************/
	final static public double PID_DRIVE_SHORT_DIST = 48.0; // 4 ft
	final static public double PID_DRIVE_MEDIUM_DIST = 144.0; // 12 ft

	final static public double PID_DRIVE_TIMEOUT_SHORT_DIST = 3.0; // SHOULD CREATE A METHOD BASED ON SPEED & DISTANCE
	final static public double PID_DRIVE_TIMEOUT_MED_DIST = 10.0;
	final static public double PID_DRIVE_MAX_TIMEOUT = 20.0;

	final static double PID_DRIVE_ERROR_THRESHOLD = 1.0; // Stop within 1 inch

	final static public double PID_DRIVE_KP = 0.15;
	final static public double PID_DRIVE_KD = 0.005; // ORIGINALLY .18
	final static public double PID_DRIVE_FILTER_CONSTANT = 0.5;

	final static public double PID_DRIVE_BRAKE_POWER = 0.43;
	final static public double PID_DRIVE_BRAKE_TIME = 0.1;

	/****************************************************************************
	 * PID Drive Variables
	 ****************************************************************************/
	private static Timer functionTimer;
	private static Timer loopTimer;

	private static boolean debugMode = true;
	private static boolean done = false;

	private static double power;

	private static double deltaTimeSec;
	private static double currentHeading;
	private static double newHeading;
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

	/****************************************************************************
	 *
	 * PIDDrive()
	 *
	 ****************************************************************************/
	public static void PIDDrive(double drivePower, double distance, double timeoutSeconds) {

		boolean firstTime;
		double deltaPulseCount;
		double actualDistTraveled;
		double plannedDistTraveled = 0;
		functionTimer = new Timer();
		loopTimer = new Timer();

		functionTimer.reset();
		functionTimer.start();

		distanceAbs = Math.abs(distance);
		power = drivePower;

		previousAngleError = 0.0;
		previousDerivative = 0.0;
		prevEncoderPulseCountL = CatzRobotMap.wheelEncoderL.get();
		prevEncoderPulseCountR = CatzRobotMap.wheelEncoderR.get();
		cumulativeDriftError = 0.0;
		totalDistanceTraveled = 0.0;

		CatzRobotMap.navx.reset();
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);

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

		done = false;
		firstTime = true;

		printDebugInit();
		printDebugHeader();

		loopTimer.reset();
		loopTimer.start();

		while (done == false) {
			/**********************************************************************
			 * Get Data Samples
			 **********************************************************************/
			encoderPulseCountL = CatzRobotMap.wheelEncoderL.get();
			encoderPulseCountR = CatzRobotMap.wheelEncoderR.get();
			currentHeading = CatzRobotMap.navx.getAngle();
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
			distanceTraveledL = deltaPulseCount * CatzConstants.DRIVE_INCHES_PER_PULSE;

			deltaPulseCount = encoderPulseCountR - prevEncoderPulseCountR;
			distanceTraveledR = deltaPulseCount * CatzConstants.DRIVE_INCHES_PER_PULSE;

			driftError = Math.sin(currentHeading) * distanceTraveledL; //calculate error using left encoder only
			cumulativeDriftError = cumulativeDriftError + driftError;

			/**********************************************************************
			 * Calc Distance Traveled
			 **********************************************************************/
			actualDistTraveled = Math.abs(driftError * Math.tan(currentHeading));

			totalDistanceTraveled += actualDistTraveled;

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
						// FOR NOW ASSUME IT WILL BE SAME AS LAST TIME

						driftnewHeadingAngle = Math.asin(cumulativeDriftError / plannedDistTraveled);
					}

					/**************************************************************
					 * Calculate Heading Proportional Term Add driftnewHeadingAngle to currentError
					 * to compensate for driftError
					 **************************************************************/
					deltaAngleError = (currentHeading + driftnewHeadingAngle) - previousAngleError;
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
					 * Calculate new heading & Update drive settings
					 **************************************************************/
					newHeading = (-PID_DRIVE_KP * deltaAngleError) + (PID_DRIVE_KD * derivative);

					CatzRobotMap.drive.arcadeDrive(power, newHeading);

					printDebugData();
					Timer.delay(0.015);
				}
				/*************************************************************************
				 *
				 * Brake using motors DO WE NEED TO ACCOUNT FOR THIS IN CHECKING DISTANCE ERROR?
				 * AFTER FLAGSTAFF LOOK AT USING PID LOOP FOR DISTANCE TO MANAGE DRIVE POWER
				 * WANT TO BE ABLE TO START WITH POWER = 1.0
				 *
				 *************************************************************************/
				if (power < 0.0) {
					CatzRobotMap.drive.tankDrive(PID_DRIVE_BRAKE_POWER, PID_DRIVE_BRAKE_POWER);
				} else {
					CatzRobotMap.drive.tankDrive(-PID_DRIVE_BRAKE_POWER, -PID_DRIVE_BRAKE_POWER);
				}

				Timer.delay(PID_DRIVE_BRAKE_TIME);
				CatzRobotMap.drive.tankDrive(0.0, 0.0);
				functionTimer.stop();
			}
		}
	}

	/****************************************************************************
	 *
	 * setDebugModeEnabled()
	 *
	 ****************************************************************************/
	public static void setDebugModeEnabled(boolean enabled) {
		debugMode = enabled;
	}

	/****************************************************************************
	 *
	 * printDebugInit()
	 *
	 ****************************************************************************/
	public static void printDebugInit() {
		if (debugMode == true) {

			System.out.printf("power,         %.3f, INCHES/PULSE, %.4f\n", power, CatzConstants.DRIVE_INCHES_PER_PULSE);
			System.out.printf("distanceAbs,   %.3f, timeout,      %.3f\n", distanceAbs, timeout);
			System.out.printf("BrakePower,    %.3f, BrakeTime,    %.3f\n", PID_DRIVE_BRAKE_POWER, PID_DRIVE_BRAKE_TIME);
			System.out.printf("distErrThresh, %.3f\n", PID_DRIVE_ERROR_THRESHOLD);
			System.out.printf("kP,            %.3f\n", PID_DRIVE_KP);
			System.out.printf("kD,            %.3f\n", PID_DRIVE_KD);
			System.out.printf("DrvFiltConst,  %.3f\n", PID_DRIVE_FILTER_CONSTANT);
		}
	}

	/****************************************************************************
	 *
	 * printDebugHeader()
	 * 
	 *****************************************************************************/
	public static void printDebugHeader() {
		if (debugMode == true) {
			System.out.print("encoderStraightDrive debug data\n");
			System.out.print("timestamp,deltaTimeSec,currentErrorDegrees,derivative,deltaAngleError,newHeading\n");
		}
	}

	/****************************************************************************
	 *
	 * printDebugData()
	 *
	 ****************************************************************************/
	public static void printDebugData() {
		if (debugMode == true) {
			System.out.printf(
					"%.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f\n",
					functionTimer.get(), deltaTimeSec, encoderPulseCountL, encoderPulseCountR,

					distanceTraveledL, distanceTraveledR, actualDistanceTraveled, totalDistanceTraveled, distanceError,

					driftError, cumulativeDriftError,

					deltaAngleError, derivative,

					plannedTravelDistance, driftError, currentHeading, newHeading, power);

			// printDatainSmartDashboard();
		}
	}

	/****************************************************************************
	 *
	 * printDatainSmartDashboard()
	 *
	 ****************************************************************************/
	public static void printDatainSmartDashboard() {
		SmartDashboard.putNumber("timestamp", functionTimer.get());
		SmartDashboard.putNumber("deltaTimeMillis", deltaTimeSec);
		SmartDashboard.putNumber("currentAngleDegrees", currentHeading);
		SmartDashboard.putNumber("currentErrorDegrees", deltaAngleError);
		SmartDashboard.putNumber("derivative", derivative);

	}
}