/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of outside starting positions
*******************************************************/

package autonomous;

import robot.CatzRobotMap;
import constants.CatzOutsidePathsDistances;

public class CatzAutonomousOutsidePaths {

	public static void right_LRL () { //<5>
		
		/**********************************************************************
		*  
		*  SCALE
		*  +
		*  |  Segment 3: drive 64in forward to Scale
		*  |
		*  |   Segment 2: drive 16.5in forward to get closer to the scale
		*  +----+
		*       |
		*       |  Segment1: drive 216-19.5in forward to approach to the scale
		*       |
		*       + 
		*       Backwall Right
		**********************************************************************/
	
	   //write the code for lift 

		double distanceSegment1;
		double distanceSegment2;
		double distanceSegment3;
	
	    // Initialize distances
		distanceSegment1 = CatzOutsidePathsDistances.RIGHT_LRL_INIT_DIST - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment2 = CatzOutsidePathsDistances.RIGHT_LRL_LEFT_TURN - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment3 = CatzOutsidePathsDistances.RIGHT_LRL_APPRO_SCALE - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		
	    // Drive to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment1, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT);  //turn 90deg left 
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment2, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment3, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzRobotMap.grabber.openForearm(); 
		CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
	    // Drive to pick up power cube by switch
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_LRL_BACK_UP,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 180deg
		
		CatzPIDTurn.PIDturn(180, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 180deg right
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LRL_APPRO_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 20in to open the grabber
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LRL_ACCUE_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzRobotMap.grabber.deployBicep(); //down
		
		CatzRobotMap.grabber.openForearm(); //open the grabber
		
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.RIGHT_LRL_APPRO_SWITCH-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 50 forward to get to the switch
		
		CatzRobotMap.grabber.closeForearm(); //close the grabber 
				
	    // Drive back to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_LRL_BACK_UP, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 90deg
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LRL_ACCUE_CUBE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // 90deg
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LRL_APPRO_FINAL_SCLAE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 70in to get to scale
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		

	}
	
	public static void left_RLR() { //<5>
		
		double distanceSegment1;
		double distanceSegment2;
		double distanceSegment3;
	
	    // Initialize distances
		distanceSegment1 = CatzOutsidePathsDistances.LEFT_RLR_INIT_DIST - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment2 = CatzOutsidePathsDistances.LEFTT_RLR_LEFT_TURN - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment3 = CatzOutsidePathsDistances.LEFT_RLR_APPRO_SCALE - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		
	    // Drive to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment1, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT);  //turn 90deg right
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment2, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment3, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzRobotMap.grabber.openForearm(); 
		CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
	    // Drive to pick up power cube by switch
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_RLR_BACK_UP,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 180deg
		
		CatzPIDTurn.PIDturn(180, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 180deg right
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RLR_APPRO_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 20in to open the grabber
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RLR_ACCUE_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right
		
		CatzRobotMap.grabber.deployBicep(); //down
		
		CatzRobotMap.grabber.openForearm(); //open the grabber
		
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.LEFT_RLR_APPRO_SWITCH-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 50 forward to get to the switch
		
		CatzRobotMap.grabber.closeForearm(); //close the grabber 
				
	    // Drive back to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_RLR_BACK_UP, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 90deg
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RLR_ACCUE_CUBE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // 90deg left
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RLR_APPRO_FINAL_SCLAE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 70in to get to scale
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
	}
		
	public static void oppoRightScaleScale1() { //right LLL Calculated by Artie
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.OPPO_RIGHT_SCALE_SCALE_INIT_A-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 215.737in
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.OPPO_RIGHT_SCALE_SCALE_TO_OPPO-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 195.7385 
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.OPPO_RIGHT_SCALE_SCALE_APPRO_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); // drive 64.4in forward to get scale
		
		//extend the lift
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
		//lower the lift
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.OPPO_RIGHT_SCALE_SCALE_APPRO_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive back up 64.4in
		
		CatzPIDTurn.PIDturn(CatzOutsidePathsDistances.OPPO_RIGHT_SCALE_SCALE_CUBE_DEG, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 148.2deg left
		
		CatzRobotMap.grabber.deployBicep(); //down
		CatzRobotMap.grabber.openForearm(); //open the grabber
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, 5, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		//grab the cube
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -5, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(CatzOutsidePathsDistances.OPPO_RIGHT_SCALE_SCALE_CUBE_DEG, CatzOutsidePathsDistances.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.OPPO_RIGHT_SCALE_SCALE_APPRO_SCALE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); // drive 64.4in forward
		
		//extend the lift
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 
		
		}

	public static void right_LLL() { //<4>
	 
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.RIGHT_LLL_INIT-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
	                          CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 222in
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LLL_APPO_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 217in forward to appro to Scale
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right to face the Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LLL_GET_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 64in forward to get scale 
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_LLL_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to turn 
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LLL_ACCUE_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); // drive forward 7in to get to the cube accurately
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right
		
		//CatzRobotMap.grabber.deployBicep(); //down
		CatzRobotMap.grabber.openForearm(); //open the grabber
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LLL_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT);
		
		CatzRobotMap.grabber.closeForearm();
		
		//drive back to scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_LLL_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to go back to the scale
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LLL_ACCUE_CUBE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to get back to the Scale accurately
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_LLL_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 42in forward to get to the scale
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 
		
	}
	
