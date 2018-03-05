package autonomous;

import edu.wpi.first.wpilibj.Timer;
import mechanisms.CatzGrabber;
import robot.CatzConstants;
import robot.CatzRobotMap;

/**********************************************************
 * Timothy Vu 3 Feb 2018 TV 
 * Adding in Left and Right paths 
 * Methods: middlePathL middlePathR leftPath rightPath 
 * Functionality: Paths for the autonomous period
 *********************************************************/
public class CatzAutonomousMiddlePaths {
	/******************************
	 * All distances in the comments are not factoring in robot length
	 * 
	 * Timeout is set to 2 sec
	 ****************************/

	public static void middleSingle_RXX() {
		middlePath("Right");
/*		
		TV 3/4/18 Sequence not working
		CatzPIDDrive.PIDDriveNoTrig(-.5, (20 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from the switch
																													
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, 43.2,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the side of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDDrive.PIDDriveNoTrig(.5, 84,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the back of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		CatzRobotMap.lift.dropToGroundHeight();  //Drops the lift down
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (24 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and presses against the cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube
		
		*/
	}

	public static void middleSingle_LXX() {
		
		middlePath("left");
	}
	
	public static void middlePath(String side) {
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 * Goes around the switch and grabs a cube
		 *********************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(.6, (36 - CatzConstants.HALF_ROBOT_LENGTH), 5);  //Leaves the wall and drives to the switch
		
		if(side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-45, 2.5); 
		} else {
			CatzPIDTurn.PIDturn(45, 2.5);
		}

		CatzPIDDrive.PIDDriveNoTrig(.6, 72,5);  //Turns 45deg left and approaches the switch

		if(side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(45, 2.5); 
		} else {
			CatzPIDTurn.PIDturn(-45, 2.5); 
		}
		
		
		//CatzRobotMap.lift.liftToSwitchHeight();  //not needed to get cube into switch
		System.out.println("I am in the path");
		
