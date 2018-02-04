package autonomous;
import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*
 *  Author : Derek Duenas
 *  Last Revised : 1-28-2018 DD
 *  Translated from C++ and added integral term
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
		
		Timer.delay(CatzConstants.WAIT_0_1_SECONDS);
		
		boolean done = false;
		int PDTurnLoopcount = CatzConstants.ZERO_INT;
		
		double turnToDegrees = turnDegrees + instance.navx.getAngle();
		
		double turnThreshold = CatzConstants.TURN_THRESHOLD_0_1;
		double previousError = CatzConstants.ZERO_DOUBLE;
		
		double currentError;
		double deltaError;
		double derivative; 
		double deltaT;
		double power;
		
		double totalError = CatzConstants.ZERO_DOUBLE;
		
		functionTimer.reset();
		functionTimer.start();
		
		pdTimer.reset();
		pdTimer.start();
		
		double currentAngle = Math.abs(instance.navx.getAngle());
		double targetUnder = Math.abs(turnToDegrees)-turnThreshold;
		double targetOver = Math.abs(turnToDegrees)+turnThreshold;
		
		while(currentAngle < targetUnder || currentAngle > targetOver && done == false)
		{
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
			
			
			SmartDashboard.putNumber("PDTurn:NavxReading",instance.navx.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",functionTimer.get());
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		instance.drive.tankDrive(CatzConstants.ZERO_DOUBLE, CatzConstants.ZERO_DOUBLE);
		functionTimer.stop();
		
		pdTimer.stop();
	}
	
}
