package autonomous;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import components.CatzCANTalonSRX;
import components.CatzDrive;
import constants.CatzConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
public class DriveStraight
{
	/*CatzCANTalonSRX frontRight;
	CatzCANTalonSRX frontLeft;
	CatzCANTalonSRX backRight;
	CatzCANTalonSRX backLeft;*/
	WPI_TalonSRX frontRight;
	WPI_TalonSRX frontLeft;
	WPI_TalonSRX backRight;
	WPI_TalonSRX backLeft;
	
	CatzConstants constants = new CatzConstants();
	CatzDrive drive = new CatzDrive(frontRight, frontLeft, backRight, backLeft);
	Timer functionTimer = new Timer();
	AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
	Encoder wheelEncoder = new Encoder(constants.DIO_PORT6,constants.DIO_PORT7,false,Encoder.EncodingType.k2X);
	
	void EncoderStraightDrive(double speed, double distance, double sampleTime,double timeout)
	{
		int loopCount        = 0;
		double encoderIssues = 0;
		int dbgCount1        = 0;

		boolean done     = false;
		boolean gearState = true;		//DATA CHECK VARIABLES

		double previousAngle = 0.0;
		double currentAngle;
		double deltaAngle;			//FUNCTION VARIABLES
		double derivative;
		double deltaTime = sampleTime/1000;

		double encoderCheckNumber;
		double lastEncoderValue = 0;

		ahrs.reset();
		wheelEncoder.reset();

		functionTimer.reset();
		functionTimer.start();

		currentAngle = ahrs.getAngle();

		deltaAngle = currentAngle-previousAngle;

		derivative = deltaAngle/deltaTime;

		drive.tankDrive(speed, constants.straightkP*currentAngle + constants.straightkD*derivative);

		previousAngle = currentAngle;

		if (functionTimer.get() > timeout)
			done = true;

			/***********************************************
			*encoderCheckNumber = wheelEncoder.Get();
			*if(lastEncoderValue==encoderCheckNumber)
			*	encoderIssues++;
			*lastEncoderValue=encoderCheckNumber;
			*loopCount++;
			*dbgCount1++;
			************************************************/

		dbgCount1++;
		if (dbgCount1== constants.VAR_1_BUFFER_SIZE)
			dbgCount1=0;

		if(speed<0)
			drive.tankDrive(.43,0);
		else
			drive.tankDrive(-.43,0);
		drive.tankDrive(0,0);
		functionTimer.stop();

		SmartDashboard.putNumber("Function timer value", functionTimer.get());
		SmartDashboard.putBoolean("gearState", gearState);
		SmartDashboard.putNumber("encoderCheck", encoderIssues);
		SmartDashboard.putNumber("drive straight loop count", loopCount);
	}	
}