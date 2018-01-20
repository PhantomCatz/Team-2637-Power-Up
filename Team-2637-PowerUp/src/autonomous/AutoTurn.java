package autonomous;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class AutoTurn
{
	AHRS ahrs = new AHRS(SerialPort.Port.kMXP);
	Timer functionTimer = new Timer();
	void PDTurn(double turnDegrees, int timeout)
	{
		
		ahrs.reset();
		//hardware->navx->Reset();
		//Wait(.1);
		wait(.1);

		boolean done = false;
		int PDTurnLoopcount=0;
		//double TurnToDegrees = turnDegrees+hardware->navx->GetAngle();
		double TurnToDegrees = turnDegrees+ahrs.getAngle();
		double turnThreshold = .1;
		//double kP = 0.039, kD = 0.0147;
		double currentError,previousError = 0;
		double deltaError,derivative,deltaT;
		double power;

		/*functionTimer->Reset();
		functionTimer->Start();
		PDTimer->Reset();*/
		
		functionTimer.reset();
		functionTimer.start();
		//while((fabs(hardware->navx->GetAngle()) < fabs(TurnToDegrees)-turnThreshold ||
			//	fabs(hardware->navx->GetAngle()) > fabs(TurnToDegrees)+turnThreshold) && done!=true)
		while(Math.abs(ahrs.getAngle()) < Math.abs(TurnToDegrees)-turnThreshold || 
				Math.abs(ahrs.getAngle()) > Math.abs(TurnToDegrees)+turnThreshold && done!= true)
		{
			// make data array for deltaT,currentError; loop of about 100 + counter for how many loops
			PDTimer->Stop();
			deltaT = PDTimer->Get();
			PDTimer->Reset();
			PDTimer->Start();



			currentError = TurnToDegrees-hardware->navx->GetAngle();
			deltaError = currentError-previousError;
			derivative = deltaError/deltaT;

			power = .6*((TurnkP*currentError)+(TurnkD*derivative));
			driver->AutoDrive(power,-power);

			previousError = currentError;

			if (functionTimer->Get() > timeout)
				done = true;

			SmartDashboard::PutNumber("PDTurn:NavxReading",hardware->navx->GetAngle());
			SmartDashboard::PutNumber("PDTurn:TimerReading",functionTimer->Get());
			SmartDashboard::PutNumber("PDTurn:LoopCount",PDTurnLoopcount);
		}

		driver->AutoDrive(0,0);
		functionTimer->Start();
		functionTimer->Reset();
		PDTimer->Stop();
		PDTimer->Reset();
	}
}
