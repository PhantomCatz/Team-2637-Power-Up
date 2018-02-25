package autonomous;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import mechanisms.CatzGrabber;
import robot.CatzRobotMap;

/**********************************************************
 * Timothy Vu 3 Feb 2018 TV 
 * Adding in Left and Right paths 
 * Methods: middlePathL middlePathR leftPath rightPath 
 * Functionality: Paths for the autonomous period
 *********************************************************/
public class CatzAutonomousPaths {
	/******************************
	 * All distances in the comments are not factoring in robot length
	 * 
	 * Timeout is set to 2 sec
	 ****************************/

	public static void middlePathR() {

		/***************************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 * Goes around the switch and grabs a second cube
		 **************************************************/
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (36 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leave the wall 

		CatzPIDTurn.PIDturn(45, CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 72,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turn 45deg right and drive towards the scale

		CatzPIDTurn.PIDturn(-45, CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzRobotMap.lift.liftToSwitchHeight();  //Preemptively lift to switch height
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (55.2 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT); // Drive 45deg left and press against the switch

		CatzRobotMap.grabber.shootCube();  //Fire the cube into the switch
		
		
		/*****************************************************
		 * The following code:
		 * Backs away from the switch
		 * Drives around the switch
		 * Picks up a second cube
		 ****************************************************/
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, (20 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from the switch
																													
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 43.2,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the side of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 84,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the back of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		CatzRobotMap.lift.dropToGroundHeight();  //Drops the lift down
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (24 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and presses against the cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube
	}

	public static void middlePathL() {
		
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 * Goes around the switch and grabs a cube
		 *********************************************/
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (36 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall and drives to the switch
		
		CatzPIDTurn.PIDturn(-45, CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 72,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 45deg left and approaches the switch

		CatzPIDTurn.PIDturn(45, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		CatzRobotMap.lift.liftToSwitchHeight();  //Premptively raises the lift 
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (55.2 - CatzConstants.HALF_ROBOT_LENGTH), 
				CatzConstants.PID_DRIVE_TIMEOUT); //Turns 45deg right and presses against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch

		
		/**************************************************
		 * The following code:
		 * Goes around the switch
		 * Picks up a second cube
		 *************************************************/

		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, (20 + CatzConstants.HALF_ROBOT_LENGTH), 
				CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from the switch 
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (43.2),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the side of the switch
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 84,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the back scale

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		CatzRobotMap.lift.dropToGroundHeight();  //Lowers the lift
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (24 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses up against the cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube
		

	}

	public static void leftPath() {
		
		/************************************
		 * The following code:
		 * Drives to the scale
		 * Places cube in scale
		 * Picks up cube
		 ***********************************/

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (335.65 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Drives all the way to the scale

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);

		 /* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 41.88,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives forward to face the scale
		
		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into the scale
		
		Timer.delay(2);
		
		CatzRobotMap.lift.dropToGroundHeight();  //waits for 2sec then drops to ground height
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 112.8,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives towards the cubes

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (12 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns left and presses against cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube
	}

	public static void rightPath() {

		/************************************
		 * The following code:
		 * Drives to the scale
		 * Places cube in scale
		 * Picks up cube
		 ***********************************/
	
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (335.65 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Drives all the way to the scale

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

		 /* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (41.88 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left to face scale
		
		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale
		
		CatzRobotMap.grabber.shootCube();  //Fires cube onto scale

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (112.8 + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives towards cubes

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (12 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses against the cube

		CatzRobotMap.grabber.intakeCube();
	}

	
	  public static void leftSideNoScale() {
		  
		  /****************************************************
		   * The following code:
		   * Drives to the area between the scale and switch
		   * Crosses over to other side scale
		   * Places cube in scale
		   ***************************************************/
		  
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (251.73 - CatzConstants.HALF_ROBOT_LENGTH),
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Drives to area between switch and scale
	  
	  CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT); 
	  
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 251.73,
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and crosses to right side scale
	  
	  CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT); 
	  
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 84,
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the front of scale
	  
	  CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT);
	  
	  /* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
	  
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (24 - CatzConstants.HALF_ROBOT_LENGTH),
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and aligns with scale
	  
	  CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale

		CatzRobotMap.grabber.shootCube();  //Fires cube onto the scale
	  
	  }

	
	  public static void rightSideNoScale() {
		  
		  /***************************************************
		   * The following code:
		   * Drives to the area between the switch and scale
		   * Crosses to the other side scale
		   * Places cube on scale
		   ***************************************************/
		  
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (251.73 - CatzConstants.HALF_ROBOT_LENGTH),
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall and drives to area between right switch and right scale
	  
	  CatzPIDTurn.PIDturn(-90, CatzConstants.PID_DRIVE_TIMEOUT);
	 
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 251.73,
			  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and crosses over to the left side scale
	  
	  CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);
	  
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 84,
	  			CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives up to scale
	  
	  CatzPIDTurn.PIDturn(90, CatzConstants.PID_DRIVE_TIMEOUT);
	  
		/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
	  
	  CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (24 - CatzConstants.HALF_ROBOT_LENGTH), 
	  			CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and aligns to scale

		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale

		CatzRobotMap.grabber.shootCube();  //Fires cube onto the scale
		
	  }
	  
	public void middleRightSwitchScale() {

		/***************************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube into the switch
		 **************************************************/
		
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (66.3 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall

		CatzPIDTurn.PIDturn(60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 64,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 60deg right and drive towards the switch

		CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (36.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses against the 

		CatzRobotMap.grabber.shootCube();  //Fires the cube into the switch
		
		
		/******************************************************
		 * The following code: 
		 * Backs away from the switch
		 * Drives around the switch and intakes a second cube
		 * Turns and drives to the scale
		 * Places the cube on the scale
		 ****************************************************/

		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, (36.5 + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 65,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the side of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the back of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (47.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and presses against the cube

		CatzRobotMap.grabber.intakeCube();  //Picks up the second cube

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 8,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the switch
		
		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale

		CatzRobotMap.grabber.shootCube();  //Fires cube onto the scale

	}

	public void middleLeftSwitchScale() {
		
		/*******************************************************
		 * The following code:
		 * Drives towards the switch
		 * Shoots cube into the switch
		 *******************************************************/

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (66.3 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall

		CatzPIDTurn.PIDturn(-60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 64,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 60deg right and starts towards the switch

		CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (36 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses up against the switch

		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch

		/**************************************************
		 * The following code:
		 * Backs away from the switch
		 * Goes around the switch and picks up a cube
		 * Drives to the scale and places cube on scale
		 *************************************************/
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, (36.5 + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from switch 

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 65,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and goes to the side of the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and goes the the back of the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (47.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses up against the cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the scale

		CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale
		
		CatzRobotMap.grabber.shootCube();  //Fires cube onto scale
		
	}

	public void middleRightSwitchSwitch() {
		
		/************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube in the switch
		 ************************************/
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (66.5 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall
																														
		CatzPIDTurn.PIDturn(60, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 64.0,            
					CatzConstants.PID_TURN_TIMEOUT);  //Turns 60deg right and drives towards the switch
		
		CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (36.5 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses up against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch
		
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Turns and approaches the side of switch
		 * Places second cube in switch
		 *****************************************/
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, (36.5 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the side of switch
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to back of switch
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and presses against the cube
		
		CatzRobotMap.grabber.intakeCube();  //Intakes a second cube
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, 35, 
				CatzConstants.PID_TURN_TIMEOUT);  //Backs away from cube
		
		CatzPIDTurn.PIDturn(-45, CatzConstants.PID_DRIVE_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 45.5, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 45deg left and presses against the switch  NOT PERPENDICULAR TO THE SWITCH
		
		CatzRobotMap.grabber.shootCube();
		
	}
	
	public void middleLeftSwitchSwitch() {
		
		/************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube in the switch
		 ************************************/
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (66.5 - CatzConstants.HALF_ROBOT_LENGTH),
					CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall 
																														
		CatzPIDTurn.PIDturn(-60, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 64,            
					CatzConstants.PID_TURN_TIMEOUT);  //Turns 60deg left and approaches the switch
		
		CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (36.5 - CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg right and presses against the switch
		
		CatzRobotMap.grabber.shootCube();  //Fires cube into switch
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Turns and approaches the side of switch
		 * Places second cube in switch
		 *****************************************/
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, (36.5 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the side of switch
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right left and drives to back of switch
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses against the cube
		
		CatzRobotMap.grabber.intakeCube();  //Intakes a second cube
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, 35.0, 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from cube
		
		CatzPIDTurn.PIDturn(45, CatzConstants.PID_DRIVE_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, 45.5, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 45deg right and presses against the switch  NOT PERPENDICULAR TO SWITCH
		
		CatzRobotMap.grabber.shootCube();
		
	}
	
}
