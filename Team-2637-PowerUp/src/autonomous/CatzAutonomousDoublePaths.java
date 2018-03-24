/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of 2 cubes autonomous
*******************************************************/

package autonomous;

import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousDoublePaths {

	public static void rightDoubleCube_XRX () { 
		
	   toScale("right");        
	}
	
	public static void leftDoubleCube_XLX() { 
		 
		toScale("left");	        
	}

	public static void middleDoubleCube_LLX() {
		
		/*******************************************************
		 * The following code:
		 * Drives towards the switch
		 * Shoots cube into the switch
		 *******************************************************/

		CatzPIDDrive.PIDDriveNoTrig((66.3 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall

		CatzPIDTurn.PIDturn(-60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(64,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 60deg right and starts towards the switch


		CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig((36 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses up against the switch

		CatzRobotMap.grabber.shootCube();  //Fires cube into the switch

		/**************************************************
		 * The following code:
		 * Backs away from the switch
		 * Goes around the switch and picks up a cube
		 * Drives to the scale and places cube on scale
		 *************************************************/
		
		CatzPIDDrive.PIDDriveNoTrig((36.5 + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from switch 


		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(65,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and goes to the side of the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		CatzPIDDrive.PIDDriveNoTrig(89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and goes the the back of the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig((47.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses up against the cube

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		
		CatzPIDDrive.PIDDriveNoTrig(89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the scale
	}
	
	public static void rightDoubleCube_XLX() { 
		toOppoScale("right"); 		
	}
	
	public static void leftDoubleCube_XRX() { 

		toOppoScale("left"); 	
		
	}
	
	public static void middleDoubleCube_LXX() { 
		
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 * Goes around the switch and grabs a cube
		 *********************************************/
		
		//Leaves the wall and drives to the switch
		CatzPIDDrive.PIDDriveNoTrig((31 - CatzConstants.HALF_ROBOT_LENGTH), 1.2);  
		
		// AL timeout was 1
		CatzPIDTurn.PIDturn(-45, 1.1);  

		//Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(69.2,3.5);  

		CatzPIDTurn.PIDturn(45, 1.1);
			
		//not needed to get cube into switch
		CatzRobotMap.lift.liftToSwitchHeight(); 
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig(17, 1); //AL was 22.5in
		CatzPIDDrive.PIDDriveNoTrig(5.5, .7);
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube();  
	
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Drives to opposite switch
		 * Places cube in opposite switch
		 *****************************************/
		
		CatzPIDDrive.PIDDriveNoTrig((36.5 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  
		
		CatzPIDDrive.PIDDriveNoTrig(65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the side of switch
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig(89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to back of switch
		  
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		 CatzPIDDrive.PIDDriveNoTrig(251.73,
				  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and crosses over to the right side scale
		 
		 CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		 
		 CatzPIDDrive.PIDDriveNoTrig(47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses against the cube
		
		CatzRobotMap.grabber.intakeCube();  //Intakes a second cube
		  
		  CatzPIDTurn.PIDturn(180, CatzConstants.PID_DRIVE_TIMEOUT);
		  
			/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		  
		  CatzPIDDrive.PIDDriveNoTrig((89 - CatzConstants.HALF_ROBOT_LENGTH), 
		  			CatzConstants.PID_DRIVE_TIMEOUT);  //aligns to scale

		  CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale
		CatzPIDDrive.PIDDriveNoTrig(-.5, (39.0 + CatzConstants.HALF_ROBOT_LENGTH), 
				CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(90, 1.5);
	
		CatzPIDDrive.PIDDriveNoTrig(0.5, 52.1 - CatzConstants.HALF_ROBOT_LENGTH, 2);  //Turns right and drive to cube pyramid

		CatzRobotMap.grabber.intakeCube();  //Intakes the first cube in the pyramid
	
		CatzPIDTurn.PIDturn(180, 1.5);
	
		CatzPIDDrive.PIDDriveNoTrig(0.5, 52.1 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);  //Turns 180 to the right and drives to be in front of the switch
	
		CatzPIDTurn.PIDturn(90, 1.3);
	
		CatzPIDDrive.PIDDriveNoTrig(0.5, 39 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);  //Drives up to the switch
	
		CatzRobotMap.grabber.placeCube();  //PLaces cube, apparently liftToSwitchHeight not needed
		
		}
	
	public static void middleDoubleCube_RXX() { 
		
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 * Goes around the switch and grabs a cube
		 *********************************************/
		
		//Leaves the wall and drives to the switch
		CatzPIDDrive.PIDDriveNoTrig( (36 - CatzConstants.HALF_ROBOT_LENGTH), 1.5);  
		
		CatzPIDTurn.PIDturn(45, 1.1);

		 //Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(60.0, 3.0); //AL was 65

		CatzPIDTurn.PIDturn(-45, 1.1);
			
		//not needed to get cube into switch
		CatzRobotMap.lift.liftToSwitchHeight();  
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig((12.5), 1.5);  // AL was 45
		CatzPIDDrive.PIDDriveNoTrig((7), .6);     //timeout was 1.5
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube();  

		
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Places cube in switch
		 *****************************************/
		
		CatzPIDDrive.PIDDriveNoTrig((36.5 + CatzConstants.HALF_ROBOT_LENGTH), 2); 
				
		CatzPIDDrive.PIDDriveNoTrig(-.5, (39.0 + CatzConstants.HALF_ROBOT_LENGTH), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
		
		CatzPIDTurn.PIDturn(-90, 1.5);
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 52.1 - CatzConstants.HALF_ROBOT_LENGTH, 2);  //Turns left and drive to cube pyramid
	
		CatzRobotMap.grabber.intakeCube();  //Intakes the first cube in the pyramid
		

		CatzPIDDrive.PIDDriveNoTrig(65, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg and drives to the side of switch

		CatzPIDTurn.PIDturn(-180, 1.5);

		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 52.1 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);  //Turns 180 to the left and drives to be in front of the switch
		
		CatzPIDDrive.PIDDriveNoTrig(89, 
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to back of switch
		  
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);
		
		 CatzPIDDrive.PIDDriveNoTrig(251.73,
				  	CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and crosses over to the left side scale
		 
		 CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);
		 
		 CatzPIDDrive.PIDDriveNoTrig(47.5,
					CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and presses against the cube

		CatzPIDTurn.PIDturn(-90, 1.3);
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 39 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);  //Drives up to the switch

		
		CatzRobotMap.grabber.placeCube();  //PLaces cube, apparently liftToSwitchHeight not needed
	

	
		  CatzPIDTurn.PIDturn(180, CatzConstants.PID_DRIVE_TIMEOUT);
		  
			/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */
		  
		  CatzPIDDrive.PIDDriveNoTrig((89 - CatzConstants.HALF_ROBOT_LENGTH), 
		  			CatzConstants.PID_DRIVE_TIMEOUT);  //aligns to scale

		  CatzRobotMap.lift.liftToScaleHeight();  //Lifts to the height of the scale
}
	public static void toScale(String side) { 	 
		
		/**********************************************************************
		*  drive 196in forward
		*  turn 30deg left(pos : right) right (pos : left)
		*  drive 106in to get to the scale
		*  place the lst cube in to the scale
		************************************************************************
		*  back up 37in
		*  turn 120 deg left (pos : right) right (pos : left) 
		*  drive 68in forward
		*  pick up 2nd cube
		*  *********************************************************************
		*  back up 68in
		*  turn 120 deg right (pos : right) left (pos : left)
		*  drive 37 forward 
		*  place 2nd cube in to the scale
		**********************************************************************/
	
		CatzPIDDrive.PIDDriveNoTrig(0.5, 196 - CatzConstants.HALF_ROBOT_LENGTH, 8); 
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);  //turn 60deg right
		} else {
			CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);  //turn 60deg left
		}
		
		CatzPIDDrive.PIDDriveNoTrig((66.3 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Leaves the wall

		CatzPIDTurn.PIDturn(60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(64,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 60deg right and drive towards the switch

		CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDriveNoTrig((36.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 30deg left and presses against the 

		CatzRobotMap.grabber.shootCube();  //Fires the cube into the switch
		CatzRobotMap.lift.liftToScaleHeight();
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 106 - CatzConstants.HALF_ROBOT_LENGTH, 5); 
		
		CatzRobotMap.grabber.placeCube(); //place the lst cube into the scale
		
		/******************************************************
		 * The following code: 
		 * Backs away from the switch
		 * Drives around the switch and intakes a second cube
		 * Turns and drives to the scale
		 * Places the cube on the scale
		 ****************************************************/

		CatzPIDDrive.PIDDriveNoTrig((36.5 + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Backs away from the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(65,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the side of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig(89,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and drives to the back of the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDriveNoTrig((47.5 - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and presses against the cube

		CatzRobotMap.grabber.intakeCube();  //Picks up the second cube

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);
		
		/* CatzRobotMap.lift.liftToScaleHeight();  //Lifts to height of the scale while driving? */

		CatzPIDDrive.PIDDriveNoTrig(8,
				CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg right and drives to the switch
		////////////////////////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, -37.44 + CatzConstants.HALF_ROBOT_LENGTH, 1.5);
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(120, CatzConstants.PID_TURN_TIMEOUT);  //turn 120deg right
		} else {
			CatzPIDTurn.PIDturn(-120, CatzConstants.PID_TURN_TIMEOUT);  //turn 120deg left
		}
		
		CatzRobotMap.grabber.openForearm(); //TODO test the new intake
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 68 - CatzConstants.HALF_ROBOT_LENGTH, 3.5);
		
		CatzRobotMap.grabber.closeForearm(); //TODO test the new intake 
		
		//////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, -68 + CatzConstants.HALF_ROBOT_LENGTH, 3);
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-120, CatzConstants.PID_TURN_TIMEOUT);  //turn 120deg left
		} else {
			CatzPIDTurn.PIDturn(120, CatzConstants.PID_TURN_TIMEOUT);  //turn 120deg right
		}
		
		CatzRobotMap.lift.liftToScaleHeight();
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 37 - CatzConstants.HALF_ROBOT_LENGTH, 2);
		
		CatzRobotMap.grabber.placeCube(); //place 2nd cube in to the Scale
	
	}	
	
	public static void toOppoScale(String side) { // drive to left scale <4> <6> opposite side start at right 
		
		/**********************************************************************
		*  drive 180in forward //this is AZ dimension
		*  turn 90deg right (pos : left) left (pos : right)
		*  drive 210 forward  //this is AZ dimension
		*  turn 90deg left (pos : left) right (pos : right) 
		*  drive 64in forward
		*  Place the lst cube
		************************************************************************
		*  back up 30in
		*  turn 162deg left (pos : left) right (pos : right)
		*  drive 63in forward
		*  pick up the 2nd cube
		*************************************************************************
		*  back up 63in
		*  turn 162deg left (pos : left) right (pos : right)
		*  drive 30in forward
		*  palce the 2nd cube in to the scale
		**********************************************************************/
				
		CatzPIDDrive.PIDDriveNoTrig(0.5,180-CatzConstants.HALF_ROBOT_LENGTH, 10); //drive forward 180(AZ dimension)
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		}
		
		CatzPIDDrive.PIDDrive(0.5, 210, CatzConstants.PID_DRIVE_TIMEOUT); //drive 210in (AZ dimension)
				
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		} else {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		}
		
		CatzRobotMap.lift.liftToScaleHeight();
				
		CatzPIDDrive.PIDDrive(0.5, 64-CatzConstants.HALF_ROBOT_LENGTH,
						CatzConstants.PID_DRIVE_TIMEOUT); //drive 64in forward to get scale (AZ dimension is 15 but it doesn't make sense!)
				
		CatzRobotMap.grabber.placeCube(); //place the 1st cube into the Scale
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, -30 + CatzConstants.HALF_ROBOT_LENGTH, 1.5);
	
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg left
		} else {
			CatzPIDTurn.PIDturn(162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg right
		}
		
		CatzRobotMap.grabber.openForearm(); //TODO test the new intake
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 63 - CatzConstants.HALF_ROBOT_LENGTH, 8);
		
		CatzRobotMap.grabber.closeForearm(); //TODO test the new intake
		
		////////////////////////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, -63 + CatzConstants.HALF_ROBOT_LENGTH, 4);
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg left
		} else {
			CatzPIDTurn.PIDturn(162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg right
		}
		
		CatzRobotMap.lift.liftToScaleHeight();
		
		CatzPIDDrive.PIDDriveNoTrig(0.5, 30 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);
		
		CatzRobotMap.grabber.placeCube();
	
	}
}
