package mechanisms;

import org.usfirst.frc.team2637.robot.CatzRobotMap;
import constants.CatzConstants;

/*
 *  Author : Derek Duenas
 *  Last Revised : 2-19-18
 *  Added debug data printouts
 *  Methods : lift
 *  Functionality : Move the lift up and down
 */

public class CatzLift 
{
	public CatzLift() {
		printOutDebugData("CatzLift successfully initialized");
	}
	
	public void liftToSwitchHeight(){
		Thread t = new Thread(() -> {
            double error=CatzConstants.INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				while(error>CatzConstants.LIFTER_ERROR_THRESHOLD_PULSES) {
	            	if(CatzRobotMap.liftEncoder.get() < CatzConstants.LIFT_SWITCH_HEIGHT)
	        			this.liftUp();
	        		else if(CatzRobotMap.liftEncoder.get() > CatzConstants.LIFT_SWITCH_HEIGHT)
	        			this.liftDown();
	        		else
	        			this.stopLift();
	            	error = Math.abs(CatzRobotMap.liftEncoder.get()-CatzConstants.LIFT_SWITCH_HEIGHT);
				}
				this.stopLift();
				printOutDebugData("Lift to switch height thread complete");
				Thread.currentThread().interrupt();
            }
        });
        t.start();
		
	}
	
	public void liftToScaleHeight(){
		Thread t = new Thread(() -> {
            double error=CatzConstants.INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				while(error>CatzConstants.LIFTER_ERROR_THRESHOLD_PULSES) {
	            	if(CatzRobotMap.liftEncoder.get() < CatzConstants.LIFT_SCALE_HEIGHT)
	        			this.liftUp();
	        		else if(CatzRobotMap.liftEncoder.get() > CatzConstants.LIFT_SCALE_HEIGHT)
	        			this.liftDown();
	        		else
	        			this.stopLift();
	            	error = Math.abs(CatzRobotMap.liftEncoder.get()-CatzConstants.LIFT_SCALE_HEIGHT);
				}
				this.stopLift();
				printOutDebugData("Lift to scale height thread complete");
				Thread.currentThread().interrupt();
            }
        });
        t.start();
	}
	
	public void setLiftSpeed(double relativeSpeed) {
		CatzRobotMap.lifterL.set(relativeSpeed);
		CatzRobotMap.lifterR.set(relativeSpeed);
	}
	
	public void liftUp(){
		CatzRobotMap.lifterL.set(CatzConstants.LIFT_SPEED);
		CatzRobotMap.lifterR.set(CatzConstants.LIFT_SPEED);
	}
	
	public void liftDown(){
		CatzRobotMap.lifterL.set(-CatzConstants.LIFT_SPEED);
		CatzRobotMap.lifterR.set(-CatzConstants.LIFT_SPEED);
	}
	
	public void stopLift(){
		CatzRobotMap.lifterL.set(CatzConstants.ZERO);
		CatzRobotMap.lifterR.set(CatzConstants.ZERO);
	}
	
	private static void printOutDebugData(String data){
		if(CatzRobotMap.debugMode==true) {
			System.out.println(data);
		}
	}
}
