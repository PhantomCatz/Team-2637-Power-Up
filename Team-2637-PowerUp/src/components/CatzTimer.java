package components;
import edu.wpi.first.wpilibj.Timer;

public class CatzTimer extends Timer
{
	public static CatzTimer instance;
	
	public double getMilliseconds() 
	{	
		return getTime()*1000; 	
	}	
	public void wait(double t)
	{
		double startingTime = getTime();
		while(getTime()<t+startingTime);
	}
	
	public double getTime() 
	{
		return this.get();
	}
	public static CatzTimer getInstance() 
	{
		if(instance == null) {
			 instance = new CatzTimer();
		}
		return instance;
	}
}
