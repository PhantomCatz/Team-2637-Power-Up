package constants;
/*
 *  Author : Derek Duenas
 *  Last Revised : 2-4-2018 DD
 *  Added climber speed and lift speed
 *  Functionality : stores all final values
 */
public class CatzConstants
{
	final static public int DIO_PORT_0 = 0;
	final static public int DIO_PORT_1 = 1;
	final static public int DIO_PORT_2 = 2;
	final static public int DIO_PORT_3 = 3;
	final static public int DIO_PORT_4 = 4;
	final static public int DIO_PORT_5 = 5;
	final static public int DIO_PORT_6 = 6;
	final static public int DIO_PORT_7 = 7;
	final static public int DIO_PORT_8 = 8;
	final static public int DIO_PORT_9 = 9;
	
	final static public int PORT_0 = 0;
	final static public int PORT_1 = 1;
	final static public int PORT_2 = 2;
	final static public int PORT_3 = 3;
	final static public int PORT_4 = 4;
	final static public int PORT_5 = 5;
	final static public int PORT_6 = 6;
	final static public int PORT_7 = 7;
	final static public int PORT_8 = 8;
	final static public int PORT_9 = 9;
	
	final static public int PWM_PORT_0 = 0;
	final static public int PWM_PORT_1 = 1;
	final static public int PWM_PORT_2 = 2;
	final static public int PWM_PORT_3 = 3;
	final static public int PWM_PORT_4 = 4;
	final static public int PWM_PORT_5 = 5;
	final static public int PWM_PORT_6 = 6;
	final static public int PWM_PORT_7 = 7;
	final static public int PWM_PORT_8 = 8;
	final static public int PWM_PORT_9 = 9;
	
	final static public int PCM_PORT_0 = 0;
	final static public int PCM_PORT_1 = 1;
	final static public int PCM_PORT_2 = 2;
	final static public int PCM_PORT_3 = 3;
	final static public int PCM_PORT_4 = 4;
	final static public int PCM_PORT_5 = 5;
	final static public int PCM_PORT_6 = 6;
	final static public int PCM_PORT_7 = 7;
	final static public int PCM_PORT_8 = 8;
	final static public int PCM_PORT_9 = 9;
	
	final static public int CUBEE_TALON_ID_L_FRONT = 0;
	final static public int CUBEE_TALON_ID_L_REAR  = 1;
	final static public int CUBEE_TALON_ID_R_FRONT = 4;
	final static public int CUBEE_TALON_ID_R_REAR  = 5;

	final static public int TALON_ID_L_FRONT = 5;
	final static public int TALON_ID_L_REAR  = 1;
	final static public int TALON_ID_R_FRONT = 4;
	final static public int TALON_ID_R_REAR  = 6;
	
	final static public double PID_DRIVE_KP = .15;
	final static public double PID_DRIVE_KD = .005;  //ORIGINALLY .18
	final static public double PID_DRIVE_BRAKE_SPEED = .43;
	final static public double PID_DRIVE_BRAKE_TIME = .3;
	final static public double PID_DRIVE_FILTER_CONSTANT = 0.5;
	final static public int VAR_1_BUFFER_SIZE = 20;
	
	final static public int LOGGER_LEVEL1 = 1;
	final static public int LOGGER_LEVEL2 = 2;
	final static public int LOGGER_LEVEL3 = 3;
	final static public int LOGGER_LEVEL4 = 4;
	final static public int LOGGER_LEVEL5 = 5;
	
	final static public double PID_TURN_THRESHOLD = .12;
	final static public double NAVX_RESET_WAIT_TIME = 0.3;
	
	final static public double PID_TURN_FILTER_CONSTANT = .5;
	static public double PID_TURN_POWER_SCALE_FACTOR = 1.0;    //0.7;
	static public double PID_TURN_KP = 0.0508;  //0.0508
	static public double PID_TURN_KD = 0.008;  //0.0744
	static public double PID_TURN_KI = 0.0;    //
	final static public double PID_TURN_TIMEOUT = 3.0;
	final static public double PID_TURN_INTEGRAL_MAX = 0.0;  //need values
	final static public double PID_TURN_INTEGRAL_MIN = 0.0;  //
	
	final public static double PID_TURN_MAX_POWER_RT =  1.0;
	final public static double PID_TURN_MIN_POWER_RT =  0.4;

	final public static double PID_TURN_MAX_POWER_LT = -1.0;
	final public static double PID_TURN_MIN_POWER_LT = -0.4;
	
	//the distances below are for MID AUTONOMOUS PATHS
	
