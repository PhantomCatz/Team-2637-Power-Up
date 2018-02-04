package mechanisms;

import org.usfirst.frc.team2637.robot.CatzRobotMap;
import constants.CatzConstants;

/*
 *  Author : Derek Duenas
 *  Last Revised : 2-4-2018 DD
 *  Added functionality to be able to climb
 *  Methods : climb
 *  Functionality : Climb
 */

public class CatzClimber 
{
	CatzRobotMap instance;
	public void climb()
	{
		instance = CatzRobotMap.getInstance();
		if(instance.joy.getButtonThree())
		{
			instance.climber.set(CatzConstants.CLIMB_SPEED);
			instance.climber2.set(CatzConstants.CLIMB_SPEED);
		}
		else
		{
			instance.climber.set(CatzConstants.ZERO_DOUBLE);
			instance.climber2.set(CatzConstants.ZERO_DOUBLE);
		}
		if(instance.joy.getButtonFour())
		{
			instance.climber.set(-CatzConstants.CLIMB_SPEED);
			instance.climber2.set(-CatzConstants.CLIMB_SPEED);
		}
		else
		{
			instance.climber.set(CatzConstants.ZERO_DOUBLE);
			instance.climber2.set(CatzConstants.ZERO_DOUBLE);
		}
	}
}
