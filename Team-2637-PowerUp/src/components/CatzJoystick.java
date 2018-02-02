package components;
import edu.wpi.first.wpilibj.Joystick;
//import logger.CatzLogger;
public class CatzJoystick
{
	//private CatzTimerMap timer;
	//private final String NAME;
	private Joystick joy;
	//private CatzLogger //logger;
	final static private int TRIGGER = 1;
	final static private int THUMB_BUTTON = 2;
	final static private int BUTTON_THREE = 3;
	final static private int BUTTON_FOUR = 4;
	final static private int BUTTON_FIVE = 5;
	final static private int BUTTON_SIX = 6;
	final static private int BUTTON_SEVEN = 7;
	final static private int BUTTON_EIGHT = 8;
	final static private int BUTTON_NINE = 9;
	final static private int BUTTON_TEN = 10;
	final static private int BUTTON_ELEVEN = 11;
	final static private int BUTTON_TWELVE = 12;
	final static private int STICK_X_AXIS = 0;
	final static private int STICK_Y_AXIS = 1;
	final static private int STICK_Z_AXIS = 2;
	final static private int SLIDER = 3;
	
	
	public CatzJoystick(int port)
	{
		//NAME = this.getClass().getSimpleName();
		joy = new Joystick(port);
		////logger = CatzLogger.getInstance();
	}
	public boolean getTrigger()
	{
		//logger.add(NAME, "Trigger button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(TRIGGER);
	}
	public boolean getThumbButton()
	{
		//logger.add(NAME, "Thumb button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(THUMB_BUTTON);
	}
	public boolean getButtonThree()
	{
		//logger.add(NAME, "Three button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_THREE);
	}
	public boolean getButtonFour()
	{
		//logger.add(NAME, "Four button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_FOUR);
	}
	public boolean getButtonFive()
	{
		//logger.add(NAME, "Five button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_FIVE);
	}
	public boolean getButtonSix()
	{
		//logger.add(NAME, "Six button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_SIX);
	}
	public boolean getButtonSeven()
	{
		//logger.add(NAME, "Seven button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_SEVEN);
	}
	public boolean getButtonEight()
	{
		//logger.add(NAME, "Eight button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_EIGHT);
	}
	public boolean getButtonNine()
	{
		//logger.add(NAME, "Nine button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_NINE);
	}
	public boolean getButtonTen()
	{
		//logger.add(NAME, "Ten button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_TEN);
	}
	public boolean getButtonEleven()
	{
		//logger.add(NAME, "Eleven button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_ELEVEN);
	}
	public boolean getButtonTwelve()
	{
		//logger.add(NAME, "Twelve button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawButton(BUTTON_TWELVE);
	}
	public double getXAxis()
	{
		//logger.add(NAME, "Moved in the X direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawAxis(STICK_X_AXIS);
	}
	public double getYAxis()
	{
		//logger.add(NAME, "Moved in the Y direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawAxis(STICK_Y_AXIS);
	}
	public double getZAxis()
	{
		//logger.add(NAME, "Moved in the Z direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawAxis(STICK_Z_AXIS);
	}
	public double getSlider()
	{
		//logger.add(NAME, "Slider moved.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return joy.getRawAxis(SLIDER);
	}
}