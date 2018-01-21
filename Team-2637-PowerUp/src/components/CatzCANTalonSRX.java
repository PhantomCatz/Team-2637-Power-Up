package components;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import logger.CatzLogger;

public class CatzCANTalonSRX extends WPI_TalonSRX
{
	private final String NAME;
	private WPI_TalonSRX cantalon;
	private CatzLogger log;
	
	public CatzCANTalonSRX(int port)
	{
		super(port);
		log = CatzLogger.getInstance();
		NAME = this.getClass().getSimpleName();

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
