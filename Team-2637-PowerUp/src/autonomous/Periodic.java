package autonomous;

import constants.CatzConstants;

public class Periodic
{
	public void middlePathR()
	{
		
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_36IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);      //Drive forward 36in
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                   //Turn 45deg right
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_72IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);        //Drive forward 72in
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                 //Turn 45deg left
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_55_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		DriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, CatzConstants.DIST_20IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_43_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_84IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);   
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_24IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);         //48in can be used here for the further cube
		
		/******************************************************
		 * Write cube pick up code here
		 * 
		 ******************************************************/
	}
	
	public void middlePathL()
	{
		
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_36IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);      //Drive forward 36in
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                   //Turn 45deg right
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_72IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);        //Drive forward 72in
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                 //Turn 45deg left
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_55_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		DriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, CatzConstants.DIST_20IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_43_2IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_84IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);    
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_24IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);         //48in can be used here for the further cube
		
		/******************************************************
		 * Write cube pick up code here
		 * 
		 ******************************************************/
	}
	
	public void leftPath()
	{
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_335_65IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_41_88IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_112_8IN - CatzConstants.ROBOT_L, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		
		/******************************************************
		 * Write cube pick up code here
		 * 
		 ******************************************************/
		
		
	}
}
