package mechanisms;

import edu.wpi.first.wpilibj.Timer;
import robot.CatzConstants;
import robot.CatzRobotMap;

/*
 *  Author : Derek Duenas
 *  
 *  Revision History : 
 *  2-19-18	Added debug data printouts
 *  3-16-18 Removed lift to scale height 2 method
 *  3-17-18 Using the bottom limit switch instead of encoder for the lift to go down all the way
 *  
 *  Methods : lift
 *  Functionality : Move the lift up and down
 */

public class CatzLift {
	/* need to acquire for final robot */final static private double LIFT_SCALE_HEIGHT = 674898.0;
	/* need to acquire for final robot */final static private double LIFT_SWITCH_HEIGHT = 201349.0;
	/* need to acquire for final robot */private static final double LIFTER_ERROR_THRESHOLD_PULSES = 600;
	/* need to acquire for final robot */final static private double LIFT_SPEED = 1.0;
	private static final double INITIAL_LIFTER_ERROR = 1000;
	public static boolean threadComplete;
	public static boolean liftThreadRunning;
	private static Timer timeout = new Timer();

	public CatzLift() {
		threadComplete = false;
		liftThreadRunning = false;
		printOutDebugData("Successfully initialized CatzLift");
	}

	/**
	 * liftingTo____Height: The three methods below will move the lifter to the
	 * desired height in parallel with other code. For example, you can run one of
	 * these lift loops and drive/turn at the same time by using threading.
	 */
	public void liftToSwitchHeight() {
		Thread t = new Thread(() -> {
			double error = INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				
				if(liftThreadRunning == true) {
					while(liftThreadRunning == true) {
						//wait for previous thread to finish
					}
				}
				liftThreadRunning = true;
				
				threadComplete = false;
				timeout.start();
				printOutDebugData("Lifter thread beginning");
				this.liftUp();
				while (error > LIFTER_ERROR_THRESHOLD_PULSES && timeout.get() < 3 && CatzRobotMap.lifterLimitTop.get()==false) {
					error = Math.abs(CatzRobotMap.liftEncoder.get() - LIFT_SWITCH_HEIGHT);
				}
				this.stopLift();
				timeout.stop();
				timeout.reset();

				liftThreadRunning = false;
				threadComplete = true;
				printOutDebugData("Lift to switch height thread complete");
				Thread.currentThread().interrupt();
			}
		});
		t.start();

	}

	public void liftToScaleHeight() {
		Thread t = new Thread(() -> {
			double error = INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				
				if(liftThreadRunning == true) {
					while(liftThreadRunning == true) {
						//wait for previous thread to finish
					}
				}
				liftThreadRunning = true;
				
				threadComplete = false;
				timeout.start();
				printOutDebugData("Lifter thread beginning");
				this.liftUp();
				while (error > LIFTER_ERROR_THRESHOLD_PULSES && timeout.get() < 7.0 && CatzRobotMap.lifterLimitTop.get()==false) {
					error = Math.abs(CatzRobotMap.liftEncoder.get() - LIFT_SCALE_HEIGHT);
				}
				this.stopLift();
				timeout.stop();
				timeout.reset();

				liftThreadRunning = false;
				threadComplete = true;
				printOutDebugData("Lift to scale height thread complete");
				Thread.currentThread().interrupt();
			}
		});
		t.start();
	}

	public void dropToGroundHeight() {

		Thread t = new Thread(() -> {
			double error = INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				
				if(liftThreadRunning == true) {
					while(liftThreadRunning == true) {
						//wait for previous thread to finish
					}
				}
				liftThreadRunning = true;
				
				threadComplete = false;
				timeout.start();
				printOutDebugData("Lifter thread beginning");
				this.liftDown();
				while (CatzRobotMap.lifterLimitBottom.get()==false&&timeout.get()<4.0) {
					//wait for bottom limit switch to read true
				}
				this.stopLift();
				timeout.stop();
				timeout.reset();

				liftThreadRunning = false;
				threadComplete = true;
				printOutDebugData("Lift to switch height thread complete");
				Thread.currentThread().interrupt();
			}
		});
		t.start();
	}


	public void liftUp() {
		CatzRobotMap.lifterL.set(LIFT_SPEED);
		CatzRobotMap.lifterR.set(LIFT_SPEED);
	}

	public void liftDown() {
		CatzRobotMap.lifterL.set(-0.9); // It was 1 JK 3/30
		CatzRobotMap.lifterR.set(-0.9);
	}

	public void stopLift() {
		CatzRobotMap.lifterL.set(0);
		CatzRobotMap.lifterR.set(0);
	}
	
	public void setReadyToLift(boolean ready) {
		threadComplete = ready;
	}

	private static void printOutDebugData(String info) {
		if (CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}
