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

	public void setIntakeSpeed(double relativeSpeed) {
		CatzRobotMap.intakeLeft.set(relativeSpeed);
		CatzRobotMap.intakeRight.set(-relativeSpeed);
	}
	
	public void forearmOpen() 
	{
		CatzConstants.forearmOpen=true;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmOpen);
	}

	public void forearmClose() 
	{
		CatzConstants.forearmOpen=false;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmOpen);
	}
	
	public void toggleForearm() {
		if(CatzConstants.forearmOpen==true) {
			this.forearmClose();
		}
		else {
			this.forearmOpen();
		}
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
