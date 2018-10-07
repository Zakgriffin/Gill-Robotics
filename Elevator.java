//package org.usfirst.frc.team2458.robot;   <-- UNCOMMENT Z

import edu.wpi.first.wpilibj.*;

public class Elevator {
	private Spark elevatorMotor;
	private Encoder cimEncoder;
	private Joystick liftJoy;
	
	private final static int ELEVATOR_LIMIT = 5900;
	private final static double DEAD_ZONE = 0.25;
	private final static int LIMIT_OVERRIDE_BUTTON = 7;
	
	public Elevator(Spark elevatorMotor, Encoder cimEncoder, Joystick liftJoy) {
		this.elevatorMotor = elevatorMotor;
		this.cimEncoder = cimEncoder; // 20 PPR
		this.liftJoy = liftJoy;
	}
	
	
	public void teleopInit() {
		cimEncoder.reset();
		elevatorMotor.set(0);
	}

	public void teleopPeriodic() {
		double targetSpeed = readJoystick();
		int encoderValue = cimEncoder.get();
		System.out.println("Encoder = " + encoderValue);
		
		if(encoderValue > 0 && encoderValue < ELEVATOR_LIMIT) {
			// if elevator within range, move elevator
			elevatorMotor.set(targetSpeed);
		}
		else if(liftJoy.getRawButton(LIMIT_OVERRIDE_BUTTON)) {
			// override limit and re-calibrate encoder
			elevatorMotor.set(targetSpeed);
			//cimEncoder.reset();
		}
	}
	
	
	public void autonomousInit() {
		
	}
	
	public void autonomousPeriodic() {
		/*
		Timer time = new Timer();
		time.start();
		while(!time.hasPeriodPassed(1)) {
			elevatorMotor.set(-0.25);
		}
		*/
	}
	
	
	private double readJoystick() {
		double speed = liftJoy.getY();
		if(Math.abs(speed) < DEAD_ZONE) speed = 0;
		return speed;
	}
}


