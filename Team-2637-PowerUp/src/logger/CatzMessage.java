package org.usfirst.frc.team2637.robot;

public class CatzMessage {
	
	private String operation; //TODO maybe find a better name for these objects
	private String object;
	private double time;
	private int logLevel;
	private final String  COMMA = ",";
	
	
	public CatzMessage(String op, String ob, double t, int log) {
		operation = op;
		object = ob;
		t = time;
		logLevel = log;
	}
	
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public double getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public double getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}
	
	
	// order to be printed
	
	@Override
	public String toString() {
		return time + COMMA + object + COMMA + operation + COMMA + logLevel ;
	}
}

