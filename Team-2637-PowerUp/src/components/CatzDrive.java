package components;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import logger.CatzLogger;
public class CatzDrive
{
	
	final String NAME;
	CatzLogger log;
	DifferentialDrive Drive;
	
	public CatzDrive(int frontRightID, int frontLeftID, int backRightID, int backLeftID)
	{
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		Drive = new DifferentialDrive(frontRightID, frontLeftID, backRightID, backLeftID);
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