package mechanisms;

import constants.CatzConstants;
import robot.CatzRobotMap;

/*
 *  Author : Derek Duenas
 *  Last Revised : 2-19-18 AL
 *  added debug data printout
 *  Methods : climb
 *  Functionality : Climb
 */

public class CatzClimber 
{
	public CatzClimber() {
		printOutDebugData("Successfully instantiated CatzClimber");
	}
	public void setClimberSpeed(double relativeSpeed) {
		CatzRobotMap.climberMotor.set(relativeSpeed);
	}
	public void climbUp() {
		CatzRobotMap.climberMotor.set(CatzConstants.CLIMB_SPEED);
		//CatzRobotMap.climber2.set(CatzConstants.CLIMB_SPEED);
	}
	public void climbDown() {
		CatzRobotMap.climberMotor.set(-CatzConstants.CLIMB_SPEED);
		//CatzRobotMap.climber2.set(-CatzConstants.CLIMB_SPEED);
	}
	public void stopClimb() {
		CatzRobotMap.climberMotor.set(CatzConstants.ZERO);
		//CatzRobotMap.climber2.set(CatzConstants.ZERO);
	}
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}
