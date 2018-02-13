package org.usfirst.frc.team2637.robot;

import com.kauailabs.navx.frc.AHRS;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import components.CatzCANTalonSRX;
import components.CatzDrive;
import components.CatzJoystick;
import components.CatzSpark;
import components.CatzXboxController;
import mechanisms.CatzClimber;
import mechanisms.CatzGrabber;
import mechanisms.CatzLift;

public class CatzRobotMap 
{
	public static CatzRobotMap instance;
	
	public CatzClimber Climber;
	public CatzGrabber grabber;
	public CatzLift lift;
	
	public CatzCANTalonSRX fRight;
	public CatzCANTalonSRX rRight;
	public CatzCANTalonSRX fLeft;
	public CatzCANTalonSRX rLeft;
	
	public CatzCANTalonSRX climber;  
	//public CatzCANTalonSRX climber2;  //robot does not yet have a second climber motor
	
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
	
	public Solenoid intakeForearm;
	public Solenoid intakeBicep;
	
	//public static CatzLogger logger;
	
	//public  Lidar lidar;
	
	private CatzRobotMap() 
	{	
		
		fRight = new CatzCANTalonSRX(CatzConstants.PORT_6);
		rRight = new CatzCANTalonSRX(CatzConstants.PORT_5);
		fLeft = new CatzCANTalonSRX(CatzConstants.PORT_1);
		rLeft = new CatzCANTalonSRX(CatzConstants.PORT_2);
		
		climber = new CatzCANTalonSRX(CatzConstants.PORT_3);
		//climber2 = new CatzCANTalonSRX(CatzConstants.PORT_4);
		
		navx = new AHRS(SPI.Port.kMXP,(byte)200);
				
		wheelEncoderR = new Encoder(CatzConstants.DIO_PORT_2,CatzConstants.DIO_PORT_3, false, Encoder.EncodingType.k2X);
		wheelEncoderL = new Encoder(CatzConstants.DIO_PORT_0,CatzConstants.DIO_PORT_1,false,Encoder.EncodingType.k2X);
		
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
		
		intakeForearm = new Solenoid(CatzConstants.PCM_PORT_0);
		intakeBicep = new Solenoid(CatzConstants.PCM_PORT_1);
		//logger = new CatzLogger();
	}
	public static CatzRobotMap getInstance()
	{
		if(instance == null)
			instance = new CatzRobotMap();
		return instance;
	}
}