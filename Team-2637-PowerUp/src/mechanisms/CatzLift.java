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
	/* need to acquire for final robot */final static private double LIFT_SCALE_HEIGHT = 414762.0;
	/* need to acquire for final robot */final static private double LIFT_SWITCH_HEIGHT = 120930.0;
	/* need to acquire for final robot */private static final double LIFTER_ERROR_THRESHOLD_PULSES = 60;
	/* need to acquire for final robot */final static private double LIFT_SPEED = 1.0;
	private static final double INITIAL_LIFTER_ERROR = 1000;
	public static boolean readyToLift;
	private static Timer timeout = new Timer();

	public CatzLift() {
		readyToLift = true;
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

				if (CatzRobotMap.liftEncoder.get() > 60 || readyToLift == false) {
					// printOutDebugData(String.valueOf(readyToLift));
					// printOutDebugData(String.valueOf(CatzRobotMap.liftEncoder.get()));
					// printOutDebugData("ERROR: Lifter must be set at bottom position to run
					// Thread");
				} else {
					timeout.start();
					printOutDebugData("Lifter thread beginning");
					while (error > LIFTER_ERROR_THRESHOLD_PULSES && timeout.get() < 1.5) {
						if (CatzRobotMap.liftEncoder.get() < LIFT_SWITCH_HEIGHT)
							this.liftUp();
						else
							this.stopLift();
						error = Math.abs(CatzRobotMap.liftEncoder.get() - LIFT_SWITCH_HEIGHT);
					}
					this.stopLift();
					timeout.stop();
					timeout.reset();
					readyToLift = false;
					printOutDebugData("Lift to switch height thread complete");
				}
				Thread.currentThread().interrupt();
			}
		});
		t.start();

	}

	public void liftToScaleHeight() {
		Thread t = new Thread(() -> {
			double error = INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				if (CatzRobotMap.liftEncoder.get() > 60 || readyToLift == false) {
					printOutDebugData(String.valueOf(readyToLift));
					printOutDebugData(String.valueOf(CatzRobotMap.liftEncoder.get()));
					printOutDebugData("ERROR: Lifter must be set at bottom position to run Thread");
				} else {
					timeout.start();
					printOutDebugData("Lifter thread beginning");
					while (error > LIFTER_ERROR_THRESHOLD_PULSES && timeout.get() < 4.5) {
						if (CatzRobotMap.liftEncoder.get() < LIFT_SCALE_HEIGHT)
							this.liftUp();
						else
							this.stopLift();
						error = Math.abs(CatzRobotMap.liftEncoder.get() - LIFT_SCALE_HEIGHT);
					}
					this.stopLift();
					timeout.stop();
					timeout.reset();
					readyToLift = false;
					printOutDebugData("Lift to scale height thread complete");
				}
				Thread.currentThread().interrupt();
			}
		});
		t.start();
	}

	public void dropToGroundHeight() {
		Thread t = new Thread(() -> {

			while (!Thread.interrupted()) {

				//double initialReading = CatzRobotMap.liftEncoder.get();
				//double target = initialReading * 2;
				//double error = target - initialReading;
				timeout.start();
				while (CatzRobotMap.lifterLimitBottom.get() && timeout.get() < 3) {
					this.liftDown();
					//error = target - CatzRobotMap.liftEncoder.get();
				}
				timeout.stop();
				timeout.reset();
				this.stopLift();
				readyToLift = true;
				CatzRobotMap.liftEncoder.reset();
				printOutDebugData("Drop to ground thread complete");
				Thread.currentThread().interrupt();

			}
		});
		t.start();
	}

	public void dropToHalfHeight() {
		Thread t = new Thread(() -> {

			while (!Thread.interrupted()) {

				double initialReading = CatzRobotMap.liftEncoder.get();
				double target = initialReading * 1.7;
				double error = target - initialReading;
				timeout.start();
				while (error > LIFTER_ERROR_THRESHOLD_PULSES && timeout.get() < 3) {
					this.liftDown();
					error = target - CatzRobotMap.liftEncoder.get();
				}
				timeout.stop();
				timeout.reset();
				this.stopLift();
				printOutDebugData("Drop to half height thread complete");
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
		CatzRobotMap.lifterL.set(-LIFT_SPEED);
		CatzRobotMap.lifterR.set(-LIFT_SPEED);
	}

	public void stopLift() {
		CatzRobotMap.lifterL.set(0);
		CatzRobotMap.lifterR.set(0);
	}

	private static void printOutDebugData(String info) {
		if (CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}
