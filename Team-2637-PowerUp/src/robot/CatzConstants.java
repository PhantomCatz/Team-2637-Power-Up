package robot;
/*
 *  Author : Derek Duenas
 *  Last Revised : 2-4-2018 DD
 *  Added climber speed and lift speed
 *  Functionality : stores all final values
 */
public class CatzConstants
{
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

	final static public double DRIVE_INCHES_PER_PULSE = (DRIVE_WHEEL_CIRCUMFRENCE * DRIVE_GEAR_MECH_SPROCKET_RATIO)
			/ DRIVE_ENCODER_PULSES_PER_REV;
	

	/*need to acquire for final robot*/final public static double DRIVE_MAX_POS_POWER =  1.0;
	/*need to acquire for final robot*/final public static double DRIVE_MAX_NEG_POWER = -1.0;
	
	final static public int DRIVE_WHEEL_ENCODER_R_DIOA = 0;
	final static public int DRIVE_WHEEL_ENCODER_R_DIOB = 1;
	
	final static public int DRIVE_WHEEL_ENCODER_L_DIOA = 8;
	final static public int DRIVE_WHEEL_ENCODER_L_DIOB = 9;

	//ROBOT COMPONENTS PORTS
	final static public int CUBEE_TALON_ID_L_FRONT = 0;
	final static public int CUBEE_TALON_ID_L_REAR  = 1;
	final static public int CUBEE_TALON_ID_R_FRONT = 4;
	final static public int CUBEE_TALON_ID_R_REAR  = 5;

	final static public int TALON_ID_FRONT_L = 4;
	final static public int TALON_ID_REAR_L  = 3;
	final static public int TALON_ID_FRONT_R = 5;
	final static public int TALON_ID_REAR_R  = 6;
	
	final static public int CLIMBER_TALON_ID = 1;
	
	final static public int DRIVE_XBOX_PORT = 0;
	final static public int AUX_XBOX_PORT = 1;
	
	final static public int RIGHT_LIFTER_PWM = 1;
	final static public int LEFT_LIFTER_PWM = 0;
	
	final static public int RIGHT_INTAKE_PWM = 4; //2 for new robot
	final static public int LEFT_INTAKE_PWM = 5;  //3 for new robot
	final static public int INTAKE_FOREARM_PCM = 0; //1 for new robot
	final static public int INTAKE_BICEP_PCM = 1;  //0 for new robot
	
	
	
	final static public int LIFT_ENCODER_DIOA = 2;
	final static public int LIFT_ENCODER_DIOB = 3;
	//**************************************************************
	
	
	
	/*need to acquire for final robot*/final static public double NAVX_RESET_WAIT_TIME = 0.3;
	

	final static public double AUTO_TURN_TIMEOUT_90_DEG = 3.0;
	
	final static public double FUNCTION_EXECUTION_DELAY = 0.1;
	
	/*need to acquire for final robot*/static public double PID_LIFT_POWER_SCALE_FACTOR = 1.0;
	/*need to acquire for final robot*/static public double PID_LIFT_KP = 0.0508;  //0.0508
	/*need to acquire for final robot*/static public double PID_LIFT_KD = 0.008;  //0.0744
	/*need to acquire for final robot*/static public double PID_LIFT_KI = 0.0;
	

	/*need to acquire for final robot*/final static public double PID_TURN_TIMEOUT = 3.0;
	/*need to acquire for final robot*/final static public double PID_DRIVE_TIMEOUT = 5.0;

	/*need to acquire for final robot*/final public static double LIFT_ENCODER_DISTANCE_PER_PULSE = 1.0;
	/*need to acquire for final robot*/final public static double LEFT_DRIVE_TRAIN_DISTANCE_PER_PULSE = 1.0;
	/*need to acquire for final robot*/final public static double RIGHT_DRIVE_TRAIN_DISTANCE_PER_PULSE = 1.0;
	
	//the distances below are for MID AUTONOMOUS PATHS
	
	final static public double MID_LEAVE_WALL = 36.0;                                 
	final static public double MID_AFTER_TURN_DIST = 72.0;
	final static public double MID_APPROACH_SWITCH = 55.2;
	final static public double MID_LEAVE_SWITCH = 43.2;
	final static public double MID_GO_AROUND_SWITCH = 84.0;
	final static public double MID_APPROACH_CUBE_CLOSE = 24.0;
	final static public double MID_APPROACH_CUBE_FAR = 48.0;
	final static public double MID_BACK_AWAY = 20.0;
	
