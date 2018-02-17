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

	CatzRobotMap instance;

	public void forearmOpen() {

		CatzConstants.forearmOpen = true;
		instance.intakeForearm.set(CatzConstants.forearmOpen);

	}

	public void forearmClose() {
		CatzConstants.forearmOpen = false;
		instance.intakeForearm.set(CatzConstants.forearmOpen);

	}

	public void intakeCube() { // meant to be used with right trigger
		instance.intakeRight.setSpeed(CatzConstants.INTAKE_SPEED);
		instance.intakeLeft.setSpeed(-CatzConstants.INTAKE_SPEED);

	}

	public void launchCube() { // meant to be used with left trigger

		instance.intakeRight.setSpeed(-CatzConstants.INTAKE_SPEED);
		instance.intakeLeft.setSpeed(CatzConstants.INTAKE_SPEED);

	}
	
	public void stopLauncher() {
		instance.intakeRight.setSpeed(0);
		instance.intakeLeft.setSpeed(0);
		
	}

	public void retractBicep() { // meant to be used with right bumper
		CatzConstants.bicepDeployed = false;
		instance.intakeBicep.set(CatzConstants.bicepDeployed);

	}

	public void deployBicep() { // meant to be used with left bumper
		CatzConstants.bicepDeployed = true;
		instance.intakeBicep.set(CatzConstants.bicepDeployed);
	}


}
