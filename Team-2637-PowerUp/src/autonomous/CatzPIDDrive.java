package autonomous;
import java.util.concurrent.TimeUnit;
import org.usfirst.frc.team2637.robot.CatzRobotMap;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class CatzPIDDrive
{	
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
	
	private static boolean debugMode = true;
	
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
	
			derivative =    CatzConstants.PID_TURN_FILTER_CONSTANT*previousDerivative + 
		               ((1-CatzConstants.PID_TURN_FILTER_CONSTANT)*(deltaError/deltaTimeSec));
			
			//derivative = deltaAngleDegrees/deltaTimeSec;
			correction = -CatzConstants.PID_DRIVE_KP*currentError + CatzConstants.PID_DRIVE_KD*derivative;
			
			CatzRobotMap.drive.arcadeDrive(speed, correction);
	
			previousError = currentError;
	
			printDebugData();
			
			if (functionTimer.get() > timeout)
				done = true;
			
			previousDerivative = derivative;
		}
		
		
		//brake using motors speed
		if(speed<0) {
			CatzRobotMap.drive.tankDrive(CatzConstants.PID_DRIVE_BRAKE_SPEED,CatzConstants.PID_DRIVE_BRAKE_SPEED);
			Timer.delay(CatzConstants.PID_DRIVE_BRAKE_TIME);
		}
		else {
			CatzRobotMap.drive.tankDrive(-CatzConstants.PID_DRIVE_BRAKE_SPEED,-CatzConstants.PID_DRIVE_BRAKE_SPEED);
			Timer.delay(CatzConstants.PID_DRIVE_BRAKE_TIME);
		}
		CatzRobotMap.drive.tankDrive(0,0);
		functionTimer.stop();
		
	}	
	public static void setDebugModeEnabled(boolean enabled) {
		debugMode = enabled;
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
			printDatainSmartDashboard();
			
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