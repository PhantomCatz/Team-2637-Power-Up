package org.usfirst.frc.team2637.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot{
	
	boolean check_box1;
	boolean check_box2;
	boolean check_box3;

	boolean prev_box1;
	boolean prev_box2;
	boolean prev_box3;

	int last_selected_box_num;
	
	WPI_TalonSRX talon_1;
	final int PORT_3 = 3;

	public Robot() {
		
	}
	
	public void teleopInit() {
		talon_1 = new WPI_TalonSRX(PORT_3);
		
		last_selected_box_num = 0; // no motor has been selected.
		check_box1 = false; 
		check_box2 = false;
		check_box3 = false;
		prev_box1 = false;
		prev_box2 = false;
		prev_box3 = false;

		SmartDashboard.putBoolean("Set Motor at 1", check_box1);
		SmartDashboard.putBoolean("Set Motor at 0", check_box2);
		SmartDashboard.putBoolean("Set Motro at -1", check_box3);	
		
	}
	
	public void teleopPeriodic() {
		check_box1 = SmartDashboard.getBoolean("Set Motor at 1", false);
		check_box2 = SmartDashboard.getBoolean("Set Motor at 0", false);
		check_box3 = SmartDashboard.getBoolean("Set Motro at -1", false);
		
		if ((check_box1 != prev_box1) && (check_box1 == true)) {
			prev_box1 = check_box1;
			prev_box2 = false;
			prev_box3 = false;
			System.out.println("I am check box 1");
			talon_1.set(.5);
		} else if ((check_box2 != prev_box2) && (check_box2 == true)) {
			prev_box1 = false;
			prev_box2 = check_box2;
			prev_box3 = false;
			System.out.println("I am check box 2");
			talon_1.set(0);
		} else if ((check_box3 != prev_box3) && (check_box3 == true)) {
			prev_box1 = false;
			prev_box2 = false;
			prev_box3 = check_box3;
			System.out.println("I am check box 3");
			talon_1.set(-.5);
		}  		
		
		// Update display
		SmartDashboard.putBoolean("Set Motor at 1", prev_box1);
		SmartDashboard.putBoolean("Set Motor at 0", prev_box2);
		SmartDashboard.putBoolean("Set Motro at -1", prev_box3);		
	}
}

