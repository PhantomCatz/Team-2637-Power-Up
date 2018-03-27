package server;

import java.io.Serializable;

public class VisionObject implements Serializable{
	
	public VisionObject(double _heading, double _distance) 
	{
		this.heading = _heading;
		this.distance = _distance;
	}

	private static final long serialVersionUID = 1L;
	private double heading;
	private double distance;

	public double getHeading() {
		return heading;
	}

	public void getHeading(double _heading) {
		this.heading = _heading;
	}

	public double getDistance() {
		return distance;
	}

	public void getDistance(double _distance) {
		this.distance = _distance;
	}
	

	public String toString() {
		return "{H:" + getHeading() + ",D:" + getDistance() + "}";
	}
	
}
