package robot;

import java.text.DecimalFormat;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import components.CatzDrive;
import components.CatzXboxController;
import mechanisms.CatzClimber;
import mechanisms.CatzGrabber;
import mechanisms.CatzLift;
import robotFunctions.CatzAutonomousInit;
import server.UDPServer;

/*
 *  Author : Derek Duenas
 *  Revision History : 
 *  3-17-18 Added bottom limit switch
 *  
 *  Methods : setDebugEnabled, instantiateRobot, printDebugData
 *  Functionality : instantiate all objects in one place
 */

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
	public static CatzXboxController xboxTest;
	
	public static CatzDrive drive;
	public static SpeedControllerGroup leftMotors;
	public static SpeedControllerGroup rightMotors;
	
	public static Solenoid intakeForearm;
	public static Solenoid intakeBicep;
	
	//public static Compressor comp;
	
	public static DigitalInput lifterLimitTop;
	public static DigitalInput lifterLimitBottom;
	
	public static DecimalFormat secondsFormat;
	
	public static UDPServer server;

	public static boolean debugMode = true;
	public static boolean usingCubeee = false;
	

	//public static CatzLogger logger;
	
	//public  Lidar lidar;
	
	private CatzRobotMap() 
	{	
		globalTimer = new Timer();
		
		if(usingCubeee) 
		{
			fRight = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_R_FRONT); 
			rRight = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_R_REAR);
			fLeft  = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_L_FRONT);
			rLeft  = new WPI_TalonSRX(CatzConstants.CUBEE_TALON_ID_L_REAR);
		} 
		else {
			fRight = new WPI_TalonSRX(CatzConstants.TALON_ID_FRONT_R); 
			rRight = new WPI_TalonSRX(CatzConstants.TALON_ID_REAR_R);
			fLeft  = new WPI_TalonSRX(CatzConstants.TALON_ID_FRONT_L);
			rLeft  = new WPI_TalonSRX(CatzConstants.TALON_ID_REAR_L);
		}
		
		fRight.setSafetyEnabled(false);
		rRight.setSafetyEnabled(false);
		fLeft.setSafetyEnabled(false);
		rLeft.setSafetyEnabled(false);
		
		leftMotors  = new SpeedControllerGroup(fLeft, rLeft);
		rightMotors = new SpeedControllerGroup(fRight, rRight);
		drive = new CatzDrive(leftMotors, rightMotors);
		printOutDebugData("Successfully initialized full drive train");
		
		navx = new AHRS(SPI.Port.kMXP,(byte)200);
		
		//comp = new Compressor(3);
			
		if(usingCubeee) {
			wheelEncoderL = new Encoder(6,7,false,Encoder.EncodingType.k4X);
			wheelEncoderL.setDistancePerPulse(CatzConstants.CUBEE_ENCODER_INCHES_PER_PULSE);
		} else {
			wheelEncoderR = new Encoder(CatzConstants.DRIVE_WHEEL_ENCODER_R_DIOA, CatzConstants.DRIVE_WHEEL_ENCODER_R_DIOB, false, Encoder.EncodingType.k4X);
			wheelEncoderL = new Encoder(CatzConstants.DRIVE_WHEEL_ENCODER_L_DIOA, CatzConstants.DRIVE_WHEEL_ENCODER_L_DIOB, false, Encoder.EncodingType.k4X);
			wheelEncoderR.setDistancePerPulse(CatzConstants.DRIVE_ENCODER_INCHES_PER_PULSE);
			wheelEncoderR.setReverseDirection(true);
			wheelEncoderL.setDistancePerPulse(CatzConstants.DRIVE_ENCODER_INCHES_PER_PULSE);
			liftEncoder   = new Encoder(CatzConstants.LIFT_ENCODER_DIOA, CatzConstants.LIFT_ENCODER_DIOB, false, Encoder.EncodingType.k1X);
		}
		printOutDebugData("Successfully Encoders");
	
		
		xboxDrive = new CatzXboxController(CatzConstants.DRIVE_XBOX_PORT);
		xboxAux   = new CatzXboxController(CatzConstants.AUX_XBOX_PORT);
		xboxTest = new CatzXboxController(2);
		
		if(usingCubeee) {
			//intakeForearm = new Solenoid(1); //solenoid to control the flaps currently not plugged in
		}
		else {
			climberMotor = new WPI_TalonSRX(CatzConstants.CLIMBER_TALON_ID);
			climberMotor.setSafetyEnabled(false);
			printOutDebugData("Successfully initialized climber Motor");
			//climber2 = new CatzCANTalonSRX(CatzConstants.PORT_4);
			
			lifterR = new Spark(CatzConstants.RIGHT_LIFTER_PWM);
			lifterL = new Spark(CatzConstants.LEFT_LIFTER_PWM);
	
			lifterLimitTop =    new DigitalInput(CatzConstants.TOP_LIFT_LIMIT_DIO);
			lifterLimitBottom = new DigitalInput(CatzConstants.BOT_LIFT_LIMIT_DIO);
			
	
			intakeRight   = new Spark(CatzConstants.RIGHT_INTAKE_PWM);
			intakeLeft    = new Spark(CatzConstants.LEFT_INTAKE_PWM);
			intakeForearm = new Solenoid(CatzConstants.INTAKE_FOREARM_PCM);
			intakeBicep   = new Solenoid(CatzConstants.INTAKE_BICEP_PCM);
			printOutDebugData("Successfully initialized auxilary actuators");
			
			climberMechanism = new CatzClimber();
			grabber          = new CatzGrabber();
			lift             = new CatzLift();
	
			secondsFormat = new DecimalFormat("#.###");
			
			server  = new UDPServer();
			//logger = new CatzLogger();
		}
	}
	public static void setDebugModeEnabled(boolean enabled) 
	{
		debugMode = enabled;
	}
	public static CatzRobotMap instantiateRobot()
	{
		if(instance == null)
			instance = new CatzRobotMap();
		return instance;
	}
	private static void printOutDebugData(String info) {
		if(debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}