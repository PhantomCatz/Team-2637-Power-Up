package components;
import edu.wpi.first.wpilibj.XboxController;
import robot.CatzRobotMap;
public class CatzXboxController
{
	
	final private String NAME;
	private XboxController xbox;
	CatzRobotMap robotmap;
	public CatzXboxController(int port)
	{
		NAME = this.getClass().getSimpleName();
		xbox = new XboxController(port);
		robotmap = CatzRobotMap.getInstance();
	}
	public boolean GetAButton()
	{
		robotmap.logger.add(NAME, "'A' button pressed.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawButton(robotmap.constants.A_BUTTON);
	}
	public boolean GetBButton()
	{
		robotmap.logger.add(NAME, "'B' button pressed.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawButton(robotmap.constants.B_BUTTON);
	}
	public boolean GetXButton()
	{
		robotmap.logger.add(NAME, "'X' button pressed.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawButton(robotmap.constants.X_BUTTON);
	}
	public boolean GetYButton()
	{
		robotmap.logger.add(NAME, "'Y' button pressed.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawButton(robotmap.constants.Y_BUTTON);
	}
	public boolean GetLeftBumper()
	{
		robotmap.logger.add(NAME, "Left Bumper pressed.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawButton(robotmap.constants.LEFT_BUMPER);
	}
	public boolean GetRightBumper()
	{
		robotmap.logger.add(NAME, "Right Bumper pressed.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawButton(robotmap.constants.RIGHT_BUMPER);
	}
	public double GetLeftStickX()
	{
		robotmap.logger.add(NAME, "Left Stick moved in X direction.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawAxis(robotmap.constants.LEFT_STICK_X);
	}
	public double GetLeftStickY()
	{
		robotmap.logger.add(NAME, "Left Stick moved in Y direction.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawAxis(robotmap.constants.LEFT_STICK_Y);
	}
	public double GetRightStickX()
	{
		robotmap.logger.add(NAME, "Right Stick moved in X direction.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawAxis(robotmap.constants.RIGHT_STICK_X);
	}
	public double GetRightStickY()
	{
		robotmap.logger.add(NAME, "Right Stick moved in Y direction.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawAxis(robotmap.constants.RIGHT_STICK_Y);
	}
	public double GetRightTrigger()
	{
		robotmap.logger.add(NAME, "Right Trigger moved.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawAxis(robotmap.constants.RIGHT_TRIGGER);
	}
	public double GetLeftTrigger()
	{
		robotmap.logger.add(NAME, "Left Trigger moved.", robotmap.constants.LEVEL5, robotmap.timer.get(robotmap.constants.LOGGERTIMER));
		return xbox.getRawAxis(robotmap.constants.LEFT_TRIGGER);
	}
}