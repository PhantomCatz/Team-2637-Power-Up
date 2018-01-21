package robot;

import com.kauailabs.navx.frc.AHRS;
import components.CatzCANTalonSRX;
import components.CatzDrive;
import components.CatzJoystick;
import components.CatzTimer;
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
	public CatzRobotInit init;
	
	public static CatzRobotMap instance;
	
	public SendableChooser<String> m_chooser;
	public String m_autoSelected;	
	
	public CatzCANTalonSRX fRight;
	public CatzCANTalonSRX rRight;
	public CatzCANTalonSRX fLeft;
	public CatzCANTalonSRX rLeft;
	
	public Encoder wheelEncoder;
	public AHRS navx;
	public CatzConstants constants;
	
	public CatzTimer timer;
	public CatzXboxController xbox;
	public CatzJoystick joy;
	
	public CatzDrive drive;
	public SpeedControllerGroup leftMotors;
	public SpeedControllerGroup rightMotors;
	
	public Thread m_visionThread;
	
	public CatzLogger logger;
	public CatzMessage message;
	
	//public  Encoder rightWheelEncoder;
	//public  Encoder leftWheelEncoder;
	
	//public  Lidar lidar;
	
	private CatzRobotMap() 
	{
		init = new CatzRobotInit();
		
		fRight = new CatzCANTalonSRX(constants.PORT_0);
		rRight = new CatzCANTalonSRX(constants.PORT_4);
		fLeft = new CatzCANTalonSRX(constants.PORT_1);
		rLeft = new CatzCANTalonSRX(constants.PORT_5);
		
		navx = new AHRS(SerialPort.Port.kMXP);
		
		wheelEncoder = new Encoder(constants.DIO_PORT6, constants.DIO_PORT7,false,Encoder.EncodingType.k2X);
		
		timer = new CatzTimer();
		
		xbox = new CatzXboxController(constants.PORT_0);
		joy = new CatzJoystick(constants.PORT_1);
		
		m_visionThread = new Thread();
		
		leftMotors = new SpeedControllerGroup(fLeft, rLeft);
		rightMotors = new SpeedControllerGroup(fRight, rRight);
		drive = new CatzDrive(fRight, rRight, fLeft, rLeft);
		
		m_chooser = new SendableChooser<>();
		
		logger = new CatzLogger();
		message = new CatzMessage();
	}
	public static CatzRobotMap getInstance()
	{
		if(instance == null)
			instance = new CatzRobotMap();
		return instance;
	}
	
}