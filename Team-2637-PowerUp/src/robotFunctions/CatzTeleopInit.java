package robotFunctions;

import robot.CatzRobotMap;


public class CatzTeleopInit 
{
	public static void runTeleopInit()
	{
		setMechanisms();
	}
	public static void setMechanisms() {

		CatzRobotMap.grabber.closeForearm(0.0);
		CatzRobotMap.grabber.retractBicep(0.0);
	}
}
