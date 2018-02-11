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
	public void liftUp()
	{
		instance.lifterL.setSpeed(CatzConstants.LIFT_SPEED);
		instance.lifterR.setSpeed(CatzConstants.LIFT_SPEED);
	}
	public void liftDown()
	{
		instance.lifterL.setSpeed(-CatzConstants.LIFT_SPEED);
		instance.lifterR.setSpeed(-CatzConstants.LIFT_SPEED);
	}
	public void noLift()
	{
		instance.lifterL.setSpeed(CatzConstants.ZERO);
		instance.lifterR.setSpeed(CatzConstants.ZERO);
	}
}
