package robot;
/*
 *  Author : Derek Duenas
 *  Last Revised : 2-4-2018 DD
 *  Added climber speed and lift speed
 *  Functionality : stores all final values
 */
public class CatzConstants
{
	final public static double DRIVE_MAX_POS_POWER =  1.0;
	final public static double DRIVE_MAX_NEG_POWER = -1.0;
	
	final static public int DRIVE_WHEEL_ENCODER_R_DIOA = 0;
	final static public int DRIVE_WHEEL_ENCODER_R_DIOB = 1;
	
	final static public int DRIVE_WHEEL_ENCODER_L_DIOA = 8;
	final static public int DRIVE_WHEEL_ENCODER_L_DIOB = 9;

	final static public int CUBEE_TALON_ID_L_FRONT = 0;
	final static public int CUBEE_TALON_ID_L_REAR  = 1;
	final static public int CUBEE_TALON_ID_R_FRONT = 4;
	final static public int CUBEE_TALON_ID_R_REAR  = 5;

	final static public int TALON_ID_FRONT_L = 3;
	final static public int TALON_ID_REAR_L  = 2;
	final static public int TALON_ID_FRONT_R = 6;
	final static public int TALON_ID_REAR_R  = 5;
	
	final static public int DRIVE_XBOX_PORT = 0;
	final static public int AUX_XBOX_PORT   = 1;
	
	final static public int RIGHT_RIGHT_LIFTER_PWM = 4;
	final static public int RIGHT_LEFT_LIFTER_PWM  = 9;
	final static public int LEFT_RIGHT_LIFTER_PWM = 7;
	final static public int LEFT_LEFT_LIFTER_PWM = 8;
	
	final static public int RIGHT_INTAKE_PWM   = 4;
	final static public int LEFT_INTAKE_PWM    = 1;
	final static public int INTAKE_FOREARM_PCM = 1;
	final static public int INTAKE_BICEP_PCM   = 0;
	
	final static public int LIFT_ENCODER_DIOA = 2;
	final static public int LIFT_ENCODER_DIOB = 3;
	
	final static public int TOP_LIFT_LIMIT_DIO = 6;
	final static public int BOT_LIFT_LIMIT_DIO = 7;
	
	final static public double INTAKE_CUBE = 1.0;
	final static public double SHOOT_CUBE = -1.0;
	
	final static public int DEF_VALUE = -1;
	
	/****************************************************************************
	 * Drivetrain Encoder - pulses to inches - MOVE TO CONSTANTS FILE Encoder is a
	 * Grayhill 63R256 which provides 256 pulses per revolution. This is connected
	 * to the output shaft of the drive gear box. The output shaft of the gearbox
	 * has a 15 tooth sprocket & is connected by a chain to the drivetrain center
	 * wheel which has a 22 tooth sprocket. This provides a 15/22 reduction. The
	 * center wheel is 6" in diameter.
	 ****************************************************************************/
	final static public double DRIVE_ENCODER_PULSES_PER_REV = 256.0;
	final static public double DRIVE_WHEEL_DIAMETER = 6.0;
	final static public double DRIVE_WHEEL_CIRCUMFRENCE = DRIVE_WHEEL_DIAMETER * Math.PI;
	final static public double DRIVE_GEAR_MECH_SPROCKET_RATIO = (15.0 / 22.0);

	final static public double DRIVE_ENCODER_INCHES_PER_PULSE = (DRIVE_WHEEL_CIRCUMFRENCE * DRIVE_GEAR_MECH_SPROCKET_RATIO)
			/ DRIVE_ENCODER_PULSES_PER_REV;
	
	final static public double LIFT_ENCODER_INCHES_PER_PULSE = 117225/13.58;
	
	final static public double CUBEE_ENCODER_INCHES_PER_PULSE = 1/750000;

	final static public double LIFT_TO_SCALE_TIMEOUT = 6.0; //It was 7.5  
	
	final static public double NAVX_RESET_WAIT_TIME = 0.2;

	final static public double AUTO_TURN_TIMEOUT_90_DEG = 3.0;
	
	final static public double FUNCTION_EXECUTION_DELAY = 0.1;
	
	/*need to acquire for final robot*/final static public double PID_TURN_TIMEOUT = 1.5;
	/*need to acquire for final robot*/final static public double PID_DRIVE_TIMEOUT = 2.0;
	
	
	final static public double ROBOT_WIDTH = 34.0; //includes bumpers
	final static public double ROBOT_LENGTH = 39.0; //includes bumpers
	final static public double HALF_ROBOT_LENGTH = ROBOT_LENGTH/2;
	final static public double HALF_ROBOT_WIDTH = ROBOT_WIDTH/2;

	final static public double INTAKE_SPEED = 1.0;	
	
	final static public String POSITION_SELECTORL = "Position Left";
	final static public String POSITION_SELECTORM = "Position Mid";
	final static public String POSITION_SELECTORR = "Position Right";
	static public String TURN_KP_LABEL = "Turn KP";
	static public String TURN_KI_LABEL = "Turn KI";
	static public String TURN_KD_LABEL = "Turn KD";
	static public String SCALE_FACTOR_LABEL = "Turn Scale Factor";
	
	final static public double AUTO_STARTPOS_DEF_SPEED = 0.6;
	final static public double AUTO_STARTPOS_DEF_DISTANCE = 120.0;
	final static public double AUTO_STARTPOS_DEF_TIMEOUT = 5.0;

	final static public double CUBE_OUTTAKE_WAIT_TIME = 0.5;
	final static public double CUBE_INTAKE_WAIT_TIME = 0.4;
	
	final static public double DEG_TO_RAD = Math.PI/180.0;
	final static public double RAD_TO_DEG = 180.0/Math.PI;

	final static public double LIFT_SCALE_HEIGHT = 70.0;
}