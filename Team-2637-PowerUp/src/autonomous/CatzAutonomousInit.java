package autonomous;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzAutonomousInit
{
	public static void runAutnomousInit()
	{
		String gameData;
		boolean check_box1;
		boolean check_box2;
		boolean check_box3;

		boolean prev_box1;
		boolean prev_box2;
		boolean prev_box3;

		int last_selected_box_num;

		WPI_TalonSRX talon_1;
		final int PORT_3 = 3;

		talon_1 = new WPI_TalonSRX(PORT_3);

		last_selected_box_num = 0; // no motor has been selected.
		check_box1 = false;
		check_box2 = false;
		check_box3 = false;
		prev_box1 = false;
		prev_box2 = false;
		prev_box3 = false;

		SmartDashboard.putBoolean("Position Left", check_box1);
		SmartDashboard.putBoolean("Position Mid", check_box2);
		SmartDashboard.putBoolean("Position Right", check_box3);

		check_box1 = SmartDashboard.getBoolean("Position Left", false);
		check_box2 = SmartDashboard.getBoolean("Position Mid", false);
		check_box3 = SmartDashboard.getBoolean("Position Right", false);

		if ((check_box1 != prev_box1) && (check_box1 == true)) {
			prev_box1 = check_box1;
			prev_box2 = false;
			prev_box3 = false;
			System.out.println("Position Left");
		} else if ((check_box2 != prev_box2) && (check_box2 == true)) {
			prev_box1 = false;
			prev_box2 = check_box2;
			prev_box3 = false;
			System.out.println("Position Mid");
		} else if ((check_box3 != prev_box3) && (check_box3 == true)) {
			prev_box1 = false;
			prev_box2 = false;
			prev_box3 = check_box3;
			System.out.println("Position Right");
		}

		// Update display
		SmartDashboard.putBoolean("Position Left", prev_box1);
		SmartDashboard.putBoolean("Position Mid", prev_box2);
		SmartDashboard.putBoolean("Position Right", prev_box3);

		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L' && check_box2 == true) {
			CatzAutonomousPaths.middlePathL();
		} else if (gameData.charAt(0) == 'R' && check_box2 == true) {
			CatzAutonomousPaths.middlePathR();
		}
		else if(gameData.charAt(1) == 'R' && check_box3 == true)
		{
		CatzAutonomousPaths.rightPath();	
		}
		else if(gameData.charAt(1) == 'L' && check_box1 == true)
		{
			CatzAutonomousPaths.leftPath();
		}

	}
}
