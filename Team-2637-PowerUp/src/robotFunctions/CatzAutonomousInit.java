/*******************************************************
 *  Author : Jean Kwon
 *  Last Revised : 2-19-2018 JK
 *  Last revision summary: rename the check box
 *  Methods: runAutonomousInit, choosePath, choosePathDouble
 *  Functionality: choose the path for autonomous 
*******************************************************/

package robotFunctions;

import autonomous.CatzAutonomousMiddlePaths;
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
		//CatzRobotMap.lift.liftToScaleHeight();
		//CatzPIDTurn.PIDturn(45, CatzConstants.DEF_VALUE);
		//choosePathDouble();
		//setMechanisms();
		choosePath();
		//CatzPIDTurn.PIDturn(45, CatzConstants.DEF_VALUE);
	}

	
	public static void choosePath() {	
		
		check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
	    check_boxM = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false); 
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		boolean initSuccessFlag = true;

	System.out.println(gameData);
	
	if (check_boxL == true) {
		System.out.println("Starting position left");
		
		if (gameData.charAt(0)=='R') {
			
			if(gameData.charAt(1)=='R') {
				printOutDebugData("left_RRR");
			//	CatzAutonomousMiddlePaths.left_XRX();
			} else if (gameData.charAt(1)=='L') {
				printOutDebugData("left_RLR");
			//	CatzAutonomousMiddlePaths.left_XLX();
			} 
		  
		} else if (gameData.charAt(0) == 'L') {
		    	
			if (gameData.charAt(1)=='R') {
		    	printOutDebugData("left_LRL");
		    //	CatzAutonomousMiddlePaths.left_XRX();
		    } else if (gameData.charAt(1) == 'L') {
		    	printOutDebugData("left_LLL");
		    //	CatzAutonomousMiddlePaths.left_XLX();
		    }
			
		}
      } else if (check_boxM == true) {
		System.out.println("Starting position Middle");

			if (gameData.charAt(0) == 'L') {
				
				if (gameData.charAt(1) == 'L') {
					printOutDebugData("middle_LLL");
			//		CatzAutonomousMiddlePaths.middleSingle_LXX();
				} else if (gameData.charAt(1) == 'R') {
					printOutDebugData("middle_LRL");
				//	CatzAutonomousMiddlePaths.middleSingle_LXX();
				}
				
			} else if (gameData.charAt(0) == 'R') {
				
				if (gameData.charAt(1) == 'R') {
					printOutDebugData("middle_RRR");
				//	CatzAutonomousMiddlePaths.middleSingle_RXX();
				} else if (gameData.charAt(1) == 'L') {
					printOutDebugData("middle_RLR");
				//	CatzAutonomousMiddlePaths.middleSingle_RXX();
				} 
			}
			
	} else if (check_boxR == true) {
		System.out.println("Starting position Right");

			if (gameData.charAt(0) == 'L') {
				
				if(gameData.charAt(1) == 'L') {
					printOutDebugData("right_LLL");
				//	CatzAutonomousMiddlePaths.right_XLX();
				} else if(gameData.charAt(1) == 'R') {
					printOutDebugData("right_LRL");
				//	CatzAutonomousMiddlePaths.right_XRX();
				}
				
			} else if(gameData.charAt(0) == 'R') {
				
				if (gameData.charAt(1) == 'L') {
					printOutDebugData("right_RLR");
				//	CatzAutonomousMiddlePaths.right_XLX();
				} else if (gameData.charAt(1) == 'R') {
					printOutDebugData("right_RRR");
				//	CatzAutonomousMiddlePaths.right_XRX();
				}
			}
		} else {
			initSuccessFlag = false;
		} 
	
	if (initSuccessFlag == true) {
		printOutDebugData("Init Done");
		} else {
			printOutDebugData("Init failed");
			//CatzPIDDrive.PIDDrive(CatzConstants.AUTO_STARTPOS_DEF_SPEED,
			  //            		  CatzConstants.AUTO_STARTPOS_DEF_DISTANCE,
			    //                  CatzConstants.AUTO_STARTPOS_DEF_TIMEOUT); //if all else fails, just drive forward
		}
	
	}
			
	
	public static void choosePathDouble() {
		
		check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
	    check_boxM = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false); 
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		boolean initSuccessFlag = true;

	System.out.println(gameData);
	
	if (check_boxL == true) {
		System.out.println("Starting position left");
		
		if (gameData.charAt(0)=='R') {
			
			if(gameData.charAt(1)=='R') {
				printOutDebugData("left_RRR");
				//CatzAutonomousOutsidePaths.left_RRR();
			} else if (gameData.charAt(1)=='L') {
				printOutDebugData("left_RLR");
				//CatzAutonomousOutsidePaths.left_RLR();
			} 
		  
		} else if (gameData.charAt(0) == 'L') {
		    	
			if (gameData.charAt(1)=='R') {
		    	printOutDebugData("left_LRL");
				//CatzAutonomousOutsidePaths.left_LRL();
		    } else if (gameData.charAt(1) == 'L') {
		    	printOutDebugData("left_LLL");
				//CatzAutonomousOutsidePaths.left_LLL();
		    }
			
		}
      } else if (check_boxM == true) {
		System.out.println("Starting position Middle");

			if (gameData.charAt(0) == 'L') {
				
				if (gameData.charAt(1) == 'L') {
					printOutDebugData("middle_LLL");
					CatzAutonomousMiddlePaths.middle_LLX();
				} else if (gameData.charAt(1) == 'R') {
					printOutDebugData("middle_LRL");
					CatzAutonomousMiddlePaths.middle_LRX();
				}
				
			} else if (gameData.charAt(0) == 'R') {
				
				if (gameData.charAt(1) == 'R') {
					printOutDebugData("middle_RRR");
					CatzAutonomousMiddlePaths.middle_RRX();
				} else if (gameData.charAt(1) == 'L') {
					printOutDebugData("middle_RLR");
					CatzAutonomousMiddlePaths.middle_RLX();
				} 
			}
			
	} else if (check_boxR == true) {
		System.out.println("Starting position Right");

			if (gameData.charAt(0) == 'L') {
				
				if(gameData.charAt(1) == 'L') {
					printOutDebugData("right_LLL");
					//CatzAutonomousOutsidePaths.right_LLL();
				} else if(gameData.charAt(1) == 'R') {
					printOutDebugData("right_LRL");
					//CatzAutonomousOutsidePaths.right_LRL();
				}
				
			} else if(gameData.charAt(0) == 'R') {
				
				if (gameData.charAt(1) == 'L') {
					printOutDebugData("right_RLR");
					//CatzAutonomousOutsidePaths.right_RLR();
				} else if (gameData.charAt(1) == 'R') {
					printOutDebugData("right_RRR");
					//CatzAutonomousOutsidePaths.right_RRR();
				}
			}
		} else {
			initSuccessFlag = false;
		} 
	
	if (initSuccessFlag == true) {
		printOutDebugData("Init Done");
		} else {
			printOutDebugData("Init failed");
			//CatzPIDDrive.PIDDrive(CatzConstants.AUTO_STARTPOS_DEF_SPEED,
			  //            		  CatzConstants.AUTO_STARTPOS_DEF_DISTANCE,
			    //                  CatzConstants.AUTO_STARTPOS_DEF_TIMEOUT); //if all else fails, just drive forward
		}
	
	}
			
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}

}
