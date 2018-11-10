package org.usfirst.frc.team2458.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {
	private DifferentialDrive drive;
	private Joystick leftJoy, rightJoy, twistJoy;
	
	private final static double DRIVE_DOWN_SCALE = 0.6;
	private final static double CURVE_FACTOR = 0.4; // -0.5 to 1 range
	private final static double DEAD_ZONE = 0.1; // 0 to 1 range
	private final static int DEAD_ZONE_BUTTON = 2;
	
	public Drive(Spark leftDrive, Spark rightDrive, Joystick leftJoy, Joystick rightJoy, Joystick twistJoy) {
		 drive = new DifferentialDrive(leftDrive, rightDrive);
		 this.leftJoy =  leftJoy;
		 this.rightJoy = rightJoy;
		 this.twistJoy = twistJoy;
	}
	
	
	public void teleopInit() {
		drive.setExpiration(0.1);
		drive.setSafetyEnabled(true);
	}
	
	public void teleopPeriodic() {
		if(twistJoy.getRawButton(12)) {
			double twist = twistJoy.getTwist();
			// allows elevator operator to override rotation
			twist = deadZone(twist, 0.2);
			drive.tankDrive(twist, -twist);
		} else {
			// drive functionality
			double throtR = rightJoy.getY();
			double throtL = leftJoy.getY();
			
			if(rightJoy.getRawButton(DEAD_ZONE_BUTTON)) {
				// optimize control for dead zones
				throtR = deadZone(throtR, DEAD_ZONE);
				throtL = deadZone(throtL, DEAD_ZONE);
			}
			if(!leftJoy.getTrigger()) {
				// drive with curve
				throtR = Math.pow(throtR, 3) * CURVE_FACTOR + throtR * (1 - CURVE_FACTOR);
				throtL = Math.pow(throtL, 3) * CURVE_FACTOR + throtL * (1 - CURVE_FACTOR);
			}
			if(rightJoy.getTrigger()) {
				// drive slow
				throtR *= DRIVE_DOWN_SCALE;
				throtL *= DRIVE_DOWN_SCALE;
			}
			double speedL = -throtR;
			double speedR = throtR;
			
			speedR += throtL;
			speedL += throtL;
			
			//Z motor polarity should really be switched
			drive.tankDrive(-throtL, -throtR);
			//drive.tankDrive(-speedL, -speedR);
		}
	}
	
	
	private String gameData;
	private String leftRight = null;
	
	boolean started;
	boolean isDone;
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		leftRight = gameData.substring(0, 1);
		
		// Temporary for testing
		/*
		leftRight = "R";
		started = false;
		isDone = false;
		*/
	}
	public void autonomousPeriodic() {
		/*
		if(started == false) {
			started = true;
			Timer time = new Timer();
			time.start();
			System.out.println("test");
			while(!time.hasPeriodPassed(3)) {//!time.hasPeriodPassed( 4.0 )
				drive.tankDrive(0.7, 0.7);
			}
			isDone = true;
		}
		if(isDone) {
			drive.tankDrive(0, 0);
		}
	}
	private double deadZone(double joyInput, double deadZone) {
		if(Math.abs(joyInput) < deadZone) {
			return 0;
		}
		if(joyInput > 0) {
			return (joyInput - 1) / (1 - deadZone) + 1;
		}
		else {
			return (joyInput + 1) / (1 - deadZone) - 1;
		}
		*/
	}
}
