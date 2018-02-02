package components;
import edu.wpi.first.wpilibj.Spark;
public class CatzSpark 
{
	//private final String NAME;
	private Spark spark;
	//private CatzLogger logger;
	//private CatzTimerMap timer;
	public CatzSpark(int port)
	{
		//timer = CatzTimerMap.getInstance();
		//logger = CatzLogger.getInstance();
		spark = new Spark(port);
		//NAME = this.getClass().getSimpleName();
	}
	public double getSpeed()
	{
		return spark.get();
	}
	public void setSpeed(double speed)
	{
		spark.set(speed);
		//logger.add(NAME, "Spark speed set to " + speed + ".", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
	}
}
