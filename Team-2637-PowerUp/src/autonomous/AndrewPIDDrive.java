package autonomous;

import edu.wpi.first.wpilibj.Timer;
import robot.CatzConstants;
import robot.CatzRobotMap;

public class AndrewPIDDrive {
	private final static double PID_DRIVE_ERROR_THRESHOLD = 1.0; // Stop within 1 inch
	private final static double PID_DRIVE_FILTER_CONSTANT = 0.5;
	private final static double PID_DRIVE_KP = 0.15;
	private final static double PID_DRIVE_KD = 0.005; // ORIGINALLY .18
	private final static double PID_DRIVE_BRAKE_POWER = 0.43;
	private final static double PID_DRIVE_BRAKE_TIME = 0.1;
	
	public static void PIDDrive(double driveDistance, double relativeSpeed, double timeoutSeconds) {
		CatzRobotMap.navx.reset();
		CatzRobotMap.wheelEncoderL.reset();
		
		Timer.delay(CatzConstants.NAVX_RESET_WAIT_TIME);
		
		Timer functionTimer = new Timer();
		Timer loopTimer = new Timer();
		
		boolean done = false;
		
		double timeoutSec = timeoutSeconds;
		
		double currentDistanceAbs;
		double distanceErrorAbs;
		double driveDistanceAbs = Math.abs(driveDistance);
		double headingError;
		double previousError = CatzRobotMap.navx.getAngle();
		double deltaHeadingError;
		double currentTimestamp;
		double lastTimestamp = 0;
		double deltaT;
		double rotationSpeed;
		
		double derivative;
		double previousDerivative = 0;
		
		functionTimer.start();
		loopTimer.start();
		
		while(done == false) 
		{	
			currentDistanceAbs = CatzRobotMap.wheelEncoderL.getDistance();
			distanceErrorAbs = driveDistanceAbs - currentDistanceAbs;
			
			loopTimer.stop();
			currentTimestamp = loopTimer.get();
			loopTimer.reset();
			loopTimer.start();
			
			deltaT = currentTimestamp - lastTimestamp;
			
			if( distanceErrorAbs<PID_DRIVE_ERROR_THRESHOLD || currentTimestamp>timeoutSec ) {
				done = true;
			} else {
				headingError = CatzRobotMap.navx.getAngle();
				deltaHeadingError = headingError - previousError;
				
				derivative = (PID_DRIVE_FILTER_CONSTANT * previousDerivative)
						+ ((1 - PID_DRIVE_FILTER_CONSTANT) * (deltaHeadingError / deltaT));
				
				rotationSpeed = (-PID_DRIVE_KP * headingError) + (PID_DRIVE_KD * derivative);
				
				CatzRobotMap.drive.arcadeDrive(relativeSpeed, rotationSpeed);
			}	
		}
		
		//Brake using motor speed
		if (relativeSpeed < 0.0) {
			CatzRobotMap.drive.tankDrive(PID_DRIVE_BRAKE_POWER, PID_DRIVE_BRAKE_POWER);
		} else {
			CatzRobotMap.drive.tankDrive(-PID_DRIVE_BRAKE_POWER, -PID_DRIVE_BRAKE_POWER);
		}
		Timer.delay(PID_DRIVE_BRAKE_TIME);
		CatzRobotMap.drive.tankDrive(0.0, 0.0);
		
		functionTimer.stop();
	}
}

