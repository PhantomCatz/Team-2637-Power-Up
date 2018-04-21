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
 *  4-5-18  Changed lift method to go to different heights
 *  
 *  Methods : lift
 *  Functionality : Move the lift up and down
 */

public class CatzLift {

	//694851.675/80; //
	
	final private static double LIFT_COUNTS_PER_INCH          =   505026.0 / 80.0;    //527221.0/80.0;  // 674898.0 / 60.0 - wrong counts per inch value

	final private static double LIFT_SCALE_HEIGHT             = 68.0 * LIFT_COUNTS_PER_INCH;
	final private static double LIFT_SWITCH_HEIGHT            = 17 * LIFT_COUNTS_PER_INCH; //TODO test
	final private static double LIFTER_ERROR_THRESHOLD_PULSES = LIFT_COUNTS_PER_INCH;

	final private static double LIFT_SPEED = 1.0;

	static boolean threadComplete;
	public static boolean liftThreadRunning;

	private static Timer timeout = new Timer();

	public CatzLift() {
		threadComplete    = false;
		liftThreadRunning = false;
		printOutDebugData("Successfully initialized CatzLift");
	}

	/**
	 * liftingTo____Height: The three methods below will move the lifter to the
	 * desired height in parallel with other code. For example, you can run one of
	 * these lift loops and drive/turn at the same time by using threading.
	 */
	
	//RENAME listToScaleHeight() to liftToHeight() AND PASS IN HEIGHT IN INCHES
	//THEN GET RID OF SWITCH HEIGHT AND USE COMMON FUNCTION SO WE DON'T HAVE TO MAINTAIN TWO 
	//VERSIONS OF THE SAME CODE

	public void liftToHeight(double height) {
		Thread t = new Thread(() -> {
			double error = 0;
			int count = 0;
			double counts = height * LIFT_COUNTS_PER_INCH;
			double targetCount = CatzRobotMap.liftEncoder.get() + counts;
      
			int limitSwitchCount = 0;

			
			boolean done             = false;
			boolean limitSwitchState = false;
			
			while (!Thread.interrupted()) {

				printOutDebugData("Lifter scale thread beginning");
				timeout.reset();
				timeout.start();

				while(liftThreadRunning == true) {
					///wait for previous thread to finish
					Timer.delay(0.005);
				}

				liftThreadRunning = true;
				threadComplete    = false;
				double startingCount = CatzRobotMap.liftEncoder.get();
				targetCount = startingCount + counts;
				
				this.liftUp();
				while (done == false && timeout.get() < 3.5) 

				{
					count = CatzRobotMap.liftEncoder.get();
					error = Math.abs(targetCount - count);

					if(error < LIFTER_ERROR_THRESHOLD_PULSES) {
						done = true;
					}
					
					limitSwitchState = CatzRobotMap.lifterLimitTop.get();
					if (limitSwitchState == true)
					{
						limitSwitchCount++;
						System.out.println("limitTripped");
					}
					else
					{
						limitSwitchCount = 0;
					}
					
					if(limitSwitchCount > 2)
					{
						done = true;
					}
					Timer.delay(0.010);
				}
				this.stopLift();
				liftThreadRunning = false;
				threadComplete    = true;

				printOutDebugData("Lift to scale height thread complete");

				System.out.println(timeout.get() + ": " + count + ", " + error + ", " + startingCount + ", " + targetCount);

				timeout.stop();
				timeout.reset();

				Thread.currentThread().interrupt();
			}
		});
		t.start();
	}

	public void dropToGroundHeight() {

		Thread t = new Thread(() -> {
			int count = 0;

			boolean done             = false;
			boolean limitSwitchState = false;


			while (!Thread.interrupted()) {
				int limitSwitchStateCounter = 0;
				printOutDebugData("dropToGroundHeight thread beginning");
				timeout.reset();
				timeout.start();
				limitSwitchState = false;
				
				while(liftThreadRunning == true) {
					//wait for previous thread to finish
					Timer.delay(0.005);
				}

				liftThreadRunning = true;	
				threadComplete    = false;
				
				this.liftDown();
				System.out.println("Limit Switch: " + limitSwitchState);

				while (done == false && timeout.get() < 5.0) {	//wait for bottom limit switch to read true
					
					count            = CatzRobotMap.liftEncoder.get();
					limitSwitchState = CatzRobotMap.lifterLimitBottom.get();
					
					if(limitSwitchState)
					{
						limitSwitchStateCounter++;
					}
					else
					{
						limitSwitchStateCounter = 0;
					}
					
					if(limitSwitchStateCounter > 5)
					{
						done = true;
					}
					System.out.println("Limit Switch: " + limitSwitchState);
					Timer.delay(0.010);
				}
				this.stopLift();

				liftThreadRunning = false;
				threadComplete    = true;

				printOutDebugData("Drop to ground height thread complete");
				System.out.println(timeout.get() + ": " + count + ", " + limitSwitchState);

				timeout.stop();
				timeout.reset();

				Thread.currentThread().interrupt();
			}
		});
		t.start();
	}


	public void liftUp() {
		CatzRobotMap.lifterRightLeft.set(LIFT_SPEED);
		CatzRobotMap.lifterRightRight.set(LIFT_SPEED);
		CatzRobotMap.lifterLeftRight.set(LIFT_SPEED);
		CatzRobotMap.lifterLeftLeft.set(LIFT_SPEED);
	}

	public void liftDown() {
		CatzRobotMap.lifterRightLeft.set(-0.8);
		CatzRobotMap.lifterRightRight.set(-0.8);
		CatzRobotMap.lifterLeftRight.set(-0.8);
		CatzRobotMap.lifterLeftLeft.set(-0.8);
	}

	public void stopLift() {
		CatzRobotMap.lifterRightLeft.set(0);
		CatzRobotMap.lifterRightRight.set(0);
		CatzRobotMap.lifterLeftRight.set(0);
		CatzRobotMap.lifterLeftLeft.set(0);
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
