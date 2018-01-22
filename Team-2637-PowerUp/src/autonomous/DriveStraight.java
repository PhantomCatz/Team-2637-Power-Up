package autonomous;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzRobotMap;
public class DriveStraight
{
	CatzRobotMap robotMap;
	void EncoderStraightDrive(double speed, double distance, double sampleTime,double timeout)
	{
		robotMap = CatzRobotMap.getInstance();
		int loopCount        = 0;
		double encoderIssues = 0;
		int dbgCount1        = 0;

		boolean done     = false;

		double previousAngle = 0.0;
		double currentAngle;
		double deltaAngle;			//FUNCTION VARIABLES
		double derivative;
		double deltaTime = sampleTime/1000;

		double encoderCheckNumber;
		double lastEncoderValue = 0;

		robotMap.navx.reset();
		robotMap.wheelEncoder.reset();

		robotMap.timer.reset(CatzConstants.FUNCTION_TIMER);
		robotMap.timer.start(CatzConstants.FUNCTION_TIMER);
		while(Math.abs(robotMap.wheelEncoder.getDistance()) < distance && done != true)
		{
			currentAngle = robotMap.navx.getAngle();
	
			deltaAngle = currentAngle-previousAngle;
	
			derivative = deltaAngle/deltaTime;
	
			robotMap.drive.tankDrive(speed, CatzConstants.straightkP*currentAngle + CatzConstants.straightkD*derivative);
	
			previousAngle = currentAngle;
	
			if (robotMap.timer.get(CatzConstants.FUNCTION_TIMER) > timeout)
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
			robotMap.drive.tankDrive(.43,0);
		else
			robotMap.drive.tankDrive(-.43,0);
		robotMap.drive.tankDrive(0,0);
		robotMap.timer.stop(CatzConstants.FUNCTION_TIMER);

		SmartDashboard.putNumber("Function timer value", robotMap.timer.get(CatzConstants.FUNCTION_TIMER));
		SmartDashboard.putNumber("encoderCheck", encoderIssues);
		SmartDashboard.putNumber("drive straight loop count", loopCount);
	}	
}