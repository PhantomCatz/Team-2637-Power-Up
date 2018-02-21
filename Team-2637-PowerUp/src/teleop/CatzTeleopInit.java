package teleop;

/*
 *  Author : Andrew Lai
 *  Last Revised : 2-20-2018 AL
 *  added method to set mechanisms in desired start position
 *  Methods :  runTeleopInit,setMechanisms
 *  Functionality : prepares robot for operator control
 */

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

public class CatzTeleopInit 
{
	public static void runTeleopInit()
	{
		CatzRobotMap.comp.setClosedLoopControl(true);
		setMechanisms();
		
	}
	public static void setMechanisms() {
		CatzRobotMap.comp.setClosedLoopControl(true);
		CatzRobotMap.grabber.closeForearm();
		CatzRobotMap.grabber.retractBicep();
	}
}
