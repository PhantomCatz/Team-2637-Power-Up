/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: rename the check box
 *  Methods: run, setSmartDashboard
 *  Functionality: set the smartDashboard
*******************************************************/

package robotFunctions;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzRobotInit 
{
	
	static boolean check_boxL = false;
	static boolean check_boxM = false;
	static boolean check_boxR = false;

	static UsbCamera camera;
	public static void runRobotInit() 
	{
		CatzRobotMap.instantiateRobot();
		CatzRobotMap.liftEncoder.reset();
		setSmartDashboard();
		cameraSetup();
	}
	public static void runDisabledInit() {
		CatzRobotMap.liftEncoder.reset();
		setSmartDashboard();
		CatzRobotMap.xboxAux.setRumble(RumbleType.kLeftRumble, 0);
		CatzRobotMap.xboxAux.setRumble(RumbleType.kRightRumble, 0);
	}
	
	public static void setSmartDashboard() {
		
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, true);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, true);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, true);
		
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, false);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, false);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, false);
	
		SmartDashboard.putBoolean("Use default autonomous?", false);

	}
	
	public static void cameraSetup()
	{
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(80, 60);
		camera.setFPS(16);
	}
}