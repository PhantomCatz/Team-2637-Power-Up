package autonomous;

import edu.wpi.first.wpilibj.Timer;
import robot.CatzConstants;
import robot.CatzRobotMap;

/*
 *  Author : Derek Duenas
 *  Revision History : 
 *  	1-28-2018 D. Duenas Translated from C++ and added integral term
 *  	2-4-2018 D. Duenas Revising code
 *  
 *  Methods : PIDturn
 *  Functionality : Accurately turn autonomously
 */
public class CatzPIDTurn {

	// PID Turn Constants
	final static public double PID_TURN_MAX_TIMEOUT = 4.0;

	final static public double PID_TURN_THRESHOLD = 0.5;

	/***************************************************************************
	 * PID_TURN_DELTAERROR_THRESHOLD_HI - Delta Error Values larger than this are
	 * considered invalid and will be ignored PID_TURN_DELTAERROR_THRESHOLD_LO -
	 * When drivetrain power drops below the PID_TURN_MIN_xxx_POWER, we will check
	 * to see if deltaError is below this threshold before setting power at
	 * PID_TURN_MIN_xxx_POWER.
	 ***************************************************************************/
	final static public double PID_TURN_DELTAERROR_THRESHOLD_HI = 4.0;
	final public static double PID_TURN_DELTAERROR_THRESHOLD_LO = 0.11;

	final static public double PID_TURN_FILTER_CONSTANT = 0.7;
	      static public double PID_TURN_POWER_SCALE_FACTOR = 1.0;

	static public double PID_TURN_KP = 0.08;
	static public double PID_TURN_KI = 0.0;
	static public double PID_TURN_KD = 0.012; // 0.0744

	final static public double PID_TURN_INTEGRAL_MAX = 1.0;
	final static public double PID_TURN_INTEGRAL_MIN = -1.0;

	final public static double PID_TURN_MIN_POS_POWER = 0.6; // 0.4 is min power to move robot when it is stopped
	final public static double PID_TURN_MIN_NEG_POWER = -PID_TURN_MIN_POS_POWER;

	// PID Turn Variables

	static Timer functionTimer;
	static Timer pdTimer;
	
	static double currentError; 
	static double deltaError;
	static double derivative;
	static double deltaT;

	static double power;

	static double previousError;
	static double totalError;

	static double currentAngle;
	static double currentAngleAbs;
	static double targetAngle;
	static double targetAngleAbs;

	static double timeout;

	static double previousDerivative = 0;

	static boolean done;
	static boolean tuningMode = false;
	static boolean debugMode = false;
	static String debugData;

	/***************************************************************************
	 * PIDturn()
	 * 
	 * timeoutSeconds: -1 : PIDturn() will calculate timeout based on degreesToTurn
	 * 0 : Max Timeout >0 : # of seconds before aborting
	 ***************************************************************************/
	public static void PIDturn(double degreesToTurn, double timeoutSeconds) {
		boolean firstTime = true;
		functionTimer = new Timer();
		pdTimer = new Timer();

		CatzRobotMap.navx.reset();
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);

		done = false;

		previousError = 0.0;
		totalError = 0.0;

		functionTimer.reset();
		functionTimer.start();

		currentAngle = CatzRobotMap.navx.getAngle();
		targetAngle = degreesToTurn + currentAngle;
		currentError = targetAngle - currentAngle;

		targetAngleAbs = Math.abs(targetAngle);

		/***************************************************************************
		 * Calculate Timeout timeoutSeconds: <0 : PIDturn() will calculate timeout based
		 * on degreesToTurn 0 : Max Timeout >0 : # of seconds before aborting
		 ***************************************************************************/
		if (timeoutSeconds < 0.0) {
			if (targetAngleAbs < 91.0) {
				timeout = 1.2;
			} else if (targetAngleAbs < 150.0) {
				timeout = 2.0;
			} else {
				timeout = PID_TURN_MAX_TIMEOUT;
			}
		} else {
			if (timeoutSeconds == 0.0) {
				timeout = PID_TURN_MAX_TIMEOUT;
			} else {
				timeout = timeoutSeconds;
			}
		}

		printDebugInit();
		printDebugHeader();

