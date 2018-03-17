/*******************************************************
 *  Author : Jean Kwon
 *   Last Revised : 2-19-2018 JK
 *  Last revision summary: rename the check box
 *  Methods: run, setSmartDashboard
 *  Functionality: set the smartDashboard
*******************************************************/

package robotFunctions;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzRobotInit 
{
	
	static boolean check_boxL = false;
	static boolean check_boxM = false;
	static boolean check_boxR = false;

	
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
	}
	
	public static void setSmartDashboard() {
		
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, true);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, true);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, true);
		
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, false);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, false);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, false);
		
	}
	
	public static void cameraSetup()
	{
		CameraServer.getInstance().startAutomaticCapture();
		 new Thread(() -> {
             UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
             
             camera.setResolution(640, 480);
             
             CvSink cvSink = CameraServer.getInstance().getVideo();
             CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
             
             Mat source = new Mat();
             Mat output = new Mat();
             
             while(!Thread.interrupted()) 
             { 
                 cvSink.grabFrame(source);
                 Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                 outputStream.putFrame(output);
             }
         }).start();
	}
}