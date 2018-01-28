package components;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.Spark;
import logger.CatzLogger;
public class CatzSpark 
{
	private final String NAME;
	private Spark spark;
	private CatzLogger logger;
	private CatzTimerMap timer;
	public CatzSpark(int port)
	{
		timer = CatzTimerMap.getInstance();
		logger = CatzLogger.getInstance();
		spark = new Spark(port);
		NAME = this.getClass().getSimpleName();
	}
	public double GetSpeed()
	{
		return spark.get();
	}
	public void SetSpeed(double speed)
	{
		spark.set(speed);
		logger.add(NAME, "Spark speed set to " + speed + ".", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
	}
}
