package robotFunctions;

import robot.CatzConstants;
import robot.CatzRobotMap;


public class CatzTeleopInit 
{
	public static void runTeleopInit()
	{
		//setMechanisms();
		
	}
	public static void setMechanisms() {

		CatzRobotMap.grabber.closeForearm();
		CatzRobotMap.grabber.retractBicep();
	}
}
