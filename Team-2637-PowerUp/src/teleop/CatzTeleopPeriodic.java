package teleop;

import robot.CatzRobotMap;

/**********************************************************
 * Andrew Lai
 * 2/16/2018 AL
 * Revised driver controls to fun with 2 xbox controllers
 * Methods: runGrabbercontrols,runLiftControls,unClimberControls,runTeleopPeriodic
 * Functionality: activates driver controls in periodic class
 *********************************************************/

public class CatzTeleopPeriodic 
{
	public static void runTeleopPeriodic()
	{		

		//sets drive control to drive xbox controller 
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxDrive);
		
		//runGrabberControls();
		runLiftControls();
		//runClimberControls();
		System.out.println(CatzRobotMap.wheelEncoderL.get()+","+CatzRobotMap.wheelEncoderR.get()+","+CatzRobotMap.liftEncoder.get());
	}
	private static void runGrabberControls() 
	{
		CatzRobotMap.grabber.setIntakeSpeed(CatzRobotMap.xboxDrive.getRightTrigger() - CatzRobotMap.xboxDrive.getLeftTrigger());
		
		if(CatzRobotMap.xboxDrive.getRightBumper()==true){
			CatzRobotMap.grabber.deployBicep();
		}
		if(CatzRobotMap.xboxDrive.getLeftBumper()==true) {
			CatzRobotMap.grabber.retractBicep();
		}
		if(CatzRobotMap.xboxDrive.getAButton()==true){
			CatzRobotMap.grabber.toggleForearm();
		}
		
	}
	private static void runLiftControls()
	{
		CatzRobotMap.lift.setLiftSpeed(CatzRobotMap.xboxAux.getRightTrigger() - CatzRobotMap.xboxAux.getLeftTrigger());
	}
	private static void runClimberControls()
	{
		CatzRobotMap.climberMechanism.setClimberSpeed(CatzRobotMap.xboxAux.getRightTrigger() - CatzRobotMap.xboxAux.getLeftTrigger());
	}
}
