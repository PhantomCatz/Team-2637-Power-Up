package org.usfirst.frc.team2637.robot;

public abstract class CatzEncoder 
{
	public double wheelRadiusInches;
	public int pulsesPerRevolution;

	public abstract double getDistance();

	public abstract void resetDistance();

	public abstract void setDistancePerPulse();

	public abstract int getPulses();
}
