package mechanisms;

import robot.CatzConstants;
import robot.CatzRobotMap;

//import PIDFunctions.PID;
//import edu.wpi.first.wpilibj.Timer;


/*
 *  Author : Derek Duenas
 *  Last Revised : 2-19-18
 *  Added debug data printouts
 *  Methods : lift
 *  Functionality : Move the lift up and down
 */

public class CatzLift 
{
	/*need to acquire for final robot*/final static public double LIFT_SCALE_HEIGHT = 414762.0;
	/*need to acquire for final robot*/final static public double LIFT_SWITCH_HEIGHT = 120930.0;
	/*need to acquire for final robot*/public static final double LIFTER_ERROR_THRESHOLD_PULSES = 60;
	/*need to acquire for final robot*/final static public double LIFT_SPEED = .5;
	public static final double INITIAL_LIFTER_ERROR = 1000;
	private boolean readyToLift;
	
	public CatzLift() {
		readyToLift = true;
		printOutDebugData("Successfully initialized CatzLift");
	}
	
	
	/**
	 * liftingTo____Height:
	 * The three methods below will move the lifter to the desired height
	 * in parallel with other code. For example, you can run one of these 
	 * lift loops and drive/turn at the same time by using threading.
	 */
	public void liftToSwitchHeight(){
		Thread t = new Thread(() -> {
            double error=INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				if(CatzRobotMap.liftEncoder.get()>30) {
					System.out.println("ERROR: Lifter must be set at bottom position to run Thread");
				} else {
					while(error>LIFTER_ERROR_THRESHOLD_PULSES) {
		            	if(CatzRobotMap.liftEncoder.get() < LIFT_SWITCH_HEIGHT)
		        			this.liftUp();
		        		else
		        			this.stopLift();
		            	error = Math.abs(CatzRobotMap.liftEncoder.get()-LIFT_SWITCH_HEIGHT);
					}
					this.stopLift();
					readyToLift = false;
					printOutDebugData("Lift to switch height thread complete");
				}
				Thread.currentThread().interrupt();

            }
        });
        t.start();
		
	}
	
	public void liftToScaleHeight(){
		Thread t = new Thread(() -> {
            double error=INITIAL_LIFTER_ERROR;
			while (!Thread.interrupted()) {
				if(CatzRobotMap.liftEncoder.get()>30 || readyToLift == false) {
					System.out.println("ERROR: Lifter must be set at bottom position to run Thread");
				} else {
					while(error>LIFTER_ERROR_THRESHOLD_PULSES) {
		            	if(CatzRobotMap.liftEncoder.get() < LIFT_SCALE_HEIGHT)
		        			this.liftUp();
		        		else
		        			this.stopLift();
		            	error = Math.abs(CatzRobotMap.liftEncoder.get()-LIFT_SCALE_HEIGHT);
					}
					this.stopLift();
					readyToLift = false;
					printOutDebugData("Lift to scale height thread complete");
				}
				Thread.currentThread().interrupt();
            }
        });
        t.start();
	}
	
	public void dropToGroundHeight(){
		Thread t = new Thread(() -> {
            
			while (!Thread.interrupted()) {
				
	            double initialReading = CatzRobotMap.liftEncoder.get();
	            double target = initialReading*2;
	            double error = target - initialReading;
	            
				while(error>LIFTER_ERROR_THRESHOLD_PULSES) {
	        		this.liftDown();
	            	error = target - CatzRobotMap.liftEncoder.get();
				}
				
				this.stopLift();
				readyToLift = true;
				printOutDebugData("Drop to ground thread complete");
				Thread.currentThread().interrupt();
				
            }
        });
        t.start();
	}

	
	/*need to acquire for final robot*/static public double PID_LIFT_KP = 0.0508;  //0.0508
	/*need to acquire for final robot*/static public double PID_LIFT_KD = 0.008;  //0.0744
	/*need to acquire for final robot*/static public double PID_LIFT_KI = 0.0;
	/*need to acquire for final robot*/static public double PID_LIFT_POWER_SCALE_FACTOR = 1.0;    //0.7;
	
	
	/*
	public void liftToScaleHeight2(){
		
		PID liftPID = new PID();
		Timer timer  = new Timer();
		
		
		Thread t = new Thread(() -> {
			
			while (!Thread.interrupted()) {
				while(liftPID.getError()>LIFTER_ERROR_THRESHOLD_PULSES) {
					
					liftPID.setPreviousTime(liftPID.getCurrentTime());
					liftPID.setCurrentTime(timer.getMatchTime());
					liftPID.deltaTCalculate();
					
					liftPID.calculatePID(LIFT_SCALE_HEIGHT,CatzRobotMap.liftEncoder.get(),PID_LIFT_POWER_SCALE_FACTOR);
					
				}
				this.stopLift();
				printOutDebugData("Lift to scale height thread complete");
				Thread.currentThread().interrupt();
            }
        });
        t.start();
	}
	
	*/
	
	public void liftUp()
	{
		CatzRobotMap.lifterL.set(LIFT_SPEED);
		CatzRobotMap.lifterR.set(LIFT_SPEED);
	}
	public void liftDown()
	{
		CatzRobotMap.lifterL.set(-LIFT_SPEED);
		CatzRobotMap.lifterR.set(-LIFT_SPEED);
	}
	public void stopLift()
	{
		CatzRobotMap.lifterL.set(0);
		CatzRobotMap.lifterR.set(0);
	}
	
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}
