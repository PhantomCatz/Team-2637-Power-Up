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
	private static boolean reversed;
	public static void runTeleopPeriodic()
	{	
		if(CatzRobotMap.xboxDrive.getBackButtonPressed())
			reversed = !reversed;
		
		 
		if(reversed)
			CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxDrive);
		else
			CatzRobotMap.drive.setModeReverseArcadeDriveFlash(CatzRobotMap.xboxDrive);
		
		runGrabberControls();
		runLiftControls();
		runClimberControls();
	}
	
	
	private static void runGrabberControls() 
	{
		CatzRobotMap.grabber.setIntakeSpeed(CatzRobotMap.xboxDrive.getLeftStickY());
		
		if(CatzRobotMap.xboxAux.getRightBumper())
		{
			CatzRobotMap.grabber.deployBicep();
		}
		
		if(CatzRobotMap.xboxAux.getLeftBumper()) 
		{
			CatzRobotMap.grabber.retractBicep();
		}
		
		if(CatzRobotMap.xboxDrive.getAButton())
		{
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
