/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: create the class
 *  Methods: rightScaleScale
 *  Functionality: paths of outside starting positions
*******************************************************/

package autonomous;

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
		double distanceSegment4;
	
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
		
		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
	
	    // Drive to pick up power cube by switch
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, -CatzConstants.RIGHT_SCALE_SCALE_BACK_UP,
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(180, CatzConstants.PID_TURN_TIMEOUT); //turn 180deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCALE_SCALE_APPRO_CUBE_OPEN, 
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive forward 50in to open the grabber
		
		//open the grabber
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCLALE_SCALE_APPRO_CUBE,
				              CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		//close the grabber
		
		
	    // Drive back to Scale
		//CatzConstants.PIDDrive(CatzConstants.HALF_SPEED,CatzConstants.
	}
}
