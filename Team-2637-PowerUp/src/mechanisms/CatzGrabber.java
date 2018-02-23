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

import constants.CatzConstants;
import robot.CatzRobotMap;

public class CatzGrabber 
{
	public CatzGrabber() {
		printOutDebugData("CatzGrabber successfully initialized");
	}
	
	public void setIntakeSpeed(double relativeSpeed) {
		CatzRobotMap.intakeLeft.set(relativeSpeed);
		CatzRobotMap.intakeRight.set(-relativeSpeed);
	}
	
	public void openForearm() {
		CatzConstants.forearmClosed = false;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmClosed);
		printOutDebugData("Grabber forearm set to Open");
	}

	public void closeForearm() {
		CatzConstants.forearmClosed = true;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmClosed);
		printOutDebugData("Grabber forearm set to Closed");
	}
	
	public void toggleForearm() {
		if(CatzConstants.forearmClosed == false) {
			this.closeForearm();
		}
		else{
			this.openForearm();
		}
	}

	public void retractBicep() { 
		CatzConstants.bicepRetracted = true;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepRetracted);
		printOutDebugData("Grabber Bicep set to Retract");
	}

	public void deployBicep() {
		CatzConstants.bicepRetracted = false;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepRetracted);
		printOutDebugData("Grabber forearm set to Deploy");
	}
	
	private static void printOutDebugData(String data){
		if(CatzRobotMap.debugMode==true) {
			System.out.println(data);
		}
	}
}
