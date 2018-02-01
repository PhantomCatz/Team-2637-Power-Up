package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

public class CatzTeleopMethods 
{
	public static void runTeleopPeriodic()
	{
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xbox);
	}
}
