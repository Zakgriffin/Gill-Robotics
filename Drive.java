//package org.usfirst.frc.team2458.robot;   <-- UNCOMMENT Z

import edu.wpi.first.wpilibj.*;

//import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {
	private DifferentialDrive drive;
	private Joystick leftJoy, rightJoy, twistJoy;
	
	//Z motor polarity should really be switched
	private final static double DRIVE_DOWN_SCALE = -0.6;
	private final static double CURVE_FACTOR = 0.8; // 0 to 1 range
	private final static double CF3 = Math.pow(CURVE_FACTOR, 3);
	
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
		if(Math.abs(twist) > 0.25) {
			// allows elevator operator to override rotation
			drive.tankDrive(twist, -twist);
		}
		else {
			// drive functionality
			double speedR = CF3 * rightJoy.getY() + CURVE_FACTOR * (1 - rightJoy.getY());
			double speedL = CF3 * leftJoy.getY() + CURVE_FACTOR * (1 - leftJoy.getY());
			
			if(leftJoy.getTrigger()) {
				// drive linear
				speedR = rightJoy.getY();
				speedL = leftJoy.getY();
			}
			if(rightJoy.getTrigger()) {
				// drive slow
				speedR *= DRIVE_DOWN_SCALE;
				speedL *= DRIVE_DOWN_SCALE;
			}
			drive.tankDrive(speedL, speedR);
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
}
