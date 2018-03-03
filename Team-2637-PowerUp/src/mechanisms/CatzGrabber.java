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
	static public boolean bicepRetracted = true;
	static public boolean forearmClosed = true;
	
	public CatzGrabber() {
		printOutDebugData("Successfully initialized CatzGrabber");
	}
	
	public void setIntakeSpeed(double relativeSpeed) {
		CatzRobotMap.intakeLeft.set(relativeSpeed);
		CatzRobotMap.intakeRight.set(-relativeSpeed);
		//if bump switch is pressed then set at 0
	}
	
	public void openForearm() {
		forearmClosed = false;
		CatzRobotMap.intakeForearm.set(forearmClosed);
		printOutDebugData("Grabber forearm set to Open");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}

	public void closeForearm() {
		forearmClosed = true;
		CatzRobotMap.intakeForearm.set(forearmClosed);
		printOutDebugData("Grabber forearm set to Closed");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}
	
	public void toggleForearm() {
		if(forearmClosed == false) {
			this.closeForearm();
		}
		else{
			this.openForearm();
		}
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}

	public void retractBicep() { 
		bicepRetracted = true;
		CatzRobotMap.intakeBicep.set(bicepRetracted);
		printOutDebugData("Grabber Bicep set to Retract");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}

	public void deployBicep() {
		bicepRetracted = false;
		CatzRobotMap.intakeBicep.set(bicepRetracted);
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