		pdTimer.reset();
		pdTimer.start();
		while (done == false) {
			currentAngle = CatzRobotMap.navx.getAngle();
			deltaT = pdTimer.get();

			currentAngleAbs = Math.abs(currentAngle);

			pdTimer.stop();
			pdTimer.reset();
			pdTimer.start();

			// calculates proportional term
			currentError = targetAngle - currentAngle;

			if (Math.abs(currentError) < PID_TURN_THRESHOLD) {
				done = true;
			} else {
				if (functionTimer.get() > timeout) {
					done = true;
				} else {
					/************************************************************
					 * Calculate derivative term If this is the first time through the loop, we
					 * don't have a previousError or previouisDerivative value, so we will just set
					 * derivative to zero.
					 ************************************************************/
					deltaError = currentError - previousError;

					if (firstTime == false) {

						/*************************************************************
						 * Filter out invalid values (noise) as we don't want the control loop to react
						 * to these. Invalid values can occur due to mechanical imperfections causing
						 * the drivetrain to bind/release as it is turning, missed samples, etc. When
						 * the control loop reacts to these unexpected jumps, it will lead to large
						 * swings in power as it tries to correct for a large intermittent error that
						 * comes & goes. This may be seen as the robot shaking during the turn.
						 *
						 * An invalid value is characterized as one o - jumping to zero when we are not
						 * close to targetAngle - Change in delta error has exceeded a threshold
						 *
						 * If we have an invalid value, use the previous derivative value.
						 *************************************************************/
						if ((deltaError == 0.0) && (Math.abs(currentError) > 3.0)) {
							derivative = previousDerivative;
						} else {

							if (Math.abs(deltaError) > PID_TURN_DELTAERROR_THRESHOLD_HI) {
								derivative = previousDerivative;

							} else {
								/**********************************************************
								 * We have a good deltaError value. Filter the derivative value to smooth out
								 * jumps in derivative value
								 **********************************************************/
								derivative = PID_TURN_FILTER_CONSTANT * previousDerivative
										+ ((1 - PID_TURN_FILTER_CONSTANT) * (deltaError / deltaT));
							}
						}
					} else {
						firstTime = false;
						derivative = 0;
					}

					// Save values for next iteration
					previousDerivative = derivative;
					previousError = currentError;

					/*******************************************************************
					 * Calculate integral term
					 *
					 * Check if we are entering saturation. If we are cap totalError at max value
					 * (make sure the integral term doesn't get too big or small)
					 *******************************************************************/
					totalError += currentError * deltaT;

					if (totalError >= PID_TURN_INTEGRAL_MAX)
						totalError = PID_TURN_INTEGRAL_MAX;

					if (totalError <= PID_TURN_INTEGRAL_MIN)
						totalError = PID_TURN_INTEGRAL_MIN;

					// calculates drivetrain power
					power = PID_TURN_POWER_SCALE_FACTOR
							* ((PID_TURN_KP * currentError) + (PID_TURN_KI * totalError) + (PID_TURN_KD * derivative));

					// Verify we have not exceeded max power when turning right or left
					if (power > CatzConstants.DRIVE_MAX_POS_POWER)
						power = CatzConstants.DRIVE_MAX_POS_POWER;

					if (power < CatzConstants.DRIVE_MAX_NEG_POWER)
						power = CatzConstants.DRIVE_MAX_NEG_POWER;

					/**********************************************************************
					 * We need to make sure drivetrain power doesn't get too low but we also need to
					 * allow the robot to gradually brake. The brake condition is defined as when
					 * deltaError is > PID_TURN_DELTAERROR_THRESHOLD_LO If deltaError is <
					 * PID_TURN_DELTAERROR_THRESHOLD_LO, then we will set power to
					 * PID_TURN_MIN_xxx_POWER.
					 **********************************************************************/
					if (power >= 0.0) {
						if (power < PID_TURN_MIN_POS_POWER && Math.abs(deltaError) < PID_TURN_DELTAERROR_THRESHOLD_LO)
							power = PID_TURN_MIN_POS_POWER;
					} else if (power < 0.0) {
						if (power > PID_TURN_MIN_NEG_POWER && Math.abs(deltaError) < PID_TURN_DELTAERROR_THRESHOLD_LO)
							power = PID_TURN_MIN_NEG_POWER;
					}

					/*******************************************************************
					 * Cmd robot to turn at new power level Note: Power will be positive if turning
					 * right and negative if turning left
					 *******************************************************************/
					CatzRobotMap.drive.tankDrive(power, -power);

					printDebugData();
					Timer.delay(0.015); // was .005,.008
				}
			}
		}

		/**********************************************************************
		 * We're at targetAngle or timed out. Stop the robot and do final cleanup. -
		 * Print out last set of debug data (note that this may not be a complete set of
		 * data) - Stop timers
		 **********************************************************************/
		CatzRobotMap.drive.tankDrive(0.0, 0.0); // makes robot stop

		printDebugData();

		functionTimer.stop();
		pdTimer.stop();
	}

	public static void setPIDTurnDebugModeEnabled(boolean enabled) {
		debugMode = enabled;
	}

	public static boolean isTuningModeEnabled() {
		return tuningMode;
	}

	public static void printDebugInit() {
		if (debugMode == true) {
			debugData = ("CurrentAngle," + currentAngle + "\n" + "targetAngle," + targetAngle + "\n" + "targetAngleAbs,"
					+ targetAngleAbs + "\n" + "errorThreshold," + PID_TURN_THRESHOLD + "\n" + "kP," + PID_TURN_KP + "\n"
					+ "kI," + PID_TURN_KI + "\n" + "kD," + PID_TURN_KD + "\n" + "PwrScaleFactor,"
					+ PID_TURN_POWER_SCALE_FACTOR + "\n" + "MaxI," + PID_TURN_INTEGRAL_MAX + "\n" + "MinI,"
					+ PID_TURN_INTEGRAL_MIN + "\n");
			System.out.println("****************************************************************************");
			System.out.print(debugData);
			System.out.printf("PIDTurn DerivFiltConst, %.3f\n", PID_TURN_FILTER_CONSTANT);
			System.out.printf("PIDTurn MinPosPwr,      %.3f\n", PID_TURN_MIN_POS_POWER);
			System.out.printf("PIDTurn MinNegPwr,      %.3f\n", PID_TURN_MIN_NEG_POWER);
			System.out.printf("PIDTurn deltaErrThresh, %.3f\n", PID_TURN_DELTAERROR_THRESHOLD_HI);
			System.out.printf("PIDTurn deltaErrThresh, %.3f\n", PID_TURN_DELTAERROR_THRESHOLD_LO);
		}
	}

	public static void printDebugHeader() {
		if (debugMode == true) {
			System.out.print("PIDTurn debug data\n");
			System.out.print("timestamp,deltaT,currentAngle,currentError,deltaError,derivative,totalError,power\n");
		}
	}

	public static void printDebugData() {
		if (debugMode == true) {

			System.out.printf("%.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f \n", functionTimer.get(), deltaT,
					currentAngle, currentError, deltaError, derivative, totalError, power);

		}
	}

}
