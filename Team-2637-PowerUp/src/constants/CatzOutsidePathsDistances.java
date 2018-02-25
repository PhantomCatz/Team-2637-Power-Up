/*
 *  Author : Jean Kwon
 *  Last Revised : 2-22-2018 JK
 *  created the class
 *  Functionality : stores all final values of outside paths
 */
package constants;

public class CatzOutsidePathsDistances {

	
	//the distance below are for the right_LRL <5>
	
		final static public double RIGHT_LRL_INIT_DIST = 261.0;
		final static public double TO_SCALE = 85.25 - //Distance from switch to wall
					                                	 29.69 - //Distance from side to wall to robot
					                                     34.00 - //Width of robot
					                                     6.0;   //Avoid platform ramp
		final static public double RIGHT_LRL_APPRO_SCALE = 38.0;
		final static public double RIGHT_LRL_ACCUE_CUBE = 7.0;
		final static public double RIGHT_LRL_TO_CUBE = 45.0;
		
		//the distance below are for the left_RLR <5>
		
		final static public double LEFT_RLR_INIT_DIST = 261.0;
		final static public double LEFTT_RLR_LEFT_TURN = 85.25 - //Distance from switch to wall
						                                             29.69 - //Distance from side to wall to robot
						                                             34.00 - //Width of robot
						                                             6.0;   //Avoid platform ramp
		final static public double LEFT_RLR_APPRO_SCALE = 38.0;
		final static public double LEFT_RLR_ACCUE_CUBE = 7.0;
		final static public double LEFT_RLR_TO_CUBE = 45.0;
	
		// the distance below are for the right_LLL 
		final static public double RIGHT_LLL_INIT = 222.0;
		final static public double RIGHT_LLL_APPO_SCALE = 217.12;
		final static public double RIGHT_LLL_GET_SCALE = 64.0;
		final static public double RIGHT_LLL_ACCUE_CUBE = 7.0;
		final static public double RIGHT_LLL_FINAL = 45.0;

		// the distance below are for the left_RRR
		final static public double LEFT_RRR_INIT = 222.0;
		final static public double LEFT_RRR_APPO_SCALE = 217.12;
		final static public double LEFT_RRR_GET_SCALE = 64.0;
		final static public double LEFT_RRR_ACCUE_CUBE = 7.0;
		final static public double LEFT_RRR_FINAL = 45.0;
		
		// the distance below are for the right_RLR Autonomous PATHS
		final static public double RIGHT_RLR_INIT = 222.0;
		final static public double RIGHT_RLR_APPO_SCALE = 217.12;
		final static public double RIGHT_RLR_GET_SCALE = 64.0;
		final static public double RIGHT_RLR_ACCUE_CUBE = 7.0;
		final static public double RIGHT_RLR_FINAL = 45.0;
		
		// the distance below are for the left_LRL Autonomous PATHS
		final static public double LEFT_LRL_INIT = 222.0;
		final static public double LEFT_LRL_APPO_SCALE = 217.12;
		final static public double LEFT_LRL_GET_SCALE = 64.0;
		final static public double LEFT_LRL_ACCUE_CUBE = 7.0;
		final static public double LEFT_LRL_FINAL = 45.0;
		
		// the distance below are for the right_RRR Autonomous PATHS

		final static public double RIGHT_RRR_INIT_DIST = 261.0;
		final static public double RIGHT_RRR_LEFT_TURN = 85.25 - //Distance from switch to wall
						                                             29.69 - //Distance from side to wall to robot
						                                             34.00 - //Width of robot
						                                             6.0;   //Avoid platform ramp
		final static public double RIGHT_RRR_APPRO_SCALE = 38.0;
		final static public double RIGHT_RRR_ACCUE_CUBE = 7.0;
		final static public double RIGHT_RRR_TO_CUBE = 45.0;
		
		// the distance below are for the left_LLL Autonomous PATHS
		final static public double LEFT_LLL_INIT_DIST = 261.0;
		final static public double LEFTT_LLL_LEFT_TURN = 85.25 - //Distance from switch to wall
						                                             29.69 - //Distance from side to wall to robot
						                                             34.00 - //Width of robot
						                                             6.0;   //Avoid platform ramp
		final static public double LEFT_LLL_APPRO_SCALE = 38.0;
		final static public double LEFT_LLL_APPRO_CUBE = 50.7;
		final static public double LEFT_LLL_ACCUE_CUBE = 7.0;
		final static public double LEFT_LLL_TO_CUBE = 45.0;
		
}
