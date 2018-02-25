/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of outside starting positions
*******************************************************/

package autonomous;

import robot.CatzRobotMap;
import constants.CatzConstants;
import constants.CatzOutsidePathsDistances;

public class CatzAutonomousOutsidePaths {

	final static public double PICK_UP_CUBE_ACCUE_CUBE = 7.0; //to get to the cube accurately
	final static public double PICK_UP_CUBE_TO_CUBE = 45.0; //go to the scale to pick up the cube
	final static public double BACK_TO_SCALE = 45.0; //back to the scale to place the 2nd cube
	final static public double TO_SCALE = 85.25 - //Distance from switch to wall
       	                                  29.69 - //Distance from side to wall to robot
                                          34.00 - //Width of robot
                                          6.0;   //Avoid platform ramp

	public static void right_LRL () { //<5>
		
	    // Drive to right Scale
		to_Right_Scale();
		
	    // Drive to right switch to pick up the cube
		pick_Up_Cube_Right();
				
	    // Drive back to right Scale
		back_To_Right_Scale();

	}
	
	public static void left_RLR() { //<5>
		 
		// Drive to left Scale
		to_Left_Scale();
	
	    // Drive to switch to pick up the cube
		pick_Up_Cube_Left();
				
	    // Drive back to left Scale
		back_To_Left_Scale();
		
	}

	public static void right_LLL() { //<4> opposite side
	 
		//drive to left scale (opposite)
		to_Left_Scale_Oppo();
	
		//drive to left scale to pick up the cube
		
		pick_Up_Cube_Left();
		
		//drive back to scale
		
		back_To_Left_Scale();
		
	}
	
