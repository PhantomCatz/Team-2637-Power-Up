/*******************************************************
 *  Author : Jean Kwon, Tim Vu and Derek Duenas 
 *  Revision History :
 *  2-19-2018 create the class JK
 *  4-5-2018  updated autonomous paths DD
 *  Methods: rightScaleScale
 *  Functionality: paths of 2 cubes autonomous
*******************************************************/

package autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousDoublePaths {
	

	final static public double PLACE_CUBE_SCALE_SPEED = -0.8;
	
	static String gameData = DriverStation.getInstance().getGameSpecificMessage();
	
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
		CatzPIDTurn.PIDturn(-45, 0.8);  

		//Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(0.0, 69 ,3.5);  

		CatzPIDTurn.PIDturn(45, 0.8);
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig(0.65, 16, 1); //originally 14 in but added more to touch switch wall DD
												  //18 in bounced off wall AL
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube(CatzConstants.SHOOT_CUBE);  
	
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Turns to cube pyramid
		 * Picks up cube from pyramid
		 * Returns and places into switch
		 *****************************************/
		CatzPIDDrive.PIDDriveNoTrig(-.65, -16, 1);  //Backs away from the switch
	
		CatzPIDTurn.PIDturn(50, 1.2);

		CatzRobotMap.grabber.deployBicep();
		
		Timer.delay(0.5);   // temporary fix to let low pressure biceps go down in time DD 
		
		CatzRobotMap.grabber.setIntakeSpeed(CatzConstants.INTAKE_CUBE);
		
		Timer.delay(0.1);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 22, 2);  //Turns right and drive to cube pyramid
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, -28, 1);
		
		CatzRobotMap.grabber.setIntakeSpeed(0); // (whc) moved this to after back off.  was before previous line 4/5/18
		
		CatzRobotMap.grabber.retractBicep();
		
		//Timer.delay(0.6); dont need to wait for biceps because we dont shoot cube DD 4-20
		
		CatzPIDTurn.PIDturn(-50, 1.2);
		
		//auto ends before function completes
	
		//CatzPIDDrive.PIDDriveNoTrig(0.55, 30, 1.5); //originally 20 in but added more to touch switch wall DD
		
		//CatzRobotMap.grabber.placeCube(CatzConstants.SHOOT_CUBE);  //Places cube, apparently liftToSwitchHeight not needed
		
	}
	
	public static void middleDoubleCube_RXX() 
	{ 	
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 *********************************************/
		//Leaves the wall and drives to the switch
		CatzPIDDrive.PIDDriveNoTrig(0.55,  (36 - CatzConstants.HALF_ROBOT_LENGTH), 1.0);  
		
		CatzPIDTurn.PIDturn(45, 0.8);

		CatzPIDDrive.PIDDriveNoTrig(0.0, 62.0, 1.2);     //Drive to right side of switch

		CatzPIDTurn.PIDturn(-45, 0.8);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 23.0, 1.2);    //Drive up to switch - originally 19 in but added more to touch switch wall DD
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube(CatzConstants.SHOOT_CUBE);

		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Drives to cube pyramid
		 * Returns to switch to place cube
		 *****************************************/
				
		CatzPIDDrive.PIDDriveNoTrig(-0.55, -16.0, 0.9);  //Backs away from the switch
		
		CatzPIDTurn.PIDturn(-60.0, 1.1);
		
		CatzRobotMap.grabber.deployBicep();
		Timer.delay(0.5);   // temporary fix to let low pressure biceps go down in time DD 
		/***********************************************************************
         *  Turn on intake motors to ingest cube as we approach
         *  Drive to cube at base
         *  Turn off intake motors before we back away
         *  Drive away from cube pyramid & raise bicep
         **********************************************************************/
		CatzRobotMap.grabber.setIntakeSpeed(CatzConstants.INTAKE_CUBE);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 35.0, 1);  //Drive to cube pyramid

		Timer.delay(0.2); 
		
		//Wait 200 msec to try and ingest cube
		CatzRobotMap.grabber.setIntakeSpeed(0.0);
	
		CatzPIDDrive.PIDDriveNoTrig(0.0, -26.0, 1.2);  //Backup away from cube pyramid
		
		CatzRobotMap.grabber.retractBicep(0.2);  // (whc) 4/5/18 see following comment
		//Timer.delay(0.1); (whc) 4/5/18 using version of retractBicep that passes delay time as argument
		/***********************************************************************
         *  Turn to face switch
         *  Drive to switch base & place cube in switch
         **********************************************************************/
		Timer.delay(0.6);
		
		CatzPIDTurn.PIDturn(60, 0.7);
		
		CatzPIDDrive.PIDDriveNoTrig(0.55, 24, 1.2);  //CHECK DISTANCE   originally 16 in but changed to touch switch wall DD
		
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
	
		CatzPIDDrive.PIDDriveNoTrig(0.0, 210, 2.65);

		CatzRobotMap.lift.liftToHeight(CatzConstants.LIFT_SCALE_HEIGHT); 
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(33, 0.8);  //turn 60deg right
		} else {
			CatzPIDTurn.PIDturn(-33, 0.8);  //turn 60deg left
		}
		
		CatzPIDDrive.PIDDriveNoTrig(0.55, 70, 1.90);   //This is going 30.709 inches and timing out WAS60 AL
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
		
		if(CatzRobotMap.lifterLimitTop.get())
		{
			CatzRobotMap.grabber.placeCube(-0.2); // if lift goes all the way to the top throw the cube with less speed DD 4-20
		}
		else
		{
			CatzRobotMap.grabber.placeCube(PLACE_CUBE_SCALE_SPEED);
		}
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		CatzPIDDrive.PIDDriveNoTrig(-0.55, -56, 1.6);
			
		if(side.equalsIgnoreCase("left"))
		{
			CatzPIDTurn.PIDturn(103, 1.7);  //was 98  7:17 4/20/18 AL
		}
		else
		{
			CatzPIDTurn.PIDturn(-103, 1.7);  //was 98  7:17 4/20/18 AL
		}
		
		CatzRobotMap.grabber.deployBicep();
		
		CatzRobotMap.grabber.setIntakeSpeed(1.0);
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 50, 1.8);  //was 39 7:17 4/20/18 AL
		
		CatzRobotMap.grabber.setIntakeSpeed(0.0);
		
		CatzRobotMap.grabber.retractBicep();
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, -34, 1.0);
		
		if(side.equalsIgnoreCase("left"))
		{
			CatzPIDTurn.PIDturn(-85, 1.4);
		}
		else
		{
			CatzPIDTurn.PIDturn(85, 1.4);
		}
		
		/*CatzRobotMap.lift.liftToHeight(CatzConstants.LIFT_SCALE_HEIGHT);
		
		CatzPIDDrive.PIDDriveNoTrig(0.55, 34, 1.2);
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
		
		CatzRobotMap.grabber.placeCube(PLACE_CUBE_SCALE_SPEED);
		
		CatzRobotMap.grabber.retractBicep();*/
		
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
				
		CatzPIDDrive.PIDDriveNoTrig(0.0, 207, 2.6); //was 210in but shortened to avoid platform TV 4/7/18
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(97, 1.2); //was 93 but increased to not hit the platform 4-6-18 DD
		} else {
			CatzPIDTurn.PIDturn(-97, 1.2); //was -93 but increased to not hit the platform 4-6-18 DD
		}
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 218, 2.6); //drive 210in (AZ) it was 217
		
		CatzRobotMap.lift.liftToHeight(CatzConstants.LIFT_SCALE_HEIGHT);  
		
		if (side.equalsIgnoreCase("left")) 
		{
			CatzPIDTurn.PIDturn(-130, 1.2); //turn 90deg left
		} 
		else 
		{													//adding 25deg to turn to scale TV 4-7-18
			CatzPIDTurn.PIDturn(130, 1.2); //turn 90deg right
		}
				
		CatzPIDDrive.PIDDriveNoTrig(0.55, 33, 1.58); //drive 64in forward to get scale (AZ dimension is 15) WAS29 AL
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
				
		if(CatzRobotMap.lifterLimitTop.get())
		{
			CatzRobotMap.grabber.placeCube(-0.2); // if lift goes all the way to the top throw the cube with less speed DD 4-20
		}
		else
		{
			CatzRobotMap.grabber.placeCube(PLACE_CUBE_SCALE_SPEED);
		}
		
		CatzRobotMap.lift.dropToGroundHeight();
	
		CatzPIDDrive.PIDDriveNoTrig(-0.55, -20, 1.1);
		
		if (side.equalsIgnoreCase("left")) {

			CatzPIDTurn.PIDturn(-115, 1.8); //turn 130 deg left
		} else {													//subtracting 25deg to adjust for increase of previous turn TV
			CatzPIDTurn.PIDturn(115, 1.8); //turn 130 deg right
		}
		
		CatzRobotMap.grabber.deployBicep();
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
				
		CatzRobotMap.grabber.setIntakeSpeed(1.0);;
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, 34, 1.8);
		
		CatzRobotMap.grabber.setIntakeSpeed(0.0);
		
		CatzRobotMap.grabber.retractBicep();
		
		
		//We do not want to attempt to place a second cube in auto
		
		/*if(gameData.charAt(0) == 'L' && side.equals("right"))
		{
			System.out.print("Shooting cube into switch");
		}
		else if(gameData.charAt(0) == 'R' && side.equals("left"))
		{
			System.out.println("Shooting cube into switch");
		}
		
		//removing placing second cube for oppo scale auton path TV 4/6/18
		
		CatzPIDDrive.PIDDriveNoTrig(0.0, -6, 0.8);
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(130, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg left
		} else {
			CatzPIDTurn.PIDturn(-130, CatzConstants.PID_TURN_TIMEOUT); //turn 162deg right
		}
		

		CatzRobotMap.lift.liftToHeight(CatzConstants.LIFT_SCALE_HEIGHT); 

		
		CatzPIDDrive.PIDDriveNoTrig(0.55, 29, 1.3);
		
		while (CatzRobotMap.lift.liftThreadRunning == true)
		{
			//Wait for thread to complete
			Timer.delay(0.005);
		}
		
		CatzRobotMap.grabber.placeCube(PLACE_CUBE_SCALE_SPEED);

		CatzRobotMap.grabber.retractBicep();	*/
	}
}
