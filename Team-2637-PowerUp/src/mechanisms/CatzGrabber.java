/************************************************
 * Timothy Vu
 * 
 * Last Revised: 2/19/18
 * 
 * added printout debug data code
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
	public CatzGrabber() {
		printOutDebugData("CatzGrabber successfully initialized");
	}
	
	private static void deployGrabber()
	{
		CatzRobotMap.grabber.deployBicep();
		CatzRobotMap.grabber.openForearm();
	}
	private static void retractGrabber()
	{
		CatzRobotMap.grabber.closeForearm();
		CatzRobotMap.grabber.retractBicep();
	}
	
	public void setIntakeSpeed(double relativeSpeed) {
		CatzRobotMap.intakeLeft.set(relativeSpeed);
		CatzRobotMap.intakeRight.set(-relativeSpeed);
	}
	
	public void openForearm() {
		CatzConstants.forearmOpen = true;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmOpen);
		printOutDebugData("Grabber forearm set to Open");
	}

	public void closeForearm() {
		CatzConstants.forearmOpen = false;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmOpen);
		printOutDebugData("Grabber forearm set to Closed");
	}
	
	public void toggleForearm() {
		if(CatzConstants.forearmOpen == true) {
			this.closeForearm();
		}
		else{
			this.openForearm();
		}
	}

	public void retractBicep() { 
		CatzConstants.bicepDeployed = false;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepDeployed);
		printOutDebugData("Grabber Bicep set to Retract");
	}

	public void deployBicep() {
		CatzConstants.bicepDeployed = true;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepDeployed);
		printOutDebugData("Grabber forearm set to Deploy");
	}
	
	private static void printOutDebugData(String data){
		if(CatzRobotMap.debugMode==true) {
			System.out.println(data);
		}
	}
}
