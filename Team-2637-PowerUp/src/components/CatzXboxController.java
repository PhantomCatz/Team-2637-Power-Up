package components;
import edu.wpi.first.wpilibj.XboxController;
import logger.CatzLogger;
public class CatzXboxController
{
	final private int A_BUTTON = 1;
	final private int B_BUTTON = 2;
	final private int X_BUTTON = 3;
	final private int Y_BUTTON = 4;
	final private int LEFT_BUMPER = 5;
	final private int RIGHT_BUMPER = 6;
	final private int LEFT_STICK_X = 0;
	final private int LEFT_STICK_Y = 1;
	final private int LEFT_TRIGGER = 2;
	final private int RIGHT_TRIGGER = 3;
	final private int RIGHT_STICK_X = 4;
	final private int RIGHT_STICK_Y = 5;
	
	final private String NAME;
	
	private XboxController xbox;
	private CatzLogger log;
	
	public CatzXboxController(int port)
	{
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		xbox = new XboxController(port);
		
	}
	public boolean GetAButton()
	{
		log.add(NAME, "'A' button pressed.", 5, -1);
		return xbox.getRawButton(A_BUTTON);
	}
	public boolean GetBButton()
	{
		log.add(NAME, "'B' button pressed.", 5, -1);
		return xbox.getRawButton(B_BUTTON);
	}
	public boolean GetXButton()
	{
		log.add(NAME, "'X' button pressed.", 5, -1);
		return xbox.getRawButton(X_BUTTON);
	}
	public boolean GetYButton()
	{
		log.add(NAME, "'Y' button pressed.", 5, -1);
		return xbox.getRawButton(Y_BUTTON);
	}
	public boolean GetLeftBumper()
	{
		log.add(NAME, "Left Bumper pressed.", 5, -1);
		return xbox.getRawButton(LEFT_BUMPER);
	}
	public boolean GetRightBumper()
	{
		log.add(NAME, "Right Bumper pressed.", 5, -1);
		return xbox.getRawButton(RIGHT_BUMPER);
	}
	public double GetLeftStickX()
	{
		log.add(NAME, "Left Stick moved in X direction.", 5, -1);
		return xbox.getRawAxis(LEFT_STICK_X);
	}
	public double GetLeftStickY()
	{
		log.add(NAME, "Left Stick moved in Y direction.", 5, -1);
		return xbox.getRawAxis(LEFT_STICK_Y);
	}
	public double GetRightStickX()
	{
		log.add(NAME, "Right Stick moved in X direction.", 5, -1);
		return xbox.getRawAxis(RIGHT_STICK_X);
	}
	public double GetRightStickY()
	{
		log.add(NAME, "Right Stick moved in Y direction.", 5, -1);
		return xbox.getRawAxis(RIGHT_STICK_Y);
	}
	public double GetRightTrigger()
	{
		log.add(NAME, "Right Trigger moved.", 5, -1);
		return xbox.getRawAxis(RIGHT_TRIGGER);
	}
	public double GetLeftTrigger()
	{
		log.add(NAME, "Left Trigger moved.", 5, -1);
		return xbox.getRawAxis(LEFT_TRIGGER);
	}
}