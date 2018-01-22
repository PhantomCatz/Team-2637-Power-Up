package autonomous;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;

public class PIDTurn
{
	CatzRobotMap robotMap;
	void PIDturn(double turnDegrees, int timeoutSeconds)
	{
		robotMap.navx.reset();
		robotMap.timer.wait(.1);

		boolean done = false;
		int PDTurnLoopcount = 0;
		double turnToDegrees = turnDegrees + robotMap.navx.getAngle();
		double turnThreshold = .1;
		double currentError;
		double previousError = 0;
		
		double deltaError;
		double derivative; 
		double deltaT;
		double power;
		
		double totalError = 0;
		
		robotMap.timer.reset(CatzConstants.FUNCTION_TIMER);
		robotMap.timer.start(CatzConstants.FUNCTION_TIMER);
		
		robotMap.timer.reset(CatzConstants.PD_TIMER);
		
		while(Math.abs(robotMap.navx.getAngle()) < Math.abs(turnToDegrees)-turnThreshold ||
				Math.abs(robotMap.navx.getAngle()) > Math.abs(turnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			robotMap.timer.stop(CatzConstants.PD_TIMER); // empty function
			
			deltaT = robotMap.timer.get(CatzConstants.PD_TIMER);
			robotMap.timer.reset(CatzConstants.PD_TIMER);
			robotMap.timer.start(CatzConstants.PD_TIMER);

			currentError = turnToDegrees-robotMap.navx.getAngle();
			deltaError = currentError-previousError;
			totalError += deltaError;
			derivative = deltaError/deltaT;

			power = .6*((CatzConstants.TURN_KP*currentError)+(CatzConstants.TURN_KD*derivative)+(CatzConstants.TURN_KI * (totalError)));
			
			robotMap.drive.tankDrive(power,-power);

			previousError = currentError;

			if (robotMap.timer.get(CatzConstants.FUNCTION_TIMER) > timeoutSeconds)
				done = true;
			
			if(totalError >= CatzConstants.POS_MAX)    // saturation
				totalError = CatzConstants.POS_MAX;
			
			if(totalError <= CatzConstants.NEG_MAX)
				totalError = CatzConstants.NEG_MAX;
			
			SmartDashboard.putNumber("PDTurn:NavxReading",robotMap.navx.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",robotMap.timer.get(CatzConstants.FUNCTION_TIMER));
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		robotMap.drive.tankDrive(0,0);
		robotMap.timer.stop(CatzConstants.FUNCTION_TIMER);
		robotMap.timer.reset(CatzConstants.FUNCTION_TIMER);
		
		robotMap.timer.stop(CatzConstants.PD_TIMER);
		robotMap.timer.reset(CatzConstants.PD_TIMER);
	}
	
}
