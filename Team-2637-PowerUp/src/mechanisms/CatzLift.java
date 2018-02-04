package mechanisms;

import org.usfirst.frc.team2637.robot.CatzRobotMap;
import constants.CatzConstants;

/*
 *  Author : Derek Duenas
 *  Last Revised : 2-4-2018 DD
 *  Added functionality to move the lift up and down
 *  Methods : lift
 *  Functionality : Move the lift up and down
 */

public class CatzLift 
{
	CatzRobotMap instance;
	public void lift()
	{
		instance = CatzRobotMap.getInstance();
		if(instance.joy.getTrigger())
		{
			instance.lifterL.setSpeed(CatzConstants.LIFT_SPEED);
			instance.lifterR.setSpeed(CatzConstants.LIFT_SPEED);
		}
		else
		{
			instance.lifterL.setSpeed(CatzConstants.ZERO_DOUBLE);
			instance.lifterR.setSpeed(CatzConstants.ZERO_DOUBLE);
		}
		if(instance.joy.getThumbButton())
		{
			instance.lifterL.setSpeed(-CatzConstants.LIFT_SPEED);
			instance.lifterR.setSpeed(-CatzConstants.LIFT_SPEED);
		}
		else
		{
			instance.lifterL.setSpeed(CatzConstants.ZERO_DOUBLE);
			instance.lifterR.setSpeed(CatzConstants.ZERO_DOUBLE);
		}
	}
}
