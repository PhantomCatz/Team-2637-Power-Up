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

	public void forearmOpen() 
	{
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmOpen);
	}

	public void forearmClose() 
	{
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmOpen);
	}

	public void intakeCube() 
	{
		CatzRobotMap.intakeRight.setSpeed(CatzConstants.INTAKE_SPEED);
		CatzRobotMap.intakeLeft.setSpeed(-CatzConstants.INTAKE_SPEED);
	}

	public void launchCube() 
	{
		CatzRobotMap.intakeRight.setSpeed(-CatzConstants.INTAKE_SPEED);
		CatzRobotMap.intakeLeft.setSpeed(CatzConstants.INTAKE_SPEED);
	}

	public void retractBicep() 
	{ 
		CatzConstants.bicepDeployed = false;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepDeployed);
	}

	public void deployBicep() 
	{
		CatzConstants.bicepDeployed = true;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepDeployed);
	}
}
