package autonomous;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;
import constants.CatzConstants;


/*
 *  Author : Derek Duenas
 *  Revision History : 
 *  	1-28-2018 D. Duenas Translated from C++ and added integral term
 *  	2-4-2018 D. Duenas Revising code
 *  
 *  Methods : PIDturn
 *  Functionality : Accurately turn autonomously
 */
public class CatzPIDTurn
{
	
	/*need to acquire for final robot*/final static public double PID_TURN_FILTER_CONSTANT = .9;
	/*need to acquire for final robot*/static public double PID_TURN_POWER_SCALE_FACTOR = 1.0;    //0.7;
	/*need to acquire for final robot*/static public double PID_TURN_KP = 0.0508;  //0.0508
	/*need to acquire for final robot*/static public double PID_TURN_KD = 0.008;  //0.0744
	/*need to acquire for final robot*/static public double PID_TURN_KI = 0.0;
	/*need to acquire for final robot*/final static public double PID_TURN_INTEGRAL_MAX = 0.0;
	/*need to acquire for final robot*/final static public double PID_TURN_INTEGRAL_MIN = 0.0;
	
	/*need to acquire for final robot*/final public static double PID_TURN_MAX_POWER_RT =  1.0;
	/*need to acquire for final robot*/final public static double PID_TURN_MIN_POWER_RT =  0.4;

	/*need to acquire for final robot*/final public static double PID_TURN_MAX_POWER_LT = -1.0;
	/*need to acquire for final robot*/final public static double PID_TURN_MIN_POWER_LT = -0.4;
	
	/*need to acquire for final robot*/final static public double PID_TURN_THRESHOLD = .12;
	
	static Timer functionTimer;
	static Timer pdTimer;
	static Timer debugTimer;
	
	static double currentError; 
	static double deltaError;
	static double derivative; 
	static double deltaT;
	
	static double timeout;
	
	static double power;
	
	static double previousError;
	static double totalError;
	
	static double currentAngle;
	static double currentAngleAbs;
	static double targetAngle;
	static double targetAngleAbs;
	static double targetUpperLimit;
	static double targetLowerLimit;
	
	static double previousDerivative = 0;

	static boolean done;
	static boolean tuningMode = false;
	static boolean debugMode = false;
	static String debugData;
	
	
	
	public static void PIDturn(double degreesToTurn, double timeoutSeconds)
	{
		boolean firstTime = true;
		functionTimer = new Timer();
		pdTimer = new Timer();
		
		CatzRobotMap.navx.reset();
		
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);
		
		done = false;
		
		previousError = 0.0;
		totalError = 0.0;
		
		functionTimer.reset();
		functionTimer.start();
		
		
		currentAngle = CatzRobotMap.navx.getAngle();
		targetAngle = degreesToTurn + currentAngle;
		currentError = targetAngle - currentAngle;
		
		targetAngleAbs = Math.abs(targetAngle);
		
		if(timeoutSeconds < 0.0)
			if(targetAngleAbs < 91.0)
				timeout = 1.2;
			else if(targetAngleAbs < 150.0)
				timeout = 2.0;
			else
				timeout = 2000;
		
		else
			if(timeoutSeconds == 0.0)
				timeout = 2000;
			else
				timeout = timeoutSeconds;
		
		printDebugInit();
		printDebugHeader();
		
		
		pdTimer.reset();
		pdTimer.start();
		while(done == false)
		{
			currentAngle = CatzRobotMap.navx.getAngle();
			deltaT = pdTimer.get();

			currentAngleAbs = Math.abs(currentAngle);
			
			pdTimer.stop();
			pdTimer.reset();
			pdTimer.start();

			// calculates proportional term
			currentError = targetAngle - currentAngle;
			
			
			if (Math.abs(currentError)<PID_TURN_THRESHOLD) {
				done = true;
			} else {
			
				// calculates derivative term
				//filter smoothes derivative graph; ask Walter for specifics
				deltaError = currentError-previousError;
				if (firstTime == false) {
	   			   if ( (deltaError == 0.0) && (Math.abs(currentError) > 3.0 ) ) {
	   				   derivative =  previousDerivative;   
	   			   } else {
	   				   derivative =  PID_TURN_FILTER_CONSTANT*previousDerivative + 
	   			               ((1-PID_TURN_FILTER_CONSTANT)*(deltaError/deltaT));
	   			   }
				} else {
					firstTime = false;
					derivative = 0;
				}
				
				
				previousDerivative = derivative;
				
				previousError = currentError;  // saves error for next iteration
				
				
				// calculates integral term
				totalError += currentError * deltaT;   
				
				if(totalError >= PID_TURN_INTEGRAL_MAX)     // saturation
					totalError = PID_TURN_INTEGRAL_MAX;	 // makes sure the integral term doesn't get too big or small
				
				if(totalError <= PID_TURN_INTEGRAL_MIN)
					totalError = PID_TURN_INTEGRAL_MIN;
				
				
				power = PID_TURN_POWER_SCALE_FACTOR*((PID_TURN_KP * currentError)
														+(PID_TURN_KI * totalError)
														+(PID_TURN_KD * derivative));	
				
				if (power > 0.0) {
					
					if(power > PID_TURN_MAX_POWER_RT)
						power = PID_TURN_MAX_POWER_RT;
					else if (power < PID_TURN_MIN_POWER_RT)
						power = PID_TURN_MIN_POWER_RT;
				} else {
				
	      			if(power < PID_TURN_MAX_POWER_LT)
					    power = PID_TURN_MAX_POWER_LT;
	      			else if (power > PID_TURN_MIN_POWER_LT)
	 				    power = PID_TURN_MIN_POWER_LT;
				}
				
	      			
				CatzRobotMap.drive.tankDrive(power, -power);
				
				if (functionTimer.get() > timeoutSeconds)
					done = true;
				
				printDebugData();
			}
			Timer.delay(0.015); //was .005,.008
		}
		printDebugData();
		CatzRobotMap.drive.tankDrive(0.0, 0.0); // makes robot stop
		
