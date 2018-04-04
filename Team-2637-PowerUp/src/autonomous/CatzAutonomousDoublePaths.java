/*******************************************************
 *  Author : Jean Kwon and Tim Vu
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of 2 cubes autonomous
*******************************************************/

package autonomous;

import edu.wpi.first.wpilibj.Timer;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousDoublePaths {

	static Timer t = new Timer();
	
	public static void rightDoubleCube_XRX () { 
		
	   toScale("right");        
	}
	
	public static void leftDoubleCube_XLX() { 
		 
		toScale("left");	        
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
		CatzPIDDrive.PIDDriveNoTrig(0.65, (36 - CatzConstants.HALF_ROBOT_LENGTH), 1.2);  
		
		// AL timeout was 1
		CatzPIDTurn.PIDturn(-45, 1.1);  

		//Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(0.0, 69 ,3.5);  

		CatzPIDTurn.PIDturn(45, 1.1);
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig(0.65, 14, 1); //AL was 22.5in	
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube(-1.0);  
	
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Turns to cube pyramid
		 * Picks up cube from pyramid
		 * Returns and places into switch
		 *****************************************/
		CatzPIDDrive.PIDDriveNoTrig(-.65, -12, 1);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(50, 1.2);

		CatzRobotMap.grabber.deployBicep();
	
		CatzRobotMap.grabber.setIntakeSpeed(1.0);
		
		Timer.delay(0.1);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 22, 2);  //Turns right and drive to cube pyramid
	
		CatzRobotMap.grabber.setIntakeSpeed(0);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, -28, 1);
		
		CatzRobotMap.grabber.retractBicep();
		
		CatzPIDTurn.PIDturn(-50, 1.5);
	
		CatzPIDDrive.PIDDriveNoTrig(0.55, 17, 0.8);
		
		CatzRobotMap.grabber.placeCube(-1.0);  //Places cube, apparently liftToSwitchHeight not needed
		
		}
	
	public static void middleDoubleCube_RXX() { 
		
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 *********************************************/
		//Leaves the wall and drives to the switch
		CatzPIDDrive.PIDDriveNoTrig(0.65,  (36 - CatzConstants.HALF_ROBOT_LENGTH), 1.0);  
		
		CatzPIDTurn.PIDturn(45, 1);

		CatzPIDDrive.PIDDriveNoTrig(0.0, 62.0, 1.6);     //Drive to right side of switch

		CatzPIDTurn.PIDturn(-45, 1);
			
		//CatzRobotMap.lift.liftToSwitchHeight();  		//not needed to get cube into switch
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 24.0, 0.8);    //Drive up to switch - CHECK DISTANCE
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube(-1.0);

		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Drives to cube pyramid
		 * Returns to switch to place cube
		 *****************************************/
				
		CatzPIDDrive.PIDDriveNoTrig(-0.65, -12.0, 1);  //Backs away from the switch
		
		CatzPIDTurn.PIDturn(-60.0, 1.1);
		
		CatzRobotMap.grabber.deployBicep();
		Timer.delay(0.1);
		/***********************************************************************
         *  Turn on intake motors to ingest cube as we approach
         *  Drive to cube at base
         *  Turn off intake motors before we back away
         *  Drive away from cube pyramid & raise bicep
         **********************************************************************/
		CatzRobotMap.grabber.setIntakeSpeed(CatzConstants.INTAKE_SPEED);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 35.0, 1);  //Drive to cube pyramid

		Timer.delay(0.2);                             //Wait 100 msec to try and ingest cube
		CatzRobotMap.grabber.setIntakeSpeed(0.0);
	
		CatzPIDDrive.PIDDriveNoTrig(0.0, -22.0, 1.2);  //Backup away from cube pyramid
		
		CatzRobotMap.grabber.retractBicep();
		Timer.delay(0.1);
		/***********************************************************************
         *  Turn to face switch
         *  Drive to switch base & place cube in switch
         **********************************************************************/
		CatzPIDTurn.PIDturn(60, 0.7);
		
		CatzPIDDrive.PIDDriveNoTrig(0.55, 10, 0.8);  //CHECK DISTANCE
		
		CatzRobotMap.grabber.placeCube(-1.0);           //PLaces cube, apparently liftToSwitchHeight not needed
		
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
	
		CatzPIDDrive.PIDDriveNoTrig(0.0, 210, 3.0);

		CatzRobotMap.lift.liftToHeight(65.0); 
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(30, CatzConstants.PID_TURN_TIMEOUT);  //turn 60deg right
		} else {
			CatzPIDTurn.PIDturn(-30, CatzConstants.PID_TURN_TIMEOUT);  //turn 60deg left
		}
		
		CatzPIDDrive.PIDDriveNoTrig(0.55, 57, 1.5);
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
		
		CatzRobotMap.grabber.placeCube(-0.8);
		
		CatzRobotMap.grabber.retractBicep();
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		CatzPIDDrive.PIDDriveNoTrig(-0.55, -53, 1.5);
			
		if(side.equalsIgnoreCase("left"))
		{
			CatzPIDTurn.PIDturn(105, 1.2);
		}
		else
		{
			CatzPIDTurn.PIDturn(-105, 1.2);
		}
		
		CatzRobotMap.grabber.deployBicep();
		
		CatzRobotMap.grabber.setIntakeSpeed(1.0);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 34, 1);
		
		CatzRobotMap.grabber.setIntakeSpeed(0.0);
		
		CatzRobotMap.grabber.retractBicep();
		
		CatzPIDDrive.PIDDriveNoTrig(-0.65, -34, 1);
		
		if(side.equalsIgnoreCase("left"))
		{
			CatzPIDTurn.PIDturn(-105, 1.2);
		}
		else
		{
			CatzPIDTurn.PIDturn(-105, 1.2);
		}
		
		CatzRobotMap.lift.liftToHeight(65.0);
		
		CatzPIDDrive.PIDDriveNoTrig(0.55, 36, 1.2);
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
		
		CatzRobotMap.grabber.placeCube(-0.8);
		
		CatzRobotMap.grabber.retractBicep();
		
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
				
		CatzPIDDrive.PIDDriveNoTrig(0.0, 210, 10); //drive forward 180(AZ) it was 222
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(93, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-93, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		}
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 210, CatzConstants.PID_DRIVE_TIMEOUT); //drive 210in (AZ) it was 217
		
		CatzRobotMap.lift.liftToHeight(65.0);  
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-125, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		} else {
			CatzPIDTurn.PIDturn(125, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		}
				
		CatzPIDDrive.PIDDriveNoTrig(0.55, 29, CatzConstants.PID_DRIVE_TIMEOUT); //drive 64in forward to get scale (AZ dimension is 15)
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
				
		CatzRobotMap.grabber.placeCube(-0.9); //place the 1st cube into the Scale
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		
	
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg left
		} else {
			CatzPIDTurn.PIDturn(162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg right
		}
		
		
		CatzPIDDrive.PIDDriveNoTrig(0.6, 63 - CatzConstants.HALF_ROBOT_LENGTH, 8);
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
				
		CatzRobotMap.grabber.setIntakeSpeed(1.0);;
		
		////////////////////////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(-0.6, 63 - CatzConstants.HALF_ROBOT_LENGTH, 4);
		
		CatzRobotMap.grabber.setIntakeSpeed(0.0);
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg left
		} else {
			CatzPIDTurn.PIDturn(162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg right
		}
		
		CatzRobotMap.lift.liftToHeight(68.0); 
		
		CatzPIDDrive.PIDDriveNoTrig(0.6, 30 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
		
		CatzRobotMap.grabber.placeCube(0.9);
		 
	}
}
