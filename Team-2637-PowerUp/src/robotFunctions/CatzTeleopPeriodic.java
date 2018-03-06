package robotFunctions;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import mechanisms.CatzGrabber;
import robot.CatzConstants;
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
	public static boolean reversed = false;
	public static void runTeleopPeriodic()
	{	
		runDriveTrainControls();
		runGrabberControls();
		runLiftControls();
		runClimberControls();
	}
	
	private static void runDriveTrainControls() 
	{
		if(CatzRobotMap.xboxDrive.getBButton()) {
			reversed = !reversed;
			Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
		}
		 
		if(reversed==true)
			CatzRobotMap.drive.setModeReverseArcadeDriveFlash(CatzRobotMap.xboxDrive);
		else
			CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xboxDrive);
	}
	
	private static void runGrabberControls() 
	{
		CatzRobotMap.grabber.setIntakeSpeed(CatzRobotMap.xboxDrive.getRightTrigger()-CatzRobotMap.xboxDrive.getLeftTrigger());
		
		if(CatzGrabber.forearmOpen==false) { //only control the biceps if the forearms are closed
			if(CatzRobotMap.xboxAux.getRightBumper()){
				CatzRobotMap.grabber.deployBicep();
			}								//if forearms are open and biceps are moved, a leak occurs
			else if(CatzRobotMap.xboxAux.getLeftBumper()) {
				CatzRobotMap.grabber.retractBicep();
			}
		}
		
		if(CatzRobotMap.xboxAux.getAButton()==true) {
			CatzRobotMap.grabber.retractBicep();
			CatzRobotMap.grabber.openForearm();
		}
		
		if(CatzRobotMap.xboxDrive.getAButton()){
			CatzRobotMap.grabber.toggleForearm();
		}
		
		if(CatzRobotMap.xboxDrive.getXButton()){
			CatzRobotMap.grabber.openForearm(0.0);
			CatzRobotMap.grabber.deployBicep();
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
