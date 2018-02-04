package mechanisms;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

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