		CatzPIDDrive.PIDDriveNoTrig(.6, (40 - CatzConstants.HALF_ROBOT_LENGTH), 3); //Turns 45deg right and presses against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch
			
	}

	public static void left_XLX() {
		scalePath("left");
	}

	public static void right_XRX() {
		scalePath("right");    
	}
	
	public static void scalePath (String side) {
		
		/************************************
		 * The following code:
		 * Drives to the scale
		 * Places cube in scale
		 * Picks up cube
		 ***********************************/
		
		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving?
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (335.65 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Drives all the way to the scale

		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT);
		}
		
	
		CatzPIDDrive.PIDDriveNoTrig(.5, 41.88,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives forward to face the scale
	
		CatzRobotMap.grabber.placeCube();  //Fires cube into the scale
	
		Timer.delay(2);
	
		CatzRobotMap.lift.dropToGroundHeight();  //waits for 2sec then drops to ground height
	
		if(side.equalsIgnoreCase("left")) {
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT);

		}
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 112.8,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives towards the cubes

		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

		} else {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT); 

		}
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (12 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns left and presses against cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube

	}

	  public static void left_XRX() {  
		  oppoScalePath("left");
	  }
	  
	  public static void right_XLX() {		  
		  oppoScalePath("right");
	  }
	  
	  public static void oppoScalePath (String side) {
		  
		  /****************************************************
		   * The following code:
		   * Drives to the area between the scale and switch
		   * Crosses over to other side scale
		   * Places cube in scale
		   ***************************************************/
		  
	   CatzPIDDrive.PIDDriveNoTrig(.5, (251.73 - CatzConstants.HALF_ROBOT_LENGTH),
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Drives to area between switch and scale
	
	   if (side.equalsIgnoreCase("left")) {
		   CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT); 
	   } else {
		   CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

	   }
	  
	   CatzPIDDrive.PIDDriveNoTrig(.5, 251.73,
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and crosses to right side scale
	  if (side.equalsIgnoreCase("left")) {
		   CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

	  } else {
		   CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT); 

	  }
	  
	   CatzPIDDrive.PIDDriveNoTrig(.5, 84,
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the front of scale
	  
	   if (side.equalsIgnoreCase("left")) {
	   CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT);
	   } else {
		   CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);

	   }
	   
	   /* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
	  
	    CatzPIDDrive.PIDDriveNoTrig(.5, (24 - CatzConstants.HALF_ROBOT_LENGTH),
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and aligns with scale
	  
	    CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale

		CatzRobotMap.grabber.shootCube();  //Fires cube onto the scale
	  }
	  
	  public static void pickUpCube(String side) {
		
		  //	 TV 3/4/18 Sequence not working
			 
			CatzPIDDrive.PIDDriveNoTrig(-.5, (20 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from the switch 
			
			if(side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 
			} else {
				CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);
			}
			CatzPIDDrive.PIDDriveNoTrig(.5, (43.2),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the side of the switch
			
			if(side.equalsIgnoreCase("left")) {
				CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);
			} else {
				CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT);
			}
			
			CatzPIDDrive.PIDDriveNoTrig(.5, 84,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the back scale
		
			if(side.equalsIgnoreCase("left")) {
				CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT); 
			} else {
				CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 
			}
			
			CatzRobotMap.lift.dropToGroundHeight();  //Lowers the lift
			
			CatzPIDDrive.PIDDriveNoTrig(.5, (24 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses up against the cube

			CatzRobotMap.grabber.intakeCube();  //Intakes the cube 
	  }
	  
	public static void middle_RRX() {

		/***************************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube into the switch
		 **************************************************/
		
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (66.3 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall

		CatzPIDTurn.PIDturn(60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, 64,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 60deg right and drive towards the switch

		CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (36.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses against the 

		CatzRobotMap.grabber.shootCube();  //Fires the cube into the switch
		
		
		/******************************************************
		 * The following code: 
		 * Backs away from the switch
		 * Drives around the switch and intakes a second cube
		 * Turns and drives to the scale
		 * Places the cube on the scale
		 ****************************************************/

		CatzPIDDrive.PIDDriveNoTrig(-.5, (36.5 + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, 65,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the side of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, 89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the back of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, (47.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and presses against the cube

		CatzRobotMap.grabber.intakeCube();  //Picks up the second cube

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */

		CatzPIDDrive.PIDDriveNoTrig(.5, 8,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the switch
		
		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale

		CatzRobotMap.grabber.shootCube();  //Fires cube onto the scale

	}

	public static void middle_LLX() {
		
		/*******************************************************
		 * The following code:
		 * Drives towards the switch
		 * Shoots cube into the switch
		 *******************************************************/


		CatzPIDDrive.PIDDriveNoTrig(.5, (66.3 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall

		CatzPIDTurn.PIDturn(-60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, 64,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 60deg right and starts towards the switch


		CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, (36 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses up against the switch

		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch

		/**************************************************
		 * The following code:
		 * Backs away from the switch
		 * Goes around the switch and picks up a cube
		 * Drives to the scale and places cube on scale
		 *************************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(-.5, (36.5 + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from switch 


		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, 65,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and goes to the side of the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		CatzPIDDrive.PIDDriveNoTrig(.5, 89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and goes the the back of the switch


		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(.5, (47.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses up against the cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the scale

		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale
		
		CatzRobotMap.grabber.shootCube();  //Fires cube onto scale
		
	}

	public static void middleDouble_RXX() {
		
		/************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube in the switch
		 ************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (66.5 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall
																														
		CatzPIDTurn.PIDturn(60, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 64.0,            
					CatzConstants.PID_TURN_TIMEOUT);  //Turns 60deg right and drives towards the switch
		
		CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (36.5 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses up against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch
		
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Turns and approaches the side of switch
		 * Places second cube in switch
		 *****************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(-.5, (36.5 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the side of switch
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to back of switch
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and presses against the cube
		
		CatzRobotMap.grabber.intakeCube();  //Intakes a second cube
		
		CatzPIDDrive.PIDDriveNoTrig(-.5, 35, 
				CatzConstants.PID_TURN_TIMEOUT);  //Backs away from cube
		
		CatzPIDTurn.PIDturn(-45, CatzConstants.PID_DRIVE_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 45.5, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 45deg left and presses against the switch  NOT PERPENDICULAR TO THE SWITCH
		
		CatzRobotMap.grabber.shootCube();
		
	}
	
	public static void middleDouble_LXX() {
		
		/************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube in the switch
		 ************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (66.5 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall 
																														
		CatzPIDTurn.PIDturn(-60, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 64,            
					CatzConstants.PID_TURN_TIMEOUT);  //Turns 60deg left and approaches the switch
		
		CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (36.5 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg right and presses against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into switch
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Turns and approaches the side of switch
		 * Places second cube in switch
		 *****************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(-.5, (36.5 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the side of switch
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right left and drives to back of switch
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses against the cube
		
		CatzRobotMap.grabber.intakeCube();  //Intakes a second cube
		
		CatzPIDDrive.PIDDriveNoTrig(-.5, 35.0, 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from cube
		
		CatzPIDTurn.PIDturn(45, CatzConstants.PID_DRIVE_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 45.5, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 45deg right and presses against the switch  NOT PERPENDICULAR TO SWITCH
		
		CatzRobotMap.grabber.shootCube();
		
	}
	
	public static void middle_RLX() { 
		
		/************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube in the switch
		 ************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (66.5 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall
																														
		CatzPIDTurn.PIDturn(60, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 64.0,            
					CatzConstants.PID_TURN_TIMEOUT);  //Turns 60deg right and drives towards the switch
		
		CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (36.5 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses up against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch
		
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Drives to opposite switch
		 * Places cube in opposite switch
		 *****************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(-.5, (36.5 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the side of switch
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to back of switch
		  
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);
		
		 CatzPIDDrive.PIDDriveNoTrig(.5, 251.73,
				  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and crosses over to the left side scale
		 
		 CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);
		 
		 CatzPIDDrive.PIDDriveNoTrig(.5, 47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses against the cube
		
		CatzRobotMap.grabber.intakeCube();  //Intakes a second cube
	
		  CatzPIDTurn.PIDturn(180, CatzConstants.PID_DRIVE_TIMEOUT);
		  
			/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		  
		  CatzPIDDrive.PIDDriveNoTrig(.5, (89 - CatzConstants.HALF_ROBOT_LENGTH), 
		  			CatzConstants.PID_DRIVE_TIMEOUT);  //aligns to scale

		  CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale

		  CatzRobotMap.grabber.shootCube();  //Fires cube onto the scale
		
	}
	
public static void middle_LRX() { 
		
		/************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube in the switch
		 ************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (66.5 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall
																														
		CatzPIDTurn.PIDturn(-60, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 64.0,            
					CatzConstants.PID_TURN_TIMEOUT);  //Turns 60deg right and drives towards the switch
		
		CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, (36.5 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses up against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch
		
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Drives to opposite switch
		 * Places cube in opposite switch
		 *****************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(-.5, (36.5 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the side of switch
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(.5, 89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to back of switch
		  
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		 CatzPIDDrive.PIDDriveNoTrig(.5, 251.73,
				  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and crosses over to the right side scale
		 
		 CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		 
		 CatzPIDDrive.PIDDriveNoTrig(.5, 47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses against the cube
		
		CatzRobotMap.grabber.intakeCube();  //Intakes a second cube
		  
		  CatzPIDTurn.PIDturn(180, CatzConstants.PID_DRIVE_TIMEOUT);
		  
			/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		  
		  CatzPIDDrive.PIDDriveNoTrig(.5, (89 - CatzConstants.HALF_ROBOT_LENGTH), 
		  			CatzConstants.PID_DRIVE_TIMEOUT);  //aligns to scale

		  CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale

		  CatzRobotMap.grabber.shootCube();  //Fires cube onto the scale
		
	}
	
	
	
}
