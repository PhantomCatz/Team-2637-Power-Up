/**********************************************************
 * Timothy Vu 3 Feb 2018 TV 
 * Adding in Left and Right paths 
 * Methods: middlePathL middlePathR leftPath rightPath 
 * Functionality: Paths for the autonomous period
 *********************************************************/
package autonomous;

import edu.wpi.first.wpilibj.Timer;
import mechanisms.CatzLift;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousSinglePaths {
	
	static Timer t = new Timer();
	
	public static void leftSingleCube_XRX() {  
		  oppoScalePath("left");
	  }
	
	public static void leftSingleCube_XLX() {
		scalePath("left");
	}
	
	public static void middleSingleCube_RXX() {
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 * Goes around the switch and grabs a cube
		 *********************************************/
		
		//Leaves the wall and drives to the switch
		CatzPIDDrive.PIDDriveNoTrig(.75, (36 - CatzConstants.HALF_ROBOT_LENGTH), 1.5);  
		
		CatzPIDTurn.PIDturn(45, 1.1);

		 //Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(.7, 60.0, 3.0); //AL was 65

		CatzPIDTurn.PIDturn(-45, 1.1);
			
		//not needed to get cube into switch
		CatzRobotMap.lift.liftToSwitchHeight();  
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig(.75, (12.5), 1.5);  // AL was 45
		CatzPIDDrive.PIDDriveNoTrig(.45, (7), .6);     //timeout was 1.5
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube();  

	}
	
	public static void middleSingleCube_LXX() {
		
		/**********************************************
		 * The following code:
		 * Drives to the switch
		 * Places a cube in the switch
		 * Goes around the switch and grabs a cube
		 *********************************************/
		
		//Leaves the wall and drives to the switch
		CatzPIDDrive.PIDDriveNoTrig(.7, (31 - CatzConstants.HALF_ROBOT_LENGTH), 1.2);  
		
		// AL timeout was 1
		CatzPIDTurn.PIDturn(-45, 1.1);  

		//Turns 45deg left and approaches the switch
		CatzPIDDrive.PIDDriveNoTrig(.6, 69.2,3.5);  

		CatzPIDTurn.PIDturn(45, 1.1);
			
		//not needed to get cube into switch
		CatzRobotMap.lift.liftToSwitchHeight(); 
		
		//Turns 45deg right and presses against the switch
		CatzPIDDrive.PIDDriveNoTrig(.75, 17, 1); //AL was 22.5in
		CatzPIDDrive.PIDDriveNoTrig(.45, 5.5, .7);
		
		//Fires cube into the switch
		CatzRobotMap.grabber.placeCube();  
	}
	
	public static void rightSingleCube_XRX() {
		scalePath("right");    
	}
	
	 public static void rightSingleCube_XLX() {		  
		  oppoScalePath("right");
	 }
	
	public static void scalePath (String side) {
		
		/************************************
		 * The following code:
		 * Drives to the scale
		 * Places cube in scale
		 * Picks up cube
		 ***********************************/
			
		CatzPIDDrive.PIDDriveNoTrig(.75, 262, 20); //Drives all the way to the scale   //TV 3-9-18 subtracting 12in from distance bc we drove too far
		CatzRobotMap.lift.liftToScaleHeight(); //Lifts to height of the scale while driving
		CatzPIDDrive.PIDDriveNoTrig(.5, 20 ,20);	//TV 3-9-18 Second distance to the scale at a slower speed
		
		if (side.equalsIgnoreCase("left")) {
			CatzPIDTurn.PIDturn(90, 2.0);
		} else {
			
			CatzPIDTurn.PIDturn(-90, 2.0);
		}
		
		t.start();
		while(CatzLift.readyToLift==true&&t.get()<3) {
			//do nothing
		}
		t.stop();
		t.reset();

	
		CatzPIDDrive.PIDDriveNoTrig(.5, 15 ,5);  //AL was 18
	
		CatzRobotMap.grabber.placeCube();  //Fires cube into the scale
	
		CatzPIDDrive.PIDDriveNoTrig(-0.5, 25, CatzConstants.PID_DRIVE_TIMEOUT);
		
		Timer.delay(.5);
		CatzRobotMap.lift.dropToHalfHeight();
		
		//Timer.delay(.4);
	
	//	CatzRobotMap.lift.dropToGroundHeight();   //waits for 2sec then drops to ground height
		
	/*	if(side.equalsIgnoreCase("left")) {
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

		CatzRobotMap.grabber.intakeCube();  //Intakes the cube */

	}
	
	public static void oppoScalePath (String side) {
		  
		  /****************************************************
		   * The following code:
		   * Drives to the area between the scale and switch
		   * Crosses over to other side scale
		   * Places cube in scale
		   ***************************************************/
		  
	   CatzPIDDrive.PIDDriveNoTrig(.7, 180,7);  //Drives to area between switch and scale
	
	   if (side.equalsIgnoreCase("left")) {
		   CatzPIDTurn.PIDturn(90, 1.2); 
	   } else {
		   CatzPIDTurn.PIDturn(-90, 1.5); 

	   }
	  
	   CatzPIDDrive.PIDDriveNoTrig(.7, 210 ,8);  //Turns 90deg and crosses to right side scale
	
	   if (side.equalsIgnoreCase("left")) {
		   CatzPIDTurn.PIDturn(-105, 1.5); 

	  } else {
		   CatzPIDTurn.PIDturn(105, 1.5); 

	  }
	  
	/*   CatzPIDDrive.PIDDriveNoTrig(.7, 88,4);  //Turns 90deg left and drives to the front of scale
	  
	   if (side.equalsIgnoreCase("left")) {
	   CatzPIDTurn.PIDturn(-90, 1.5);
	   } else {
		   CatzPIDTurn.PIDturn(90, 1.5);

	   } */
	   
	    CatzRobotMap.lift.liftToScaleHeight();   //Lifts to height of the scale while driving? */
	  
	    CatzPIDDrive.PIDDriveNoTrig(.5, 15, CatzConstants.PID_DRIVE_TIMEOUT);  //Turns 90deg left and aligns with scale
	  
	    
		t.start();
		while(CatzLift.readyToLift==true&&t.get()<5) {
			//do nothing
		}
		t.stop();
		t.reset();

		CatzPIDDrive.PIDDriveNoTrig(0.5, 6, CatzConstants.PID_DRIVE_TIMEOUT);
		
		CatzRobotMap.grabber.placeCube();  //Fires cube onto the scale
		
		CatzPIDDrive.PIDDriveNoTrig(-0.5, 25, CatzConstants.PID_DRIVE_TIMEOUT);
		
		Timer.delay(.5);
		CatzRobotMap.lift.dropToHalfHeight();
		
	  }
	  

}
