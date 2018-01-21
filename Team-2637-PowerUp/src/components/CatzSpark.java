package components;

import edu.wpi.first.wpilibj.Spark;
import logger.CatzLogger;
import robot.CatzRobotMap;
import components.CatzTimer;
public class CatzSpark 
{
	private final String NAME;
	private Spark spark;
	private CatzRobotMap robotmap;
	public CatzSpark(int port)
	{
		robotmap = CatzRobotMap.getInstance();
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
		robotmap.logger.add(NAME, "Spark speed set to " + speed + ".", 5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
	}
}
