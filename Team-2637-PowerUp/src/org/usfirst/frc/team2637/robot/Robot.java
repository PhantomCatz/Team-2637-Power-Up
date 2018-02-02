/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2637.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import teleop.CatzTeleopMethods;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot 
{
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		//CatzRobotMap.init.run();
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
		//CatzAutonomousInit.runAutnomousInit();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		//CatzAutonomousPeriodic.runAutonomousPeriodic();
	}
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{			
		CatzTeleopMethods.runTeleopPeriodic();
	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic()
	{
		
	}
}
