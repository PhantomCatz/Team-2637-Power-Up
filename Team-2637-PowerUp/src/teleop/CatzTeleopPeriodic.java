package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

public class CatzTeleopPeriodic 
{
	static CatzRobotMap instance;
	public static void runTeleopPeriodic()
	{
		instance.drive.setModeArcadeDriveFlash(instance.xbox);
		//runDriverControls()
		
		
		
		/*if(instance.xbox.getAButton()==true) {
			instance.climberMechanism.climbUp();
		} else if(instance.xbox.getBButton()==true) {        for lifter testing
			instance.climberMechanism.climbDown();
		} else {
			instance.climberMechanism.stopClimb();
		}*/
			
		
	}
	public void runDriverControls() {
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
			instance.climberMechanism.climbUp();
		else
			instance.climberMechanism.stopClimb();
		
		// if xbox right bumper is pressed the intake is deployed
		if(instance.xbox.getRightBumper())
			instance.grabber.deployBicep();
		
		
		// if xbox left bumper is pressed the intake runs
		if(instance.xbox.getLeftBumper())
			instance.grabber.intakeCube();
		
		// if xbox left trigger is pressed the intake is retracted
		if(instance.xbox.getLeftTrigger() > 0.8)
			instance.grabber.retractBicep();
		
		// if xbox right trigger is pressed the cube is launched
		if(instance.xbox.getRightTrigger() > 0.8)
			instance.grabber.launchCube();
		
		//sets drive control to xbox
		instance.drive.setModeArcadeDriveFlash(instance.xbox);
	}
}
