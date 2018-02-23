/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;

import autonomous.CatzAutonomousInit;
import autonomous.CatzAutonomousPeriodic;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import teleop.CatzTeleopInit;
import teleop.CatzTeleopPeriodic;


public class Robot extends IterativeRobot 
{
	@Override
	public void robotInit()
	{
		CatzRobotInit.runRobotInit();
	}
	
	@Override
	public void robotPeriodic()
	{
		CatzRobotPeriodic.runRobotPeriodic();
	}
	
	@Override
	public void autonomousInit() 
	{
		CatzAutonomousInit.runAutonomousInit();
	}
/*
	@Override
	public void autonomousPeriodic()
	{
		CatzAutonomousPeriodic.runAutonomousPeriodic();
	}


	@Override
	public void teleopInit()
	{
		CatzTeleopInit.runTeleopInit();
	}
	
	@Override
	public void teleopPeriodic()
	{			
		CatzTeleopPeriodic.runTeleopPeriodic();
	}
*/
}
