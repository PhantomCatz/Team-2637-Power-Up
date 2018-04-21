package components;
import edu.wpi.first.wpilibj.Spark;
import robot.CatzRobotMap;

/*
 *  Author : Derek Duenas
 *  Last Revised : 2-1-2018 DD
 *  Removed timer and logger form class
 *  Methods : getSpeed , setSpeed
 *  Functionality : Can set and read speed from a spark
 */

public class CatzSpark extends Spark
{

	public CatzSpark(int port)
	{
		super(port);
		printOutDebugData("Successfully instantiated spark #"+port);

	}
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}