	final static public double MID_LEAVE_WALL = 36.0;                                 
	final static public double MID_AFTER_TURN_DIST = 72.0;
	final static public double MID_APPROACH_SWITCH = 55.2;
	final static public double MID_LEAVE_SWITCH = 43.2;
	final static public double MID_GO_AROUND_SWITCH = 84.0;
	final static public double MID_APPROACH_CUBE_CLOSE = 24.0;
	final static public double MID_APPROACH_CUBE_FAR = 48.0;
	final static public double MID_BACK_AWAY = 20.0;
	
	final static public double MID_RIGHT_SWITCH_SCALE_INIT_DISTANCE = 66.3;
	final static public double MID_RIGHT_SWITCH_SCALE_TOWARDS_SWITCH = 64;
	final static public double MID_RIGHT_SWITCH_SCALE_NEXT_TO_SWITCH = 36.5;
	final static public double MID_RIGHT_SWITCH_SCALE_LEAVE_SWITCH = 65;
	final static public double MID_RIGHT_SWITCH_SCALE_AROUND_SWITCH = 89;
	final static public double MID_RIGHT_SWITCH_SCALE_TOWARDS_CUBE = 47.5;
	final static public double MID_RIGHT_SWITCH_SCALE_TOWARDS_SCALE = 89;
	
	final static public int TURN_DEG_45 = 45;
	final static public int TURN_DEG_90 = 90;
	public static final double TURN_DEG_60 = 60;
	public static final int TURN_DEG_30 = 30;
	
	final static public double HALF_SPEED = 0.5;
	final static public double STOP = 0.0;
	
	final static public int STRAIGHTDRIVE_TIMEOUT = 2;
	
	final static public double SAMPLE_TIME = 0.0;
	
	final static public double ROBOT_WIDTH = 34.0;
	final static public double ROBOT_LENGTH = 39.0;
	final static public double HALF_ROBOT_LENGTH = 19.5;
	final static public double HALF_ROBOT_WIDTH = 17.0;
	
	//the distances below are for the LEFT AND RIGHT AUTONOMOUS PATHS
	
	final static public double SIDE_PATH_INIT_DISTANCE = 335.65;
	final static public double SIDE_PATH_APPROACH_SCALE = 41.88;
	final static public double SIDE_PATH_LEAVE_SCALE = 112.8;
	final static public double SIDE_PATH_APPROACH_CUBE = 12;
	
	//the distances below are for the LEFT AND RIGHT NO SCALE AUTONOMOUS PATHS
	
	final static public double NO_SCALE_INIT_DISTANCE = 251.73;
	final static public double NO_SCALE_APPROACH_SCALE = 84;
	final static public double NO_SCALE_POSITION_NEXT_TO_SCALE = 24.0;

	//the distance below are for the rightSclaeScale AUTONOMOUS PATHS
	
	final static public double RIGHT_SCALE_SCALE_INIT_DIST = 216.0;
	final static public double RIGHT_SCALE_SCALE_LEFT_TURN = 85.25 - //Distance from switch to wall
				                                             29.69 - //Distance from side to wall to robot
				                                             34.00 - //Width of robot
				                                             6.0;   //Avoid platform ramp
	final static public double RIGHT_SCALE_SCALE_APPRO_SCALE = 64.0;
	final static public double RIGHT_SCALE_SCALE_BACK_UP = 20.0;
	final static public double RIGHT_SCALE_SCALE_APPRO_CUBE_OPEN = 30.7; 
	final static public double RIGHT_SCLALE_SCALE_APPRO_CUBE = 20.0;
		
	final static public double ZERO = 0.0;

	final static public double CLIMB_SPEED = 0;
	final static public double LIFT_SPEED = 0;
	final static public double INTAKE_SPEED = 0.7;
	
	static public boolean bicepDeployed = false;
	static public boolean forearmOpen = false;
	
	final static public String POSITION_SELECTORL = "Position Left";
	final static public String POSITION_SELECTORM = "Position Mid";
	final static public String POSITION_SELECTORR = "Position Right";
	static public String Turn_KP = "Turn KP";
	static public String Turn_KI = "Turn KI";
	static public String Turn_KD = "Turn KD";
	static public String SCALE_FACTOR_LABEL = "Turn Scale Factor";
	
	final static public double LIFT_SCALE_HEIGHT = 0.0;
	final static public double LIFT_SWITCH_HEIGHT = 0.0;
	
	static public double AUTO_STARTPOS_DEF_SPEED = 0.5;
	static public double AUTO_STARTPOS_DEF_DISTANCE = 10.0;
	static public double AUTO_STARTPOS_DEF_TIMEOUT = 3.0;
	


}