package org.usfirst.frc.team2637.robot;

import edu.wpi.first.wpilibj.Joystick;

public class CatzJoystick
{
	final int TRIGGER = 1;
	final int THUMB_BUTTON = 2;
	final int BUTTON_THREE = 3;
	final int BUTTON_FOUR = 4;
	final int BUTTON_FIVE = 5;
	final int BUTTON_SIX = 6;
	final int BUTTON_SEVEN = 7;
	final int BUTTON_EIGHT = 8;
	final int BUTTON_NINE = 9;
	final int BUTTON_TEN = 10;
	final int BUTTON_ELEVEN = 11;
	final int BUTTON_TWELVE = 12;
	final int STICK_X_AXIS = 0;
	final int STICK_Y_AXIS = 1;
	final int STICK_Z_AXIS = 2;
	final int SLIDER = 3;
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
		log.add(NAME, "Trigger button pressed.", 5, -1);
		return joy.getRawButton(TRIGGER);
	}
	public boolean GetThumbButton()
	{
		log.add(NAME, "Thumb button pressed.", 5, -1);
		return joy.getRawButton(THUMB_BUTTON);
	}
	public boolean GetButtonThree()
	{
		log.add(NAME, "Three button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_THREE);
	}
	public boolean GetButtonFour()
	{
		log.add(NAME, "Four button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_FOUR);
	}
	public boolean GetButtonFive()
	{
		log.add(NAME, "Five button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_FIVE);
	}
	public boolean GetButtonSix()
	{
		log.add(NAME, "Six button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_SIX);
	}
	public boolean GetButtonSeven()
	{
		log.add(NAME, "Seven button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_SEVEN);
	}
	public boolean GetButtonEight()
	{
		log.add(NAME, "Eight button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_EIGHT);
	}
	public boolean GetButtonNine()
	{
		log.add(NAME, "Nine button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_NINE);
	}
	public boolean GetButtonTen()
	{
		log.add(NAME, "Ten button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_TEN);
	}
	public boolean GetButtonEleven()
	{
		log.add(NAME, "Eleven button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_ELEVEN);
	}
	public boolean GetButtonTwelve()
	{
		log.add(NAME, "Twelve button pressed.", 5, -1);
		return joy.getRawButton(BUTTON_TWELVE);
	}
	public double GetXAxis()
	{
		log.add(NAME, "Moved in the X direction.", 5, -1);
		return joy.getRawAxis(STICK_X_AXIS);
	}
	public double GetYAxis()
	{
		log.add(NAME, "Moved in the Y direction.", 5, -1);
		return joy.getRawAxis(STICK_Y_AXIS);
	}
	public double GetZAxis()
	{
		log.add(NAME, "Moved in the Z direction.", 5, -1);
		return joy.getRawAxis(STICK_Z_AXIS);
	}
	public double GetSlider()
	{
		log.add(NAME, "Slider moved.", 5, -1);
		return joy.getRawAxis(SLIDER);
	}
}