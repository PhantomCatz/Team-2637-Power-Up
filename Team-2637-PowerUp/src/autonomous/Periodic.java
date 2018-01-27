package autonomous;

import constants.CatzConstants;

public class Periodic
{
	public void middlePathR()
	{
		
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_36IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);      //Drive forward 36in
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                   //Turn 45deg right
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_72IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);        //Drive forward 72in
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                 //Turn 45deg left
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_55_2IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		DriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, CatzConstants.DIST_20IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_43_2IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_84IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);   
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_24IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);         //48in can be used here for the further cube
	}
	
	public void middlePathL()
	{
		
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_36IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);      //Drive forward 36in
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                   //Turn 45deg right
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_72IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);        //Drive forward 72in
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.TIMEOUT_2int);                                 //Turn 45deg left
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_55_2IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		DriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, CatzConstants.DIST_20IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_43_2IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_84IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);
		PIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.TIMEOUT_2int);    
		DriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, CatzConstants.DIST_24IN, CatzConstants.SAMPLE_TIME, CatzConstants.TIMEOUT_2);         //48in can be used here for the further cube
		
	}
}
