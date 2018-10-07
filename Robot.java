/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2458.robot;

// These are for TalonSRX
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
	private Elevator elevator;
	private Grabber grabber;
	private Drive driver;
	private Camera cam;
	//private TalonSRX motor;
	private Joystick leftJoy, rightJoy, liftJoy;
	
	@Override
	public void robotInit() {
		leftJoy = new Joystick(2);
		rightJoy = new Joystick (1);
		liftJoy = new Joystick(0);
		elevator = new Elevator( new Spark(0),new Solenoid(0), new Solenoid(4), liftJoy, new Encoder(0, 1) );
		elevator.ElevatorInit();
		grabber = new Grabber( new Compressor(0), new Solenoid(1), new Solenoid(2), new Solenoid(3), liftJoy, new Talon(3), new Talon(4));//grab, fold, push
		grabber.GrabberInit();
		driver = new Drive (new Spark(1), new Spark(2), leftJoy, rightJoy );
		cam = new Camera();
	}


	@Override
	public void autonomousInit() {
		
		elevator.autonomousInit();
		grabber.autonomousInit();
		driver.init();
	}

	@Override
	public void autonomousPeriodic() {
		elevator.autonomousPeriodic();
		driver.autonomousPeriodic();
		grabber.autonomousPeriodic();
	}

	@Override
	public void teleopInit() {
		elevator.teleopInit();
		grabber.teleopInit();
		driver.init();
	}

	@Override
	public void teleopPeriodic() {
		elevator.moveElevator();
		grabber.Grab();
		driver.teleopDrive();
		//grabber.teleopPeriodic();
/*
		b4 = launchPad.getRawButton( 4 );
		b5 = launchPad.getRawButton( 5 );
//System.out.println( "b4="+b4+" b5="+b5+" curr="+current+" last="+last );
//System.out.println( "val="+cimEncoder.get());
		motor3.set( driveJoy.getY() );
		motor2.set( liftJoy.getY() );

		if( b5 ) {
			if( current == State.INIT ) {
				cimEncoder.reset();
				last = current;
				current = State.FORWARD;
			}
		} else if( b4 ) {
			if( last == State.INIT ) {
				cimEncoder.reset();
				last = current;
				current = State.REVERSE;
			}
		} else {
			last = current;
			current = State.INIT;
		}
		
		if( current == State.FORWARD ) {
			if( cimEncoder.get() < CIM_PPR ) {
				if( last != State.FORWARD )
					motor2.set( CIM_SPEED );
			} else {
				last = current;
				current = State.DONE;
				motor2.set( 0 );
			}
		} else if( current == State.REVERSE ) {
			if(cimEncoder.get() > -CIM_PPR ){
				if( last != State.REVERSE )
					motor2.set( -CIM_SPEED );
			} else {
				last = current;
				current = State.DONE;
				motor2.set( 0 );
			}
		} else if( current == State.INIT ) {
			if( last == State.FORWARD || last == State.REVERSE )
				motor2.set( 0 );
		}

*/
	}
}


