/*
 *  Author : Jean Kwon
 *  Last Revised : 2-10-2018 JK
 *  add the checkbooks to select the path
 *  Methods :  startPositionSelector, updateSmartDashboard
 *  Functionality : select the start position, show the values
 */
package org.usfirst.frc.team2637.robot;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import mechanisms.CatzGrabber;

public class CatzRobotPeriodic
{	
	static CatzRobotMap instance = CatzRobotMap.getInstance();
	
	static boolean check_box1 = false;
	static boolean check_box2 = false;
	static boolean check_box3 = false;
	
	static boolean prev_box1 = false;
	static boolean prev_box2 = false;
	static boolean prev_box3 = false;

	public static void runRobotPeriodic(){
	
		startPositionSelector();
		//updateSmartDashboard();

	} 
	
	public static void startPositionSelector() {
	
		check_box1 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
		check_box2 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		check_box3 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false);

		if ((check_box1 != prev_box1) && (check_box1 == true)) {
			prev_box1 = check_box1;
			prev_box2 = false;
			prev_box3 = false;
			//System.out.println("Position Left");
		} else if ((check_box2 != prev_box2) && (check_box2 == true)) {
			prev_box1 = false;
			prev_box2 = check_box2;
			prev_box3 = false;
			//System.out.println("Position Mid");
		} else if ((check_box3 != prev_box3) && (check_box3 == true)) {
			prev_box1 = false;
			prev_box2 = false;
			prev_box3 = check_box3;
			//System.out.println("Position Right");
		}

		// Update display
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, prev_box1);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, prev_box2);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, prev_box3);

		
	}
	
	public static void updateSmartDashboard() {
		
		SmartDashboard.putNumber("navX", CatzRobotMap.getInstance().navx.getAngle());
		SmartDashboard.putNumber("Distance of left Encoder", CatzRobotMap.getInstance().wheelEncoderL.getDistance());
		SmartDashboard.putNumber("Distance of right Encoder", CatzRobotMap.getInstance().wheelEncoderR.getDistance());
		SmartDashboard.putNumber("Speed of Left Encoder", CatzRobotMap.getInstance().wheelEncoderL.getRate());
		SmartDashboard.putNumber("Speed of Right Encoder", CatzRobotMap.getInstance().wheelEncoderR.getRate());
		
		if (CatzConstants.flapOpen == true) {
			SmartDashboard.putString("Grabber flap", "Open");
		} else {
			SmartDashboard.putString("Grabber flap", "Closed");
		}
		
		if (CatzConstants.grabberDeployed == false) {
			SmartDashboard.putString("Grabber Deployed", "Up");
		} else {
			SmartDashboard.putString("Grabber Deployed", "Down");
		}	
		
	}
}
