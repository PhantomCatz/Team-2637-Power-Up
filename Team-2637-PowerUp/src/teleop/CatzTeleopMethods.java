package teleop;

import robot.CatzRobotMap;

public class CatzTeleopMethods 
{
	public static void runTeleopPeriodic()
	{
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xbox);
		
	}
}
