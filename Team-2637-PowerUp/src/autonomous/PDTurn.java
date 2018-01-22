package autonomous;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;

public class PDTurn
{
	CatzRobotMap robotmap = CatzRobotMap.getInstance();
	
	void PDturn(double turnDegrees, double timeoutSeconds) 
	{
		robotmap.navx.reset();
		robotmap.timer.wait(.1);

		boolean done = false;
		int PDTurnLoopcount = 0;
		double TurnToDegrees = turnDegrees + robotmap.navx.getAngle();
		double turnThreshold = .1;
		double currentError;
		double previousError = 0;
		double deltaError;
		double derivative; 
		double deltaT;
		double power;
		
		robotmap.timer.reset(CatzConstants.FUNCTION_TIMER);
		robotmap.timer.start(CatzConstants.FUNCTION_TIMER);
		
		robotmap.timer.reset(CatzConstants.PD_TIMER);

		//while((fabs(hardware->navx->GetAngle()) < fabs(TurnToDegrees)-turnThreshold ||
			//	fabs(hardware->navx->GetAngle()) > fabs(TurnToDegrees)+turnThreshold) && done!=true)
		while(Math.abs(robotmap.navx.getAngle()) < Math.abs(TurnToDegrees)-turnThreshold ||
				Math.abs(robotmap.navx.getAngle()) > Math.abs(TurnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			robotmap.timer.stop(CatzConstants.PD_TIMER); // empty function
			
			deltaT = robotmap.timer.get(CatzConstants.PD_TIMER);
			robotmap.timer.reset(CatzConstants.PD_TIMER);
			robotmap.timer.start(CatzConstants.PD_TIMER);

			currentError = TurnToDegrees-robotmap.navx.getAngle();
			deltaError = currentError-previousError;
			derivative = deltaError/deltaT;

			power = .6*((CatzConstants.TURN_KP*currentError)+(CatzConstants.TURN_KD*derivative));
			robotmap.drive.tankDrive(power,-power);

			previousError = currentError;

			if (robotmap.timer.get(CatzConstants.FUNCTION_TIMER) > timeoutSeconds)
				done = true;

			SmartDashboard.putNumber("PDTurn:NavxReading",robotmap.navx.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",robotmap.timer.get(CatzConstants.FUNCTION_TIMER));
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		robotmap.drive.tankDrive(0,0);
		robotmap.timer.start(CatzConstants.FUNCTION_TIMER);
		robotmap.timer.reset(CatzConstants.FUNCTION_TIMER);
		
		robotmap.timer.start(CatzConstants.PD_TIMER);
		robotmap.timer.reset(CatzConstants.PD_TIMER);
	}
	
}
	
