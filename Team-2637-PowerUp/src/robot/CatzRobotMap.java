package robot;

import com.kauailabs.navx.frc.AHRS;
import components.CatzCANTalonSRX;
import components.CatzDrive;
import components.CatzJoystick;
import components.CatzTimerMap;
import components.CatzXboxController;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import logger.CatzLogger;
import logger.CatzMessage;

public class CatzRobotMap 
{
	public static CatzRobotInit init;
	
	public static CatzRobotMap instance;
	
	public static SendableChooser<String> m_chooser;
	public static String m_autoSelected;	
	
	public static CatzCANTalonSRX fRight;
	public static CatzCANTalonSRX rRight;
	public static CatzCANTalonSRX fLeft;
	public static CatzCANTalonSRX rLeft;
	
	public static Encoder wheelEncoder;
	public static AHRS navx;
	
	public static CatzTimerMap timer;
	public static CatzXboxController xbox;
	public static CatzJoystick joy;
	
	public static CatzDrive drive;
	public static SpeedControllerGroup leftMotors;
	public static SpeedControllerGroup rightMotors;
	
	public static Thread m_visionThread;
	
	public static CatzLogger logger;
	public static CatzMessage message;
	
	//public  Encoder rightWheelEncoder;
	//public  Encoder leftWheelEncoder;
	
	//public  Lidar lidar;
	
	private CatzRobotMap() 
	{
		init = new CatzRobotInit();
		
		fRight = new CatzCANTalonSRX(CatzConstants.PORT_0);
		rRight = new CatzCANTalonSRX(CatzConstants.PORT_4);
		fLeft = new CatzCANTalonSRX(CatzConstants.PORT_1);
		rLeft = new CatzCANTalonSRX(CatzConstants.PORT_5);
		
		navx = new AHRS(SerialPort.Port.kMXP);
		
		wheelEncoder = new Encoder(CatzConstants.DIO_PORT6, CatzConstants.DIO_PORT7, false, Encoder.EncodingType.k2X);
		
		timer = new CatzTimerMap();
		
		xbox = new CatzXboxController(CatzConstants.PORT_0);
		joy = new CatzJoystick(CatzConstants.PORT_1);
		
		m_visionThread = new Thread();
		
		leftMotors = new SpeedControllerGroup(fLeft, rLeft);
		rightMotors = new SpeedControllerGroup(fRight, rRight);
		drive = new CatzDrive(fRight, rRight, fLeft, rLeft);
		
		m_chooser = new SendableChooser<>();
		
		logger = new CatzLogger();
	}
	public static CatzRobotMap getInstance()
	{
		if(instance == null)
			instance = new CatzRobotMap();
		return instance;
	}
	
}