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
	static boolean debugMode = true;
	static String debugData;
	
	
	public static void setDebugModeEnabled(boolean enabled){
		debugMode = enabled;
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
                    "kP,"             + CatzConstants.TURN_KP          + "\n" +
                    "kI,"             + CatzConstants.TURN_KI          + "\n" +
                    "kD,"             + CatzConstants.TURN_KD          + "\n" +
                    "Power Scale Factor," + CatzConstants.TURN_SCALE_FACTOR + "\n" +
                    "MaxI,"           + CatzConstants.PID_INTEGRAL_MAX + "\n" +
                    "MinI,"           + CatzConstants.PID_INTEGRAL_MAX + "\n" );
			System.out.println("****************************************************************************");
			System.out.print(debugData);
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
	
	public static void PIDturn(double degreesToTurn, int timeoutSeconds)
	{
		
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
		while(Math.abs(currentError)>CatzConstants.PID_TURN_THRESHOLD && done == false)
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
			deltaError = currentError-previousError;
			derivative = CatzConstants.PIDTURN_FILTER_CONSTANT*previousDerivative + ((1-CatzConstants.PIDTURN_FILTER_CONSTANT)*(deltaError/deltaT));
			//filter smoothes derivative graph; ask Walter for specifics ^
			previousDerivative = derivative;
			
			previousError = currentError;  // saves error for next iteration
			
			
			// calculates integral term
			totalError += currentError * deltaT;   
			
			if(totalError >= CatzConstants.PID_INTEGRAL_MAX)     // saturation
				totalError = CatzConstants.PID_INTEGRAL_MAX;	 // makes sure the integral term doesn't get too big or small
			
			if(totalError <= CatzConstants.PID_INTEGRAL_MIN)
				totalError = CatzConstants.PID_INTEGRAL_MIN;
			
			
			power = CatzConstants.TURN_SCALE_FACTOR*((CatzConstants.TURN_KP * currentError)
													+(CatzConstants.TURN_KI * totalError)
													+(CatzConstants.TURN_KD * derivative));	
			
			if(power > CatzConstants.PID_TURN_MAX_POWER)
				power = CatzConstants.PID_TURN_MAX_POWER;
			
			if(power < CatzConstants.PID_TURN_MIN_POWER)
				power = CatzConstants.PID_TURN_MIN_POWER;
			
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
}
