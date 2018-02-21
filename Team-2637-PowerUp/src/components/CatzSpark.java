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
	//private final String NAME;
	//private CatzLogger logger;
	//private CatzTimerMap timer;
	public CatzSpark(int port)
	{
		super(port);
		printOutDebugData("Successfully instantiated spark #"+port);
		//timer = CatzTimerMap.getInstance();
		//logger = CatzLogger.getInstance();
		//NAME = this.getClass().getSimpleName();
	}
	private static void printOutDebugData(String data){
		if(CatzRobotMap.debugMode==true) {
			System.out.println(data);
		}
	}
}
