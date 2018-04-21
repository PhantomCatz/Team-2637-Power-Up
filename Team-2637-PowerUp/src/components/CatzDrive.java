package components;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import robot.CatzRobotMap;

/*
*  Author : Derek Duenas
*  Last Revised : 2-9-2018 AL
*  touchup
*  Methods : setModeArcadeDriveRacing, setModeArcadeDriveFlash
*  Functionality : sets the drive mode to racing or flash
*/

public class CatzDrive
{
	private DifferentialDrive drive;
	public CatzDrive(SpeedControllerGroup leftMotors, SpeedControllerGroup rightMotors){
		drive = new DifferentialDrive(leftMotors, rightMotors);
		drive.setSafetyEnabled(false);
		printOutDebugData("Successfully instantiated CatzDrive");
	}
	
	public void setModeArcadeDriveRacing(CatzXboxController control){
		drive.arcadeDrive(control.getRightTrigger()-control.getLeftTrigger(), control.getRightStickX());
	}
	
	public void setModeArcadeDriveFlash(CatzXboxController control){
		drive.arcadeDrive(control.getLeftStickY(), control.getRightStickX());
	}
	public void setModeReverseArcadeDriveFlash(CatzXboxController control){
		drive.arcadeDrive(-control.getLeftStickY(), control.getRightStickX());
	}
	
	public void tankDrive(double lPower, double rPower){
		drive.tankDrive(lPower, rPower);
	}
	
	public void arcadeDrive(double xSpeed, double zRotation) {
		drive.arcadeDrive(xSpeed, zRotation);
	}
	
	private static void printOutDebugData(String info) {
		if(CatzRobotMap.debugMode == true) {
			double currentTime = CatzRobotMap.globalTimer.get();
			System.out.println(currentTime + "  -" + info);
		}
	}
}