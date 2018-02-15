package org.usfirst.frc.team2637.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
	
	public static CatzClimber climberMechanism;
	public static CatzGrabber grabber;
	public static CatzLift lift;
	
	public static WPI_TalonSRX fRight;
	public static WPI_TalonSRX rRight;
	public static WPI_TalonSRX fLeft;
	public static WPI_TalonSRX rLeft;
	
	public static WPI_TalonSRX climberMotor;  
	//public static CatzCANTalonSRX climber2;  //robot does not yet have a second climber motor
	
	public static Encoder wheelEncoderR;
	public static Encoder wheelEncoderL;
	
	public static CatzSpark lifterR;
	public static CatzSpark lifterL;
	
	public static CatzSpark intakeRight;
	public static CatzSpark intakeLeft;
	
	public static AHRS navx;
	
	public static Timer timer;
	public static CatzXboxController xbox;
	public static CatzJoystick joy;
	
	public static CatzDrive drive;
	public static SpeedControllerGroup leftMotors;
	public static SpeedControllerGroup rightMotors;
	
	public static Solenoid intakeForearm;
	public static Solenoid intakeBicep;
	
	//public static CatzLogger logger;
	
	//public  Lidar lidar;
	
	private CatzRobotMap() 
	{	
		System.out.println("Started Contstructor");

		
		fRight = new WPI_TalonSRX(CatzConstants.PORT_4);  //cubee : FR = 4, BR = 5, FL = 0, BL = 1 
		rRight = new WPI_TalonSRX(CatzConstants.PORT_5);
		fLeft = new WPI_TalonSRX(CatzConstants.PORT_0);
		rLeft = new WPI_TalonSRX(CatzConstants.PORT_1);
		fRight.setSafetyEnabled(false);
		rRight.setSafetyEnabled(false);
		fLeft.setSafetyEnabled(false);
		rLeft.setSafetyEnabled(false);
		
		climberMotor = new WPI_TalonSRX(CatzConstants.PORT_3);
		climberMotor.setSafetyEnabled(false);
		//climber2 = new CatzCANTalonSRX(CatzConstants.PORT_4);
		
		navx = new AHRS(SPI.Port.kMXP,(byte)200);
				
		wheelEncoderR = new Encoder(CatzConstants.DIO_PORT_2,CatzConstants.DIO_PORT_3, false, Encoder.EncodingType.k2X);
		wheelEncoderL = new Encoder(CatzConstants.DIO_PORT_0,CatzConstants.DIO_PORT_1,false,Encoder.EncodingType.k2X);
		
		timer = new Timer();
		
		System.out.println("controllers");
		
		xbox = new CatzXboxController(CatzConstants.PORT_0);
		joy = new CatzJoystick(CatzConstants.PORT_1);
		
		leftMotors = new SpeedControllerGroup(fLeft, rLeft);
		rightMotors = new SpeedControllerGroup(fRight, rRight);
		
		System.out.println("Before CatzDrive construction");
		drive = new CatzDrive(leftMotors, rightMotors);
		
		lifterR = new CatzSpark(CatzConstants.PWM_PORT_1);
		lifterL = new CatzSpark(CatzConstants.PWM_PORT_0);
		
		intakeRight = new CatzSpark(CatzConstants.PWM_PORT_2);
		intakeLeft = new CatzSpark(CatzConstants.PWM_PORT_3);
		
		intakeForearm = new Solenoid(CatzConstants.PCM_PORT_0);
		intakeBicep = new Solenoid(CatzConstants.PCM_PORT_1);
		
		climberMechanism=new CatzClimber();
		grabber=new CatzGrabber();
		lift= new CatzLift();
		//logger = new CatzLogger();
		System.out.println("finished Contstructor");
	}
	public static CatzRobotMap getInstance()
	{
		if(instance == null)
			instance = new CatzRobotMap();
		return instance;
	}
}