package robotFunctions;

import robot.CatzConstants;
import robot.CatzRobotMap;


public class CatzTeleopInit 
{
	public static void runTeleopInit()
	{
		setMechanisms();
		//CatzRobotMap.comp.setClosedLoopControl(true);
	}
	public static void setMechanisms() {

		CatzRobotMap.grabber.closeForearm(0.0);
		CatzRobotMap.grabber.retractBicep(0.0);
	}
}
