package org.usfirst.frc.team2637.robot;

import edu.wpi.first.wpilibj.Spark;

public class CatzSpark 
{
	final String NAME;
	Spark spark;
	CatzLogger log;
	
	public CatzSpark(int port)
	{
		spark = new Spark(port);
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
	}
	public double GetSpeed()
	{
		return spark.get();
	}
	public void SetSpeed(double speed)
	{
		spark.set(speed);
		log.add(NAME, "Spark speed set to " + speed + ".", 5, -1);
	}
}
