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
	public static void PIDturn(double turnDegrees, int timeoutSeconds)
	{
		Timer functionTimer = new Timer();
		Timer pdTimer = new Timer();
		
		instance = CatzRobotMap.getInstance();
		instance.navx.reset();
		
		Timer.delay(CatzConstants.NAVX_WAIT_TIME);
		
		boolean done = false;
		
		double turnToDegrees = turnDegrees + instance.navx.getAngle();
		
		double previousError = 0;
		
		double currentError;
		double deltaError;
		double derivative; 
		double deltaT;
		double power;
		
		double totalError = 0;
		
		functionTimer.reset();
		functionTimer.start();
		
		pdTimer.reset();
		pdTimer.start();
		
		double currentAngle = Math.abs(instance.navx.getAngle());
		double targetUpperLimit = Math.abs(turnToDegrees)-CatzConstants.PID_TURN_THRESHOLD;
		double targetLowerLimit = Math.abs(turnToDegrees)+CatzConstants.PID_TURN_THRESHOLD;
		
		while((currentAngle < targetLowerLimit || currentAngle > targetUpperLimit) && done == false)
		{
			currentAngle = Math.abs(instance.navx.getAngle());
			pdTimer.stop();
			
			deltaT = pdTimer.get();
			pdTimer.reset();
			pdTimer.start();

			currentError = turnToDegrees - currentAngle;
			deltaError = currentError-previousError;
			totalError += currentError * deltaT;           
			derivative = deltaError/deltaT;

			if(totalError >= CatzConstants.POS_MAX)     // saturation
				totalError = CatzConstants.POS_MAX;		// makes sure the integral term doesn't get too big or small
			
			if(totalError <= CatzConstants.NEG_MAX)
				totalError = CatzConstants.NEG_MAX;
			
			power = CatzConstants.DRIVE_POWER_SCALE*((CatzConstants.TURN_KP*currentError)+(CatzConstants.TURN_KD*derivative)+(CatzConstants.TURN_KI * (totalError)));
			
			instance.drive.tankDrive(power,-power);

			previousError = currentError;

			if (functionTimer.get() > timeoutSeconds)
				done = true;
		}
		instance.drive.tankDrive(0, 0);
		functionTimer.stop();
		
		pdTimer.stop();
	}
	
}
