package autonomous;
import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class CatzDriveStraight
{
	static void EncoderStraightDrive(double speed, double distance, double sampleTimeSec,double timeout)
	{
		int loopCount        = 0;
		double encoderIssues = 0;
		int dbgCount1        = 0;

		boolean done     = false;

		double previousAngle = 0.0;
		double currentAngle;
		double deltaAngle;			//FUNCTION VARIABLES
		double derivative;
		double deltaTime = sampleTimeSec/1000;

		//double encoderCheckNumber;
		//double lastEncoderValue = 0;

		CatzRobotMap.navx.reset();
		CatzRobotMap.wheelEncoder.reset();

		CatzRobotMap.timer.reset(CatzConstants.FUNCTION_TIMER_INDEX);
		CatzRobotMap.timer.start(CatzConstants.FUNCTION_TIMER_INDEX);
		while(Math.abs(CatzRobotMap.wheelEncoder.getDistance()) < distance && done != true)
		{
			currentAngle = CatzRobotMap.navx.getAngle();
	
			deltaAngle = currentAngle-previousAngle;
	
			derivative = deltaAngle/deltaTime;
	
			CatzRobotMap.drive.tankDrive(speed, CatzConstants.straightkP*currentAngle + CatzConstants.straightkD*derivative);
	
			previousAngle = currentAngle;
	
			if (CatzRobotMap.timer.get(CatzConstants.FUNCTION_TIMER_INDEX) > timeout)
				done = true;
	
				/***********************************************
				*encoderCheckNumber = wheelEncoder.Get();
				*if(lastEncoderValue==encoderCheckNumber)
				*	encoderIssues++;
				*lastEncoderValue=encoderCheckNumber;
				*loopCount++;
				*dbgCount1++;
				************************************************/
	
			dbgCount1++;
			if (dbgCount1== CatzConstants.VAR_1_BUFFER_SIZE)
				dbgCount1=0;
		}
		if(speed<0)
			CatzRobotMap.drive.tankDrive(.43,0);
		else
			CatzRobotMap.drive.tankDrive(-.43,0);
		CatzRobotMap.drive.tankDrive(0,0);
		CatzRobotMap.timer.stop(CatzConstants.FUNCTION_TIMER_INDEX);

		SmartDashboard.putNumber("Function timer value", CatzRobotMap.timer.get(CatzConstants.FUNCTION_TIMER_INDEX));
		SmartDashboard.putNumber("encoderCheck", encoderIssues);
		SmartDashboard.putNumber("drive straight loop count", loopCount);
	}	
}