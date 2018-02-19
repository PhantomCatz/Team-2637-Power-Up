/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: add the header
 *  Methods: runAutonomousPeriodic
 *  Functionality: suppress warnings
*******************************************************/

package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

public class CatzAutonomousPeriodic
{
	public static void runAutonomousPeriodic(){
		CatzRobotMap.drive.tankDrive(0,0); // To suppress warnings..
	}
}
