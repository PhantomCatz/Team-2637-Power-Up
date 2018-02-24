package autonomous;

import constants.CatzConstants;
import mechanisms.CatzGrabber;
import robot.CatzRobotMap;

/**********************************************************
 * Timothy Vu 3 Feb 2018 TV 
 * Adding in Left and Right paths 
 * Methods: middlePathL middlePathR leftPath rightPath 
 * Functionality: Paths for the autonomous period
 *********************************************************/
public class CatzAutonomousPaths {
	/******************************
	 * All distances in the comments are not factoring in robot length
	 * 
	 * Timeout is set to 2 sec
	 ****************************/

	public static void middlePathR() {

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_LEAVE_WALL - CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); // Drive

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_LEAVE_WALL - CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); 


		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.PID_DRIVE_TIMEOUT); // Turn 45deg right

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_AFTER_TURN_DIST),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 72in

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.PID_DRIVE_TIMEOUT); // Turn 45deg left

		CatzRobotMap.lift.liftToSwitchHeight();
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_APPROACH_SWITCH - CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); // Drive
																															// forward
		/******************************************************
		/* cube placing code here   */
		CatzRobotMap.grabber.shootCube();
		/******************************************************/
		

		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED,
				(CatzConstants.MID_BACK_AWAY + CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); // Drive
																													// backwards
																													// 20in

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg right

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_LEAVE_SWITCH),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 43.2in

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg left

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_GO_AROUND_SWITCH),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 84in

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg left
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_APPROACH_CUBE_CLOSE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT); // 48in can be used here for the further cube

		/******************************************************
		/*  cube pickup code here  */
		CatzRobotMap.grabber.intakeCube();
		/******************************************************/
	}

	public static void middlePathL() {
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_LEAVE_WALL - CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); // Drive
																													// forward
																													// 36in
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_45, CatzConstants.PID_DRIVE_TIMEOUT); // Turn 45deg right

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_AFTER_TURN_DIST),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 72in

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_45, CatzConstants.PID_DRIVE_TIMEOUT); // Turn 45deg left
		
		CatzRobotMap.lift.liftToSwitchHeight();
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_APPROACH_SWITCH - CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); // Drive
																															// forward
																															// 55.2in
		//CatzAutonomousInit.shootCube();
		/******************************************************
		/*  cube placing code here   */
		CatzRobotMap.grabber.shootCube();
		/******************************************************/

		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED,
				(CatzConstants.MID_BACK_AWAY + CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); // Drive
																													// backwards
																													// 20in

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg left

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_LEAVE_SWITCH),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 43.2in

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg right

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_GO_AROUND_SWITCH),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 84in

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg right
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_APPROACH_CUBE_CLOSE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT); // 48in can be used here for the further cube
		/******************************************************
		 * Write cube pickup code here
		 * 
		 ******************************************************/
		//CatzAutonomousInit.intakeCube();
		/* cube pickup code here  */
		CatzRobotMap.grabber.intakeCube();
		/******************************************************/

	}

	public static void leftPath() {

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 335_65in

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg right

		CatzRobotMap.lift.liftToScaleHeight();
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.SIDE_PATH_APPROACH_SCALE),
				CatzConstants.PID_DRIVE_TIMEOUT); // drive forward 41.88in
		
		
		/******************************************************
		/*  scale cube placing code here  */
		CatzRobotMap.grabber.shootCube();
		/******************************************************/
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg right

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.SIDE_PATH_LEAVE_SCALE),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 112_8in

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg
		
		CatzRobotMap.lift.dropToGroundHeight();
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_APPROACH_CUBE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT); // Drive forward 12in

		/******************************************************
		/*  cube pickup code here */
		CatzRobotMap.grabber.intakeCube();
		/******************************************************/
	}

	public static void rightPath() {

		CatzRobotMap.getInstance();
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),

				CatzConstants.PID_DRIVE_TIMEOUT); // drive forward 335_65in

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg left

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_APPROACH_SCALE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT); // drive forward 41.88in
		
		
		/******************************************************
		/* Write scale cube placing code here */
		CatzRobotMap.grabber.shootCube();
		/******************************************************/


		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg left

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_LEAVE_SCALE + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT); // drive forward 112.8in

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_DRIVE_TIMEOUT); // turn 90deg right

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.SIDE_PATH_APPROACH_CUBE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT); // drive forward 12in

		/******************************************************
		/* Write cube pickup code here     */
		CatzRobotMap.grabber.intakeCube();
		/******************************************************/
	}

	/*
	 * public static void leftSideNoScale() {
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
	 * CatzConstants.PID_DRIVE_TIMEOUT); // drive forward 251.73in
	 * 
	 * CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90,
	 * CatzConstants.PID_DRIVE_TIMEOUT); // turn 90 deg right
	 * 
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
	 * CatzConstants.PID_DRIVE_TIMEOUT); // drive 251.73in
	 * 
	 * CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90,
	 * CatzConstants.PID_DRIVE_TIMEOUT); // turn 90 deg left
	 * 
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_APPROACH_SCALE - CatzConstants.HALF_ROBOT_LENGTH),
	 * CatzConstants.PID_DRIVE_TIMEOUT); // drive 84 in
	 * 
	 * CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90,
	 * CatzConstants.PID_DRIVE_TIMEOUT); // turn 90 deg left
	 * 
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_POSITION_NEXT_TO_SCALE -
	 * CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT); // drive
	 * forward 24in
	 * 
	 * /****************************************************** Write scale cube
	 * placing code here
	 * 
	 ******************************************************/

	/*
	 * public static void rightSideNoScale() {
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
	 * CatzConstants.PID_DRIVE_TIMEOUT);
	 * 
	 * CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90,
	 * CatzConstants.PID_DRIVE_TIMEOUT);
	 * 
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
	 * CatzConstants.PID_DRIVE_TIMEOUT);
	 * 
	 * CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90,
	 * CatzConstants.PID_DRIVE_TIMEOUT);
	 * 
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_APPROACH_SCALE - CatzConstants.HALF_ROBOT_LENGTH),
	 * CatzConstants.PID_DRIVE_TIMEOUT);
	 * 
	 * CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90,
	 * CatzConstants.PID_DRIVE_TIMEOUT);
	 * 
	 * CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
	 * (CatzConstants.NO_SCALE_POSITION_NEXT_TO_SCALE -
	 * CatzConstants.HALF_ROBOT_LENGTH), CatzConstants.PID_DRIVE_TIMEOUT);
	 * 
	 * /****************************************************** Write scale cube
	 * placing code here
	 * 
	 ******************************************************/

	public void middleRightSwitchScale() {


		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_SWITCH_SCALE_TOWARDS_SWITCH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_NEXT_TO_SWITCH - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzRobotMap.grabber.shootCube();

		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_NEXT_TO_SWITCH + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_LEAVE_SWITCH - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.MID_SWITCH_SCALE_AROUND_SWITCH,
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				CatzConstants.MID_SWITCH_SCALE_TOWARDS_CUBE - CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT);


		/******************************************************
		/* Write cube pickup code here     */
		CatzRobotMap.grabber.intakeCube();
		/******************************************************/

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.MID_SWITCH_SCALE_TOWARDS_SCALE,
				CatzConstants.PID_DRIVE_TIMEOUT);

		/******************************************************
		/* scale cube deploy code here   */
		CatzRobotMap.grabber.shootCube();
		/******************************************************/

	}

	public void middleLeftSwitchScale() {

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_INIT_DISTANCE - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_60, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, (CatzConstants.MID_SWITCH_SCALE_TOWARDS_SWITCH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_30, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_NEXT_TO_SWITCH - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzRobotMap.grabber.shootCube();

		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_NEXT_TO_SWITCH + CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				(CatzConstants.MID_SWITCH_SCALE_LEAVE_SWITCH - CatzConstants.HALF_ROBOT_LENGTH),
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.MID_SWITCH_SCALE_AROUND_SWITCH,
				CatzConstants.PID_DRIVE_TIMEOUT);

		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED,
				CatzConstants.MID_SWITCH_SCALE_TOWARDS_CUBE - CatzConstants.HALF_ROBOT_LENGTH,
				CatzConstants.PID_DRIVE_TIMEOUT);


		/******************************************************
		/* Write cube pickup code here     */
		CatzRobotMap.grabber.intakeCube();
		/******************************************************/
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_90, CatzConstants.PID_TURN_TIMEOUT);

		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.MID_SWITCH_SCALE_TOWARDS_SCALE,
				CatzConstants.PID_DRIVE_TIMEOUT);

		/******************************************************
		/* Write scale cube deploy code here   */

		CatzRobotMap.grabber.shootCube();
		/******************************************************/
	}

	public void middleRightSwitchSwitch() {
		
		/************************************
		 * The following code:
		 * Drives forward to the switch
		 * Places cube in the switch
		 ************************************/
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.MID_SWITCH_SWITCH_INIT_DIST - CatzConstants.HALF_ROBOT_LENGTH,
					CatzConstants.PID_DRIVE_TIMEOUT);
																														
		CatzPIDTurn.PIDturn(CatzConstants.TURN_DEG_60, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.MID_SWITCH_SCALE_TOWARDS_SWITCH,            
					CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDTurn.PIDturn(-CatzConstants.TURN_DEG_30, CatzConstants.PID_TURN_TIMEOUT);
		
		CatzPIDDrive.PIDDrive(CatzConstants.HALF_SPEED, CatzConstants.MID_SWITCH_SWITCH_APPROACH_SWITCH - CatzConstants.HALF_ROBOT_LENGTH, 
					CatzConstants.PID_DRIVE_TIMEOUT);
		
		CatzRobotMap.grabber.shootCube();
		
		/*****************************************
		 * The following code:
		 * Backs away from switch
		 * Goes around and grabs another cube
		 * Turns and approaches the side of switch
		 * Places second cube in switch
		 *****************************************/
		
		CatzPIDDrive.PIDDrive(-CatzConstants.HALF_SPEED, CatzConstants.MID_LEAVE_SWITCH + CatzConstants.HALF_ROBOT_LENGTH, 
					CatzConstants.PID_TURN_TIMEOUT);
		
		

	}
}
