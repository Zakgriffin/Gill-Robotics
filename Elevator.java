/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2458.robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.*;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Elevator {
	private final double CIM_SPEED = 0.20;
	private final int CIM_PPR = 20;		// cimcoder pulses per rotation
	private final double DEAD_ZONE = 0.25;
	private final double HOLD_POWER = 0; //CHANGE
	private final double MAX_COUNT = 22800;
	private final double BRAKE_SPEED = 0.25;
	private final int ELEVATOR_LIMIT = 5900;
	//private enum State { INIT, FORWARD, REVERSE, DONE };
	private double targetSpeed;
	private double targetCount;
	private double holdCount;
	private boolean brakeEngaged;
	//private boolean atLowerLimit;
	//private State current, last;
	private Spark elevatorMotor;
	private Encoder cimEncoder;
	private Solenoid brakeOpen, brakeClose;
	private Joystick liftJoy;
	//private DigitalInput lowerLimit;
	
	private boolean b4, b5;
	//private Timer timer;

	public Elevator( Spark motor, Solenoid o, Solenoid c, Joystick joy, Encoder e ){//, DigitalInput l ) {
		elevatorMotor = motor;
		brakeOpen = o;
		brakeClose = c;
		liftJoy = joy;
		cimEncoder = e;		// 20 PPR
		//lowerLimit = l;
	}
	
	public void ElevatorInit(  ) {
		//timer = new Timer();
		//atLowerLimit = true; Might need to change depending on location of switch $$
	}

	public void autonomousInit() {
		//timer.reset();
		//timer.start();
	}

	public void autonomousPeriodic() {
		Timer timed = new Timer();
		timed.start();
		while (!timed.hasPeriodPassed(1)) { // bring up the elevator
						elevatorMotor.set( -0.25 );
		}
	}
	

	public void teleopInit() {
		cimEncoder.reset();
		//current = State.INIT;
		//last = current;
		elevatorMotor.set( 0 );
		
	}
	
	/*public void teleopPeriodic() {
		elevatorMotor.set( liftJoy.getY() );
	}
	*/

	public void moveElevator() {
		readJoystick();
		int encoderValue = cimEncoder.get();
System.out.println( "encoder="+encoderValue );
/*		
		if (cimEncoder.get() >= ELEVATOR_LIMIT || cimEncoder.get() <= 0){
			elevatorMotor.set( 0 );

			if (targetSpeed != 0){
				if (!brakeEngaged){
					brake.set( true );
					brakeEngaged = true;
				}
				else{
					brake.set( false );
					brakeEngaged = false;
				}
			}

		}

		if (targetSpeed == 0){
			holdCount = targetCount;
			if (cimEncoder.get() != holdCount){
				elevatorMotor.set( HOLD_POWER );
			}
			else {
				elevatorMotor.set( 0 );
			}
		
		}
		else {
			elevatorMotor.set( targetSpeed );
			targetCount = cimEncoder.get();
		}

		
		// Using limit switches
		/*
		lowerLimit.setUpSourceEdge(false, true)
		lowerLimit.enableInterrupts();
		lowerLimit.requestInterrupts(new InterruptHandlerFunction<Object>() {
	 		@Override
	 	public void interruptFired( int mask, Object param ) {
	 			atLowerLimit = true;
	 		}
	 	})
		 * */

/* original elevator code
	if ( encoderValue <= 20 && targetSpeed < 0){
		elevatorMotor.set( 0 );
	}
	else{
		if (targetSpeed == 0){
			//brakeOpen.set( true );
			//brakeClose.set( false );
			elevatorMotor.set( 0 );
		}
		
		else {
			elevatorMotor.set( -targetSpeed );
			//brakeOpen.set( false );
			//brakeClose.set( true );
			}
		}
*/
	// elevator using encoder limits
//elevatorMotor.set( targetSpeed );
	if( targetSpeed == 0 ) {
		elevatorMotor.set( 0 );
	} else if( targetSpeed > 0) {
			// going up
			if( encoderValue < ELEVATOR_LIMIT  ) {
				elevatorMotor.set( -targetSpeed );
			} else {
				elevatorMotor.set( 0 );
			}
		} else {
			//going down
			if( encoderValue > 0 || liftJoy.getRawButton( 7 ) == true) {
				elevatorMotor.set( -targetSpeed );
			} else {
				elevatorMotor.set( 0 );
			}
		}
	
	}
	
	
	
	
	
	
	private void readJoystick() {
		targetSpeed = liftJoy.getY();
		//System.out.println("RAW: " + targetSpeed);
		
		 if (targetSpeed > 0 && (targetSpeed - DEAD_ZONE) <= 0) {
			targetSpeed = 0;
			} 
		else if (targetSpeed < 0 && (targetSpeed + DEAD_ZONE) >= 0) {
			targetSpeed = 0;
		}
		 
		 
		/*
		if (targetSpeed > 0) {
			// shift down through the dead zone...
			targetSpeed = targetSpeed - DEAD_ZONE;
			if (targetSpeed < 0) {
				// set it to zero in the dead zone...
				targetSpeed = 0;
			}
		} else if (targetSpeed < 0) {
			// shift up through the dead zone...
			targetSpeed = targetSpeed + DEAD_ZONE;
			if (targetSpeed > 0) {
				// set it to zero in the dead zone...
				targetSpeed = 0;
			}
		}
		*/
	}
}


