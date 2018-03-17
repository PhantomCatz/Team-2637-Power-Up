/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of outside starting positions
*******************************************************/

package autonomous;

import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousOutsidePaths {
	
	final static public double TO_GET_ACCUE = 7.0; //to get to the cube and Scale accurately
	final static public double PICK_UP_CUBE_TO_CUBE = 45.0; //go to the scale to pick up the cube
	final static public double BACK_TO_SCALE = 45.0; //back to the scale to place the 2nd cube
	final static public double TO_SCALE = 85.25 - //Distance from switch to wall
       	                                  29.69 - //Distance from side to wall to robot
                                          34.00 - //Width of robot
                                          6.0;   //Avoid platform ramp

	public static void right_XRX () { //<5>
		
	   to_Right_Scale();             // Drive to right Scale
		
	   pick_Up_Cube_Right();        // Drive to right switch to pick up the cube
				
	   back_To_Right_Scale(); 	    // Drive back to right Scale

	}
	
	public static void left_XLX() { //<5>
		 
		to_Left_Scale();	        // Drive to left Scale
	
		pick_Up_Cube_Left(); 	    // Drive to switch to pick up the cube

		back_To_Left_Scale(); 	    // Drive back to left Scale

	}

	public static void right_XLX() { //<4> opposite side
	 
		to_Left_Scale_Oppo(); 		//drive to left scale (opposite)
		
		pick_Up_Cube_Left();		//drive to left scale to pick up the cube
		
		back_To_Left_Scale();		//drive back to scale

	}
	
	public static void left_XRX() { //Left RRR <4> opposite side
					

		to_Right_Scale_Oppo(); 		//drive to right scale opposite
		
		pick_Up_Cube_Right();		//drive to right scale to pick up the cube
		
		back_To_Right_Scale();      //drive back to right scale

		}

	public static void pick_Up_Cube_Right() {
		
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

		CatzPIDDrive.PIDDrive(0.5,CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH, 
				CatzConstants.PID_DRIVE_TIMEOUT); //back 45in to turn
		
		CatzPIDTurn.PIDturn(90, CatzConstants.AUTO_TURN_TIMEOUT_90_DEG); 
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.TO_GET_ACCUE, 
				CatzConstants.PID_DRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.AUTO_TURN_TIMEOUT_90_DEG);
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzConstants.INTAKE_SPEED);

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in to go to the cube
		
		CatzRobotMap.grabber.intakeCube(); //intakes the cube
	}
	
	public static void pick_Up_Cube_Left() {
		
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
		
		CatzPIDDrive.PIDDrive(0.5,CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH, 
				CatzConstants.PID_DRIVE_TIMEOUT); //back 45in to turn

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right


		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.TO_GET_ACCUE, 

				CatzConstants.PID_DRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzRobotMap.lift.dropToGroundHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in to go to the cube
  
		CatzRobotMap.grabber.intakeCube(); //intakes the cube
		
	}
	
	public static void back_To_Right_Scale() {
		
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
		
		CatzPIDDrive.PIDDrive(0.5, -CatzAutonomousOutsidePaths.BACK_TO_SCALE+CatzConstants.HALF_ROBOT_LENGTH,
				              CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in back to go to the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.TO_GET_ACCUE,
				               CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); // turn 90deg left

		CatzRobotMap.lift.liftToScaleHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.BACK_TO_SCALE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in to get to the scale


		CatzRobotMap.grabber.shootCube();
	}
	
	public static void back_To_Left_Scale() {

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
		
		CatzPIDDrive.PIDDrive(0.5, -CatzAutonomousOutsidePaths.BACK_TO_SCALE+CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in back to go to the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.TO_GET_ACCUE,

				CatzConstants.PID_DRIVE_TIMEOUT); 

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); // turn 90deg right

		CatzRobotMap.lift.liftToScaleHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.BACK_TO_SCALE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT); // drive 45in to get to the scale

		CatzRobotMap.grabber.shootCube();
	}
	
	public static void to_Right_Scale () {	    // Drive to Right Scale <3> <5>
		
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
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  //turn 90deg left 
	
		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.TO_SCALE - CatzConstants.HALF_ROBOT_LENGTH, 
				             CatzConstants.PID_DRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzRobotMap.lift.liftToScaleHeight();

		CatzPIDDrive.PIDDrive(0.5, 38 - CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		CatzRobotMap.grabber.shootCube(); //out take the cube in the scale 
	}
	
	public static void to_Left_Scale() { 	    // Drive to left Scale <3> <5>
		
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
		*  Backwall Left
		**********************************************************************/
		

		CatzPIDDrive.PIDDrive(0.5, 261 - CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  //turn 90deg right
	
		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.TO_SCALE - CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzRobotMap.lift.liftToScaleHeight();
	
		CatzPIDDrive.PIDDrive(0.5, 38 - CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); 
		
	}
	
	public static void to_Right_Scale_Oppo() { //drive to right scale  <4> <6> opposite side start at left 
		
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
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		
		CatzPIDDrive.PIDDrive(0.5, 217-CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); //drive 217in forward to appro to Scale
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left to face the Sclae
		
		CatzRobotMap.lift.liftToScaleHeight();
		
		CatzPIDDrive.PIDDrive(0.5, 64-CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); //drive 64in forward to get scale 
		
		CatzRobotMap.grabber.shootCube(); //outtake the cube in the left scale
		
	}
	
	public static void to_Left_Scale_Oppo() { // drive to left scale <4> <6> opposite side start at right 
		
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
				
		CatzPIDDrive.PIDDrive(0.5,222-CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); //drive forward 222in
				
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
				
		CatzPIDDrive.PIDDrive(0.5, 217-CatzConstants.HALF_ROBOT_LENGTH, CatzConstants.PID_DRIVE_TIMEOUT); //drive 217in forward to appro to Scale
				
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right to face the Scale
				
		CatzRobotMap.lift.liftToScaleHeight();
				
		CatzPIDDrive.PIDDrive(0.5, 64-CatzConstants.HALF_ROBOT_LENGTH,
						CatzConstants.PID_DRIVE_TIMEOUT); //drive 64in forward to get scale 
				
		CatzRobotMap.grabber.shootCube(); //outtake the cube in the left scale
	
	}
}