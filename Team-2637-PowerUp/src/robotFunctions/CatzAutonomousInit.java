/*******************************************************
 *  Author : Jean Kwon
 *  Last Revised : 2-19-2018 JK
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
	
	static String gameData;

	static boolean check_boxL;
	static boolean check_boxM;
	static boolean check_boxR;
	
	public static void runAutonomousInit()
	{
<<<<<<< HEAD
		
		System.out.println(check_boxL);
		System.out.println(check_boxM);
		System.out.println(check_boxR);
		
		//setMechanisms();
		choosePath();
		//choosePathDouble();
		//CatzPIDTurn.setPIDTurnDebugModeEnabled(true);
		//CatzPIDDrive.setDebugModeEnabled(true);
		//CatzPIDDrive.PIDDrive(.5, 50, 10);
		// PIDTurn.PIDturn(90, 3);
		//Timer.delay(0.5);
=======
		//CatzPIDTurn.setDebugModeEnabled( true );

		//setMechanisms();
		//choosePath();
		//CatzRobotInit.setSmartDashboard();
		//CatzPIDDrive.setDebugModeEnabled(true);
		//CatzPIDDrive.PIDDrive(.5, 48, 10);
		
//        PIDTurn.PIDturn(90, 3);
//	Timer.delay(0.5);
>>>>>>> 927171c8e762a6f33de25490d125ccd60c4d2312
//		CatzPIDTurn.PIDturn(-90, 3);
//	Timer.delay(0.5);
		CatzPIDTurn.PIDturn(45, CatzConstants.DEF_VALUE);
		//Timer.delay(0.5);
		//CatzPIDTurn.PIDturn(-90, CatzConstants.DEF_VALUE);
		//retractMechanisms();
		//CatzRobotMap.grabber.retractGrabber();
<<<<<<< HEAD
		//CatzPIDTurn.setDebugModeEnabled( true );
=======
		//choosePath();

>>>>>>> 927171c8e762a6f33de25490d125ccd60c4d2312
		//CatzPIDTurn.PIDturn(-45, 5);
		//CatzPIDTurn.PIDturn(-90, 10);
		//CatzPIDDrive.PIDDrive(0.7, 100, 3);
	}

	public static void choosePath() {	
		
		check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
	    check_boxM = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false); 
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		boolean initSuccessFlag = true;

		System.out.println(gameData);
		
		if(check_boxL == true) {
			if(gameData.charAt(1) == 'L') {
				printOutDebugData("LeftPath");
				//CatzAutonomousPaths.left_XLX();
				printOutDebugData("Init Done");

			} else {
				initSuccessFlag = false;
			}
			
		} else if (check_boxM == true) {
			if(gameData.charAt(0) == 'L') {
				printOutDebugData("middlePathL");
				//CatzAutonomousPaths.middleSingle_LXX();
				printOutDebugData("Init Done");
			} else if (gameData.charAt(0) == 'R') {
				printOutDebugData("middlePathR");
				//CatzAutonomousPaths.right_XRX();	
				printOutDebugData("Init Done");
			} else {
				initSuccessFlag = false;
			}
			
		} else if (check_boxR == true) {
			if(gameData.charAt(1) == 'R') {
				printOutDebugData("rightPath");
				//CatzAutonomousPaths.right_XRX();	
				printOutDebugData("Init Done");

			} else {
				initSuccessFlag = false;
			}
			
		if (initSuccessFlag == true) {
			printOutDebugData("Init Done");
			} else {
				printOutDebugData("Init failed");
			//	CatzPIDDrive.PIDDrive(CatzConstants.AUTO_STARTPOS_DEF_SPEED,
			//	              		  CatzConstants.AUTO_STARTPOS_DEF_DISTANCE,
			//	                      CatzConstants.AUTO_STARTPOS_DEF_TIMEOUT); //if all else fails, just drive forward
			}
			
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
					//CatzAutonomousPaths.middle_LLX();
				} else if (gameData.charAt(1) == 'R') {
					printOutDebugData("middle_LRL");
					//CatzAutonomousPaths.middleDouble_LXX();
				}
				
			} else if (gameData.charAt(0) == 'R') {
				
				if (gameData.charAt(1) == 'R') {
					printOutDebugData("middle_RRR");
					//CatzAutonomousPaths.middle_RRX();
				} else if (gameData.charAt(1) == 'L') {
					printOutDebugData("middle_RLR");
					//CatzAutonomousPaths.middleDouble_RXX();
				} 
			}
			
	} else if (check_boxR == true) {
		System.out.println("Starting position Right");

			if (gameData.charAt(0) == 'L') {
				
				if(gameData.charAt(0) == 'L') {
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
