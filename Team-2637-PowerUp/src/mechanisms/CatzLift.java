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
	public void setLiftSpeed(double relativeSpeed) 
	{
		CatzRobotMap.lifterL.set(relativeSpeed);
		CatzRobotMap.lifterR.set(relativeSpeed);
	}
	public void liftUp()
	{
		CatzRobotMap.lifterL.set(CatzConstants.LIFT_SPEED);
		CatzRobotMap.lifterR.set(CatzConstants.LIFT_SPEED);
	}
	public void liftDown()
	{
		CatzRobotMap.lifterL.set(-CatzConstants.LIFT_SPEED);
		CatzRobotMap.lifterR.set(-CatzConstants.LIFT_SPEED);
	}
	public void stopLift()
	{
		CatzRobotMap.lifterL.set(CatzConstants.ZERO);
		CatzRobotMap.lifterR.set(CatzConstants.ZERO);
	}
}
