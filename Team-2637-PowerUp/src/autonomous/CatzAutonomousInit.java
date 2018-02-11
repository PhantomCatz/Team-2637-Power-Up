package autonomous;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzAutonomousInit
{
	
	public static void runAutnomousInit()
	{
		//choosePath();
		CatzPIDTurn.PIDturn(90, 3);
	}
	
	public static void choosePath() {
		
		String gameData;
		
		boolean check_box1 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORL, false);
		boolean check_box2 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORM, false);
		boolean check_box3 = SmartDashboard.getBoolean(CatzConstants.POSITION_SELECTORR, false); 

		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		System.out.println(gameData);
		if (gameData.charAt(0) == 'L' && check_box2 == true) {
			System.out.println("middlePathL");
			CatzAutonomousPaths.middlePathL();
			
		} else if (gameData.charAt(0) == 'R' && check_box2 == true) {
			System.out.println("middlePathR");
			CatzAutonomousPaths.middlePathR();
			
		} else if(gameData.charAt(1) == 'R' && check_box3 == true){
			CatzAutonomousPaths.rightPath();	
			System.out.println("rightPath");
			
		} else if(gameData.charAt(1) == 'L' && check_box1 == true){
			CatzAutonomousPaths.leftPath();
			System.out.println("LeftPath");
		} 
			
		System.out.println("Init Done");
		
	}

}
