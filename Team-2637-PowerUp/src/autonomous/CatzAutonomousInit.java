/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: rename the check box
 *  Methods: runAutonomousInit, setMechanisms, liftToSwitchHeight, liftToScaleHeight, choosePath
 *  Functionality: choose the path for autonomous
*******************************************************/

package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzAutonomousInit
{
	
	public static void runAutonomousInit()
	{
		setMechanisms();
		//choosePath();
		//CatzPIDTurn.setDebugModeEnabled( true );
		//CatzPIDTurn.PIDturn(90, 5);
		//CatzPIDTurn.PIDturn(-90, 5);
		//CatzPIDDrive.PIDDrive(0.7, 100, 3);
	}
	
	private static void printOutDebugData(String data){
		if(CatzRobotMap.debugMode==true) {
			System.out.println(data);
		}
	}
	public static void setMechanisms() 
	{
		CatzRobotMap.grabber.closeForearm();
		CatzRobotMap.grabber.retractBicep();
	}
	public static void liftToSwitchHeight()
	{
		if(CatzRobotMap.liftEncoder.get() < CatzConstants.LIFT_SWITCH_HEIGHT)
			CatzRobotMap.lift.liftUp();
		else if(CatzRobotMap.liftEncoder.get() > CatzConstants.LIFT_SWITCH_HEIGHT)
			CatzRobotMap.lift.liftDown();
		else
			CatzRobotMap.lift.stopLift();
	}
	public static void liftToScaleHeight()
	{
		if(CatzRobotMap.liftEncoder.get() < CatzConstants.LIFT_SCALE_HEIGHT)
			CatzRobotMap.lift.liftUp();
		else if(CatzRobotMap.liftEncoder.get() > CatzConstants.LIFT_SCALE_HEIGHT)
			CatzRobotMap.lift.liftDown();
		else
			CatzRobotMap.lift.stopLift();
	}
	private static void deployGrabber()
	{
		CatzRobotMap.grabber.deployBicep();
		CatzRobotMap.grabber.openForearm();
	}
	private static void retractGrabber()
	{
		CatzRobotMap.grabber.closeForearm();
		CatzRobotMap.grabber.retractBicep();
	}
	public static void choosePath() 
	{	
		String gameData;
		
		boolean check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
		boolean check_boxM = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		boolean check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false); 

		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		System.out.println(gameData);
		if (gameData.charAt(0) == 'L' && check_boxM == true) {
			printOutDebugData("middlePathL");
			CatzAutonomousPaths.middlePathL();
			printOutDebugData("Init Done");
			
		} else if (gameData.charAt(0) == 'R' && check_boxM == true) {
			printOutDebugData("middlePathR");
			CatzAutonomousPaths.middlePathR();
			printOutDebugData("Init Done");
			
		} else if(gameData.charAt(1) == 'R' && check_boxR == true){
			printOutDebugData("rightPath");
			CatzAutonomousPaths.rightPath();	
			printOutDebugData("Init Done");
			
		} else if(gameData.charAt(1) == 'L' && check_boxL == true){
			printOutDebugData("LeftPath");
			CatzAutonomousPaths.leftPath();
			printOutDebugData("Init Done");
			
		} else {
			printOutDebugData("Init failed");
			CatzPIDDrive.PIDDrive(CatzConstants.AUTO_STARTPOS_DEF_SPEED,
		              			  CatzConstants.AUTO_STARTPOS_DEF_DISTANCE,
		                          CatzConstants.AUTO_STARTPOS_DEF_TIMEOUT);
		}
			
		
	}

}
