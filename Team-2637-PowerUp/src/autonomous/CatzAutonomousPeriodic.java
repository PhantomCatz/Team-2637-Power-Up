package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

public class CatzAutonomousPeriodic
{
	static CatzRobotMap instance;

	
	public static void runAutonomousPeriodic()
	{
		instance = CatzRobotMap.getInstance();
		
		instance.drive.tankDrive(0,0); // To suppress warnings..
	}
}
