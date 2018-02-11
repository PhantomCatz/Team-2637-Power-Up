package autonomous;
import edu.wpi.first.wpilibj.Timer;
import constants.CatzConstants;

import java.text.DecimalFormat;

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
	static double currentAngleAbs;
	static double targetAngle;
	static double targetAngleAbs;
	static double targetUpperLimit;
	static double targetLowerLimit;

	static boolean done;
	static boolean debugMode= false;
	static String debugData;
	
	static DecimalFormat format = new DecimalFormat("###.#####");
	
	public static void setDebugModeEnabled(boolean enabled){
		debugMode = enabled;
	}

	
	public static void PIDturn(double degreesToTurn, int timeoutSeconds)
	{
		if(debugMode == true) {
			System.out.println("timestamp,deltaT,currentAngle,currentError,derivative,totalError/n");
		}
		
		functionTimer = new Timer();
		pdTimer = new Timer();
		
		instance = CatzRobotMap.getInstance();
		instance.navx.reset();
		
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);
		
		done = false;
		
		previousError = 0.0;
		totalError = 0.0;
		
		functionTimer.reset();
		functionTimer.start();
		
		pdTimer.reset();
		pdTimer.start();
		
		currentAngle = instance.navx.getAngle();
		currentAngleAbs = Math.abs(currentAngle);
		targetAngle = degreesToTurn + currentAngle;
		
		targetAngleAbs = Math.abs(targetAngle);
		
		targetUpperLimit = targetAngleAbs-CatzConstants.PID_TURN_THRESHOLD;
		targetLowerLimit = targetAngleAbs+CatzConstants.PID_TURN_THRESHOLD;
		
		while((currentAngleAbs < targetLowerLimit || currentAngleAbs > targetUpperLimit) && done == false)
		{
			currentAngle = instance.navx.getAngle();
			currentAngleAbs = Math.abs(currentAngle);
			
			System.out.println(currentAngle);
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
			
			double currentTime = functionTimer.get();
			if (debugMode = true) {
				debugData = format.format(currentTime) +","+  deltaT + "," + currentAngle + "," + currentError + "," + derivative + "," + totalError + "/n";
				System.out.println(debugData);
			}
			
			power = ((CatzConstants.TURN_KP * currentError)      
					+(CatzConstants.TURN_KD * derivative)
					+(CatzConstants.TURN_KI * totalError));
			
			
			
			if(currentError > 0)
				instance.drive.tankDrive(power,-power);
			else
				instance.drive.tankDrive(-power, power);
			
			

			if (currentTime > timeoutSeconds)
				done = true;
			

		}
		instance.drive.tankDrive(0.0, 0.0); // makes robot stop
		functionTimer.stop();
		
		pdTimer.stop();
	}
}
