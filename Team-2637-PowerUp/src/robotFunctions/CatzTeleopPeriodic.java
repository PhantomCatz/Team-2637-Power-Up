package robotFunctions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	private static boolean reversed = false;
	public static void runTeleopPeriodic()
	{	
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxAux);
		SmartDashboard.putNumber("Value of lifter Encoder",CatzRobotMap.liftEncoder.get());
		System.out.println(CatzRobotMap.liftEncoder.get());
		/*
		if(CatzRobotMap.xboxDrive.getBButton())
			reversed = !reversed;
		
		 
		if(reversed==true)
			CatzRobotMap.drive.setModeReverseArcadeDriveFlash(CatzRobotMap.xboxDrive);
		else
			CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxDrive);
		*/
		//runGrabberControls();
		//runLiftControls();
		CatzRobotMap.lifterL.set(CatzRobotMap.xboxAux.getLeftStickY());
		CatzRobotMap.lifterR.set(CatzRobotMap.xboxAux.getLeftStickY());
		if(CatzRobotMap.xboxAux.getAButton()== true) {
			CatzRobotMap.liftEncoder.reset();
		}
		//runClimberControls();
	}
	
	
	private static void runGrabberControls() 
	{
		CatzRobotMap.grabber.setIntakeSpeed(CatzRobotMap.xboxAux.getRightTrigger()-CatzRobotMap.xboxAux.getLeftTrigger());
		
		if(CatzRobotMap.xboxAux.getRightBumper()){
			CatzRobotMap.grabber.deployBicep();
		}
		
		if(CatzRobotMap.xboxAux.getLeftBumper()) {
			CatzRobotMap.grabber.retractBicep();
		}
		
		if(CatzRobotMap.xboxAux.getAButton()){
			CatzRobotMap.grabber.toggleForearm();
		}
		
	}
	private static void runLiftControls()
	{
		CatzRobotMap.lift.setLiftSpeed(CatzRobotMap.xboxAux.getLeftStickY());
	}
	private static void runClimberControls()
	{
		CatzRobotMap.climberMechanism.setClimberSpeed(CatzRobotMap.xboxAux.getRightTrigger() - CatzRobotMap.xboxAux.getLeftTrigger());
	}
}