	final static public double MID_SWITCH_SCALE_INIT_DISTANCE = 66.3;
	final static public double MID_SWITCH_SCALE_TOWARDS_SWITCH = 64;
	final static public double MID_SWITCH_SCALE_NEXT_TO_SWITCH = 36.5;
	final static public double MID_SWITCH_SCALE_LEAVE_SWITCH = 65;
	final static public double MID_SWITCH_SCALE_AROUND_SWITCH = 89;
	final static public double MID_SWITCH_SCALE_TOWARDS_CUBE = 47.5;
	final static public double MID_SWITCH_SCALE_TOWARDS_SCALE = 89;
	
	
	final static public int TURN_DEG_45 = 45;
	final static public int TURN_DEG_90 = 90;
	public static final double TURN_DEG_60 = 60;
	public static final int TURN_DEG_30 = 30;
	
	final static public double HALF_SPEED = 0.5;
	final static public double STOP = 0.0;
	
	
	final static public double ROBOT_WIDTH = 34.0;
	final static public double ROBOT_LENGTH = 39.0;
	final static public double HALF_ROBOT_LENGTH = ROBOT_LENGTH/2;
	final static public double HALF_ROBOT_WIDTH = ROBOT_WIDTH/2;
	
	//the distances below are for the LEFT AND RIGHT AUTONOMOUS PATHS
	
	final static public double SIDE_PATH_INIT_DISTANCE = 335.65;
	final static public double SIDE_PATH_APPROACH_SCALE = 41.88;
	final static public double SIDE_PATH_LEAVE_SCALE = 112.8;
	final static public double SIDE_PATH_APPROACH_CUBE = 12;
	
	//the distances below are for the LEFT AND RIGHT NO SCALE AUTONOMOUS PATHS
	
	final static public double NO_SCALE_INIT_DISTANCE = 251.73;
	final static public double NO_SCALE_APPROACH_SCALE = 84;
	final static public double NO_SCALE_POSITION_NEXT_TO_SCALE = 24.0;

	// the distances below are for the MID SWITCH SWITCH PATHS
	final static public double MID_SWITCH_SWITCH_INIT_DIST = 66.5;
	final static public double MID_SWITCH_SWITCH_TOWARDS_SWITCH = 64;
	final static public double MID_SWITCH_SWITCH_APPROACH_SWITCH = 36.5;
	final static public double MID_SWITCH_SWITCH_LEAVE_SWITCH = 65;
	final static public double MID_SWITCH_SWITCH_AROUND_SWITCH = 89;
	final static public double MID_SWITCH_SWITCH_TOWARDS_CUBE = 47.5;
	final static public double MID_SWITCH_SWITCH_LEAVE_CUBE = 35;
	final static public double MID_SWITCH_SWITCH_SIDE_OF_SWITCH = 45.5;

	
	final static public double ZERO = 0.0;

	/*need to acquire for final robot*/final static public double CLIMB_SPEED = 0;
	/*need to acquire for final robot*/final static public double INTAKE_SPEED = 0.7;
	
	

	static public boolean bicepRetracted = true;
	static public boolean forearmClosed = true;
	
	final static public String POSITION_SELECTORL = "Position Left";
	final static public String POSITION_SELECTORM = "Position Mid";
	final static public String POSITION_SELECTORR = "Position Right";
	static public String TURN_KP_LABEL = "Turn KP";
	static public String TURN_KI_LABEL = "Turn KI";
	static public String TURN_KD_LABEL = "Turn KD";
	static public String SCALE_FACTOR_LABEL = "Turn Scale Factor";
	
	
	final static public double AUTO_STARTPOS_DEF_SPEED = 0.5;
	final static public double AUTO_STARTPOS_DEF_DISTANCE = 10.0;
	final static public double AUTO_STARTPOS_DEF_TIMEOUT = 3.0;

	final static public double CUBE_OUTTAKE_WAIT_TIME = 0.2;
	final static public double CUBE_INTAKE_WAIT_TIME = 0.2;
	
	final static public double DEG_TO_RAD = Math.PI/180.0;
	final static public double RAD_TO_DEG = 180.0/Math.PI;

}