	public static void left_RRR() { //Left RRR <4>
		
					
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.LEFT_RRR_INIT-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
                CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 222in

		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RRR_APPO_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 217in forward to appro to Scale

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right to face the Scale

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RRR_GET_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 64in forward to get scale 

		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_RRR_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to turn 

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RRR_ACCUE_CUBE, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); // drive forward 7in to get to the cube accurately

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right

		//CatzRobotMap.grabber.deployBicep(); //down
		CatzRobotMap.grabber.openForearm(); //open the grabber
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RRR_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT);

		CatzRobotMap.grabber.closeForearm();

		//drive back to scale

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_RRR_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to go back to the scale

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RRR_ACCUE_CUBE,
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to get back to the Scale accurately

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right 

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_RRR_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 42in forward to get to the scale

		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 
		
		}
	
	public static void right_RLR() { // <6>
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.RIGHT_RLR_INIT-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
                CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 222in

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RLR_APPO_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 217in forward to appro to Scale

		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right to face the Scale

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RLR_GET_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 64in forward to get scale 

		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_RLR_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to turn 

		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RLR_ACCUE_CUBE, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); // drive forward 7in to get to the cube accurately

		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right

		//CatzRobotMap.grabber.deployBicep(); //down
		CatzRobotMap.grabber.openForearm(); //open the grabber
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RLR_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT);

		CatzRobotMap.grabber.closeForearm();

		//drive back to scale

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_RLR_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to go back to the scale

		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RLR_ACCUE_CUBE,
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to get back to the Scale accurately

		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right 

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RLR_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 42in forward to get to the scale

		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 
	
	}
	
	public static void left_LRL() {
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.LEFT_LRL_INIT-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
                CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 222in

		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LRL_APPO_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 217in forward to appro to Scale

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right to face the Scale

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LRL_GET_SCALE-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH,
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 64in forward to get scale 

		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_LRL_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to turn 

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LRL_ACCUE_CUBE, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); // drive forward 7in to get to the cube accurately

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right

		//CatzRobotMap.grabber.deployBicep(); //down
		CatzRobotMap.grabber.openForearm(); //open the grabber
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LRL_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT);

		CatzRobotMap.grabber.closeForearm();

		//drive back to scale

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_LRL_FINAL+CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 45in to go back to the scale

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LRL_ACCUE_CUBE,
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to get back to the Scale accurately

		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // turn 90deg right 

		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LRL_FINAL-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
	              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 42in forward to get to the scale

		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED); 
		
	}
	
	public static void right_RRR() {
		
		 //write the code for lift 

		double distanceSegment1;
		double distanceSegment2;
		double distanceSegment3;
	
	    // Initialize distances
		distanceSegment1 = CatzOutsidePathsDistances.RIGHT_RRR_INIT_DIST - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment2 = CatzOutsidePathsDistances.RIGHT_RRR_LEFT_TURN - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment3 = CatzOutsidePathsDistances.RIGHT_RRR_APPRO_SCALE - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		
	    // Drive to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment1, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT);  //turn 90deg left 
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment2, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment3, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzRobotMap.grabber.openForearm(); 
		CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
	    // Drive to pick up power cube by switch
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_RRR_BACK_UP,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 180deg
		
		CatzPIDTurn.PIDturn(180, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 180deg right
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RRR_APPRO_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 20in to open the grabber
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RRR_ACCUE_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzRobotMap.grabber.deployBicep(); //down
		
		CatzRobotMap.grabber.openForearm(); //open the grabber
		
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.RIGHT_RRR_APPRO_SWITCH-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 50 forward to get to the switch
		
		CatzRobotMap.grabber.closeForearm(); //close the grabber 
				
	    // Drive back to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.RIGHT_RRR_BACK_UP, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 90deg
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RRR_ACCUE_CUBE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // 90deg
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.RIGHT_RRR_APPRO_FINAL_SCLAE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 70in to get to scale
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
		}
	
	
	
	public static void left_LLL() {
		
		double distanceSegment1;
		double distanceSegment2;
		double distanceSegment3;
	
	    // Initialize distances
		distanceSegment1 = CatzOutsidePathsDistances.LEFT_LLL_INIT_DIST - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment2 = CatzOutsidePathsDistances.LEFTT_LLL_LEFT_TURN - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		distanceSegment3 = CatzOutsidePathsDistances.LEFT_LLL_APPRO_SCALE - CatzOutsidePathsDistances.HALF_ROBOT_LENGTH;
		
	    // Drive to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment1, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT);  //turn 90deg right
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment2, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left
	
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, distanceSegment3, CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzRobotMap.grabber.openForearm(); 
		CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
	    // Drive to pick up power cube by switch
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_LLL_BACK_UP,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 180deg
		
		CatzPIDTurn.PIDturn(180, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 180deg right
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LLL_APPRO_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 20in to open the grabber
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LLL_ACCUE_CUBE, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); //turn 90deg right
		
		CatzRobotMap.grabber.deployBicep(); //down
		
		CatzRobotMap.grabber.openForearm(); //open the grabber
		
		CatzRobotMap.grabber.setIntakeSpeed(CatzOutsidePathsDistances.INTAKE_SPEED); //turn on the intake
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED,CatzOutsidePathsDistances.LEFT_LLL_APPRO_SWITCH-CatzOutsidePathsDistances.HALF_ROBOT_LENGTH, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive 50 forward to get to the switch
		
		CatzRobotMap.grabber.closeForearm(); //close the grabber 
				
	    // Drive back to Scale
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, -CatzOutsidePathsDistances.LEFT_LLL_BACK_UP, 
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 90deg
		
		CatzPIDTurn.PIDturn(90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); 
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LLL_ACCUE_CUBE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(-90, CatzOutsidePathsDistances.PID_TURN_TIMEOUT); // 90deg left
		
		CatzPIDDrive.PIDDrive(CatzOutsidePathsDistances.HALF_SPEED, CatzOutsidePathsDistances.LEFT_LLL_APPRO_FINAL_SCLAE,
				              CatzOutsidePathsDistances.STRAIGHTDRIVE_TIMEOUT); //drive forward 70in to get to scale
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzOutsidePathsDistances.INTAKE_SPEED);
		
		
		
	}
}