package components;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import logger.CatzLogger;

public class CatzCANTalonSRX
{
	final String NAME;
	WPI_TalonSRX cantalon;
	CatzLogger log;
	
	public CatzCANTalonSRX(int port)
	{
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();
		cantalon = new WPI_TalonSRX(port);
	}
	public double GetSpeed()
	{
		return cantalon.get();
	}
	public void SetSpeed(double speed)
	{
		log.add(NAME, "CANTalon speed set at " + speed + ".", 5, -1);
		cantalon.set(speed);
	}
}
