package vision;

public class DerivativeOfVision extends GripMain{//fix later
	private static double firstMeasuredDistance;
	private static double second;
	private static double changeOfRate;
	private static double placeHolderForChangeOfRate = 0;
	private static double derivative;
	private static double amountOfTimesThrough;
	
	private static boolean notFirstTime = false;
	
	public static double changeOfValue(double Distance, long time) {
		
		if(notFirstTime) {
			secondValue = Distance;
			changeOfRate = secondValue - firstValue;
			derivative = changeOfRate/time;
			firstValue = Distance;
			
			return derivative;
		}
		else {
			notFirstTime = !notFirstTime;
			firstValue = Distance;
			
			return 0;
		}
	}
}


