package autonomous;
import edu.wpi.first.wpilibj.Timer;
import constants.CatzConstants;
import org.usfirst.frc.team2637.robot.CatzRobotMap;
/*
 *  Author : Derek Duenas
 *  Revision History : 
 *  	1-28-2018 D. Duenas Translated from C++ and added integral term
 *  	2-4-2018 D. Duenas Revising code
 *  
 *  Methods : PIDturn
 *  Functionality : Accurately turn autonomously
 */
public class CatzPIDTurn
{
	static CatzRobotMap instance;

	static Timer functionTimer;
	static Timer pdTimer;
	static Timer debugTimer;
	
	static double currentError;
	static double deltaError;
	static double derivative; 
	static double deltaT;
	
	static double power;
	
	static double previousError;
	static double totalError;
	
	static double currentAngle;
	static double targetAngle;
	static double targetAngleAbs;
	static double targetUpperLimit;
	static double targetLowerLimit;

	static boolean done;
	static boolean debug;
	
	public static void pidDebug()
	{
		String name;
		instance.logger.add("object", "PID Debug", 5, debugTimer.get());
	}
	
	public static void PIDturn(double degreesToTurn, int timeoutSeconds)
	{
		functionTimer = new Timer();
		pdTimer = new Timer();
		
		if(debug == true)
		{
			debugTimer = new Timer();
			debugTimer.start();
			pidDebug();
		}
		
		instance = CatzRobotMap.getInstance();
		instance.navx.reset();
		
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);
		
		done = false;
		
		previousError = CatzConstants.ZERO;
		totalError = CatzConstants.ZERO;
		
		functionTimer.reset();
		functionTimer.start();
		
		pdTimer.reset();
		pdTimer.start();
		
		currentAngle = instance.navx.getAngle();
		
		targetAngle = degreesToTurn + currentAngle;
		targetAngleAbs = Math.abs(targetAngle);
		
		targetUpperLimit = targetAngleAbs-CatzConstants.PID_TURN_THRESHOLD;
		targetLowerLimit = targetAngleAbs+CatzConstants.PID_TURN_THRESHOLD;
		
		while(currentAngle < targetLowerLimit || currentAngle > targetUpperLimit && done == false)
		{
			currentAngle = Math.abs(instance.navx.getAngle());
			pdTimer.stop();
			
			deltaT = pdTimer.get();
			pdTimer.reset();
			pdTimer.start();

			// calculates proportional term
			currentError = targetAngle - currentAngle;
			
			// calculates derivative term
			deltaError = currentError-previousError;
			derivative = deltaError/deltaT;
			
			previousError = currentError;  // saves error for next iteration
			
			// calculates integral term
			totalError += currentError * deltaT;   
	
			if(totalError >= CatzConstants.PID_INTEGRAL_MAX)     // saturation
				totalError = CatzConstants.PID_INTEGRAL_MAX;	 // makes sure the integral term doesn't get too big or small
			
			if(totalError <= CatzConstants.PID_INTEGRAL_MIN)
				totalError = CatzConstants.PID_INTEGRAL_MIN;
			
			power = ((CatzConstants.TURN_KP * currentError)      
					+(CatzConstants.TURN_KD * derivative)
					+(CatzConstants.TURN_KI * totalError));
			
			instance.drive.tankDrive(power,-power);

			if (functionTimer.get() > timeoutSeconds)
				done = true;
		}
		instance.drive.tankDrive(CatzConstants.ZERO, CatzConstants.ZERO);
		functionTimer.stop();
		
		pdTimer.stop();
	}
}
