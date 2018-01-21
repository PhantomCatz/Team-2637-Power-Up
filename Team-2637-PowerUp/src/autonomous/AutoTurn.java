package autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;

public class AutoTurn
{
	CatzRobotMap robotmap = CatzRobotMap.getInstance();
	
	void PDTurn(double turnDegrees, int timeout)
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
		
		robotmap.timer.reset(robotmap.constants.FUNCTIONTIMER);
		robotmap.timer.start(robotmap.constants.FUNCTIONTIMER);
		
		robotmap.timer.reset(robotmap.constants.PDTIMER);

		//while((fabs(hardware->navx->GetAngle()) < fabs(TurnToDegrees)-turnThreshold ||
			//	fabs(hardware->navx->GetAngle()) > fabs(TurnToDegrees)+turnThreshold) && done!=true)
		while(Math.abs(robotmap.navx.getAngle()) < Math.abs(TurnToDegrees)-turnThreshold ||
				Math.abs(robotmap.navx.getAngle()) > Math.abs(TurnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			robotmap.timer.stop(robotmap.constants.PDTIMER); // empty function
			
			deltaT = robotmap.timer.get(robotmap.constants.PDTIMER);
			robotmap.timer.reset(robotmap.constants.PDTIMER);
			robotmap.timer.start(robotmap.constants.PDTIMER);

			currentError = TurnToDegrees-robotmap.navx.getAngle();
			deltaError = currentError-previousError;
			derivative = deltaError/deltaT;

			power = .6*((robotmap.constants.TurnkP*currentError)+(robotmap.constants.TurnkD*derivative));
			robotmap.drive.tankDrive(power,-power);

			previousError = currentError;

			if (robotmap.timer.get(robotmap.constants.FUNCTIONTIMER) > timeout)
				done = true;

			SmartDashboard.putNumber("PDTurn:NavxReading",robotmap.navx.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",robotmap.timer.get(robotmap.constants.FUNCTIONTIMER));
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		robotmap.drive.tankDrive(0,0);
		robotmap.timer.start(robotmap.constants.FUNCTIONTIMER);
		robotmap.timer.reset(robotmap.constants.FUNCTIONTIMER);
		
		robotmap.timer.start(robotmap.constants.PDTIMER);
		robotmap.timer.reset(robotmap.constants.PDTIMER);
	}
	
}
	
