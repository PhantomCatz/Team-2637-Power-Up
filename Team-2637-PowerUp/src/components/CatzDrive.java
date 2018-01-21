package components;
import robot.CatzRobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import logger.CatzLogger;
import robot.CatzRobotMap;
public class CatzDrive
{
	CatzRobotMap robotmap;
	private final String NAME;
	private CatzLogger log;
	private DifferentialDrive Drive;
	private SpeedControllerGroup leftMotors;
	private SpeedControllerGroup rightMotors;
	public CatzDrive(WPI_TalonSRX frontRight, WPI_TalonSRX frontLeft, WPI_TalonSRX backRight, WPI_TalonSRX backLeft)
	{
		robotmap = CatzRobotMap.getInstance();
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
		rightMotors = new SpeedControllerGroup(frontRight, backRight);
	}
	public void setModeArcadeDriveRacing(CatzXboxController control)
	{
		log.add(NAME, "Arcade Drive set to Racing.", robotmap.constants.LEVEL5, robotmap.timer.get());
		Drive.arcadeDrive(control.GetRightTrigger()-control.GetLeftTrigger(), control.GetRightStickX());
	}
	public void setModeArcadeDriveFlash(CatzXboxController control)
	{
		log.add(NAME, "Arcade Drive set to Flash.", robotmap.constants.LEVEL5, robotmap.timer.get());
		Drive.arcadeDrive(control.GetLeftStickY(), control.GetRightStickX());
	}
	public void tankDrive(double lPower, double rPower)
	{
		Drive.tankDrive(lPower, rPower);
	}
}