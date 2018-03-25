/**
 * Class Robot (Untested)
 * 
 * contains functions that will run during the competition
 * 
 * 2/28/18 uncommented all functions to ready for testing purposes
 */

package robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import robotFunctions.CatzAutonomousInit;
import robotFunctions.CatzAutonomousPeriodic;
import robotFunctions.CatzRobotInit;
import robotFunctions.CatzRobotPeriodic;
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
		CatzRobotPeriodic.runRobotPeriodic();
	}
	
	@Override
	public void disabledInit() 
	{
		CatzRobotInit.runDisabledInit();
	}
	@Override
	public void disabledPeriodic()
	{
		CatzRobotPeriodic.runDisabledPeriodic();
	}
	@Override
	public void autonomousInit() 
	{
		CatzAutonomousInit.runAutonomousInit();
	}

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

}
