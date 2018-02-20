/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of outside starting positions
*******************************************************/

package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

public class CatzAutonomousOutsidePaths {

	public static void rightScaleScale () {
		
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
		distanceSegment1 = CatzConstants.RIGHT_SCALE_SCALE_INIT_DIST - CatzConstants.HALF_ROBOT_LENGTH;
		distanceSegment2 = CatzConstants.RIGHT_SCALE_SCALE_LEFT_TURN - CatzConstants.HALF_ROBOT_LENGTH;
		distanceSegment3 = CatzConstants.RIGHT_SCALE_SCALE_APPRO_SCALE - CatzConstants.HALF_ROBOT_LENGTH;
		
	    // Drive to Scale
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, distanceSegment1, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT);  //turn 90deg left 
	
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, distanceSegment2, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
	
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg right
	
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, distanceSegment3, CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzRobotMap.grabber.openForearm(); 
		CatzRobotMap.grabber.setIntakeSpeed(-CatzConstants.INTAKE_SPEED);
		
	    // Drive to pick up power cube by switch
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, -CatzConstants.RIGHT_SCALE_SCALE_BACK_UP,
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 180deg
		
		CatzPIDTurn.PIDturn(180, CatzConstants.PID_TURN_TIMEOUT); //turn 180deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCLALE_SCALE_APPRO_CUBE, 
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 20in to open the grabber
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg 
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCALE_SCALE_ACCUE_CUBE, 
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 7in to grab the cube accurately
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); //turn 90deg left
		
		CatzRobotMap.grabber.deployBicep(); //down
		
		CatzRobotMap.grabber.openForearm(); //open the grabber
		
		CatzRobotMap.grabber.setIntakeSpeed(CatzConstants.INTAKE_SPEED); //turn on the intake
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,CatzConstants.RIGHT_SCALE_SCAlE_APPRO_SWITCH-CatzConstants.HALF_ROBOT_LENGTH, 
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 50 forward to get to the switch
		
		CatzRobotMap.grabber.closeForearm(); //close the grabber 
				
	    // Drive back to Scale
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, -CatzConstants.RIGHT_SCALE_SCALE_BACK_UP, 
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //back up 20in to turn 90deg
		
		CatzPIDTurn.PIDturn(-90, CatzConstants.PID_TURN_TIMEOUT); 
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCALE_SCALE_ACCUE_CUBE,
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
		
		CatzPIDTurn.PIDturn(90, CatzConstants.PID_TURN_TIMEOUT); // 90deg
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCALE_SCALE_APPRO_FINAL_SCLAE,
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 70in to get to scale
		
		//CatzRobotMap.grabber.openForearm(); 
		//CatzRobotMap.grabber.setIntakeSpeed(-CatzConstants.INTAKE_SPEED);
		

	}
		

		
	}
