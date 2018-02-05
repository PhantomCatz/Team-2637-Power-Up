package mechanisms;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import components.CatzXboxController;

public class CatzGrabber {

	CatzRobotMap instance;

	public void openFlap() {

		instance = CatzRobotMap.getInstance();
		boolean toggleSol = false;

		if (instance.xbox.getLeftBumper() == true) {
			toggleSol = true;

			if (toggleSol == true)
				instance.intakeOpen.set(true);

			else
				instance.intakeOpen.set(false);
		}

	}
	
	
	public void intakeCube()
	{
		instance = CatzRobotMap.getInstance();
		
		if(instance.xbox.getRightBumper() == true)
		{
			instance.intake.setSpeed(0.7);
			instance.intake2.setSpeed(0.7);
		}
		else
		{
			instance.intake.setSpeed(0);
			instance.intake2.setSpeed(0);
		}	
	} 
	
	public void retractIntake()
	{
	
		instance = CatzRobotMap.getInstance();
		
		if(instance.xbox.getAButton() == true)
			instance.intakeDeploy.set(false);
		
	}
	
	public void deployIntake()
	{
		
		instance = CatzRobotMap.getInstance();
		
		if(instance.xbox.getBButton() == true)
			instance.intakeDeploy.set(true);
	}
}
