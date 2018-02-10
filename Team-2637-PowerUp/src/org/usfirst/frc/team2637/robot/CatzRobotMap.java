package org.usfirst.frc.team2637.robot;

import com.kauailabs.navx.frc.AHRS;
import components.CatzCANTalonSRX;
import components.CatzDrive;
import components.CatzJoystick;
import components.CatzSpark;
import components.CatzXboxController;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzRobotMap 
{
	public static CatzRobotMap instance;
	
	public CatzCANTalonSRX fRight;
	public CatzCANTalonSRX rRight;
	public CatzCANTalonSRX fLeft;
	public CatzCANTalonSRX rLeft;
	
	public CatzCANTalonSRX climber;   // unknown which one is left and right
	public CatzCANTalonSRX climber2;
	
	public Encoder wheelEncoderR;
	public Encoder wheelEncoderL;
	
	public CatzSpark lifterR;
	public CatzSpark lifterL;
	
	public CatzSpark intakeRight;
	public CatzSpark intakeLeft;
	
	public AHRS navx;
	
	public Timer timer;
	public CatzXboxController xbox;
	public CatzJoystick joy;
	
	public CatzDrive drive;
	public SpeedControllerGroup leftMotors;
	public SpeedControllerGroup rightMotors;
	
	public Solenoid intakeOpen;
	public Solenoid intakeDeploy;
	
	
	//public static CatzLogger logger;
	
	//public  Lidar lidar;
	
	private CatzRobotMap() 
	{
		
		fRight = new CatzCANTalonSRX(CatzConstants.PORT_6);
		rRight = new CatzCANTalonSRX(CatzConstants.PORT_5);
		fLeft = new CatzCANTalonSRX(CatzConstants.PORT_1);
		rLeft = new CatzCANTalonSRX(CatzConstants.PORT_2);
		
		climber = new CatzCANTalonSRX(CatzConstants.PORT_3);
		climber2 = new CatzCANTalonSRX(CatzConstants.PORT_4);
		
		navx = new AHRS(SPI.Port.kMXP);
				
		wheelEncoderR = new Encoder(CatzConstants.DIO_PORT2,CatzConstants.DIO_PORT3, false, Encoder.EncodingType.k2X);
		wheelEncoderL = new Encoder(CatzConstants.DIO_PORT0,CatzConstants.DIO_PORT1,false,Encoder.EncodingType.k2X);
		
		timer = new Timer();
		
		xbox = new CatzXboxController(CatzConstants.PORT_0);
		joy = new CatzJoystick(CatzConstants.PORT_1);
		
		leftMotors = new SpeedControllerGroup(fLeft, rLeft);
		rightMotors = new SpeedControllerGroup(fRight, rRight);
		drive = new CatzDrive(leftMotors, rightMotors);
		
		lifterR = new CatzSpark(CatzConstants.PWM_PORT_1);
		lifterL = new CatzSpark(CatzConstants.PWM_PORT_0);
		
		intakeRight = new CatzSpark(CatzConstants.PWM_PORT_2);
		intakeLeft = new CatzSpark(CatzConstants.PWM_PORT_3);
		
		intakeOpen = new Solenoid(CatzConstants.PCM_PORT_0);
		intakeDeploy = new Solenoid(CatzConstants.PCM_PORT_1);
		//logger = new CatzLogger();
	}
	public static CatzRobotMap getInstance()
	{
		if(instance == null)
			instance = new CatzRobotMap();
		return instance;
	}
}