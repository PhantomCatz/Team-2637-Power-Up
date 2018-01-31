package teleop;

import robot.CatzRobotMap;

public class CatzTeleopMethods 
{
	public static void runTeleopPeriodic()
	{
		CatzRobotMap.getInstance();
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xbox);
	}
}
