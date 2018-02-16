package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

public class CatzTeleopInit 
{
	static CatzRobotMap instance;
	public static void runTeleopInit()
	{
		instance = CatzRobotMap.getInstance();
	}
}
