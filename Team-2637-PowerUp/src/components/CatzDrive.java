package components;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import logger.CatzLogger;
public class CatzDrive
{
	
	final String NAME;
	CatzLogger log;
	DifferentialDrive Drive;
	SpeedControllerGroup leftMotors;
	SpeedControllerGroup rightMotors;
	
	public CatzDrive(WPI_TalonSRX frontRight, WPI_TalonSRX frontLeft, WPI_TalonSRX backRight, WPI_TalonSRX backLeft)
	{
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
		rightMotors = new SpeedControllerGroup(frontRight, backRight);
		Drive = new DifferentialDrive(leftMotors, rightMotors);
	}
	public void setModeArcadeDriveRacing(CatzXboxController control)
	{
		log.add(NAME, "Arcade Drive set to Racing.", 5, -1);
		Drive.arcadeDrive(control.GetRightTrigger()-control.GetLeftTrigger(), control.GetRightStickX());
	}
	public void setModeArcadeDriveFlash(CatzXboxController control)
	{
		log.add(NAME, "Arcade Drive set to Flash.", 5, -1);
		Drive.arcadeDrive(control.GetLeftStickY(), control.GetRightStickX());
	}
}