/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robotFunctions.CatzAutonomousInit;
import robotFunctions.CatzAutonomousPeriodic;
import robotFunctions.CatzRobotInit;
import robotFunctions.CatzTeleopInit;
import robotFunctions.CatzTeleopPeriodic;


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
		//CatzRobotPeriodic.runRobotPeriodic();

	}
	
	@Override
	public void autonomousInit() 
	{
		//CatzAutonomousInit.runAutonomousInit();
	}

	@Override
	public void autonomousPeriodic()
	{
		//CatzAutonomousPeriodic.runAutonomousPeriodic();
	}


	@Override
	public void teleopInit()
	{
		//CatzTeleopInit.runTeleopInit();
	}
	
	@Override
	public void teleopPeriodic()
	{			
		CatzTeleopPeriodic.runTeleopPeriodic();
	}

}
