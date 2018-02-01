package components;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import constants.CatzConstants;
import logger.CatzLogger;
public class CatzCANTalonSRX extends WPI_TalonSRX
{
	//private final String NAME;
	private WPI_TalonSRX cantalon;
	//private CatzLogger logger;
	//private CatzTimerMap timer;
	public CatzCANTalonSRX(int port)
	{
		super(port);
		//timer = CatzTimerMap.getInstance();
		//logger = CatzLogger.getInstance();
		//NAME = this.getClass().getSimpleName();
		cantalon = new WPI_TalonSRX(port);
	}
	public double getSpeed()
	{
		return cantalon.getMotorOutputPercent();
	}
	public void setSpeed(double speed)
	{
		//logger.add(NAME, "CANTalon speed set at " + speed + ".", CatzConstants.LEVEL5, timer.get(CatzConstants.LOGGER_TIMER_INDEX));
		cantalon.set(speed);
	}
}
