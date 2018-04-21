/*******************************************************
 *  Author : Jean Kwon
 *  Last Revised : 2-19-2018 JK
 *  Last revision summary: rename the check box
 *  Methods: runAutonomousInit, choosePath, choosePathDouble
 *  Functionality: choose the path for autonomous 
*******************************************************/

package robotFunctions;

import autonomous.CatzAutonomousAlternatePaths;
import autonomous.CatzAutonomousDoublePaths;
import autonomous.CatzPIDDrive;
import autonomous.CatzPIDTurn;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousInit {	
	
	static String gameData;

	static boolean check_boxL;
	static boolean check_boxM;
	static boolean check_boxR;
	
	public static void runAutonomousInit()
	{
		printOutDebugData("autonomousInit");

		setMechanisms();
		CatzPIDDrive.setDebugModeEnabled(true);
		CatzPIDTurn.setPIDTurnDebugModeEnabled(true);

		choosePathDouble();
		//CatzAutonomousDoublePaths.toOppoScale("right");
		//CatzAutonomousDoublePaths.rightDoubleCube_XRX();
	    //CatzAutonomousDoublePaths.leftDoubleCube_XLX();
		//choosePathAlternate();
		//CatzAutonomousAlternatePaths.left_ScaleSingle();
		//CatzAutonomousAlternatePaths.right_ScaleSingle();

		//CatzRobotMap.lift.liftToHeight(70);
		/*Timer.delay(1.0);
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
			System.out.println(CatzRobotMap.lift.liftThreadRunning);
		}
		*/
	}
	
	public static void choosePathDouble() 
	{
		
		check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
	    check_boxM = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false);
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	
		boolean initSuccessFlag = true;

		System.out.println(gameData);
	
	if(SmartDashboard.getBoolean("Use default autonomous?", false)==true) 
	{
		if(check_boxL == true || check_boxR == true) {
			System.out.println("default auto - left or right");
			CatzPIDDrive.PIDDriveNoTrig(0.7, 193, 5);   // goes straight
		} else {
			System.out.println("default auto - middle");
			CatzPIDDrive.PIDDriveNoTrig(.6, (36 - CatzConstants.HALF_ROBOT_LENGTH), 1.2);  //Leaves the wall and drives to the switch
			CatzPIDTurn.PIDturn(45, 1.0); 
			CatzPIDDrive.PIDDriveNoTrig(.6, 65.0,3.0);  //Turns 45deg left and approaches the switch
			CatzPIDTurn.PIDturn(-45, 1.0); 
			CatzPIDDrive.PIDDriveNoTrig(.6, (45 - CatzConstants.HALF_ROBOT_LENGTH), 1); //Turns 45deg right and presses against the switch
		}
			
	} 
	else 
	{
		if (check_boxL == true) {
			System.out.println("Starting position left");
					
			if(gameData.charAt(1)=='R') {
				printOutDebugData("left_Right Scale");
				CatzAutonomousDoublePaths.leftDoubleCube_XRX();
			} else if (gameData.charAt(1)=='L') {
				printOutDebugData("left_Left Scale");
				CatzAutonomousDoublePaths.leftDoubleCube_XLX();
			} 
		  
      } 
	else if (check_boxM == true) 
		{
		System.out.println("Starting position Middle");

			if (gameData.charAt(0) == 'L') {
				printOutDebugData("middle left auto");
				CatzAutonomousDoublePaths.middleDoubleCube_LXX();
			} else if (gameData.charAt(0) == 'R') {
				printOutDebugData("middle right auto");
				CatzAutonomousDoublePaths.middleDoubleCube_RXX();
			} 
		
	} 
	else if (check_boxR == true) 
	{
		System.out.println("Starting position Right");

		if(gameData.charAt(1) == 'L') {
			printOutDebugData("right_left Scale");
			CatzAutonomousDoublePaths.rightDoubleCube_XLX();
		} else if(gameData.charAt(1) == 'R') {
			printOutDebugData("right_right Scale");
			CatzAutonomousDoublePaths.rightDoubleCube_XRX();
		}
				
	} 
	else 
	{
		initSuccessFlag = false;
	} 
	
	if (initSuccessFlag == true) {
		printOutDebugData("Init Done");
		} else {
			printOutDebugData("Init failed");
			CatzPIDDrive.PIDDriveNoTrig(CatzConstants.AUTO_STARTPOS_DEF_SPEED,
			              		  CatzConstants.AUTO_STARTPOS_DEF_DISTANCE,
			                      CatzConstants.AUTO_STARTPOS_DEF_TIMEOUT); //if all else fails, just drive forward
			}
	
		}
	
	}
	public void choosePathAlternate()
	{
		check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
		check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false);
		
		if(check_boxL == true)
		{
			CatzAutonomousAlternatePaths.alternatePathChooser("left");
		}
		else if (check_boxR == true)
		{
			CatzAutonomousAlternatePaths.alternatePathChooser("right");
		}
	}

			
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
	
	public static void setMechanisms() {
		CatzRobotMap.liftEncoder.reset();
		CatzRobotMap.globalTimer.reset();
		CatzRobotMap.grabber.closeForearm(0.0);
		CatzRobotMap.grabber.retractBicep(0.0);
	}

}
