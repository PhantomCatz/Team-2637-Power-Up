/************************************************
 * Timothy Vu
 * 
 * Last Revised: 2/9/17 TV
 * 
 * Fixed bad logic within methods
 * 
 * Methods: openFlapToggle(), intakeCube(), launchCube(), 
 * deployIntake(), retractIntake()
 * 
 * Functionality:
 ***********************************************/

package mechanisms;

import org.usfirst.frc.team2637.robot.CatzRobotMap;
import constants.CatzConstants;

public class CatzGrabber {

	CatzRobotMap instance = CatzRobotMap.getInstance();

	public void openFlapToggle() {          // meant to be used with A button

		if (CatzConstants.flapOpen == true) {
			CatzConstants.flapOpen = false;
			instance.intakeOpen.set(CatzConstants.flapOpen);
			
		} 
		else {               // flapOpen == false
			CatzConstants.flapOpen = true;
			instance.intakeOpen.set(CatzConstants.flapOpen);
		}
	}

	public void intakeCube() // meant to be used with right trigger
	{
		instance.intakeRight.setSpeed(CatzConstants.INTAKE_SPEED);
		instance.intakeLeft.setSpeed(-CatzConstants.INTAKE_SPEED);

	}

	public void launchCube() // meant to be used with left trigger
	{

		instance.intakeRight.setSpeed(-CatzConstants.INTAKE_SPEED);
		instance.intakeLeft.setSpeed(CatzConstants.INTAKE_SPEED);

	}

	public void retractIntake() // meant to be used with right bumper
	{
		CatzConstants.grabberDeployed = false;
		instance.intakeDeploy.set(CatzConstants.grabberDeployed);

	}

	public void deployIntake() // meant to be used with left bumper
	{
		CatzConstants.grabberDeployed = true;
		instance.intakeDeploy.set(CatzConstants.grabberDeployed);
	}
}
