package autonomous;
import java.util.concurrent.TimeUnit;
import org.usfirst.frc.team2637.robot.CatzRobotMap;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
public class CatzDriveStraight
{
	static CatzRobotMap instance;
	public static void EncoderStraightDrive(double speed, double distance, double timeout)
	{
		instance = CatzRobotMap.getInstance();
		Timer functionTimer = new Timer();
		Timer loopTimer = new Timer();
		
		
		/*int loopCount        = 0;
		double encoderIssues = 0;
		int dbgCount1        = 0;*/

		boolean done     = false;

		double previousAngleDegrees = 0.0;
		double currentAngleDegrees;
		double deltaAngleDegrees;			//FUNCTION VARIABLES
		double derivative;
		double deltaTimeMillisec;
		/*
		double encoderCheckNumber;
		double lastEncoderValue = 0;
		*/
		instance.navx.reset();
		//instance.wheelEncoderL.reset();
		
		
		functionTimer.start();
		loopTimer.start();
		
		
		while(/*Math.abs(instance.wheelEncoderL.getDistance()) < distance && */done == false)
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