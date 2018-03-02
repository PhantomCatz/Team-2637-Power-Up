/*
 *  Author : Jean Kwon
 *  Last Revised : 2-10-2018 JK
 *  add the checkbooks to select the path
 *  Methods :  startPositionSelector, updateSmartDashboard
 *  Functionality : select the start position, show the values
 */
package robotFunctions;

import autonomous.CatzPIDTurn;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.CatzConstants;
import robot.CatzRobotMap;


public class CatzRobotPeriodic
{	
	static CatzRobotMap instance = CatzRobotMap.getInstance();
	
	static boolean check_boxL = false;
	static boolean check_boxM = false;
	static boolean check_boxR = false;
	
	static boolean prev_boxL = false;
	static boolean prev_boxM = false;
	static boolean prev_boxR = false;

	public static void runRobotPeriodic(){
	
		//runPositionSelector();
		//updateSmartDashboard();
		//if(CatzPIDTurn.isTuningModeEnabled())
			//updatePIDTurnConstants();

		
	} 
	
	public static void runPositionSelector() {
	
		check_boxL = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
		check_boxM = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		check_boxR = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false);

		if ((check_boxL != prev_boxL) && (check_boxL == true)) {
			prev_boxL = check_boxL;
			prev_boxM = false;
			prev_boxR = false;
			//System.out.println("Position Left");
		} else if ((check_boxM != prev_boxM) && (check_boxM == true)) {
			prev_boxL = false;
			prev_boxM = check_boxM;
			prev_boxR = false;
			//System.out.println("Position Mid");
		} else if ((check_boxR != prev_boxR) && (check_boxR == true)) {
			prev_boxL = false;
			prev_boxM = false;
			prev_boxR = check_boxR;
			//System.out.println("Position Right");
		} else {
			prev_boxL = false;
			prev_boxM = false;
			prev_boxR = false;
		}

		// Update display
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORL, prev_boxL);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORM, prev_boxM);
		SmartDashboard.putBoolean(CatzConstants.POSITION_SELECTORR, prev_boxR);

		
	}
	
	public static void updateSmartDashboard() {
		
		SmartDashboard.putNumber("navX", CatzRobotMap.navx.getAngle());
		SmartDashboard.putNumber("Distance of Left Encoder", CatzRobotMap.wheelEncoderL.getDistance());
		SmartDashboard.putNumber("Distance of Right Encoder", CatzRobotMap.wheelEncoderR.getDistance());
		SmartDashboard.putNumber("Value of Left Encoder", CatzRobotMap.wheelEncoderL.get());
		SmartDashboard.putNumber("Value of Right Encoder", CatzRobotMap.wheelEncoderR.get());
		SmartDashboard.putNumber("Value of lifter Encoder",CatzRobotMap.liftEncoder.get());
		
		SmartDashboard.putBoolean("Graber Forearms Open", CatzConstants.forearmClosed);
		SmartDashboard.putBoolean("Grabber Bicep Deployed", CatzConstants.bicepRetracted);
		
	}
	
	public static void updatePIDTurnConstants()   {

		CatzPIDTurn.PID_TURN_POWER_SCALE_FACTOR = SmartDashboard.getNumber(CatzConstants.SCALE_FACTOR_LABEL,0);
		CatzPIDTurn.PID_TURN_KP = SmartDashboard.getNumber(CatzConstants.TURN_KP_LABEL,0);
		CatzPIDTurn.PID_TURN_KD = SmartDashboard.getNumber(CatzConstants.TURN_KD_LABEL,0);
		CatzPIDTurn.PID_TURN_KI = SmartDashboard.getNumber(CatzConstants.TURN_KI_LABEL,0);

	}
	
	
}
