package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;
import mechanisms.CatzLift;

/**********************************************************
 * Timothy Vu
 * 3 Feb 2018  TV
 * Adding in Left and Right paths
 * Methods: middlePathL  middlePathR  leftPath  rightPath
 *Functionality: Paths for the autonomous period
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
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_LEAVE_WALL - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);      //Drive forward 36in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                   //Turn 45deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_AFTER_TURN_DIST - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);        //Drive forward 72in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                 //Turn 45deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_APPROACH_SWITCH - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_BACK_AWAY - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);    //Drive backwards 20in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_LEAVE_SWITCH - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);    //Drive forward 43.2in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_GO_AROUND_SWITCH - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);   //Drive forward 84in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);   	//turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_APPROACH_CUBE_CLOSE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);         //48in can be used here for the further cube

		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
	}
	
	public static void middlePathL()
	{
		CatzRobotMap.getInstance();
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_LEAVE_WALL - CatzConstants.ROBOT_LENGTH),
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);      //Drive forward 36in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                   //Turn 45deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_AFTER_TURN_DIST - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);        //Drive forward 72in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.STRAIGHTDRIVE_TIMEOUT);                                 //Turn 45deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_APPROACH_SWITCH - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);     //Drive forward 55.2in
		
		/******************************************************
		 * Write cube placing code here
		 * 
		 ******************************************************/
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_BACK_AWAY - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//Drive backwards 20in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_LEAVE_SWITCH - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//Drive forward 43.2in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_GO_AROUND_SWITCH - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//Drive forward 84in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);    //turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.MID_APPROACH_CUBE_CLOSE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);         //48in can be used here for the further cube
		
		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
	}
	
	public static void leftPath()
	{
		CatzRobotMap.getInstance();
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_INIT_DISTANCE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//Drive forward 335_65in 
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_APPROACH_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//drive forward 41.88in
		
		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_LEAVE_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//Drive forward 112_8in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_APPROACH_CUBE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//Drive forward 12in
		
		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
		
	}
	
	public static void rightPath()
	{
		CatzRobotMap.getInstance();
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_INIT_DISTANCE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//drive forward 335_65in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_APPROACH_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//drive forward 41.88in
		
		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_LEAVE_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//drive forward 112.8in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);		//turn 90deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.SIDE_PATH_APPROACH_CUBE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);			//drive forward 12in
		
		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
		
	}
	
	public static void leftSideNoScale()
	{
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);    //drive forward 251.73in
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);   //turn 90 deg right
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);      //drive 251.73in
		 
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);   //turn 90 deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_APPROACH_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);   //drive 84 in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);  //turn 90 deg left
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_POSITION_NEXT_TO_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);   //drive forward 24in
		
		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
	}
	
	public static void rightSideNoScale()
	{
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_APPROACH_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, Math.abs(CatzConstants.NO_SCALE_POSITION_NEXT_TO_SCALE - CatzConstants.ROBOT_LENGTH), 
				 CatzConstants.STRAIGHTDRIVE_TIMEOUT);

		/******************************************************
		 * Write scale cube placing code here
		 * 
		 ******************************************************/
		
	}
	
	public static void rightScaleScale () {
	
	//write the code for lift 
	
	CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCALE_SCALE_INIT_DIST - CatzConstants.ROBOT_LENGTH2,
			              CatzConstants.STRAIGHTDRIVE_TIMEOUT); //drive 216-17.5 to approach to the scale
	
	CatzPIDTurn.PIDturn(-90, CatzConstants.STRAIGHTDRIVE_TIMEOUT);  //turn 90deg left 
	
	CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.RIGHT_SCALE_SCALE_LEFT_TURN - CatzConstants.ROBOT_LENGTH2,
			              CatzConstants.STRAIGHTDRIVE_TIMEOUT); 
	
	
	}
}
