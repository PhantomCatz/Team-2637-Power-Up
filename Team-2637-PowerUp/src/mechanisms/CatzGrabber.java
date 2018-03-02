/************************************************
 * Timothy Vu
 * 
 * Last Revised: 2/20/18 AL
 * 
 * added printout debug data code
 * flipped polarity of forearm and bicep solenoids
 * 
 * Methods: openFlapToggle(), intakeCube(), launchCube(), 
 * deployIntake(), retractIntake()
 * 
 * Functionality:
 ***********************************************/

package mechanisms;

import edu.wpi.first.wpilibj.Timer;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class CatzGrabber 
{
	public CatzGrabber() {
		printOutDebugData("CatzGrabber successfully initialized");
	}
	
	public void setIntakeSpeed(double relativeSpeed) {
		CatzRobotMap.intakeLeft.set(relativeSpeed);
		CatzRobotMap.intakeRight.set(-relativeSpeed);
		//if bump switch is pressed then set at 0
	}
	
	public void openForearm() {
		CatzConstants.forearmClosed = false;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmClosed);
		printOutDebugData("Grabber forearm set to Open");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}

	public void closeForearm() {
		CatzConstants.forearmClosed = true;
		CatzRobotMap.intakeForearm.set(CatzConstants.forearmClosed);
		printOutDebugData("Grabber forearm set to Closed");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}
	
	public void toggleForearm() {
		if(CatzConstants.forearmClosed == false) {
			this.closeForearm();
		}
		else{
			this.openForearm();
		}
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}

	public void retractBicep() { 
		CatzConstants.bicepRetracted = true;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepRetracted);
		printOutDebugData("Grabber Bicep set to Retract");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}

	public void deployBicep() {
		CatzConstants.bicepRetracted = false;
		CatzRobotMap.intakeBicep.set(CatzConstants.bicepRetracted);
		printOutDebugData("Grabber forearm set to Deploy");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}
	
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
	public void retractGrabber() 
	{
		this.closeForearm();
		this.retractBicep();
	}
	public void deployGrabber()
	{
		this.deployBicep();
		this.openForearm();
	}
	public void shootCube()
	{
		this.deployBicep();
		this.setIntakeSpeed(-CatzConstants.INTAKE_SPEED);
		Timer.delay(CatzConstants.CUBE_OUTTAKE_WAIT_TIME);
		this.setIntakeSpeed(0.0);
	}
	public void intakeCube()
	{
		this.deployBicep();
		this.setIntakeSpeed(CatzConstants.INTAKE_SPEED);
		Timer.delay(CatzConstants.CUBE_OUTTAKE_WAIT_TIME);
		this.setIntakeSpeed(0.0);
	}
}
