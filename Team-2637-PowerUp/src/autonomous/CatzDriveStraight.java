package autonomous;
import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class CatzDriveStraight
{
	static CatzRobotMap instance;
	public static void EncoderStraightDrive(double speed, double distance, double sampleTimeSec,double timeout)
	{
		instance = CatzRobotMap.getInstance();
		Timer functionTimer = new Timer();
		int loopCount        = 0;
		double encoderIssues = 0;
		int dbgCount1        = 0;

		boolean done     = false;

		double previousAngle = 0.0;
		double currentAngle;
		double deltaAngle;			//FUNCTION VARIABLES
		double derivative;
		double deltaTime = sampleTimeSec/1000;
		/**/
		double encoderCheckNumber;
		double lastEncoderValue = 0;
		/**/
		instance.navx.reset();
		instance.wheelEncoder.reset();
		functionTimer.reset();
		functionTimer.start();
		while(Math.abs(instance.wheelEncoder.getDistance()) < distance && done != true)
		{
			currentAngle = instance.navx.getAngle();
	
			deltaAngle = currentAngle-previousAngle;
	
			derivative = deltaAngle/deltaTime;
	
			instance.drive.tankDrive(speed, CatzConstants.straightkP*currentAngle + CatzConstants.straightkD*derivative);
	
			previousAngle = currentAngle;
	
			if (functionTimer.get() > timeout)
				done = true;
	
				/***********************************************/
				encoderCheckNumber = instance.wheelEncoder.get();
				if(lastEncoderValue==encoderCheckNumber)
					encoderIssues++;
				lastEncoderValue=encoderCheckNumber;
				loopCount++;
				dbgCount1++;
				/************************************************/
	
			dbgCount1++;
			if (dbgCount1== CatzConstants.VAR_1_BUFFER_SIZE)
				dbgCount1=0;
		}
		if(speed<0)
			instance.drive.tankDrive(.43,0.43);
		else
			instance.drive.tankDrive(-.43,-0.43);
		instance.drive.tankDrive(0,0);
		functionTimer.stop();
		instance.wheelEncoder.reset();
		SmartDashboard.putNumber("Function timer value", functionTimer.get());
		SmartDashboard.putNumber("encoderCheck", encoderIssues);
		SmartDashboard.putNumber("drive straight loop count", loopCount);
	}	
}