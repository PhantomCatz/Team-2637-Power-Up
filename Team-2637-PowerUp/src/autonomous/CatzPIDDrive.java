package autonomous;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;

/**********************************************************
 * 
 * 
 * 
 * 
 *
 *********************************************************/

public class CatzPIDDrive
{	
	final static double PID_DRIVE_ERROR_THRESHOLD = 1.0;
	final static public double PID_DRIVE_TIMEOUT = 5.0;
	
	/*need to acquire for final robot*/final static public double PID_DRIVE_KP = .15;
	/*need to acquire for final robot*/final static public double PID_DRIVE_KD = .005;  //ORIGINALLY .18
	/*need to acquire for final robot*/final static public double PID_DRIVE_BRAKE_SPEED = .43;
	/*need to acquire for final robot*/final static public double PID_DRIVE_BRAKE_TIME = .15;
	/*need to acquire for final robot*/final static public double PID_DRIVE_FILTER_CONSTANT = 0.5;
	
	private static boolean done     = false;

	private static double previousError = 0.0;
	private static double currentHeading;			//FUNCTION VARIABLES
	private static double derivative;
	private static double deltaTimeSec;
	private static double previousDerivative;
	private static double deltaError;
	private static double correction;
	private static double encoderLeft;
	private static double encoderRight;
	private static double cumDriftError;
	private static double actualDistanceTraveled;
	private static double totalDistanceTraveled;
	private static double distanceError;
	private static double driftCorrectionAngle;
	private static double plannedTravelDistance;
	
	
	private static Timer functionTimer;
	private static Timer loopTimer;
	
	private static boolean debugMode = false;
	
	static double loopDistanceTraveled;
	static double previousEncoder;
	static double distanceTraveled;
	
	static double driftError;
	
	public static void PIDDrive(double speed, double distance, double timeout)
	{
		
		CatzRobotMap.wheelEncoderL.reset();
		
		functionTimer = new Timer();
		loopTimer = new Timer();
		
		printDebugHeader();
		
		CatzRobotMap.navx.reset();
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);
		
		functionTimer.start();
		loopTimer.start();
		
		 distanceTraveled = CatzRobotMap.wheelEncoderL.getDistance();
		 previousEncoder = 0.0;
		 
		 totalDistanceTraveled = 0.0;
		 
		while(Math.abs(CatzRobotMap.wheelEncoderL.getDistance()) < Math.abs(distance) && done == false)
		{
			
			encoderLeft = CatzRobotMap.wheelEncoderL.getDistance();
			encoderRight = CatzRobotMap.wheelEncoderR.getDistance();
			currentHeading = CatzRobotMap.navx.getAngle();
			deltaTimeSec = loopTimer.get();
			
			distanceTraveled = encoderLeft - previousEncoder;
			previousEncoder = encoderLeft;
			
			actualDistanceTraveled = Math.abs(driftError*Math.tan(currentHeading*CatzConstants.DEG_TO_RAD));
			
			loopTimer.stop();
			loopTimer.reset();
			loopTimer.start();
			
			currentHeading = CatzRobotMap.navx.getAngle();
			deltaError = currentHeading - previousError;  
	
			derivative =    PID_DRIVE_FILTER_CONSTANT*previousDerivative + 
		               ((1-PID_DRIVE_FILTER_CONSTANT)*(deltaError/deltaTimeSec));
			//add code for dead encoder
			
			driftError = Math.sin(currentHeading*CatzConstants.DEG_TO_RAD) * distanceTraveled;
			cumDriftError = cumDriftError + driftError;
			
			totalDistanceTraveled = totalDistanceTraveled + actualDistanceTraveled;
			
			distanceError = distance - totalDistanceTraveled;
			
			
			if(distanceError <= PID_DRIVE_ERROR_THRESHOLD)
				done = true;
			else
				if (functionTimer.get() > timeout)
					done = true;
			
			if(driftError == 0.0)
				driftCorrectionAngle = 0;
			else
			{
				plannedTravelDistance = actualDistanceTraveled;
				driftCorrectionAngle = Math.asin(cumDriftError/plannedTravelDistance)*CatzConstants.RAD_TO_DEG;
			}
			
			deltaError = (currentHeading + driftCorrectionAngle) - previousError;  
			
			derivative =    PID_DRIVE_FILTER_CONSTANT*previousDerivative + 
		               ((1-PID_DRIVE_FILTER_CONSTANT)*(deltaError/deltaTimeSec));
			
			previousDerivative = derivative;
			
			//derivative = deltaAngleDegrees/deltaTimeSec;
			correction = -PID_DRIVE_KP*currentHeading + PID_DRIVE_KD*derivative;
			
			CatzRobotMap.drive.arcadeDrive(speed, correction);
	
			previousError = currentHeading;
			
			printDebugData();
			
			Timer.delay(0.015);
			
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
	
	public static void setDebugModeEnabled(boolean enabled) {
		debugMode = enabled;
	}
	public static void printDebugInit() {
		if(debugMode == true) {
			System.out.printf("PID Drive KP, %.3f\n",PID_DRIVE_KP);
			System.out.printf("PID Drive KD, %.3f\n",PID_DRIVE_KD);
			System.out.printf("PID Drive Brake speed, %.3f\n",PID_DRIVE_BRAKE_SPEED);
			System.out.printf("PID Drive Brake time, %.3f\n",PID_DRIVE_BRAKE_TIME);
			System.out.printf("PID Drive Filter Constant, %.3f\n",PID_DRIVE_FILTER_CONSTANT);
			System.out.printf("PID Drive Error Threshold, %.3f\n",PID_DRIVE_ERROR_THRESHOLD);
		}
	}
	public static void printDebugHeader() {
		if (debugMode == true) {
			System.out.print("encoderStraightDrive debug data\n");
			System.out.print("timestamp,deltaTimeSec,actualDistanceTraveled,totalDistanceTraveled,distanceError,"
						   + "cumDriftError,driftCorrectionAngle,currentHeadingDegrees,derivative,deltaError,correction\n");
		}
	}
	
	public static void printDebugData() {
		if(debugMode == true) {
			String data = functionTimer.get() +","+
						  deltaTimeSec        +","+
						  actualDistanceTraveled +","+ 
						  totalDistanceTraveled + "," +
						  distanceError			+ "," +
						  driftError			+"," +
						  cumDriftError			+","+
						  driftCorrectionAngle  +"," +
						  currentHeading        +","+
						  encoderLeft		  +"," +
						  encoderRight		  +"," +	
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
		SmartDashboard.putNumber("currentAngleDegrees", currentHeading);
		SmartDashboard.putNumber("currentErrorDegrees", deltaError);
		SmartDashboard.putNumber("derivative",derivative );
		
	}
}	