package robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.kauailabs.navx.frc.AHRS;
import components.CatzTimer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class CatzRobotInit 
{
	CatzRobotMap robotmap;
	String m_autoSelected;
	SendableChooser<String> m_chooser = new SendableChooser<>();

	CatzTimer timer;
	
	Thread m_visionThread;
	
	AHRS ahrs;
	Encoder wheelEncoder;
	private void init()
	{
		//m_chooser.addDefault("Default Auto", kDefaultAuto);
		//m_chooser.addObject("My Auto", kCustomAuto);
		
		m_visionThread = new Thread(() -> {
				
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream
					= CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		m_visionThread.setDaemon(true);
		m_visionThread.start();
		//SmartDashboard.putData("Auto choices", m_chooser);
		
		ahrs = new AHRS(SerialPort.Port.kMXP);
		wheelEncoder = new Encoder(robotmap.constants.DIO_PORT6,robotmap.constants.DIO_PORT7,false,Encoder.EncodingType.k2X);
		 
		timer = new CatzTimer();
	}
	public void run() 
	{
		init();
	}
}
