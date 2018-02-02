package components;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
public class CatzDrive
{
	//private final String NAME;
	private DifferentialDrive drive;
	//private CatzLogger logger;
	//private CatzTimerMap timer;
	public CatzDrive(SpeedControllerGroup leftMotors, SpeedControllerGroup rightMotors)
	{
		//timer = CatzTimerMap.getInstance();
		//logger = CatzLogger.getInstance();
		//NAME = this.getClass().getSimpleName();
		drive = new DifferentialDrive(leftMotors, rightMotors);
	}
	public void setModeArcadeDriveRacing(CatzXboxController control)
	{
		//logger.add(NAME, "Arcade Drive set to Racing.", CatzConstants.LEVEL5, timer.get());
		drive.arcadeDrive(control.getRightTrigger()-control.getLeftTrigger(), control.getRightStickX());
	}
	public void setModeArcadeDriveFlash(CatzXboxController control)
	{
		//logger.add(NAME, "Arcade Drive set to Flash.", CatzConstants.LEVEL5, timer.get());
		drive.arcadeDrive(control.getLeftStickY(), control.getRightStickX());
	}
	public void tankDrive(double lPower, double rPower)
	{
		drive.tankDrive(lPower, rPower);
	}
}