package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

public class CatzTeleopPeriodic 
{
	public static void runTeleopPeriodic()
	{		

		//sets drive control to xbox
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxDrive);

		//lifter mech speed is controlled by auxController left joystick y-axis
		CatzRobotMap.lift.setSpeed(CatzRobotMap.xboxAux.getLeftStickY());
		
		runGrabberControls();
	}
	
	
	private static void runGrabberControls() {
		CatzRobotMap.grabber.setIntakeSpeed(CatzRobotMap.xboxAux.getRightTrigger()-CatzRobotMap.xboxAux.getLeftTrigger());
		
		if(CatzRobotMap.xboxAux.getRightBumper()==true) {
			CatzRobotMap.grabber.deployBicep();
		}
		if(CatzRobotMap.xboxAux.getLeftBumper()==true) {
			CatzRobotMap.grabber.retractBicep();
		}
		if(CatzRobotMap.xboxAux.getAButton()==true) {
			CatzRobotMap.grabber.toggleForearm();
		}

	}
}
