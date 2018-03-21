package robotFunctions;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Timer;
import mechanisms.CatzGrabber;
import robot.CatzConstants;
import robot.CatzRobotMap;

/**********************************************************
 * Author : Andrew Lai 
 * 
 * Revision History :
 * 2/16/2018 AL Revised driver controls to fun with 2 xbox
 * 3-17-18   DD Integrated bottom limit switch into lift controls
 * 
 * Controllers Methods: runGrabbercontrols, runLiftControls, runClimberControls, runTeleopPeriodic
 * 
 * Functionality: activates driver controls in periodic class
 *********************************************************/

public class CatzTeleopPeriodic {
	public static boolean reversed = false;
	private static final double MIN_LIFT_INPUT_POWER = .75;
	private static final int MIN_DELTA_PULSES = 1;
	private static final double LIFTER_DISABLED_TIME = 1.5;
	private static Timer lifterDisabler = new Timer();
	private static double currentLiftValue;
	private static double lastLiftValue = 0;
	private static double deltaLiftValue;
	private static boolean liftDisabled = false;

	
	void inputCurrent(double speed)
	{
		ControlMode current = ControlMode.Current;
		CatzRobotMap.fLeft.set(ControlMode.Current, speed);
	}
	
	public static void runTeleopPeriodic() {
		
		runDriveTrainControls();
		runGrabberControls();
		runLiftControls();
		runClimberControls();
	}

	private static void runDriveTrainControls() {
		if (CatzRobotMap.xboxDrive.getBButton()) {
			reversed = !reversed;
			Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
		}

		if (reversed == true) {
			CatzRobotMap.xboxDrive.setRumble(RumbleType.kLeftRumble, .5);
			CatzRobotMap.xboxDrive.setRumble(RumbleType.kRightRumble, .5);
			CatzRobotMap.drive.setModeReverseArcadeDriveFlash(CatzRobotMap.xboxDrive);
		} else {
			CatzRobotMap.xboxDrive.setRumble(RumbleType.kLeftRumble, 0);
			CatzRobotMap.xboxDrive.setRumble(RumbleType.kRightRumble, 0);
			CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxDrive);
		}

	}

	private static void runGrabberControls() {
		CatzRobotMap.grabber
				.setIntakeSpeed(CatzRobotMap.xboxDrive.getRightTrigger() - CatzRobotMap.xboxDrive.getLeftTrigger());
		// -CatzRobotMap.xboxAux.getRightTrigger());

		// bicep controls
		if (CatzRobotMap.grabber.forearmOpen == false) {
			if (CatzRobotMap.xboxAux.getRightBumper()) {
				CatzRobotMap.grabber.deployBicep(0.0);
			} else if (CatzRobotMap.xboxAux.getLeftBumper()) {
				CatzRobotMap.grabber.retractBicep(0.0);
			}
		}

		// set forearm and bicep to portal pickup mode
		if (CatzRobotMap.xboxAux.getAButton()) {
			CatzRobotMap.grabber.openForearm(0.0);
			CatzRobotMap.grabber.retractBicep();
		}

		// Driver controls for forearms
		if (CatzRobotMap.xboxDrive.getAButton()) {
			CatzRobotMap.grabber.toggleForearm();
		}

		// sets forearm and bicep to cube pickup mode
		if (CatzRobotMap.xboxDrive.getXButton()) {
			CatzRobotMap.grabber.openForearm(0.0);
			CatzRobotMap.grabber.deployBicep();
		}

		// aux driver can open forearms only
		// if(CatzRobotMap.xboxAux.getBButton()) {
		// CatzRobotMap.grabber.openForearm(0.0);
		// }

		// rumble if biceps are down
		if (CatzGrabber.bicepDeployed == true) {
			CatzRobotMap.xboxAux.setRumble(RumbleType.kLeftRumble, .5);
			CatzRobotMap.xboxAux.setRumble(RumbleType.kRightRumble, .5);
		} else {
			CatzRobotMap.xboxAux.setRumble(RumbleType.kLeftRumble, 0);
			CatzRobotMap.xboxAux.setRumble(RumbleType.kRightRumble, 0);
		}

		// for testing purposes
		if (CatzRobotMap.xboxAux.getYButton()) {
			CatzRobotMap.liftEncoder.reset();
			CatzRobotMap.wheelEncoderL.reset();
			CatzRobotMap.wheelEncoderR.reset();
		}
	}

	// Lifter controls
	private static void runLiftControls() {
		// currentLiftValue = CatzRobotMap.liftEncoder.get();
		double power = CatzRobotMap.xboxAux.getLeftStickY();
		/**
		 * if lifter limit is activated, only have the ability to move lifter down Aux
		 * controller X button overrides the limit switch
		 */

		if (CatzRobotMap.xboxAux.getXButton() == true) 
		{
			CatzRobotMap.lifterL.set(power);
			CatzRobotMap.lifterR.set(power);
		} 
		else 
		{
			if (CatzRobotMap.lifterLimitTop.get() == true) 
			{
				if(power <= .0)
				{
					CatzRobotMap.lifterL.set(power);
					CatzRobotMap.lifterR.set(power);
				}
			}
			else if(CatzRobotMap.lifterLimitBottom.get() == true)
			{
				if(power >= .0)
				{
					CatzRobotMap.lifterL.set(power);
					CatzRobotMap.lifterR.set(power);
				}
			}
			else 
			{
				CatzRobotMap.lifterL.set(power);
				CatzRobotMap.lifterR.set(power);
			}
		}
	}

	private static void printOutDebugData(String info) {
		if (CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}

	private static void runClimberControls() {
		CatzRobotMap.climberMechanism.setClimberSpeed(Math.abs(CatzRobotMap.xboxAux.getRightStickY()));
	}

	private void noStallLift() {
		double power = 0;
		if (CatzRobotMap.xboxAux.getXButton() == true) { // override limit switch (whc)
			CatzRobotMap.lifterL.set(power);
			CatzRobotMap.lifterR.set(power);
		} else {
			if (liftDisabled) {
				if (lifterDisabler.get() > LIFTER_DISABLED_TIME) {
					liftDisabled = false;
					lifterDisabler.reset();
				}
			} else {
				if (CatzRobotMap.lifterLimitTop.get() == false) {
					CatzRobotMap.lifterL.set(power);
					CatzRobotMap.lifterR.set(power);
				} else {
					if (power <= 0.3) 
					{ 
						// limit switch is engaged; lifter can only go down. (whc)
						// CatzRobotMap.lifterL.set(power);
						CatzRobotMap.lifterR.set(power);
					}
				}
			}
		}
		deltaLiftValue = currentLiftValue - lastLiftValue;

		if (liftDisabled == false && Math.abs(power) > MIN_LIFT_INPUT_POWER && deltaLiftValue < MIN_DELTA_PULSES) {
			liftDisabled = true;
			lifterDisabler.reset();
			lifterDisabler.start();
			CatzRobotMap.lifterL.set(0);
			CatzRobotMap.lifterR.set(0);
			printOutDebugData("Lifter disabled for " + LIFTER_DISABLED_TIME + " seconds:\n    deltaLiftValue: "
					+ deltaLiftValue + ", joystickPower: " + power);
		}

		lastLiftValue = currentLiftValue;

	}
}
