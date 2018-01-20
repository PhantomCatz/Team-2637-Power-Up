package components;
import constants.Constants;
import edu.wpi.first.wpilibj.Joystick;
import logger.CatzLogger;

public class CatzJoystick
{
	Constants constants = new Constants();
	
	final String NAME;
	Joystick joy;
	CatzLogger log;
	
	
	public CatzJoystick(int port)
	{
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		joy = new Joystick(port);
		
	}
	public boolean GetTrigger()
	{
		log.add(NAME, "Trigger button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.TRIGGER);
	}
	public boolean GetThumbButton()
	{
		log.add(NAME, "Thumb button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.THUMB_BUTTON);
	}
	public boolean GetButtonThree()
	{
		log.add(NAME, "Three button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_THREE);
	}
	public boolean GetButtonFour()
	{
		log.add(NAME, "Four button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_FOUR);
	}
	public boolean GetButtonFive()
	{
		log.add(NAME, "Five button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_FIVE);
	}
	public boolean GetButtonSix()
	{
		log.add(NAME, "Six button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_SIX);
	}
	public boolean GetButtonSeven()
	{
		log.add(NAME, "Seven button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_SEVEN);
	}
	public boolean GetButtonEight()
	{
		log.add(NAME, "Eight button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_EIGHT);
	}
	public boolean GetButtonNine()
	{
		log.add(NAME, "Nine button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_NINE);
	}
	public boolean GetButtonTen()
	{
		log.add(NAME, "Ten button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_TEN);
	}
	public boolean GetButtonEleven()
	{
		log.add(NAME, "Eleven button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_ELEVEN);
	}
	public boolean GetButtonTwelve()
	{
		log.add(NAME, "Twelve button pressed.", constants.level5, -1);
		return joy.getRawButton(constants.BUTTON_TWELVE);
	}
	public double GetXAxis()
	{
		log.add(NAME, "Moved in the X direction.", constants.level5, -1);
		return joy.getRawAxis(constants.STICK_X_AXIS);
	}
	public double GetYAxis()
	{
		log.add(NAME, "Moved in the Y direction.", constants.level5, -1);
		return joy.getRawAxis(constants.STICK_Y_AXIS);
	}
	public double GetZAxis()
	{
		log.add(NAME, "Moved in the Z direction.", constants.level5, -1);
		return joy.getRawAxis(constants.STICK_Z_AXIS);
	}
	public double GetSlider()
	{
		log.add(NAME, "Slider moved.", constants.level5, -1);
		return joy.getRawAxis(constants.SLIDER);
	}
}