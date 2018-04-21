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
	static public boolean bicepDeployed = false;
	static public boolean forearmOpen = false;
	
	public CatzGrabber() {
		printOutDebugData("Successfully initialized CatzGrabber");
	}
	
	public void setIntakeSpeed(double relativeSpeed) {
		CatzRobotMap.intakeLeft.set(relativeSpeed);
		CatzRobotMap.intakeRight.set(-relativeSpeed);
		//if bump switch is pressed then set at 0
	}
	
	public void closeForearm() {
		forearmOpen = false;
		CatzRobotMap.intakeForearm.set(forearmOpen);
		printOutDebugData("Grabber forearm set to Closed");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}
	public void closeForearm(double delay) {
		forearmOpen = false;
		CatzRobotMap.intakeForearm.set(forearmOpen);
		printOutDebugData("Grabber forearm set to Closed");
		Timer.delay(delay);
	}

	public void openForearm() {
		forearmOpen = true;
		CatzRobotMap.intakeForearm.set(forearmOpen);
		printOutDebugData("Grabber forearm set to Open");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}
	
	public void openForearm(double delaySec) {
		forearmOpen = true;
		CatzRobotMap.intakeForearm.set(forearmOpen);
		printOutDebugData("Grabber forearm set to Open");
		Timer.delay(delaySec);
	}
	
	public void toggleForearm()
	{
		if(forearmOpen == true) 
		{
			this.closeForearm();
		}
		else{
			this.openForearm();
		}
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}

	public void deployBicep() 
	{ 
		bicepDeployed = true;
		CatzRobotMap.intakeBicep.set(true); // (whc) 4/5/18 changed to a direct passing of "true"
		printOutDebugData("Grabber Bicep set to Deploy");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}
	public void deployBicep(double delay) { 
		bicepDeployed = true;
		CatzRobotMap.intakeBicep.set(true);  //whc 4/5/18 changed to directly passing "true"
		printOutDebugData("Grabber Bicep set to Deploy");
		Timer.delay(delay);
	}

	public void retractBicep() {
		bicepDeployed = false;
		CatzRobotMap.intakeBicep.set(false); //whc 4/5/18 changed to directly passing "false"
		printOutDebugData("Grabber forearm set to Retract");
		Timer.delay(CatzConstants.FUNCTION_EXECUTION_DELAY);
	}
	
	public void retractBicep(double delay) {
		bicepDeployed = false;
		CatzRobotMap.intakeBicep.set(false); //whc 4/5/18 changed to directly passing "false"
		printOutDebugData("Grabber forearm set to Retract");
		Timer.delay(delay);
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
	public void dropCube()
	{
		this.deployBicep();
		this.openForearm(0);
		Timer.delay(1);
		this.closeForearm(0);
		this.retractBicep();

	}
	public void placeCube(double speed)
	{
		this.deployBicep();
		this.setIntakeSpeed(speed);
		Timer.delay(CatzConstants.CUBE_OUTTAKE_WAIT_TIME);
		this.retractBicep();
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
