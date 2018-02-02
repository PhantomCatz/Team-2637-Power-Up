package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

public class CatzTeleopMethods 
{
	static CatzRobotMap instance;
	public static void runTeleopPeriodic()
	{
		instance = CatzRobotMap.getInstance();
		instance.drive.setModeArcadeDriveFlash(instance.xbox);
	}
}
