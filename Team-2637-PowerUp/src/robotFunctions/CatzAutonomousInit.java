/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: rename the check box
 *  Methods: runAutonomousInit, choosePath, choosePathDouble
 *  Functionality: choose the path for autonomous 
 *  I didn't test the double chooser yet
*******************************************************/

package robotFunctions;

import autonomous.CatzAutonomousOutsidePaths;
import autonomous.CatzAutonomousPaths;
import autonomous.CatzPIDDrive;
import autonomous.CatzPIDTurn;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousInit {	
	
	static String gameData = DriverStation.getInstance().getGameSpecificMessage();

	static boolean check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
	static boolean check_boxM = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
	static boolean check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false); 
	
	public static void runAutonomousInit()
	{
		//CatzPIDTurn.setDebugModeEnabled( true );

		//setMechanisms();
		//choosePath();
		CatzPIDDrive.setDebugModeEnabled(true);

		CatzPIDDrive.PIDDrive(0.4, 48, 1);
		//CatzAutonomousPaths.middlePathL();
	}

	public static void choosePath() {	
		
		System.out.println(gameData);
		if (gameData.charAt(0) == 'L' && check_boxM == true) {
			printOutDebugData("middlePathL");
			CatzAutonomousPaths.middleSingle_LXX();
			printOutDebugData("Init Done");
			
		} else if (gameData.charAt(0) == 'R' && check_boxM == true) {
			printOutDebugData("middlePathR");
			CatzAutonomousPaths.middleSingle_RXX();
			printOutDebugData("Init Done");
			
		} else if(gameData.charAt(1) == 'R' && check_boxR == true){
			printOutDebugData("rightPath");
			CatzAutonomousPaths.right_XRX();	
			printOutDebugData("Init Done");
			
		} else if(gameData.charAt(1) == 'L' && check_boxL == true){
			printOutDebugData("LeftPath");
			CatzAutonomousPaths.left_XLX();
			printOutDebugData("Init Done");
			
		} else {
			printOutDebugData("Init failed");
			CatzPIDDrive.PIDDrive(CatzConstants.AUTO_STARTPOS_DEF_SPEED,
		              			  CatzConstants.AUTO_STARTPOS_DEF_DISTANCE,
		                          CatzConstants.AUTO_STARTPOS_DEF_TIMEOUT); //if all else fails, just drive forward
		}
	}
	
	public static void choosePathDouble() {
		
	
		System.out.println(gameData);
		
		if (check_boxL == true) {
			System.out.println("Starting position right");
			
			if (gameData.charAt(0)=='R'&& gameData.charAt(1)=='R') {
				printOutDebugData("left_RRR");
				CatzAutonomousOutsidePaths.left_RRR();
				printOutDebugData("Init Done");
				
			} else if (gameData.charAt(0) == 'R' && gameData.charAt(1)=='L') {
				printOutDebugData("left_RLR");
				CatzAutonomousOutsidePaths.left_RLR();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				printOutDebugData("left_LRL");
				CatzAutonomousOutsidePaths.left_LRL();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
				printOutDebugData("left_LLL");
				CatzAutonomousOutsidePaths.left_LLL();
				printOutDebugData("Init Done");

			}
			
		} else if (check_boxM == true) {
			
			if (gameData.charAt(0)=='R'&& gameData.charAt(1)=='R') {
				printOutDebugData("Middle_RRR");
				CatzAutonomousPaths.middle_RRX();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0) == 'R'&& gameData.charAt(1) == 'L') {
				printOutDebugData("Middle_RLR");
				CatzAutonomousPaths.middleDouble_RXX();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				printOutDebugData("Middle_LRL");
				CatzAutonomousPaths.middleDouble_LXX();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
				printOutDebugData("Middle_LLL");
				CatzAutonomousPaths.middle_LLX();
				printOutDebugData("Init Done");

			}
			
		} else if (check_boxR == true) {
			
			if (gameData.charAt(0)=='R'&& gameData.charAt(1)=='R') {
				printOutDebugData("Right_RRR");
				CatzAutonomousOutsidePaths.right_RRR();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0)=='R' && gameData.charAt(1) == 'L') {
				printOutDebugData("Right_RLR");
				CatzAutonomousOutsidePaths.right_RLR();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0)=='L' && gameData.charAt(1) == 'R') {
				printOutDebugData("Right_LRL");
				CatzAutonomousOutsidePaths.right_LRL();
				printOutDebugData("Init Done");

			} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
				printOutDebugData("Right_LL");
				CatzAutonomousOutsidePaths.right_LLL();
				printOutDebugData("Init Done");

			}
			
		} else {
			printOutDebugData("Init failed");
			CatzPIDDrive.PIDDrive(CatzConstants.AUTO_STARTPOS_DEF_SPEED,
			              		  CatzConstants.AUTO_STARTPOS_DEF_DISTANCE,
			                      CatzConstants.AUTO_STARTPOS_DEF_TIMEOUT); //if all else fails, just drive forward
		}
		
		
	}
	
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}

}
