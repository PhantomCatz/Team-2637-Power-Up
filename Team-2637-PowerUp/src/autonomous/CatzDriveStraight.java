package autonomous;
import java.util.concurrent.TimeUnit;
import org.usfirst.frc.team2637.robot.CatzRobotMap;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class CatzDriveStraight
{
	static CatzRobotMap instance;
	
	static boolean done     = false;

	static double previousAngleDegrees = 0.0;
	static double currentAngleDegrees;
	static double deltaAngleDegrees;			//FUNCTION VARIABLES
	static double derivative;
	static double deltaTimeMillisec;
	
	static Timer functionTimer;
	static Timer loopTimer;
	
	static boolean debugMode = false;
	public static void setDebugModeEnabled(boolean enabled) {
		debugMode = enabled;
	}
	
	public static void printDebugInit() {
		if(debugMode == true) {
			
		}
	}
	public static void printDebugHeader() {
		if (debugMode == true) {
			System.out.print("encoderStraightDrive debug data/n");
			System.out.print("timestamp,deltaTimeMillis,currentAngleDegrees,currentErrorDegrees,derivative/n");
		}
	}
	
	public static void printDebugData() {
		if(debugMode == true) {
			String data = functionTimer.get()+","+
						  deltaTimeMillisec+","+
						  currentAngleDegrees+","+
						  deltaAngleDegrees+","+
						  derivative+"/n";
			System.out.print(data);
			printDatainSmartDashboard();
			
		}
	}
	
	public static void printDatainSmartDashboard() {
		SmartDashboard.putNumber("timestamp", functionTimer.get());
		SmartDashboard.putNumber("deltaTimeMillis", deltaTimeMillisec);
		SmartDashboard.putNumber("currentAngleDegrees", currentAngleDegrees);
		SmartDashboard.putNumber("currentErrorDegrees", deltaAngleDegrees);
		SmartDashboard.putNumber("derivative",derivative );
		
	}
	
	public static void encoderStraightDrive(double speed, double distance, double timeout)
	{
		instance = CatzRobotMap.getInstance();
		functionTimer = new Timer();
		loopTimer = new Timer();
		
		printDebugHeader();
		
		/*int loopCount        = 0;
		double encoderIssues = 0;
		int dbgCount1        = 0;*/


		/*
		double encoderCheckNumber;
		double lastEncoderValue = 0;
		*/
		instance.navx.reset();
		//instance.wheelEncoderL.reset();
		
		
		functionTimer.start();
		loopTimer.start();
		
		
		while(Math.abs(instance.wheelEncoderL.getDistance()) < distance && done == false)
		{
			loopTimer.stop();
			deltaTimeMillisec = TimeUnit.SECONDS.toMillis((long)loopTimer.get());
			loopTimer.reset();
			loopTimer.start();
			
			currentAngleDegrees = instance.navx.getAngle();
	
			deltaAngleDegrees = currentAngleDegrees-previousAngleDegrees;
	
			derivative = deltaAngleDegrees/deltaTimeMillisec;
	

			
			instance.drive.arcadeDrive(speed, CatzConstants.straightkP*currentAngleDegrees + CatzConstants.straightkD*derivative);
	
			previousAngleDegrees = currentAngleDegrees;
	
			printDebugData();
			
			if (functionTimer.get() > timeout)
				done = true;
	
				/*
				encoderCheckNumber = instance.wheelEncoderL.get();
				if(lastEncoderValue==encoderCheckNumber)
					encoderIssues++;
				lastEncoderValue=encoderCheckNumber;
				loopCount++;
				dbgCount1++;
				*/
	
			/*dbgCount1++;
			if (dbgCount1== CatzConstants.VAR_1_BUFFER_SIZE)
				dbgCount1=0;*/
		}
		if(speed<0)
			instance.drive.tankDrive(.43,.43);
		else
			instance.drive.tankDrive(-.43,-.43);
		instance.drive.tankDrive(0,0);
		functionTimer.stop();
		//instance.wheelEncoderL.reset();
		/*SmartDashboard.putNumber("Function timer value", functionTimer.get());
		SmartDashboard.putNumber("encoderCheck", encoderIssues);
		SmartDashboard.putNumber("drive straight loop count", loopCount);*/
	}	
}	