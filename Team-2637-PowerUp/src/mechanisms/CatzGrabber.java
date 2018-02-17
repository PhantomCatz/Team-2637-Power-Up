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

public class CatzGrabber 
{
	CatzRobotMap instance = CatzRobotMap.getInstance();

	public void forearmOpen() 
	{
		instance.intakeForearm.set(CatzConstants.forearmOpen);
	}

	public void forearmClose() 
	{
		instance.intakeForearm.set(CatzConstants.forearmOpen);
	}

	public void intakeCube() 
	{
		instance.intakeRight.setSpeed(CatzConstants.INTAKE_SPEED);
		instance.intakeLeft.setSpeed(-CatzConstants.INTAKE_SPEED);
	}

	public void launchCube() 
	{
		instance.intakeRight.setSpeed(-CatzConstants.INTAKE_SPEED);
		instance.intakeLeft.setSpeed(CatzConstants.INTAKE_SPEED);
	}

	public void retractBicep() 
	{ 
		CatzConstants.bicepDeployed = false;
		instance.intakeBicep.set(CatzConstants.bicepDeployed);
	}

	public void deployBicep() 
	{
		CatzConstants.bicepDeployed = true;
		instance.intakeBicep.set(CatzConstants.bicepDeployed);
	}
}
