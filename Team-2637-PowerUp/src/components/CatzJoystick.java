package components;
import constants.CatzConstants;
import components.CatzTimer;
import edu.wpi.first.wpilibj.Joystick;
import robot.CatzRobotMap;
public class CatzJoystick
{
	CatzConstants constants = new CatzConstants();
	private CatzTimer timer;
	private final String NAME;
	private Joystick joy;
	CatzRobotMap robotmap;
	public CatzJoystick(int port)
	{
		robotmap = CatzRobotMap.getInstance();
		NAME = this.getClass().getSimpleName();
		joy = new Joystick(port);
	}
	public boolean GetTrigger()
	{
		robotmap.logger.add(NAME, "Trigger button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.TRIGGER);
	}
	public boolean GetThumbButton()
	{
		robotmap.logger.add(NAME, "Thumb button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.THUMB_BUTTON);
	}
	public boolean GetButtonThree()
	{
		robotmap.logger.add(NAME, "Three button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_THREE);
	}
	public boolean GetButtonFour()
	{
		robotmap.logger.add(NAME, "Four button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_FOUR);
	}
	public boolean GetButtonFive()
	{
		robotmap.logger.add(NAME, "Five button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_FIVE);
	}
	public boolean GetButtonSix()
	{
		robotmap.logger.add(NAME, "Six button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_SIX);
	}
	public boolean GetButtonSeven()
	{
		robotmap.logger.add(NAME, "Seven button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_SEVEN);
	}
	public boolean GetButtonEight()
	{
		robotmap.logger.add(NAME, "Eight button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_EIGHT);
	}
	public boolean GetButtonNine()
	{
		robotmap.logger.add(NAME, "Nine button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_NINE);
	}
	public boolean GetButtonTen()
	{
		robotmap.logger.add(NAME, "Ten button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_TEN);
	}
	public boolean GetButtonEleven()
	{
		robotmap.logger.add(NAME, "Eleven button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_ELEVEN);
	}
	public boolean GetButtonTwelve()
	{
		robotmap.logger.add(NAME, "Twelve button pressed.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawButton(constants.BUTTON_TWELVE);
	}
	public double GetXAxis()
	{
		robotmap.logger.add(NAME, "Moved in the X direction.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawAxis(constants.STICK_X_AXIS);
	}
	public double GetYAxis()
	{
		robotmap.logger.add(NAME, "Moved in the Y direction.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawAxis(constants.STICK_Y_AXIS);
	}
	public double GetZAxis()
	{
		robotmap.logger.add(NAME, "Moved in the Z direction.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawAxis(constants.STICK_Z_AXIS);
	}
	public double GetSlider()
	{
		robotmap.logger.add(NAME, "Slider moved.", constants.LEVEL5, timer.get(robotmap.constants.LOGGERTIMER));
		return joy.getRawAxis(constants.SLIDER);
	}
}