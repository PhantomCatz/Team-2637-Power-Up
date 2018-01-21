package components;
import edu.wpi.first.wpilibj.Timer;
import java.util.ArrayList;

public class CatzTimer extends Timer
{
	public static CatzTimer instance;
	ArrayList<Timer> list;
	public	CatzTimer() 
	{
		list = new ArrayList<Timer>();
		list.set(0, new Timer());
	}
	
	public void start(int arrayIndex)
	{
		list.set(arrayIndex,new Timer());
		list.get(arrayIndex).start();
	}
	
	public void reset(int arrayIndex) 
	{
		list.get(arrayIndex).reset();
	}
	
	public double get(int arrayIndex)
	{
		return list.get(arrayIndex).get();
	}
	
	public void stop(int arrayIndex)
	{
		list.get(arrayIndex).stop();
	}
	
	public double getMilliseconds(int arrayIndex) 
	{	
		return list.get(arrayIndex).get()*1000; 	
	}	
	
	public void wait(double t)
	{
		reset();
		start();
		while(get()<t);
		stop();
	}
	
	public static CatzTimer getInstance() 
	{
		if(instance == null) {
			 instance = new CatzTimer();
		}
		return instance;
	}
	
}
