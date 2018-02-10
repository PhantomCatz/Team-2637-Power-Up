package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;

/**********************************************************
 * Timothy Vu
 * 3 Feb 2018  TV
 * Adding in Left and Right paths
 * Methods: middlePathL  middlePathR  leftPath  rightPath
 *Functionality: 
 *********************************************************/
public class CatzAutonomousPaths
{
	/******************************
	 * All distances in the 
	 * comments are not factoring 
	 * in robot length
	 * 
	 * Timeout is set to 2 sec
	 ****************************/
	
	
	public static void middlePathR()
	{
		CatzRobotMap.getInstance();
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_36IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);      //Drive forward 36in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                   //Turn 45deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_72IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);        //Drive forward 72in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                 //Turn 45deg left
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_55_2IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		CatzDriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_20IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);    //Drive backwards 20in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_43_2IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);    //Drive forward 43.2in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_84IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);   //Drive forward 84in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);   	//turn 90deg left
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_24IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);         //48in can be used here for the further cube

		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
	}
	
	public static void middlePathL()
	{
		CatzRobotMap.getInstance();
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_36IN - CatzConstants.ROBOT_LENGTH),
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);      //Drive forward 36in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                   //Turn 45deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_72IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);        //Drive forward 72in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                 //Turn 45deg left
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_55_2IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		CatzDriveStraight.EncoderStraightDrive(-CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_20IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//Drive backwards 20in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_43_2IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//Drive forward 43.2in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_84IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//Drive forward 84in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);    //turn 90deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_24IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);         //48in can be used here for the further cube
		
		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
	}
	
	public static void leftPath()
	{
		CatzRobotMap.getInstance();
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_335_65IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//Drive forward 335_65in 
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_41_88IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//drive forward 41.88in
		
		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_112_8IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//Drive forward 112_8in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_12IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//Drive forward 12in
		
		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
		
	}
	
	public static void rightPath()
	{
		CatzRobotMap.getInstance();
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_335_65IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//drive forward 335_65in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_41_88IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//drive forward 41.88in
		
		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_112_8IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//drive forward 112.8in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_12IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//drive forward 12in
		
		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
		
	}
	
	public static void leftSideNoScale()
	{
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_251_73IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_251_73IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_84IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_24IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
	}
	
	public static void rightSideNoScale()
	{
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_251_73IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_251_73IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_84IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzDriveStraight.EncoderStraightDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.DIST_24IN - CatzConstants.ROBOT_LENGTH), 
				CatzConstants.SAMPLE_TIME, CatzConstants.STRAIGHTDRIVE_TIMEOUT);

		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
		
	}
}
