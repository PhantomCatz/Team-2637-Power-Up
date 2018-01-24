package autonomous;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;

public class PDTurn
{
	void PDturn(double turnDegrees, double timeoutSeconds) 
	{
		CatzRobotMap.navx.reset();
		CatzRobotMap.timer.wait(.1);

		boolean done = false;
		int PDTurnLoopcount = 0;
		double TurnToDegrees = turnDegrees + CatzRobotMap.navx.getAngle();
		double turnThreshold = .1;
		double currentError;
		double previousError = 0;
		double deltaError;
		double derivative; 
		double deltaT;
		double power;
		
		CatzRobotMap.timer.reset(CatzConstants.FUNCTION_TIMER);
		CatzRobotMap.timer.start(CatzConstants.FUNCTION_TIMER);
		
		CatzRobotMap.timer.reset(CatzConstants.PD_TIMER);

		//while((fabs(hardware->navx->GetAngle()) < fabs(TurnToDegrees)-turnThreshold ||
			//	fabs(hardware->navx->GetAngle()) > fabs(TurnToDegrees)+turnThreshold) && done!=true)
		while(Math.abs(CatzRobotMap.navx.getAngle()) < Math.abs(TurnToDegrees)-turnThreshold ||
				Math.abs(CatzRobotMap.navx.getAngle()) > Math.abs(TurnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			CatzRobotMap.timer.stop(CatzConstants.PD_TIMER); // empty function
			
			deltaT = CatzRobotMap.timer.get(CatzConstants.PD_TIMER);
			CatzRobotMap.timer.reset(CatzConstants.PD_TIMER);
			CatzRobotMap.timer.start(CatzConstants.PD_TIMER);

			currentError = TurnToDegrees-CatzRobotMap.navx.getAngle();
			deltaError = currentError-previousError;
			derivative = deltaError/deltaT;

			power = .6*((CatzConstants.TURN_KP*currentError)+(CatzConstants.TURN_KD*derivative));
			CatzRobotMap.drive.tankDrive(power,-power);

			previousError = currentError;

			if (CatzRobotMap.timer.get(CatzConstants.FUNCTION_TIMER) > timeoutSeconds)
				done = true;

			SmartDashboard.putNumber("PDTurn:NavxReading",CatzRobotMap.navx.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",CatzRobotMap.timer.get(CatzConstants.FUNCTION_TIMER));
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		CatzRobotMap.drive.tankDrive(0,0);
		CatzRobotMap.timer.start(CatzConstants.FUNCTION_TIMER);
		CatzRobotMap.timer.reset(CatzConstants.FUNCTION_TIMER);
		
		CatzRobotMap.timer.start(CatzConstants.PD_TIMER);
		CatzRobotMap.timer.reset(CatzConstants.PD_TIMER);
	}
	
}
	
