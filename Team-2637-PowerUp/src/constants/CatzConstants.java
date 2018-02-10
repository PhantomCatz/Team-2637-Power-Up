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
	
	static public double straightkP = .18;
	static public double straightkD = .23;  //ORIGINALLY .18
	final static public int VAR_1_BUFFER_SIZE = 20;
	
	final static public int LOGGER_LEVEL1 = 1;
	final static public int LOGGER_LEVEL2 = 2;
	final static public int LOGGER_LEVEL3 = 3;
	final static public int LOGGER_LEVEL4 = 4;
	final static public int LOGGER_LEVEL5 = 5;
	
	static public double TURN_KP = 0.0708;
	static public double TURN_KD = 0.1044;
	static public double TURN_KI = 0.0;  //
	static public double PID_INTEGRAL_MAX = 0.0;  //need values
	static public double PID_INTEGRAL_MIN = 0.0;  //
	
	final static public double DIST_36IN = 36.0;                                 
	final static public double DIST_72IN = 72.0;
	final static public double DIST_55_2IN = 55.2;
	final static public double DIST_5IN = 5.0;
	final static public double DIST_43_2IN = 43.2;
	final static public double DIST_84IN = 84.0;
	final static public double DIST_24IN = 24.0;
	final static public double DIST_48IN = 48.0;
	final static public double DIST_20IN = 20.0;
	
	final static public int TURN_DEG_45 = 45;
	final static public int TURN_DEG_90 = 90;
	
	final static public double HALF_SPEED = 0.5;
	final static public double STOP = 0.0;
	
	final static public int STRAIGHTDRIVE_TIMEOUT = 2;
	
	final static public double SAMPLE_TIME = 0.0;
	
	final static public double ROBOT_WIDTH = 28.0;
	final static public double ROBOT_LENGTH = 31.0;   
	
	final static public double DIST_335_65IN = 335.65;
	final static public double DIST_41_88IN = 41.88;
	final static public double DIST_112_8IN = 112.8;
	final static public double DIST_12IN = 12;
	final static public double DIST_251_73IN = 251.73;
	
	final static public double ZERO = 0.0;
	
	final static public double PID_TURN_THRESHOLD = 0.1;
	final static public double NAVX_RESET_WAIT_TIME = 0.1;
	
	final static public double CLIMB_SPEED = 0;
	final static public double LIFT_SPEED = 0;
	final static public double INTAKE_SPEED = 0.7;
	
	static public boolean grabberDeployed = false;
	static public boolean flapOpen = false;
	
	final static public String POSITION_SELECTORL = "Position Left";
	final static public String POSITION_SELECTORM = "Position Mid";
	final static public String POSITION_SELECTORR = "Position Right";

}
