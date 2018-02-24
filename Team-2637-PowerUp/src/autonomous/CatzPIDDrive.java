package autonomous;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;

/**********************************************************
 * Timothy Vu
 * 3 Feb 2018  TV
 * Adding in Left and Right paths
 * Methods: middlePathL  middlePathR  leftPath  rightPath
 *Functionality: Paths for the autonomous period
 *********************************************************/

public class CatzPIDDrive
{	
	/*need to acquire for final robot*/final static public double PID_DRIVE_KP = .15;
	/*need to acquire for final robot*/final static public double PID_DRIVE_KD = .005;  //ORIGINALLY .18
	/*need to acquire for final robot*/final static public double PID_DRIVE_BRAKE_SPEED = .43;
	/*need to acquire for final robot*/final static public double PID_DRIVE_BRAKE_TIME = .3;
	/*need to acquire for final robot*/final static public double PID_DRIVE_FILTER_CONSTANT = 0.5;
	
	private static boolean done     = false;

	private static double previousError = 0.0;
	private static double currentError;			//FUNCTION VARIABLES
	private static double derivative;
	private static double deltaTimeSec;
	private static double previousDerivative;
	private static double deltaError;
	private static double correction;
	
	private static Timer functionTimer;
	private static Timer loopTimer;
	
	private static boolean debugMode = false;
	
	public static void PIDDrive(double speed, double distance, double timeout)
	{
		functionTimer = new Timer();
		loopTimer = new Timer();
		
		printDebugHeader();
		
		CatzRobotMap.navx.reset();
		
		functionTimer.start();
		loopTimer.start();
		
		
		while(Math.abs(CatzRobotMap.wheelEncoderL.getDistance()) < Math.abs(distance) && done == false)
		{
			loopTimer.stop();
			deltaTimeSec = loopTimer.get();
			loopTimer.reset();
			loopTimer.start();
			
			currentError = CatzRobotMap.navx.getAngle();
			deltaError = currentError - previousError;  
	
			derivative =    PID_DRIVE_FILTER_CONSTANT*previousDerivative + 
		               ((1-PID_DRIVE_FILTER_CONSTANT)*(deltaError/deltaTimeSec));
			
			//derivative = deltaAngleDegrees/deltaTimeSec;
			correction = -PID_DRIVE_KP*currentError + PID_DRIVE_KD*derivative;
			
			CatzRobotMap.drive.arcadeDrive(speed, correction);
	
			previousError = currentError;
	
			printDebugData();
			
			if (functionTimer.get() > timeout)
				done = true;
			
			previousDerivative = derivative;
		}
		
		
		//brake using motors' speed
		if(speed<0) {
			CatzRobotMap.drive.tankDrive(PID_DRIVE_BRAKE_SPEED,PID_DRIVE_BRAKE_SPEED);
			Timer.delay(PID_DRIVE_BRAKE_TIME);
		}
		else {
			CatzRobotMap.drive.tankDrive(-PID_DRIVE_BRAKE_SPEED,-PID_DRIVE_BRAKE_SPEED);
			Timer.delay(PID_DRIVE_BRAKE_TIME);
		}
		CatzRobotMap.drive.tankDrive(0,0);
		functionTimer.stop();
		
	}	
	
	public static void printDebugInit() {
		if(debugMode == true) {
			
		}
	}
	public static void printDebugHeader() {
		if (debugMode == true) {
			System.out.print("encoderStraightDrive debug data\n");
			System.out.print("timestamp,deltaTimeSec,currentErrorDegrees,derivative,deltaError,correction\n");
		}
	}
	
	public static void printDebugData() {
		if(debugMode == true) {
			String data = functionTimer.get() +","+
						  deltaTimeSec        +","+
						  currentError        +","+
						  derivative          +","+
						  deltaError          +","+
						  correction          +"\n";
			System.out.print(data);
			//printDatainSmartDashboard();
			
		}
	}
	
	public static void printDatainSmartDashboard() {
		SmartDashboard.putNumber("timestamp", functionTimer.get());
		SmartDashboard.putNumber("deltaTimeMillis", deltaTimeSec);
		SmartDashboard.putNumber("currentAngleDegrees", currentError);
		SmartDashboard.putNumber("currentErrorDegrees", deltaError);
		SmartDashboard.putNumber("derivative",derivative );
		
	}
}	