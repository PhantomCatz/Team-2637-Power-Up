/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: rename the check box
 *  Methods: run, setSmartDashboard
 *  Functionality: set the smartDashboard
*******************************************************/

package org.usfirst.frc.team2637.robot;

import autonomous.CatzPIDDrive;
import autonomous.CatzPIDTurn;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzRobotInit 
{
	
	static boolean check_boxL = false;
	static boolean check_boxM = false;
	static boolean check_boxR = false;

	
	public static void runRobotInit() 
	{
		setSmartDashboard();
		CatzPIDTurn.setTuningModeEnabled(true);
		CatzRobotMap.setDebugModeEnabled(true);
		CatzRobotMap.getInstance();
		
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