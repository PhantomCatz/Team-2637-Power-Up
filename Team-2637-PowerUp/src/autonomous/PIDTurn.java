package autonomous;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;

public class PIDTurn
{
	static void PIDturn(double turnDegrees, int timeoutSeconds)
	{
		CatzRobotMap.navx.reset();
		CatzRobotMap.timer.wait(.1);

		boolean done = false;
		int PDTurnLoopcount = 0;
		double turnToDegrees = turnDegrees + CatzRobotMap.navx.getAngle();
		double turnThreshold = .1;
		double currentError;
		double previousError = 0;
		
		double deltaError;
		double derivative; 
		double deltaT;
		double power;
		
		double totalError = 0;
		
		CatzRobotMap.timer.reset(CatzConstants.FUNCTION_TIMER);
		CatzRobotMap.timer.start(CatzConstants.FUNCTION_TIMER);
		
		CatzRobotMap.timer.reset(CatzConstants.PD_TIMER);
		
		while(Math.abs(CatzRobotMap.navx.getAngle()) < Math.abs(turnToDegrees)-turnThreshold ||
				Math.abs(CatzRobotMap.navx.getAngle()) > Math.abs(turnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			CatzRobotMap.timer.stop(CatzConstants.PD_TIMER); // empty function
			
			deltaT = CatzRobotMap.timer.get(CatzConstants.PD_TIMER);
			CatzRobotMap.timer.reset(CatzConstants.PD_TIMER);
			CatzRobotMap.timer.start(CatzConstants.PD_TIMER);

			currentError = turnToDegrees-CatzRobotMap.navx.getAngle();
			deltaError = currentError-previousError;
			totalError += deltaError;
			derivative = deltaError/deltaT;

			power = .6*((CatzConstants.TURN_KP*currentError)+(CatzConstants.TURN_KD*derivative)+(CatzConstants.TURN_KI * (totalError)));
			
			CatzRobotMap.drive.tankDrive(power,-power);

			previousError = currentError;

			if (CatzRobotMap.timer.get(CatzConstants.FUNCTION_TIMER) > timeoutSeconds)
				done = true;
			
			if(totalError >= CatzConstants.POS_MAX)    // saturation
				totalError = CatzConstants.POS_MAX;
			
			if(totalError <= CatzConstants.NEG_MAX)
				totalError = CatzConstants.NEG_MAX;
			
			SmartDashboard.putNumber("PDTurn:NavxReading",CatzRobotMap.navx.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",CatzRobotMap.timer.get(CatzConstants.FUNCTION_TIMER));
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		CatzRobotMap.drive.tankDrive(0,0);
		CatzRobotMap.timer.stop(CatzConstants.FUNCTION_TIMER);
		CatzRobotMap.timer.reset(CatzConstants.FUNCTION_TIMER);
		
		CatzRobotMap.timer.stop(CatzConstants.PD_TIMER);
		CatzRobotMap.timer.reset(CatzConstants.PD_TIMER);
	}
	
}