	public static void left_RRR() { //Left RRR <4> opposite side
					
		//drive to right scale 
		
		CatzPIDDrive.PIDDrive(0.5,CatzOutsidePathsDistances.LEFT_RRR_INIT-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 222in
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
		
		CatzPIDDrive.PIDDrive(0.5, CatzOutsidePathsDistances.LEFT_RRR_APPO_SCALE-CatzConstants.HALF_ROBOT_LENGTH, 
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 217in forward to appro to Scale
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left to face the Sclae
		
		CatzRobotMap.lift.liftToScaleHeight();
		
		CatzPIDDrive.PIDDrive(0.5, CatzOutsidePathsDistances.LEFT_RRR_GET_SCALE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 64in forward to get scale 
		
		CatzRobotMap.grabber.shootCube(); //outtake the cube in the left scale
		
		//drive to right scale to pick up the cube
		
		pick_Up_Cube_Right();

		//drive back to right scale
		
		back_To_Right_Scale();
		
		}
	
	public static void right_RLR() { // <6> opposite sides 
		
		//drive to left scale
		to_Left_Scale_Oppo();
		
		// drive to the left switch to pick up the cube 

		pick_Up_Cube_Left();
		
		//drive back to left scale

		back_To_Left_Scale();
	}
	
	public static void left_LRL() { //<6>
	
		//drive to right scale 
		
		CatzPIDDrive.PIDDrive(0.5,CatzOutsidePathsDistances.LEFT_LRL_INIT-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 222in

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzPIDDrive.PIDDrive(0.5, CatzOutsidePathsDistances.LEFT_LRL_APPO_SCALE-CatzConstants.HALF_ROBOT_LENGTH, 
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 217in forward to appro to Scale

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left to face the Scale

		CatzRobotMap.lift.liftToScaleHeight();
		
		CatzPIDDrive.PIDDrive(0.5, CatzOutsidePathsDistances.LEFT_LRL_GET_SCALE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 64in forward to get scale 

		CatzRobotMap.grabber.shootCube();
		
		//drive to right switch to pick up the cube

		pick_Up_Cube_Right();

		//drive back to right scale

		back_To_Right_Scale();
		
	}
	
	public static void right_RRR() {// <3>
		
	    // Drive to right Scale
		to_Right_Scale();
		
		// Drive to right switch to pick up the cube
		pick_Up_Cube_Right();

	    // Drive back to right Scale
		back_To_Right_Scale();
		
		}
	
	public static void left_LLL() { //<3>
		
		 // Drive to right Scale
		to_Left_Scale();
		
	    // Drive to left switch to pick up the cube
		
		pick_Up_Cube_Left();
		
	    // Drive back to left Scale
		
		back_To_Left_Scale();
		
	}
	
	public static void pick_Up_Cube_Right() {

		CatzPIDDrive.PIDDrive(0.5,CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH, 
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //back 45in to turn
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.PICK_UP_CUBE_ACCUE_CUBE, 
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); // drive 45in to go to the cube
		
		CatzRobotMap.grabber.intakeCube(); //intakes the cube
	}
	
	public static void pick_Up_Cube_Left() {
		
		CatzPIDDrive.PIDDrive(0.5,CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH, 
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //back 45in to turn

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.PICK_UP_CUBE_ACCUE_CUBE, 
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzRobotMap.lift.dropToGroundHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.PICK_UP_CUBE_TO_CUBE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); // drive 45in to go to the cube
  
		CatzRobotMap.grabber.intakeCube(); //intakes the cube
		
	}
	
	public static void back_To_Right_Scale() {
		
		CatzPIDDrive.PIDDrive(0.5, -CatzAutonomousOutsidePaths.BACK_TO_SCALE+CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); // drive 45in back to go to the switch

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left

		CatzPIDDrive.PIDDrive(0.5, CatzOutsidePathsDistances.RIGHT_LRL_ACCUE_CUBE,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); 

		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); // turn 90deg left

		CatzRobotMap.lift.liftToScaleHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.BACK_TO_SCALE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); // drive 45in to get to the scale

		CatzRobotMap.grabber.shootCube();
	}
	
	public static void back_To_Left_Scale() {
		
		CatzPIDDrive.PIDDrive(0.5, -CatzAutonomousOutsidePaths.BACK_TO_SCALE+CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); // drive 45in back to go to the switch

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzPIDDrive.PIDDrive(0.5, CatzOutsidePathsDistances.LEFT_RLR_ACCUE_CUBE,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); 

		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); // turn 90deg right

		CatzRobotMap.lift.liftToScaleHeight();

		CatzPIDDrive.PIDDrive(0.5, CatzAutonomousOutsidePaths.BACK_TO_SCALE-CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.STRAIGHTDRIVE_TIMEOUT); // drive 45in to get to the scale

		CatzRobotMap.grabber.shootCube();
	}
	
	public static void to_Right_Scale () {	    // Drive to Right Scale <3> <5>
		
		/**********************************************************************
		*  
		*  SCALE Drive to right scale
		*  +
		*  |  Segment 3: drive 38 in forward to Scale
		*  |
		*  |   Segment 2: drive 15.56 in forward to get closer to the scale
		*  +--+
		*     |
		*     |  Segment1: drive 216-19.5in forward to approach to the scale
		*     |
		*     + 
		*       Back wall Right
		**********************************************************************/
		
		// Initialize distances
		
		double distanceSegment1;
		double distanceSegment2;
		double distanceSegment3;
	
		distanceSegment1 = 261 - CatzConstants.HALF_ROBOT_LENGTH; 
		distanceSegment2 = CatzOutsidePathsDistances.TO_SCALE - CatzConstants.HALF_ROBOT_LENGTH;
		distanceSegment3 = 38 - CatzConstants.HALF_ROBOT_LENGTH;
		
		CatzPIDDrive.PIDDrive(0.5, distanceSegment1, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  //turn 90deg left 
	
		CatzPIDDrive.PIDDrive(0.5, distanceSegment2, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right

		CatzRobotMap.lift.liftToScaleHeight();

		CatzPIDDrive.PIDDrive(0.5, distanceSegment3, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
				
		CatzRobotMap.grabber.shootCube(); //out take the cube in the scale
	}
	
	public static void to_Left_Scale() { 	    // Drive to left Scale <3> <5>
		
		/**********************************************************************
		*  
		*  SCALE Drive to left scale
		*      +
		*      |  Segment 3: drive 38 in forward to Scale
		*      |
		*      |   Segment 2: drive 15.56 in forward to get closer to the scale
		*   +--+
		*   |
		*   |  Segment1: drive 216-19.5in forward to approach to the scale
		*   |
		*   + 
		*       Backwall Left
		**********************************************************************/
		// Initialize distances
		
		double distanceSegment1;
		double distanceSegment2;
		double distanceSegment3;
		
		distanceSegment1 = 261 - CatzConstants.HALF_ROBOT_LENGTH; 
		distanceSegment2 = CatzOutsidePathsDistances.TO_SCALE - CatzConstants.HALF_ROBOT_LENGTH;
		distanceSegment3 = 38 - CatzConstants.HALF_ROBOT_LENGTH;
		
		CatzPIDDrive.PIDDrive(0.5, distanceSegment1, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT);  //turn 90deg right
	
		CatzPIDDrive.PIDDrive(0.5, distanceSegment2, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzRobotMap.lift.liftToScaleHeight();
	
		CatzPIDDrive.PIDDrive(0.5, distanceSegment3, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
		
	}
	
	public static void to_Right_Scale_Oppo() {
		
	}
	
	public static void to_Left_Scale_Oppo() { // drive to left scale <4> <6> opposite side
		
		/**********************************************************************
		*  
		*  SCALE Drive to left scale
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
		
		//drive to left scale (opposite)
		
		CatzPIDDrive.PIDDrive(0.5,222-CatzConstants.HALF_ROBOT_LENGTH,
						CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 222in
				
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
				
		CatzPIDDrive.PIDDrive(0.5, 217-CatzConstants.HALF_ROBOT_LENGTH, 
						CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 217in forward to appro to Scale
				
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right to face the Scale
				
		CatzRobotMap.lift.liftToScaleHeight();
				
		CatzPIDDrive.PIDDrive(0.5, 64-CatzConstants.HALF_ROBOT_LENGTH,
						CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 64in forward to get scale 
				
		CatzRobotMap.grabber.shootCube(); //outtake the cube in the left scale
	
	}
}