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
		runDriveTrainControls();
		runGrabberControls();
		runLiftControls();
		runClimberControls();
	}
	
	private static void runDriveTrainControls() 
	{
		if(CatzRobotMap.xboxDrive.getBButton())
			reversed = !reversed;
		 
		if(reversed==true)
			CatzRobotMap.drive.setModeReverseArcadeDriveFlash(CatzRobotMap.xboxDrive);
		else
			CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxDrive);
	}
	
	private static void runGrabberControls() 
	{
		CatzRobotMap.grabber.setIntakeSpeed(CatzRobotMap.xboxDrive.getRightTrigger()-CatzRobotMap.xboxDrive.getLeftTrigger());
		
		if(CatzRobotMap.xboxAux.getRightBumper()){
			CatzRobotMap.grabber.deployBicep();
		}
		
		if(CatzRobotMap.xboxAux.getLeftBumper()) {
			CatzRobotMap.grabber.retractBicep();
		}
		
		if(CatzRobotMap.xboxDrive.getAButton()){
			CatzRobotMap.grabber.toggleForearm();
		}
		
		if(CatzRobotMap.xboxAux.getBButton()) {
			CatzRobotMap.liftEncoder.reset();
			CatzRobotMap.wheelEncoderL.reset();
			CatzRobotMap.wheelEncoderR.reset();
		}
		
	}
	private static void runLiftControls()
	{
		
		/**
		 * if lifter limit is activated, only have the ability to move lifter down
		 * Aux controller X button overrides the limit switch
		 */
		if(CatzRobotMap.xboxAux.getXButton()==true){
			CatzRobotMap.lifterL.set(CatzRobotMap.xboxAux.getLeftStickY());
			CatzRobotMap.lifterR.set(CatzRobotMap.xboxAux.getLeftStickY());
		} else {
			if(CatzRobotMap.lifterLimit.get()==false) {
				CatzRobotMap.lifterL.set(CatzRobotMap.xboxAux.getLeftStickY());
				CatzRobotMap.lifterR.set(CatzRobotMap.xboxAux.getLeftStickY());
			} else {
				if(CatzRobotMap.xboxAux.getLeftStickY()<=.01) {
					CatzRobotMap.lifterL.set(CatzRobotMap.xboxAux.getLeftStickY());
					CatzRobotMap.lifterR.set(CatzRobotMap.xboxAux.getLeftStickY());
				}	
			}
		}
	}
	private static void runClimberControls()
	{
		CatzRobotMap.climberMechanism.setClimberSpeed(Math.abs(CatzRobotMap.xboxAux.getRightStickY()));
	}
}
