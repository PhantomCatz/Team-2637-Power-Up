package components;
import edu.wpi.first.wpilibj.XboxController;
import robot.CatzRobotMap;

/*
 *  Author : Derek Duenas
 *  Last Revised : 2-1-2018 DD
 *  Removed timer and logger form class
 *  Methods : get value from all buttons on the controller
 *  Functionality : gets the value from the buttons on the controller
 */

public class CatzXboxController extends XboxController
{	
		
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
		printOutDebugData("Successfully initialized xbox Controller #"+port);
		
	}
	public boolean getAButton()
	{
		return this.getRawButton(A_BUTTON);
	}
	public boolean getBButton()
	{
		
		return this.getRawButton(B_BUTTON);
	}
	public boolean getXButton()
	{
		
		return this.getRawButton(X_BUTTON);
	}
	public boolean getYButton()
	{
		
		return this.getRawButton(Y_BUTTON);
	}
	public boolean getLeftBumper()
	{
		
		return this.getRawButton(LEFT_BUMPER);
	}
	public boolean getRightBumper()
	{
		
		return this.getRawButton(RIGHT_BUMPER);
	}
	public double getLeftStickX()
	{
	
		return this.getRawAxis(LEFT_STICK_X);
	}
	public double getLeftStickY()
	{
	
		return -this.getRawAxis(LEFT_STICK_Y);
	}
	public double getRightStickX()
	{
	
		return this.getRawAxis(RIGHT_STICK_X);
	}
	public double getRightStickY()
	{

		return this.getRawAxis(RIGHT_STICK_Y);
	}
	public double getRightTrigger()
	{
	
		return this.getRawAxis(RIGHT_TRIGGER);
	}
	public double getLeftTrigger()
	{
	
		return this.getRawAxis(LEFT_TRIGGER);
	}
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}