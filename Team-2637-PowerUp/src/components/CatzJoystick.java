package components;
import edu.wpi.first.wpilibj.Joystick;
import robot.CatzRobotMap;

/*
*  Author : Derek Duenas
*  Last Revised : 2-1-2018 DD
*  Removed timer and logger form class
*  Methods : get value from all buttons on the joystick
*  Functionality : gets the value from the buttons on the joystick
*/

public class CatzJoystick extends Joystick
{
	//private CatzTimerMap timer;
	//private final String NAME;
	//private CatzLogger //logger;
	final static private int TRIGGER = 1;
	final static private int SIDE_THUMB_BUTTON = 2;
	final static private int BOTTOM_LEFT_THUMB_BUTTON = 3;
	final static private int BOTTOM_RIGHT_THUMB_BUTTON = 4;
	final static private int TOP_LEFT_THUMB_BUTTON = 5;
	final static private int TOP_RIGHT_THUMB_BUTTON = 6;
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
		super(port);
		printOutDebugData("Successfully instantiated joystick #"+port);
		//logger = CatzLogger.getInstance();
	}
	public boolean getTrigger()
	{
		//logger.add(NAME, "Trigger button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(TRIGGER);
	}
	public boolean getSideThumbButton()
	{
		//logger.add(NAME, "Thumb button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(SIDE_THUMB_BUTTON);
	}
	public boolean getBottomLeftThumbButton()
	{
		//logger.add(NAME, "Three button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BOTTOM_LEFT_THUMB_BUTTON);
	}
	public boolean getBottomRightThumbButton()
	{
		//logger.add(NAME, "Four button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BOTTOM_RIGHT_THUMB_BUTTON);
	}
	public boolean getTopLeftThumbButton()
	{
		//logger.add(NAME, "Five button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(TOP_LEFT_THUMB_BUTTON);
	}
	public boolean getTopRightThumbButton()
	{
		//logger.add(NAME, "Six button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(TOP_RIGHT_THUMB_BUTTON);
	}
	public boolean getButtonSeven()
	{
		//logger.add(NAME, "Seven button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BUTTON_SEVEN);
	}
	public boolean getButtonEight()
	{
		//logger.add(NAME, "Eight button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BUTTON_EIGHT);
	}
	public boolean getButtonNine()
	{
		//logger.add(NAME, "Nine button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BUTTON_NINE);
	}
	public boolean getButtonTen()
	{
		//logger.add(NAME, "Ten button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BUTTON_TEN);
	}
	public boolean getButtonEleven()
	{
		//logger.add(NAME, "Eleven button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BUTTON_ELEVEN);
	}
	public boolean getButtonTwelve()
	{
		//logger.add(NAME, "Twelve button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawButton(BUTTON_TWELVE);
	}
	public double getXAxis()
	{
		//logger.add(NAME, "Moved in the X direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawAxis(STICK_X_AXIS);
	}
	public double getYAxis()
	{
		//logger.add(NAME, "Moved in the Y direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawAxis(STICK_Y_AXIS);
	}
	public double getZAxis()
	{
		//logger.add(NAME, "Moved in the Z direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawAxis(STICK_Z_AXIS);
	}
	public double getSlider()
	{
		//logger.add(NAME, "Slider moved.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return this.getRawAxis(SLIDER);
	}
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}