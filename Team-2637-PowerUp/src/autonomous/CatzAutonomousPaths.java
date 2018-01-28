package autonomous;

import constants.CatzConstants;
import robot.CatzRobotMap;

public class CatzAutonomousPaths
{
	public static void middlePathR()
	{
		CatzRobotMap.getInstance();
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_36IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);      //Drive forward 36in
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                   //Turn 45deg right
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_72IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);        //Drive forward 72in
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                 //Turn 45deg left
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_55_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		CatzDriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, CatzConstants.DIST_20IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_43_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_84IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);   
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_24IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);         //48in can be used here for the further cube
	}
	
	public static void middlePathL()
	{
		CatzRobotMap.getInstance();
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_36IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);      //Drive forward 36in
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                   //Turn 45deg right
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_72IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);        //Drive forward 72in
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                 //Turn 45deg left
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_55_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		CatzDriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, CatzConstants.DIST_20IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_43_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_84IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);    
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_24IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);         //48in can be used here for the further cube
	}
}
