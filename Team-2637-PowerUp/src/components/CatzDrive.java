package components;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import constants.CatzConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import logger.CatzLogger;
public class CatzDrive
{
	private final String NAME;
	private DifferentialDrive Drive;
	private CatzLogger logger;
	private CatzTimerMap timer;
	public CatzDrive(WPI_TalonSRX frontRight, WPI_TalonSRX frontLeft, WPI_TalonSRX backRight, WPI_TalonSRX backLeft)
	{
		timer = CatzTimerMap.getInstance();
		logger = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
	}
	public void setModeArcadeDriveRacing(CatzXboxController control)
	{
		logger.add(NAME, "Arcade Drive set to Racing.", CatzConstants.LEVEL5, timer.get());
		Drive.arcadeDrive(control.getRightTrigger()-control.getLeftTrigger(), control.getRightStickX());
	}
	public void setModeArcadeDriveFlash(CatzXboxController control)
	{
		logger.add(NAME, "Arcade Drive set to Flash.", CatzConstants.LEVEL5, timer.get());
		Drive.arcadeDrive(control.getLeftStickY(), control.getRightStickX());
	}
	public void tankDrive(double lPower, double rPower)
	{
		Drive.tankDrive(lPower, rPower);
	}
}