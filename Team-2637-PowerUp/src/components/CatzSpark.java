package components;
import edu.wpi.first.wpilibj.Spark;

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
		//timer = CatzTimerMap.getInstance();
		//logger = CatzLogger.getInstance();
		//NAME = this.getClass().getSimpleName();
	}
	public double getSpeed()
	{
		return this.get();
	}
	public void setSpeed(double speed)
	{
		this.set(speed);
		//logger.add(NAME, "Spark speed set to " + speed + ".", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
	}
}
