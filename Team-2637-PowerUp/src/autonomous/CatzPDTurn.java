package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzPDTurn
{
	/*static CatzRobotMap instance;
	void PDturn(double turnDegrees, double timeoutSeconds) 
	{
		
		instance.navx.reset();
		instance.timer.wait(.1);

		boolean done = false;
		int PDTurnLoopcount = 0;
		double TurnToDegrees = turnDegrees + instance.navx.getAngle();
		double turnThreshold = .1;
		double currentError;
		double previousError = 0;
		double deltaError;
		double derivative; 
		double deltaT;
		double power;
		
		instance.timer.reset(CatzConstants.FUNCTION_TIMER_INDEX);
		instance.timer.start(CatzConstants.FUNCTION_TIMER_INDEX);
		
		instance.timer.reset(CatzConstants.PD_TIMER_INDEX);

		//while((fabs(hardware->navx->GetAngle()) < fabs(TurnToDegrees)-turnThreshold ||
			//	fabs(hardware->navx->GetAngle()) > fabs(TurnToDegrees)+turnThreshold) && done!=true)
		while(Math.abs(instance.navx.getAngle()) < Math.abs(TurnToDegrees)-turnThreshold ||
				Math.abs(instance.navx.getAngle()) > Math.abs(TurnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			instance.timer.stop(CatzConstants.PD_TIMER_INDEX); // empty function
			
			deltaT = instance.timer.get(CatzConstants.PD_TIMER_INDEX);
			instance.timer.reset(CatzConstants.PD_TIMER_INDEX);
			instance.timer.start(CatzConstants.PD_TIMER_INDEX);

			currentError = TurnToDegrees-instance.navx.getAngle();
			deltaError = currentError-previousError;
			derivative = deltaError/deltaT;

			power = .6*((CatzConstants.TURN_KP*currentError)+(CatzConstants.TURN_KD*derivative));
			instance.drive.tankDrive(power,-power);

			previousError = currentError;

			if (instance.timer.get(CatzConstants.FUNCTION_TIMER_INDEX) > timeoutSeconds)
				done = true;

			SmartDashboard.putNumber("PDTurn:NavxReading",instance.navx.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",instance.timer.get(CatzConstants.FUNCTION_TIMER_INDEX));
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		instance.drive.tankDrive(0,0);
		instance.timer.start(CatzConstants.FUNCTION_TIMER_INDEX);
		instance.timer.reset(CatzConstants.FUNCTION_TIMER_INDEX);
		
		instance.timer.start(CatzConstants.PD_TIMER_INDEX);
		instance.timer.reset(CatzConstants.PD_TIMER_INDEX);
	}
	*/
}
	
