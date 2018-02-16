package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

public class CatzTeleopPeriodic 
{
	static CatzRobotMap instance;
	public static void runTeleopPeriodic()
	{
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xbox);
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
		// if joystick button three is pressed the lift goes down
		if(CatzRobotMap.joy.getBottomLeftThumbButton())
			CatzRobotMap.lift.liftDown();
		else
			CatzRobotMap.lift.noLift();
		
		// if joystick button four is pressed the lift goes up
		if(CatzRobotMap.joy.getTopLeftThumbButton())
			CatzRobotMap.lift.liftUp();
		else
			CatzRobotMap.lift.noLift();
		
		// if joystick trigger is pressed the climber goes up
		if(CatzRobotMap.joy.getTrigger())
			CatzRobotMap.climberMechanism.climbUp();
		else
			CatzRobotMap.climberMechanism.stopClimb();
		
		// if xbox right bumper is pressed the intake is deployed
		if(CatzRobotMap.xbox.getRightBumper())
			CatzRobotMap.grabber.deployBicep();
		
		// if xbox left bumper is pressed the intake is retracted
		if(CatzRobotMap.xbox.getLeftBumper())
			CatzRobotMap.grabber.retractBicep();
		
		//if xbox left trigger is pressed the forearm Closes
		if(CatzRobotMap.xbox.getLeftTrigger()>0.8) 
			CatzRobotMap.grabber.forearmClose();
		//if xbox right trigger is pressed the forearm Opens
		else if (CatzRobotMap.xbox.getRightTrigger()>0.8)
			CatzRobotMap.grabber.forearmOpen();
		
		// if xbox B button is pressed the intake runs
		if(CatzRobotMap.xbox.getBButton())
			CatzRobotMap.grabber.intakeCube();		
		// if xbox A button is pressed the cube is launched
		else if(CatzRobotMap.xbox.getAButton())
			CatzRobotMap.grabber.launchCube();
		else
			CatzRobotMap.grabber.stopLauncher();
		
		//whenever the intake is deployed the intake motors are running
		if(CatzConstants.bicepDeployed==true)
			CatzRobotMap.grabber.intakeCube();
		
		//sets drive control to xbox
		CatzRobotMap.drive.setModeArcadeDriveFlash(CatzRobotMap.xbox);
	}
}
