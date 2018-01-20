/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package robot;
import constants.Constants;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/*
 * Just doing something to change the file.
 */

/*
 * Changing the file again.
 */

/*
 * Changing the file once more.
 */

/*
 * Changes for days. 
 */

/*
 * Do you know de way?
 * 
 * Bruddahs I know de wae
 */

/*
 * Make some meaningful changes to the code
 */

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	Constants constants = new Constants();
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	/*final int DIO_PORT0 = 0;
    final int DIO_PORT1 = 1;
    final int DIO_PORT2 = 2;
    final int DIO_PORT3 = 3;
    final int DIO_PORT4 = 4;
    final int DIO_PORT5 = 5;
    final int DIO_PORT6 = 6;
    final int DIO_PORT7 = 7;
    final int DIO_PORT8 = 8;
    final int DIO_PORT9 = 9;*/

	Timer functionTimer;
	/*static final double straightkP = .18;
	static final double straightkD = .23;  //ORIGINALLY .18
	static int VAR_1_BUFFER_SIZE = 20;*/
	
	WPI_TalonSRX fright;
	WPI_TalonSRX fleft;
	WPI_TalonSRX rright;
	WPI_TalonSRX rleft;
	XboxController xbox;
	
	DifferentialDrive drive;

	SpeedControllerGroup leftMotors;
	SpeedControllerGroup rightMotors;
	
	Thread m_visionThread;
	
	AHRS ahrs;
	Encoder wheelEncoder;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
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
		
		
		fleft = new WPI_TalonSRX(0);
		fright = new WPI_TalonSRX(4);
		rleft = new WPI_TalonSRX(1);
		rright = new WPI_TalonSRX(5);
		
		
		xbox = new XboxController(1);
		
		leftMotors = new SpeedControllerGroup(fleft, rleft);
		rightMotors = new SpeedControllerGroup(fright, rright);
		
		drive = new DifferentialDrive(leftMotors, rightMotors);
		
		ahrs = new AHRS(SerialPort.Port.kMXP);
		wheelEncoder = new Encoder(constants.DIO_PORT6,constants.DIO_PORT7,false,Encoder.EncodingType.k2X);
		 
		functionTimer = new Timer();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	
	
	
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				
				
				
				break;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
				
		drive.arcadeDrive(xbox.getY(Hand.kLeft), xbox.getX(Hand.kRight));
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
