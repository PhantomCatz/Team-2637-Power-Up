/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;
import constants.CatzConstants;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import components.CatzTimer;
/*
 * Just doing something to change the file.
 */

/*
 * Changing the file again.
 */

/*
 * Changing the file once more.
 */

/*
 * Changes for days. 
 */

/*
 * Do you know de way?
 * 
 * Bruddahs I know de wae
 */

/*
 * Make some meaningful changes to the code
 */

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class CatzRobot extends IterativeRobot 
{
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	CatzRobotMap robotmap;
	
	@Override
	public void robotInit()
	{
		robotmap = CatzRobotMap.getInstance();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	
	
	
	@Override
	public void autonomousInit() 
	{
		robotmap.m_autoSelected = robotmap.m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + robotmap.m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		switch (robotmap.m_autoSelected)
		{
			case robotmap.constants.kCustomAuto:
				// Put custom auto code here
				break;
			case robotmap.constants.kDefaultAuto:
				
				break;
			default:
				// Put default auto code here
				
				
				break;
		}
	}
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{			
		robotmap.drive.arcadeDrive(robotmap.xbox.getYButton(Hand.kLeft), robotmap.xbox.getXButton(Hand.kRight));
	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic()
	{
		
	}
}