		functionTimer.stop();
		pdTimer.stop();
	}
	
	
	public static void setPIDTurnDebugModeEnabled(boolean enabled) {
		debugMode = enabled;
	}
	public static boolean isTuningModeEnabled() {
		return tuningMode;
	}
	public static void setTuningModeEnabled(boolean enabled) {
		tuningMode = enabled;
		
		if(tuningMode == true) {
			SmartDashboard.putNumber(CatzConstants.SCALE_FACTOR_LABEL, PID_TURN_POWER_SCALE_FACTOR);
			SmartDashboard.putNumber(CatzConstants.TURN_KP_LABEL, PID_TURN_KP);
			SmartDashboard.putNumber(CatzConstants.TURN_KD_LABEL, PID_TURN_KD);
			SmartDashboard.putNumber(CatzConstants.TURN_KI_LABEL, PID_TURN_KI);
		}
	}
	public static void printDebugInit()
	{
		if(debugMode == true)
		{
			debugData =  ( "CurrentAngle,"   + currentAngle                   + "\n" +
                    "targetAngle,"    + targetAngle                    + "\n" +
                    "targetAngleAbs," + targetAngleAbs                 + "\n" +
                    "tgtUpperLimit,"  + targetUpperLimit               + "\n" +
                    "tgtLowerLimit,"  + targetLowerLimit               + "\n" +
                    "kP,"             + PID_TURN_KP          + "\n" +
                    "kI,"             + PID_TURN_KI          + "\n" +
                    "kD,"             + PID_TURN_KD          + "\n" +
                    "Power Scale Factor," + PID_TURN_POWER_SCALE_FACTOR + "\n" +
                    "MaxI,"           + PID_TURN_INTEGRAL_MAX + "\n" +
                    "MinI,"           + PID_TURN_INTEGRAL_MAX + "\n" );
			System.out.println("****************************************************************************");
			System.out.print(debugData);
			System.out.printf("PIDTurn MaxPwr, %.3f\n", PID_TURN_MAX_POWER_RT);
		}
	}
	public static void printDebugHeader() {
		if(debugMode == true) {
			System.out.print("PIDTurn debug data\n");
			System.out.print("timestamp,deltaT,currentAngle,currentError,deltaError,derivative,totalError,power\n");
		}
	}
	public static void printDebugData() {
		if (debugMode == true) {
			debugData = functionTimer.get() + "," +
                    deltaT                  + "," + 
                    currentAngle            + "," + 
                    currentError            + "," + 
                    deltaError              + "," + 
                    derivative              + "," + 
                    totalError              + "," + 
                    power                   + "\n";
			System.out.print(debugData);
			//printDatainSmartDashboard();
		}
	}
	
	public static void printDatainSmartDashboard() {
		
		SmartDashboard.putNumber("PID turn:timestamp", functionTimer.get());
		SmartDashboard.putNumber("PID turn:deltaT", deltaT);
		SmartDashboard.putNumber("PID turn:currentAngle", currentAngle);
		SmartDashboard.putNumber("PID turn:CurrentError", currentError);
		SmartDashboard.putNumber("PID turn:derivative", derivative);
		SmartDashboard.putNumber("PID turn:totalError", totalError);
		SmartDashboard.putNumber("PID turn:power", power);
		
	}
}
