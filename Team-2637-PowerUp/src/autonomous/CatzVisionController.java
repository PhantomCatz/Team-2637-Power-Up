package autonomous;

import edu.wpi.first.wpilibj.Timer;
import robot.CatzConstants;
import robot.CatzRobotMap;
import server.UDPServer;
import server.VisionObject;

public class CatzVisionController {
	
	//Constants
	//************************************************************************************
	private static final double VISION_KP = 0.12; //originally .15
	private static final double VISION_KD = 0.03; // ORIGINALLY .18
	private static final double VISION_FILTER_CONSTANT = 0.5; // ORIGINALLY .18

	private static final double BRAKE_POWER = 0.43;
	private static final double BRAKE_TIME = 0.25;
	
	private static final double IDEAL_BLOCK_DISTANCE = 0.0; //NEED TO FIND THIS VALUE
	private static final double DISTANCE_ERROR_THRESHOLD = 5.0; //NEED TO TEST THIS VALUE
	//************************************************************************************
	
	private static Timer functionTimer;
	private static Timer pdTimer;
	
	public static void moveToCube(double relSpeed, double timeoutSeconds) {
		functionTimer = new Timer();
		pdTimer= new Timer();
		
		double currentHeadingError;
		double lastHeadingError=0;
		double deltaError;
		double deltaTimeSec;
		double distanceError;
		
		double previousDerivative = 0;
		double derivative;
		
		boolean done = false;

		functionTimer.start();
		pdTimer.start();
		
		VisionObject visionPacket;

		while (done == false) {
			visionPacket = UDPServer.getDatagramPacket();
			currentHeadingError = 0; //should be heading of vision object
			pdTimer.stop();
			
			deltaTimeSec = pdTimer.get();

			pdTimer.reset();
			pdTimer.start();
			
			distanceError = 0.0; //should be distance of vision object - IDEAL_BLOCK_DISTANCE


			if (distanceError < DISTANCE_ERROR_THRESHOLD) {
				done = true;
			} else {
				if (functionTimer.get() > timeoutSeconds) {
					done = true;
				} else {

					deltaError = currentHeadingError - lastHeadingError;
					/**************************************************************
					 * Calculate Heading Derivative Term
					 **************************************************************/
					derivative = (VISION_FILTER_CONSTANT * previousDerivative)
							+ ((1 - VISION_FILTER_CONSTANT) * (deltaError / deltaTimeSec));
					
					// FILTER OUT INVALID VALUES
					if(derivative == 0.0 || deltaTimeSec == 0.0) {
						derivative = previousDerivative;
					}

					previousDerivative = derivative;

					double turnValue = (VISION_KP * currentHeadingError) + (VISION_KD * derivative);

					CatzRobotMap.drive.arcadeDrive(relSpeed, turnValue);

					lastHeadingError = currentHeadingError;
				}
			}
		}
		
		CatzRobotMap.drive.arcadeDrive(0.0, 0.0);
	}
}
