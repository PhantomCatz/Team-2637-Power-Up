package mechanisms;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

public class CatzClimber 
{
	CatzRobotMap instance;
	public void climb()
	{
		instance = CatzRobotMap.getInstance();
		instance.climber.set(CatzConstants.CLIMB_SPEED);
		instance.climber2.set(CatzConstants.CLIMB_SPEED);
	}
}
