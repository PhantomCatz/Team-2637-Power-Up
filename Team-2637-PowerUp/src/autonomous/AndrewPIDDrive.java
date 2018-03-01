package autonomous;

import edu.wpi.first.wpilibj.Timer;
import robot.CatzRobotMap;

public class AndrewPIDDrive {
	final static double PID_DRIVE_ERROR_THRESHOLD = 1.0; // Stop within 1 inch
	
	public static void PIDDrive(double driveDistance, double relativeSpeed, double timeoutSeconds) {
		Timer functionTimer = new Timer();
		Timer loopTimer = new Timer();
		
		boolean done = false;
		
		double timeoutSec = timeoutSeconds;
		
		double currentDistanceAbs;
		double distanceErrorAbs;
		double driveDistanceAbs = Math.abs(driveDistance);
		double currentError;
		double previousError;
		double currentTimestamp;
		double lastTimestamp;
		double deltaT;
		
		double derivative;
		double previousDerivative;
		
		
		while(done == false) {
			
			currentDistanceAbs = CatzRobotMap.wheelEncoderL.getDistance();
			distanceErrorAbs = driveDistanceAbs - currentDistanceAbs;
			currentTimestamp = loopTimer.get();
			
			if( distanceErrorAbs<PID_DRIVE_ERROR_THRESHOLD || currentTimestamp>timeoutSec ) {
				done = true;
			} else {
				
			}
			
		}
		
	}
}

