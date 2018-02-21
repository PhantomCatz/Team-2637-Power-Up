package vision;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import com.atul.JavaOpenCV.Imshow;

public class GripMainTape {
	
	private static final double PI = Math.PI;
	private static final double RAD2DEG = 180.0 / PI;
	private static final double DEG2RAD = 1.0 / RAD2DEG;

	private static final double CUBE_DIAMETER_INCHES = 17;
	private static final double VIEW_ANGLE_DIAGONAL_DEGREES = 78;
	private static final double VIEW_ANGLE_DIAGONAL_RADIANS = VIEW_ANGLE_DIAGONAL_DEGREES * DEG2RAD;
	
	
	@SuppressWarnings("unused")//possibly remove if unfound errors
	public static void main(String[] arg) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		double legLenthIsosceles;
		double heightIsosceles;
		double hypotenuseSquared;
		
		Imshow sizedShow = new Imshow("SizedFrame");
		Imshow blurShow = new Imshow("BlurFrame");
		Imshow circleShow = new Imshow("imageCircle");
		Imshow blurCircle = new Imshow("blurCircle");
				
		Mat imageCircle;
		Mat source;
		//KeyPoint blobList;
		//ArrayList<KeyPoint> blobList = new ArrayList<KeyPoint>();
		
		GripPipelineTape detectReflectiveTape = new GripPipelineTape();
		
		VideoCapture cap = new VideoCapture();
		
		cap.open(0);
		
		
		if(!cap.equals(0)) {
			
			System.out.println("Error opening video file");
		}
		
		//int frameWidth = cap.get(CV_PROP_FRAME_WIDTH);
		//int frameHeight = cap.get(CV_CAP_FRAME_HEIGHT);
		
		//System.out.println("Frame: " + frameWidth + ", " + frameHeight);
		
		int sizedFrameWidth = 480;
		
		int sizedFrameHeight = 270;
		
		//Change the math in order to get measurements for midpoint
		/*
		hypotenuseSquared =  sizedFrameWidth*sizedFrameWidth + sizedFrameHeight*sizedFrameHeight;
		
		legLenthIsosceles = Math.sqrt(hypotenuseSquared/(2*(1.0-Math.cos(VIEW_ANGLE_DIAGONAL_DEGREES))));
		heightIsosceles = legLenthIsosceles*Math.cos(0.5*VIEW_ANGLE_DIAGONAL_RADIANS);
		*/
		
		VideoWriter video = new VideoWriter("outcpp.avi", VideoWriter.fourcc('M','J','P','G'), 10, new Size(sizedFrameWidth*4, sizedFrameHeight*4));
		
		int framesProcessed = 0;
		while(true) {
			double measX = 1;
			double measY = 1;
			double objRadius = 1;
			double heading;
			double pitch;
			double distance;
			
			Mat sizedFrame = new Mat();
			ArrayList<MatOfPoint> contourOutput = new ArrayList<MatOfPoint>();
			Mat frame = new Mat();
			cap.retrieve(frame);
			
			
			if(frame.empty())
				break;
			
			//video.write(frame);
			
			++framesProcessed;
			
			detectReflectiveTape.process(frame);
			
			sizedFrame = detectReflectiveTape.resizeImageOutput();
			contourOutput = detectReflectiveTape.findContoursOutput();
			
			
			
			
		}
	}
}