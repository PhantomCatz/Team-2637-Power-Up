package robot;

import java.text.DecimalFormat;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import components.CatzDrive;
import components.CatzXboxController;
import mechanisms.CatzGrabber;
import mechanisms.CatzLift;

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
	
	public static CatzGrabber grabber;
	public static CatzLift lift;
	
	public static WPI_TalonSRX fRight;
	public static WPI_TalonSRX rRight;
	public static WPI_TalonSRX fLeft;
	public static WPI_TalonSRX rLeft;
	
	public static Encoder wheelEncoderR;
	public static Encoder wheelEncoderL;
	
	public static Encoder liftEncoder;
	public static Spark lifterRightRight;
	public static Spark lifterRightLeft;
	public static Spark lifterLeftRight;
	public static Spark lifterLeftLeft;
	
	public static WPI_TalonSRX intakeRight;
	public static WPI_TalonSRX intakeLeft;
	
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
	
	public static DigitalInput lifterLimitTop;
	public static DigitalInput lifterLimitBottom;
	
	public static DecimalFormat secondsFormat;
	
	public static boolean debugMode = true;
	public static boolean usingCubeee = false;
	

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
		navx.reset();
			
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
			liftEncoder.setDistancePerPulse(CatzConstants.LIFT_ENCODER_INCHES_PER_PULSE);
		}
		printOutDebugData("Successfully Encoders");
	
		
		xboxDrive = new CatzXboxController(CatzConstants.DRIVE_XBOX_PORT);
		xboxAux   = new CatzXboxController(CatzConstants.AUX_XBOX_PORT);
		xboxTest  = new CatzXboxController(2);
		
		if(usingCubeee) {
			//intakeForearm = new Solenoid(1); //solenoid to control the flaps currently not plugged in
		}
		else {
			
			lifterRightRight = new Spark(CatzConstants.RIGHT_RIGHT_LIFTER_PWM);
			lifterRightLeft  = new Spark(CatzConstants.RIGHT_LEFT_LIFTER_PWM);
			lifterLeftRight  = new Spark(CatzConstants.LEFT_RIGHT_LIFTER_PWM);
			lifterLeftLeft   = new Spark(CatzConstants.LEFT_LEFT_LIFTER_PWM);
			lifterRightRight.setInverted(true);
			lifterRightLeft.setInverted(true);
			lifterLimitTop =    new DigitalInput(CatzConstants.TOP_LIFT_LIMIT_DIO);
			lifterLimitBottom = new DigitalInput(CatzConstants.BOT_LIFT_LIMIT_DIO);
			
	
			intakeRight   = new WPI_TalonSRX(4);//CatzConstants.RIGHT_INTAKE_PWM);
			intakeLeft    = new WPI_TalonSRX(1);//CatzConstants.LEFT_INTAKE_PWM);
			intakeForearm = new Solenoid(CatzConstants.INTAKE_FOREARM_PCM);
			intakeBicep   = new Solenoid(CatzConstants.INTAKE_BICEP_PCM);
			printOutDebugData("Successfully initialized auxilary actuators");
			
			grabber          = new CatzGrabber();
			lift             = new CatzLift();
	
			secondsFormat = new DecimalFormat("#.###");
			
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