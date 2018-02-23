package teleop;

/*
 *  Author : Andrew Lai
 *  Last Revised : 2-20-2018 AL
 *  added method to set mechanisms in desired start position
 *  Methods :  runTeleopInit,setMechanisms
 *  Functionality : prepares robot for operator control
 */



import constants.CatzConstants;
import robot.CatzRobotMap;


public class CatzTeleopInit 
{
	public static void runTeleopInit()
	{
		setMechanisms();
		
	}
	public static void setMechanisms() {
		CatzRobotMap.grabber.closeForearm();
		CatzRobotMap.grabber.retractBicep();
	}
}
