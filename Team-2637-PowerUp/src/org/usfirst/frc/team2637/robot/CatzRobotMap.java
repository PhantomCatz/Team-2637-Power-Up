package org.usfirst.frc.team2637.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import components.CatzDrive;
import components.CatzJoystick;
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
	
	public static Encoder liftEncoder;
	public static Spark lifterR;
	public static Spark lifterL;
	
	public static Spark intakeRight;
	public static Spark intakeLeft;
	
	public static AHRS navx;
	
	public static Timer globalTimer;
	public static CatzXboxController xboxDrive;
	public static CatzXboxController xboxAux;
	
	public static CatzDrive drive;
	public static SpeedControllerGroup leftMotors;
	public static SpeedControllerGroup rightMotors;
	
	public static Solenoid intakeForearm;
	public static Solenoid intakeBicep;
	
	public static boolean debugMode = false;
	//public static CatzLogger logger;
	
	//public  Lidar lidar;
	
	private CatzRobotMap() 
	{	

		/*fRight = new WPI_TalonSRX(CatzConstants.TALON_ID_R_FRONT); 
		rRight = new WPI_TalonSRX(CatzConstants.TALON_ID_R_REAR);
		fLeft  = new WPI_TalonSRX(CatzConstants.TALON_ID_L_FRONT);
		rLeft  = new WPI_TalonSRX(CatzConstants.TALON_ID_L_REAR);*/
		fRight = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_R_FRONT);
		rRight = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_R_REAR);
		fLeft = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_L_FRONT);
		rLeft = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_L_REAR);
		fRight.setSafetyEnabled(false);
		rRight.setSafetyEnabled(false);
		fLeft.setSafetyEnabled(false);
		rLeft.setSafetyEnabled(false);
		
		leftMotors  = new SpeedControllerGroup(fLeft, rLeft);
		rightMotors = new SpeedControllerGroup(fRight, rRight);
		drive = new CatzDrive(leftMotors, rightMotors);
		printOutDebugData("Successfully initialized full drive train");
		
		climberMotor = new WPI_TalonSRX(CatzConstants.PORT_3);
		climberMotor.setSafetyEnabled(false);
		printOutDebugData("Successfully initialized climber Motor");
		//climber2 = new CatzCANTalonSRX(CatzConstants.PORT_4);
		
		navx = new AHRS(SPI.Port.kMXP,(byte)200);
				
		wheelEncoderR = new Encoder(CatzConstants.DIO_PORT_0, CatzConstants.DIO_PORT_1, false, Encoder.EncodingType.k2X);
		wheelEncoderL = new Encoder(CatzConstants.DIO_PORT_8, CatzConstants.DIO_PORT_9, false, Encoder.EncodingType.k2X);
		liftEncoder   = new Encoder(CatzConstants.DIO_PORT_2, CatzConstants.DIO_PORT_3, false, Encoder.EncodingType.k2X);
		printOutDebugData("Successfully Encoders");
		
		globalTimer = new Timer();
		
		xboxDrive = new CatzXboxController(CatzConstants.PORT_0);
		xboxAux   = new CatzXboxController(CatzConstants.PORT_1);
		
		lifterR = new Spark(CatzConstants.PWM_PORT_1);
		lifterL = new Spark(CatzConstants.PWM_PORT_0);
		
		intakeRight = new Spark(CatzConstants.PWM_PORT_2);
		intakeLeft  = new Spark(CatzConstants.PWM_PORT_3);
		
		intakeForearm = new Solenoid(CatzConstants.PCM_PORT_0);
		intakeBicep   = new Solenoid(CatzConstants.PCM_PORT_1);
		printOutDebugData("Successfully initialized auxilary actuators");
		
		climberMechanism = new CatzClimber();
		grabber          = new CatzGrabber();
		lift             = new CatzLift();

		
		//logger = new CatzLogger();
	}
	public static CatzRobotMap getInstance()
	{
		if(instance == null)
			instance = new CatzRobotMap();
		return instance;
	}
	private static void printOutDebugData(String info) {
		if(debugMode == true) {
			System.out.println(info);
		}
	}
}