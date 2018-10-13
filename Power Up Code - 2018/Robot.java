/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2458.robot;

import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
	private Drive drive;
	private Elevator elevator;
	private Grabber grabber;
	private Camera camera; //? unused var, might not need name (new Camera();)
	private Joystick leftJoy, rightJoy, liftJoy;
	
	@Override
	public void robotInit() {
		liftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
		leftJoy = new Joystick(2);

		drive = new Drive(new Spark(1), new Spark(2), leftJoy, rightJoy, liftJoy);
		//            leftDrive ^  rightDrive ^
		elevator = new Elevator(new Spark(0), new Encoder(0, 1), liftJoy);
		//             elevatorMotor ^    cimEncoder ^
		grabber = new Grabber(new Compressor(0), new Solenoid(1), new Solenoid(2), liftJoy);
		//                                   grabPiston ^     foldPiston ^
		
		camera = new Camera();
	}

	
	@Override
	public void teleopInit() {
		drive.teleopInit();
		elevator.teleopInit();
		grabber.teleopInit();
	}

	@Override
	public void teleopPeriodic() {
		drive.teleopPeriodic();
		elevator.teleopPeriodic();
		grabber.teleopPeriodic();
	}
	
	@Override
	public void autonomousInit() {
		//drive.autonomousInit();
		//elevator.autonomousInit();
		//grabber.autonomousInit();
	}

	@Override
	public void autonomousPeriodic() {
		//drive.autonomousPeriodic();
		//elevator.autonomousPeriodic();
		//grabber.autonomousPeriodic();
	}
}


