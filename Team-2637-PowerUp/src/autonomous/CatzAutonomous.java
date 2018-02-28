/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-25-2018 JK
 *  Last revision summary: create the class
 *  Methods: straightTurn. turnStraight
 *  Functionality: 
*******************************************************/
package autonomous;

import robot.CatzConstants;

public class CatzAutonomous {

	public static void straightTurn (double distance, double angle) {
		
		CatzPIDDrive.PIDDrive(robot.CatzConstants.AUTO_STARTPOS_DEF_SPEED, distance, CatzConstants.DEF_VALUE);
		CatzPIDTurn.PIDturn(angle, CatzConstants.DEF_VALUE);
		
	}
	
	public static void turnStraight (double angle, double distance) {
		
		CatzPIDTurn.PIDturn(angle, CatzConstants.DEF_VALUE);
		CatzPIDDrive.PIDDrive(CatzConstants.AUTO_STARTPOS_DEF_SPEED, distance, CatzConstants.DEF_VALUE);
	}
}
