package components;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.XboxController;
import logger.CatzLogger;
public class CatzXboxController extends XboxController
{	
	final private String NAME;
	private XboxController xbox;
	private CatzTimerMap timer;
	private CatzLogger logger;
	
	final static public int A_BUTTON = 1;
	final static public int B_BUTTON = 2;
	final static public int X_BUTTON = 3;
	final static public int Y_BUTTON = 4;
	final static public int LEFT_BUMPER = 5;
	final static public int RIGHT_BUMPER = 6;
	final static public int LEFT_STICK_X = 0;
	final static public int LEFT_STICK_Y = 1;
	final static public int LEFT_TRIGGER = 2;
	final static public int RIGHT_TRIGGER = 3;
	final static public int RIGHT_STICK_X = 4;
	final static public int RIGHT_STICK_Y = 5;
	
	public CatzXboxController(int port)
	{
		super(port);
		logger = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		xbox = new XboxController(port);
		timer = CatzTimerMap.getInstance();
	}
	public boolean GetAButton()
	{
		logger.add(NAME, "'A' button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawButton(A_BUTTON);
	}
	public boolean GetBButton()
	{
		logger.add(NAME, "'B' button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawButton(B_BUTTON);
	}
	public boolean GetXButton()
	{
		logger.add(NAME, "'X' button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawButton(X_BUTTON);
	}
	public boolean GetYButton()
	{
		logger.add(NAME, "'Y' button pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawButton(Y_BUTTON);
	}
	public boolean GetLeftBumper()
	{
		logger.add(NAME, "Left Bumper pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawButton(LEFT_BUMPER);
	}
	public boolean GetRightBumper()
	{
		logger.add(NAME, "Right Bumper pressed.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawButton(RIGHT_BUMPER);
	}
	public double GetLeftStickX()
	{
		logger.add(NAME, "Left Stick moved in X direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawAxis(LEFT_STICK_X);
	}
	public double GetLeftStickY()
	{
		logger.add(NAME, "Left Stick moved in Y direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawAxis(LEFT_STICK_Y);
	}
	public double GetRightStickX()
	{
		logger.add(NAME, "Right Stick moved in X direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawAxis(RIGHT_STICK_X);
	}
	public double GetRightStickY()
	{
		logger.add(NAME, "Right Stick moved in Y direction.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawAxis(RIGHT_STICK_Y);
	}
	public double GetRightTrigger()
	{
		logger.add(NAME, "Right Trigger moved.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawAxis(RIGHT_TRIGGER);
	}
	public double GetLeftTrigger()
	{
		logger.add(NAME, "Left Trigger moved.", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		return xbox.getRawAxis(LEFT_TRIGGER);
	}
}