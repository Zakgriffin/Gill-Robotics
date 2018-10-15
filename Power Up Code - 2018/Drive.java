package org.usfirst.frc.team2458.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {
	private DifferentialDrive drive;
	private Joystick leftJoy, rightJoy, twistJoy;
	
	private final static double DRIVE_DOWN_SCALE = 0.6;
	private final static double CURVE_FACTOR = 0.7; // -0.5 to 1 range
	private final static double DEAD_ZONE = 0.2; // 0 to 1 range
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
		double twist = twistJoy.getTwist();
		if(Math.abs(twist) > DEAD_ZONE) {
			// allows elevator operator to override rotation
			twist = deadZone(twist, DEAD_ZONE);
			drive.tankDrive(twist, -twist);
		} else {
			// drive functionality
			double throtR = rightJoy.getY();
			double throtL = leftJoy.getY();
			double speedR;
			double speedL;
			
			if(rightJoy.getRawButton(DEAD_ZONE_BUTTON)) {
				// optimize control for dead zones
				throtR = deadZone(throtR, DEAD_ZONE);
				throtL = deadZone(throtL, DEAD_ZONE);
			}
			if(leftJoy.getTrigger()) {
				// drive linear
				speedR = throtR;
				speedL = throtL;
			} else {
				// drive with curve
				speedR = Math.pow(throtR, 3) * CURVE_FACTOR + throtR * (1 - CURVE_FACTOR);
				speedL = Math.pow(throtL, 3) * CURVE_FACTOR + throtL * (1 - CURVE_FACTOR);
			}
			if(rightJoy.getTrigger()) {
				// drive slow
				speedR *= DRIVE_DOWN_SCALE;
				speedL *= DRIVE_DOWN_SCALE;
			}
			//Z motor polarity should really be switched
			drive.tankDrive(-speedL, -speedR);
		}
	}
	
	
	//private String gameData;
	//private String leftRight = null;
	
	public void autonomousInit() {
		//gameData = DriverStation.getInstance().getGameSpecificMessage();
		//leftRight = gameData.substring(0, 1);
	}
	
	public void autonomousPeriodic() {
		
	}
	private double deadZone(double joyInput, double deadZone) {
		if(joyInput > 0) {
			return (joyInput - 1) / (1 - deadZone) + 1;
		}
		else if(joyInput < 0) {
			return (joyInput + 1) / (1 - deadZone) - 1;
		}
		else return 0;
	}
}
