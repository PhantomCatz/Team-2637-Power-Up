package autonomous;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import constants.CatzConstants;
import org.usfirst.frc.team2637.robot.CatzRobotMap;


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
	static CatzRobotMap instance;

	static Timer functionTimer;
	static Timer pdTimer;
	static Timer debugTimer;
	
	static double currentError; 
	static double deltaError;
	static double derivative; 
	static double deltaT;
	
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
	static boolean debugMode = false;
	static boolean tuningMode = false;
	static String debugData;
	
	public static void PIDturn(double degreesToTurn, int timeoutSeconds)
	{
		boolean firstTime = true;
		functionTimer = new Timer();
		pdTimer = new Timer();
		
		instance = CatzRobotMap.getInstance();
		instance.navx.reset();
		
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);
		
		done = false;
		
		previousError = 0.0;
		totalError = 0.0;
		
		functionTimer.reset();
		functionTimer.start();
		
		
		currentAngle = instance.navx.getAngle();
		targetAngle = degreesToTurn + currentAngle;
		currentError = targetAngle - currentAngle;
		
		targetAngleAbs = Math.abs(targetAngle);
	
		printDebugInit();
		printDebugHeader();
		
		
		pdTimer.reset();
		pdTimer.start();
		while(Math.abs(currentError)>CatzConstants.PIDTURN_THRESHOLD && done == false)
		{
			currentAngle = instance.navx.getAngle();
			deltaT = pdTimer.get();

			currentAngleAbs = Math.abs(currentAngle);
			
			pdTimer.stop();
			pdTimer.reset();
			pdTimer.start();

			// calculates proportional term
			currentError = targetAngle - currentAngle;
			
			// calculates derivative term
			//filter smoothes derivative graph; ask Walter for specifics ^
			deltaError = currentError-previousError;
			if (firstTime == false) {
   			   if ( (deltaError == 0.0) && (Math.abs(currentError) > 3.0 ) ) {
   				   derivative =  previousDerivative;   
   			   } else {
   				   derivative =    CatzConstants.PIDTURN_FILTER_CONSTANT*previousDerivative + 
   			               ((1-CatzConstants.PIDTURN_FILTER_CONSTANT)*(deltaError/deltaT));
   			   }
			} else {
				firstTime = false;
				derivative = 0;
			}
			
			
			previousDerivative = derivative;
			
			previousError = currentError;  // saves error for next iteration
			
			
			// calculates integral term
			totalError += currentError * deltaT;   
			
			if(totalError >= CatzConstants.PIDTURN_INTEGRAL_MAX)     // saturation
				totalError = CatzConstants.PIDTURN_INTEGRAL_MAX;	 // makes sure the integral term doesn't get too big or small
			
			if(totalError <= CatzConstants.PIDTURN_INTEGRAL_MIN)
				totalError = CatzConstants.PIDTURN_INTEGRAL_MIN;
			
			
			power = CatzConstants.PIDTURN_POWER_SCALE_FACTOR*((CatzConstants.PIDTURN_KP * currentError)
													+(CatzConstants.PIDTURN_KI * totalError)
													+(CatzConstants.PIDTURN_KD * derivative));	
			
			if (power > 0.0) {
				
				if(power > CatzConstants.PIDTURN_MAX_POWER_RT)
					power = CatzConstants.PIDTURN_MAX_POWER_RT;
				else if (power < CatzConstants.PIDTURN_MIN_POWER_RT)
					power = CatzConstants.PIDTURN_MIN_POWER_RT;
			} else {
			
      			if(power < CatzConstants.PIDTURN_MAX_POWER_LT)
				    power = CatzConstants.PIDTURN_MAX_POWER_LT;
      			else if (power > CatzConstants.PIDTURN_MIN_POWER_LT)
 				    power = CatzConstants.PIDTURN_MIN_POWER_LT;
			}
			
      			
			instance.drive.tankDrive(power, -power);
			
			if (functionTimer.get() > timeoutSeconds)
				done = true;
			
			printDebugData();
			
			Timer.delay(0.015); //was .005,.008
		}
		instance.drive.tankDrive(0.0, 0.0); // makes robot stop
		
		functionTimer.stop();
		pdTimer.stop();
	}
	
	
	
	public static void setDebugModeEnabled(boolean enabled){
		debugMode = enabled;
	}
	public static boolean isTuningModeEnabled() {
		return tuningMode;
	}
	public static void setTuningModeEnabled(boolean enabled) {
		tuningMode = enabled;
		
		if(tuningMode == true) {
			SmartDashboard.putNumber(CatzConstants.SCALE_FACTOR_LABEL, CatzConstants.PIDTURN_POWER_SCALE_FACTOR);
			SmartDashboard.putNumber(CatzConstants.Turn_KP, CatzConstants.PIDTURN_KP);
			SmartDashboard.putNumber(CatzConstants.Turn_KD, CatzConstants.PIDTURN_KD);
			SmartDashboard.putNumber(CatzConstants.Turn_KI, CatzConstants.PIDTURN_KI);
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
                    "kP,"             + CatzConstants.PIDTURN_KP          + "\n" +
                    "kI,"             + CatzConstants.PIDTURN_KI          + "\n" +
                    "kD,"             + CatzConstants.PIDTURN_KD          + "\n" +
                    "Power Scale Factor," + CatzConstants.PIDTURN_POWER_SCALE_FACTOR + "\n" +
                    "MaxI,"           + CatzConstants.PIDTURN_INTEGRAL_MAX + "\n" +
                    "MinI,"           + CatzConstants.PIDTURN_INTEGRAL_MAX + "\n" );
			System.out.println("****************************************************************************");
			System.out.print(debugData);
			System.out.printf("PIDTurn MaxPwr, %.3f\n", CatzConstants.PIDTURN_MAX_POWER_RT);
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
			printDatainSmartDashboard();
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
