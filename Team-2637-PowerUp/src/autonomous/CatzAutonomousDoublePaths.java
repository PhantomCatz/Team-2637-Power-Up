/*******************************************************
 *  Author : Jean Kwon and Tim Vu
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of outside starting positions
*******************************************************/

package autonomous;

import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousDoublePaths {

	final static public double TO_GET_ACCUE = 7.0; //to get to the cube and Scale accurately 
											  //AL how did you get this value?
	final static public double PICK_UP_CUBE_TO_CUBE = 45.0; //go to the scale to pick up the cube
	final static public double BACK_TO_SCALE = 45.0; //back to the scale to place the 2nd cube
	final static public double TO_SCALE = 85.25 - //Distance from switch to wall
       	                                  29.69 - //Distance from side to wall to robot
                                          34.00 - //Width of robot
                                          6.0;   //Avoid platform ramp

	public static void rightDoubleCube_XRX () { //<5>
		
	   toScale("right");             // Drive to right Scale
		
	   pickUpCube("right");        // Drive to right switch to pick up the cube
				
	   backToScale("right"); 	    // Drive back to right Scale

	}
	
	public static void leftDoubleCube_XLX() { //<5>
		 
		toScale("left");	        // Drive to left Scale
	
		pickUpCube("left"); 	    // Drive to switch to pick up the cube

		backToScale("left"); 	    // Drive back to left Scale

	}

	public static void middleDoubleCube_LLX() {
		
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
	
	public static void middleDoubleCube_LRX() { 
		
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
	
	public static void middleDoubleCube_RLX() { 
		
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
	
	public static void middleDoubleCube_RRX() {

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

	public static void rightDoubleCube_XLX() { //<4> opposite side
	 
		toOppoScale("left"); 		//drive to left scale (opposite)
		
		pickUpCube("left");		//drive to left scale to pick up the cube
		
		backToScale("left");		//drive back to scale

	}
	
	public static void leftDoubleCube_XRX() { //Left RRR <4> opposite side

		toOppoScale("right"); 		//drive to right scale opposite
		
		pickUpCube("right");		//drive to right scale to pick up the cube
		
		backToScale("right");      //drive back to right scale

		}

	public static void backToScale(String side) {

		/**********************************************************************
		*  
		*  Left SCALE Drive to back to Left Scale
		*   +
		*   | 45inches
		*   |     
		*   | 7in 
		*   +---+ 
		*       |
		*       |  45inches
		*       |
		*       + 
		* Left Switch 
		**********************************************************************/
		
		/**********************************************************************
		*  
		*  Right SCALE Drive back to right Scale
		*      +
		*      | 
		*      |  45inches
		*  7in |   
		*   +--+ 
		*   |
		*   |  45inches
		*   |
		*   + 
		* Right Switch 
		**********************************************************************/
		
		CatzPIDDrive.PIDDrive(0.5, -CatzAutonomousDoublePaths.BACK_TO_SCALE+CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in back to go to the switch
	
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left		
		}
		
		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousDoublePaths.TO_GET_ACCUE,

				CatzConstants.PID_DRIVE_TIMEOUT); 
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); // turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); // turn 90deg left
		}
		
		CatzRobotMap.lift.liftToScaleHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousDoublePaths.BACK_TO_SCALE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in to get to the scale

		CatzRobotMap.grabber.shootCube();
	}

	public static void toScale(String side) { 	    // Drive to left Scale <3> <5>
		
		/**********************************************************************
		*  
		*  SCALE Drive to left Switch 
		*          +
		*          |  38inches
		* (TO_SCLE)|
		*  15.56in |   
		*   +------+
		*   |
		*   |  261 inches
		*   |
		*   + 
		*  Back wall Left
		**********************************************************************/
	
	   /**********************************************************************
	    *  
	    *  Right SCALE Drive to right Switch 
	    *  +
	    *  |  38 inches
	    *  |
	    *  |   15.56inches (TO_SCALE)
	    *  +----+
	    *       |
	    *       |  261inches
	    *       |
     	*       + 
	    *       Back wall Right
	    **********************************************************************/

		CatzPIDDrive.PIDDrive(0.5, 261 - CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  //turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  //turn 90deg left

		}
		
		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousDoublePaths.TO_SCALE - CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		} else {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		}
		
		CatzRobotMap.lift.liftToScaleHeight();
	
		CatzPIDDrive.PIDDrive(0.5, 38 - CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); 
		
	}	
	
	public static void pickUpCube(String side) {
		
		/**********************************************************************
		*  
		*  Left SCALE Drive to Left Cube
		*   +
		*   | 45inches
		*   |     
		*   | 7in 
		*   +---+ 
		*       |
		*       |  45inches
		*       |
		*       + 
		* Left Switch Cube
		**********************************************************************/
		
		/**********************************************************************
		*  
		*  Right SCALE Drive to Right Cube
		*      +
		*      | 
		*      |     45inches
		*  7in |   
		*   +--+ 
		*   |
		*   |  45inches
		*   |
		*   + 
		* Right Switch Cube
		**********************************************************************/
		
		CatzPIDDrive.PIDDrive(0.5,CatzAutonomousDoublePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH, 
				CatzConstants.PID_DRIVE_TIMEOUT); //back 45in to turn
	
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		}

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousDoublePaths.TO_GET_ACCUE, 

				CatzConstants.PID_DRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
	
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		}
		
		CatzRobotMap.lift.dropToGroundHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousDoublePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in to go to the cube
  
		CatzRobotMap.grabber.intakeCube(); //intakes the cube
		
	}
	
	public static void toOppoScale(String side) { // drive to left scale <4> <6> opposite side start at right 
		
		/**********************************************************************
		*  
		*  SCALE Drive to left scale opposite side 
		*   +
		*   |  64inches
		*   |
		*   |   217 inches
		*   +----------+
		*              |
		*              |  222inches
		*              |
		*              + 
		*          Backwall Right
		**********************************************************************/
		
		/**********************************************************************
		*  
		*  SCALE Drive to Right Scale opposite side
		*                  +
		*                  |  64inches
		*                  |
		*       217incehs  |   
		*   +--------------+
		*   |
		*   |  222 inches
		*   |
		*   + 
		*  Backwall Left
		**********************************************************************/
				
		CatzPIDDrive.PIDDrive(0.5,222-CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); //drive forward 222in
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		} else {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		}
		
		CatzPIDDrive.PIDDrive(0.5, 217-CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); //drive 217in forward to appro to Scale
				
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right to face the Scale
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left to face the Scale
		}
		
		CatzRobotMap.lift.liftToScaleHeight();
				
		CatzPIDDrive.PIDDrive(0.5, 64-CatzConstants.HALF_ROBOT_LENGTH,
						CatzConstants.PID_DRIVE_TIMEOUT); //drive 64in forward to get scale 
				
		CatzRobotMap.grabber.shootCube(); //out take the cube in the left scale
	
	}
}
