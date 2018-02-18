package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

public class CatzTeleopPeriodic 
{
	static CatzRobotMap instance;
	public static void runTeleopPeriodic()
	{
		instance = CatzRobotMap.getInstance();
		// if joystick button three is pressed the lfit goes down
		if(instance.joy.getBottomLeftThumbButton())
			instance.lift.liftDown();
		else
			instance.lift.noLift();
		
		// if joystick button four is pressed the lift goes up
		if(instance.joy.getTopLeftThumbButton())
			instance.lift.liftUp();
		else
			instance.lift.noLift();
		
		// if joystick trigger is pressed the climber goes up
		if(instance.joy.getTrigger())
			instance.Climber.climbUp();
		else
			instance.Climber.stopClimb();
		
		// if xbox right bumper is pressed the intake is deployed
		if(instance.xbox.getRightBumper())
			instance.grabber.deployBicep();
		
		// if xbox left bumper is pressed the intake runs
		if(instance.xbox.getLeftTrigger() > 0.8)
			instance.grabber.intakeCube();
		
		// if xbox left trigger is pressed the intake is retracted
		if(instance.xbox.getLeftBumper())
			instance.grabber.retractBicep();
		
		// if xbox right trigger is pressed the cube is launched
		if(instance.xbox.getRightTrigger() > 0.8)
			instance.grabber.launchCube();
		
		// if xbox A button is pressed the grabber opens or closes
		if(instance.xbox.getAButton())
		{
			CatzConstants.forearmOpen = !CatzConstants.forearmOpen;	
			if(CatzConstants.forearmOpen == true)
				instance.grabber.deployBicep();
			else
				instance.grabber.retractBicep();
		}
		
		//sets drive control to xbox
		instance.drive.setModeArcadeDriveFlash(instance.xbox);
	}
}
