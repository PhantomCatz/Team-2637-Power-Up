package components;

import edu.wpi.first.wpilibj.Spark;
import logger.CatzLogger;
import components.CatzTimer;
public class CatzSpark 
{
	private final String NAME;
	private Spark spark;
	private CatzLogger log;
	private CatzTimer timer;
	public CatzSpark(int port)
	{
		spark = new Spark(port);
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		timer = new CatzTimer();
	}
	public double GetSpeed()
	{
		return spark.get();
	}
	public void SetSpeed(double speed)
	{
		spark.set(speed);
		log.add(NAME, "Spark speed set to " + speed + ".", 5, timer.getTime());
	}
}
