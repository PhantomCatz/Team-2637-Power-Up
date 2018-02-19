package autonomous;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzAutonomousInit
{
	
	public static void runAutonomousInit()
	{
		setMechanisms();
		//choosePath();
		//CatzPIDTurn.setDebugModeEnabled( true );
		//CatzPIDTurn.PIDturn(90, 5);
		//CatzPIDTurn.PIDturn(-90, 5);
		CatzPIDDrive.setDebugModeEnabled(true);
		CatzPIDDrive.PIDDrive(0.7, 100, 3);
	}
	
	public static void setMechanisms() 
	{
		CatzRobotMap.grabber.forearmClose();
		CatzRobotMap.grabber.retractBicep();
	}
	public static void liftToSwitchHeight()
	{
		if(CatzRobotMap.liftEncoder.get() < CatzConstants.LIFT_SWITCH_HEIGHT)
			CatzRobotMap.lift.liftUp();
		else if(CatzRobotMap.liftEncoder.get() > CatzConstants.LIFT_SWITCH_HEIGHT)
			CatzRobotMap.lift.liftDown();
		else
			CatzRobotMap.lift.stopLift();
	}
	public static void liftToScaleHeight()
	{
		if(CatzRobotMap.liftEncoder.get() < CatzConstants.LIFT_SCALE_HEIGHT)
			CatzRobotMap.lift.liftUp();
		else if(CatzRobotMap.liftEncoder.get() > CatzConstants.LIFT_SCALE_HEIGHT)
			CatzRobotMap.lift.liftDown();
		else
			CatzRobotMap.lift.stopLift();
	}
	public static void choosePath() 
	{	
		String gameData;
		
		boolean check_box1 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
		boolean check_box2 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		boolean check_box3 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false); 

		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		System.out.println(gameData);
		if (gameData.charAt(0) == 'L' && check_box2 == true) {
			System.out.println("middlePathL");
			CatzAutonomousPaths.middlePathL();
			System.out.println("Init Done");
			
		} else if (gameData.charAt(0) == 'R' && check_box2 == true) {
			System.out.println("middlePathR");
			CatzAutonomousPaths.middlePathR();
			System.out.println("Init Done");
			
		} else if(gameData.charAt(1) == 'R' && check_box3 == true){
			System.out.println("rightPath");
			CatzAutonomousPaths.rightPath();	
			System.out.println("Init Done");
			
		} else if(gameData.charAt(1) == 'L' && check_box1 == true){
			System.out.println("LeftPath");
			CatzAutonomousPaths.leftPath();
			System.out.println("Init Done");
			
		} else {
			System.out.println("Init failed");
			CatzPIDDrive.PIDDrive(0.5,10.0,3.0);
		}
			
		
	}

}
