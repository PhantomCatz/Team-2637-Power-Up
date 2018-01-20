package autonomous;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import constants.Constants;
import components.CatzTimer;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import components.CatzDrive;

public class AutoTurn
{
	WPI_TalonSRX frontRight;
	WPI_TalonSRX frontLeft;
	WPI_TalonSRX backRight;
	WPI_TalonSRX backLeft;
	
	Constants constants = new Constants();
	AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
	CatzTimer timer = new CatzTimer();
	Timer PDTimer = new Timer();
	Timer functionTimer = new Timer();
	CatzDrive drive = new CatzDrive(frontRight, frontLeft, backRight, backLeft);
	void PDTurn(double turnDegrees, int timeout)
	{
		ahrs.reset();
		timer.wait(.1);


		boolean done = false;
		int PDTurnLoopcount = 0;
		double TurnToDegrees = turnDegrees + ahrs.getAngle();
		double turnThreshold = .1;
		//double kP = 0.039, kD = 0.0147;
		double currentError;
		double previousError = 0;
		double deltaError;
		double derivative; 
		double deltaT;
		double power;

		functionTimer.reset();
		functionTimer.start();
		PDTimer.reset();

		//while((fabs(hardware->navx->GetAngle()) < fabs(TurnToDegrees)-turnThreshold ||
			//	fabs(hardware->navx->GetAngle()) > fabs(TurnToDegrees)+turnThreshold) && done!=true)
		while(Math.abs(ahrs.getAngle()) < Math.abs(TurnToDegrees)-turnThreshold || 
				Math.abs(ahrs.getAngle()) > Math.abs(TurnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			PDTimer.stop();
			deltaT = PDTimer.get();
			PDTimer.reset();
			PDTimer.start();



			currentError = TurnToDegrees-ahrs.getAngle();
			deltaError = currentError-previousError;
			derivative = deltaError/deltaT;

			power = .6*((constants.TurnkP*currentError)+(constants.TurnkD*derivative));
			drive.tankDrive(power,-power);

			previousError = currentError;

			if (functionTimer.get() > timeout)
				done = true;

			SmartDashboard.putNumber("PDTurn:NavxReading",ahrs.getAngle());
			SmartDashboard.putNumber("PDTurn:TimerReading",functionTimer.get());
			SmartDashboard.putNumber("PDTurn:LoopCount",PDTurnLoopcount); 
		}

		drive.tankDrive(0,0);
		functionTimer.start();
		functionTimer.reset();
		PDTimer.stop();
		PDTimer.reset();
	}
	
}
	
