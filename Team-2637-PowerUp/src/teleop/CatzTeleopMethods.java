package teleop;

import org.usfirst.frc.team2637.robot.CatzRobotMap;

import autonomous.CatzDriveStraight;
import autonomous.CatzPIDTurn;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CatzTeleopMethods 
{
	static CatzRobotMap instance;
	public static void runTeleopInit()
	{
		SmartDashboard.putNumber("kp",CatzConstants.TURN_KP);
		SmartDashboard.putNumber("kd", CatzConstants.TURN_KD);
		SmartDashboard.putNumber("ki", CatzConstants.TURN_KI);
		SmartDashboard.putNumber("pos max", CatzConstants.POS_MAX);
		SmartDashboard.putNumber("neg max", CatzConstants.NEG_MAX);
		SmartDashboard.putNumber("s kd", CatzConstants.straightkD);
		SmartDashboard.putNumber("s kp", CatzConstants.straightkP);
;	}
	public static void runTeleopPeriodic()
	{
		instance = CatzRobotMap.getInstance();
		instance.drive.setModeArcadeDriveFlash(instance.xbox);
		SmartDashboard.getNumber("kp",CatzConstants.TURN_KP);
		SmartDashboard.getNumber("kd", CatzConstants.TURN_KD);
		SmartDashboard.getNumber("ki", CatzConstants.TURN_KI);
		SmartDashboard.getNumber("pos max", CatzConstants.POS_MAX);
		SmartDashboard.getNumber("neg max", CatzConstants.NEG_MAX);
		
		SmartDashboard.putNumber("navx reading", instance.navx.getAngle());
		SmartDashboard.putNumber("encoder reading", instance.wheelEncoder.getDistance());
		
		SmartDashboard.getNumber("s kd", CatzConstants.straightkD);
		SmartDashboard.getNumber("s kp", CatzConstants.straightkP);
		if(instance.xbox.getAButton())
		{
			CatzPIDTurn.PIDturn(45, 3);
		}
		if(instance.xbox.getBButton())
		{
			CatzDriveStraight.EncoderStraightDrive(0.5, 100, 1, 3);
		}
			
	}
}
