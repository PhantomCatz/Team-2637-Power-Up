/*******************************************************
 *  Author : Jean Kwon and Tim Vu
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of 2 cubes autonomous
*******************************************************/

package autonomous;

import edu.wpi.first.wpilibj.Timer;
import mechanisms.CatzLift;
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
		CatzPIDDrive.PIDDriveNoTrig(0.0,(31 - CatzConstants.HALF_ROBOT_LENGTH), 1.2);  
		
		// AL timeout was 1
		CatzPIDTurn.PIDturn(-45, 1.1);  

		//Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(0.0, 69.2,3.5);  

		CatzPIDTurn.PIDturn(45, 1.1);
			
		//not needed to get cube into switch
		CatzRobotMap.lift.liftToSwitchHeight(); 
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig(0.0, 17, 1); //AL was 22.5in
		CatzPIDDrive.PIDDriveNoTrig(0.0, 5.5, .7);
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube();  
	
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Turns to cube pyramid
		 * Picks up cube from pyramid
		 * Returns and places into switch
		 *****************************************/
		
		CatzPIDDrive.PIDDriveNoTrig(-.65, (CatzConstants.HALF_ROBOT_LENGTH/2), 
				CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(45, 1.5);
	
		CatzRobotMap.grabber.openForearm();
	
		CatzPIDDrive.PIDDriveNoTrig(0.0, 46.5 - CatzConstants.HALF_ROBOT_LENGTH, 2);  //Turns left and drive to cube pyramid
	
		CatzRobotMap.grabber.intakeCube();  //Intakes the first cube in the pyramid
	
		CatzRobotMap.grabber.closeForearm();
	
		CatzRobotMap.grabber.intakeCube();
	
		CatzPIDDrive.PIDDriveNoTrig(-.65, (46.5 + CatzConstants.HALF_ROBOT_LENGTH), 
			CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch

		CatzPIDTurn.PIDturn(-45, 1.5);
	
		CatzPIDDrive.PIDDriveNoTrig(0.0, CatzConstants.HALF_ROBOT_LENGTH/2, 1.5);  //Drives up to the switch
	
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
		CatzPIDDrive.PIDDriveNoTrig(0.0,  (36 - CatzConstants.HALF_ROBOT_LENGTH), 1.5);  
		
		CatzPIDTurn.PIDturn(45, 1.1);

		 //Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(0.0, 60.0, 1.5); //AL was 65

		CatzPIDTurn.PIDturn(-45, 1.0);
			
		//not needed to get cube into switch
		//CatzRobotMap.lift.liftToSwitchHeight();  
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig(0.0, (12.5), 1.5);  // AL was 45
		CatzPIDDrive.PIDDriveNoTrig(0.0, 5, .6);     //timeout was 1.5
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube();  

		
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Drives to cube pyramid
		 * Returns to switch to place cube
		 *****************************************/
				
		CatzPIDDrive.PIDDriveNoTrig(-.65, (CatzConstants.HALF_ROBOT_LENGTH/2), 
					CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
		
		CatzPIDTurn.PIDturn(-45, 1.5);
		
		CatzRobotMap.grabber.openForearm();
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 46.5 - CatzConstants.HALF_ROBOT_LENGTH, 2);  //Turns left and drive to cube pyramid
	
		CatzRobotMap.grabber.intakeCube();  //Intakes the first cube in the pyramid
		
		CatzRobotMap.grabber.closeForearm();
		
		CatzRobotMap.grabber.intakeCube();
		
		CatzPIDDrive.PIDDriveNoTrig(-.65, (46.5 + CatzConstants.HALF_ROBOT_LENGTH), 
				CatzConstants.PID_TURN_TIMEOUT);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(45, 1.5);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, CatzConstants.HALF_ROBOT_LENGTH/2, 1.5);  //Drives up to the switch
		
		CatzRobotMap.grabber.placeCube();  //PLaces cube, apparently liftToSwitchHeight not needed
		
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
	
		CatzPIDDrive.PIDDriveNoTrig(0.0, 204 - CatzConstants.HALF_ROBOT_LENGTH, 2.6); 
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(30, 0.8);  //turn 30deg right
		} else {
			CatzPIDTurn.PIDturn(-30, 0.7);  //turn 30deg left
		}
		
		//CatzRobotMap.lift.liftToScaleHeight();  
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 48, 1.2); //TODO check the distance
		

		
		CatzRobotMap.grabber.placeCube(); //place the lst cube into the scale
		
		////////////////////////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(-0.5, 22 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);
		
	//	CatzRobotMap.lift.dropToGroundHeight();
		


		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(115, 1.5);  //turn 120deg right
		} else {
			CatzPIDTurn.PIDturn(-11, 1.5);  //turn 120deg left
		}
		
		
		//CatzRobotMap.grabber.openForearm(); //TODO test the new intake
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 68 - CatzConstants.HALF_ROBOT_LENGTH, 2.0);
		
	//	CatzRobotMap.grabber.closeForearm(); //TODO test the new intake 
		
		CatzRobotMap.grabber.intakeCube();
		
		//////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(-0.5, 68 - CatzConstants.HALF_ROBOT_LENGTH, 3);
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-120, 1.5);  //turn 120deg left
		} else {
			CatzPIDTurn.PIDturn(120, 1.5);  //turn 120deg right
		}
		
	//	CatzRobotMap.lift.liftToScaleHeight(); 
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 37 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);
		
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
				
		CatzPIDDrive.PIDDriveNoTrig(0.0,180-CatzConstants.HALF_ROBOT_LENGTH, 10); //drive forward 180(AZ) it was 222
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		} else {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		}
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 210, CatzConstants.PID_DRIVE_TIMEOUT); //drive 210in (AZ) it was 217
				
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		} else {
			CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		}
		
	//	CatzRobotMap.lift.liftToScaleHeight();  
				
		CatzPIDDrive.PIDDriveNoTrig(0.0, 64-CatzConstants.HALF_ROBOT_LENGTH,
						CatzConstants.PID_DRIVE_TIMEOUT); //drive 64in forward to get scale (AZ dimension is 15)
		
		t.start();
		while(CatzLift.threadComplete==false&&t.get()<5) {
			//do nothing until lifter has reached target position
		}
		t.stop();
		t.reset();
				
	//	CatzRobotMap.grabber.placeCube(); //place the 1st cube into the Scale
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(-0.5, 30 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);
		
	//	CatzRobotMap.lift.dropToGroundHeight();
	
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg left
		} else {
			CatzPIDTurn.PIDturn(162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg right
		}
		
	//	CatzRobotMap.grabber.openForearm(); //TODO test the new intake
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 63 - CatzConstants.HALF_ROBOT_LENGTH, 8);
		
		t.start();
		while(CatzLift.threadComplete==false&&t.get()<5) {
			//do nothing until lifter has reached target position
		}
		t.stop();
		t.reset();
		
	//	CatzRobotMap.grabber.closeForearm(); //TODO test the new intake
		
	//	CatzRobotMap.grabber.intakeCube();
		
		////////////////////////////////////////////////////////////////////////////////////
		
		CatzPIDDrive.PIDDriveNoTrig(-0.5, 63 - CatzConstants.HALF_ROBOT_LENGTH, 4);
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(-162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg left
		} else {
			CatzPIDTurn.PIDturn(162, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg right
		}
		
	//	CatzRobotMap.lift.liftToScaleHeight(); 
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 30 - CatzConstants.HALF_ROBOT_LENGTH, 1.5);
		
		t.start();
		while(CatzLift.threadComplete==false&&t.get()<CatzConstants.LIFT_TO_SCALE_TIMEOUT) {
			//do nothing until lifter has reached target position
		}
		t.stop();
		t.reset();
		
	//	CatzRobotMap.grabber.placeCube();
	
	}
}
