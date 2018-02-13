package org.usfirst.frc.team2637.robot;

import autonomous.CatzPIDTurn;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzRobotInit 
{
	
	static boolean check_box1 = false;
	static boolean check_box2 = false;
	static boolean check_box3 = false;

	
	public static void run() 
	{
		setSmartDashboard();
		CatzPIDTurn.setDebugModeEnabled(true);
	}
	
	public static void setSmartDashboard() {
		
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, true);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, true);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, true);
		
		Timer.delay(0.2); //To update SmartDashboard (to clear the SmartDashboard)
		
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, false);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, false);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, false);
		
		/*	SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, check_box1);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, check_box2);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, check_box3); */
	}
}