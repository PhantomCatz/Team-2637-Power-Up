package PIDFunctions;

import edu.wpi.first.wpilibj.Timer;
import mechanisms.CatzLift;
import robot.CatzConstants;


public class PID {

	static Timer PIDtimer;
	
	static double currentError = 0; 
	static double integral = 0; 
	static double integralMin = 0; 
	static double integralMax = 0; 
	static double deltaError = 0;
	static double derivative = 0; 
	public static double currentTime = 0;
	public static double previousTime = 0;
	static double deltaT = 0;
	
	static double power;
	
	static double previousError;
	static double totalError;
	
	
	public double getError()
	{	
		return currentError;
	}
	
	public double getCurrentTime()
	{	
		return currentTime;
	}
	
	public void setCurrentTime(double time)
	{	
		 currentTime = time;
	}
	
	public void setPreviousTime(double time)
	{	
		previousTime = time;
	}
	
	
	
	 public double calculateError(double target, double currPos)
	{	
		return target - currPos;
	}
	 
	 public double calculateIntegral()
	{	
		return integral += currentError;
	}

	 public void setIntegralLimit(double min,double max)
	 {
		 integralMin = min;
		 
		 integralMax = max;
		 
	 }

	 
	 public double integralLimit()
	{	
			if(integral >= integralMax)     // saturation
				integral = integralMax;	 // makes sure the integral term doesn't get too big or small
			
			if(integral <= integralMin)
				integral = integralMin;
			
		   return integral;
	}
	 
	 public void deltaTCalculate()
	 {
		 
		 deltaT = currentTime - previousTime;	 
		 
	 }

	 public double calculateDerivative()
	{	
		return (currentError-previousError)/deltaT;
	}

	 public double calculatePID(double target, double currPos, double scaleFactor)
	{
		// PIDtimer = new Timer();
		
		  calculateError(target, currPos);
		  calculateIntegral();
		  integralLimit();
		  calculateDerivative();
		  
		  power = scaleFactor*(currentError*CatzLift.PID_LIFT_KP) + (integral*CatzLift.PID_LIFT_KI) + (derivative*CatzLift.PID_LIFT_KD);
		 
		  return power;
	}

	
	
	
}
