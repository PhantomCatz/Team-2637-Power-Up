package networkingHell;

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

	public double GetHeading() {
		return heading;
	}

	public void SetHeading(double _heading) {
		this.heading = _heading;
	}

	public double GetDistance() {
		return distance;
	}

	public void SetDistance(double _distance) {
		this.distance = _distance;
	}
	

	public String toString() {
		return "{H:" + GetHeading() + ",D:" + GetDistance() + "}";
	}
	
}
