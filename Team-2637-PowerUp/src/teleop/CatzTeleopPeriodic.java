package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

public class CatzTeleopPeriodic 
{
	public static void runTeleopPeriodic()
	{
		/*// if joystick button three is pressed the lfit goes down
		if(CatzRobotMap.joy.getBottomLeftThumbButton())
			CatzRobotMap.lift.liftDown();
		else
			CatzRobotMap.lift.noLift();
		
		// if joystick button four is pressed the lift goes up
		if(CatzRobotMap.joy.getTopLeftThumbButton())
			CatzRobotMap.lift.liftUp();
		
		// if joystick trigger is pressed the climber goes up
		if(CatzRobotMap.joy.getTrigger())
			CatzRobotMap.climberMechanism.climbUp();
		else
			CatzRobotMap.climberMechanism.stopClimb();
		
		// if xbox right bumper is pressed the intake is deployed
		if(CatzRobotMap.xbox.getRightBumper())
			CatzRobotMap.grabber.deployBicep();
		
		// if xbox left bumper is pressed the intake runs
		if(CatzRobotMap.xbox.getLeftTrigger() > 0.8)
			CatzRobotMap.grabber.intakeCube();
		
		// if xbox left trigger is pressed the intake is retracted
		if(CatzRobotMap.xbox.getLeftBumper())
			CatzRobotMap.grabber.retractBicep();
		
		// if xbox right trigger is pressed the cube is launched
		if(CatzRobotMap.xbox.getRightTrigger() > 0.8)
			CatzRobotMap.grabber.launchCube();
		
		// if xbox A button is pressed the grabber opens or closes
		if(CatzRobotMap.xbox.getAButton())
		{
			CatzConstants.forearmOpen = !CatzConstants.forearmOpen;	
			if(CatzConstants.forearmOpen == true)
				CatzRobotMap.grabber.deployBicep();
			else
				CatzRobotMap.grabber.retractBicep();
		}*/
		
		
		/*if (CatzRobotMap.xbox.getAButton()==true)
			CatzRobotMap.lift.liftUp();
		else if(CatzRobotMap.xbox.getBButton()==true)
			CatzRobotMap.lift.liftDown();
		else
			CatzRobotMap.lift.noLift();*/
		//sets drive control to xbox
		//CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xbox);
		CatzRobotMap.lifterL.set(CatzRobotMap.xbox.getLeftStickY());
		CatzRobotMap.lifterR.set(CatzRobotMap.xbox.getLeftStickY());
	}
}
