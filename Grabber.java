/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2458.robot;

import edu.wpi.first.wpilibj.*;

public class Grabber {
	//private final int FOLD_BUTTON = 5;
	//private final int PUSH_BUTTON = 2;
	private final int FOLD_UP_BUTTON = 3;
	private final int FOLD_DOWN_BUTTON = 5;
	private final int IN_BUTTON = 4;
	private final int OUT_BUTTON = 6;
	private Compressor compressor;
	private Solenoid grabPiston, foldPiston, pushPiston;
	private Joystick grabJoy;
	private Talon intakeL, intakeR;
	private boolean isDown;
	
	public Grabber( Compressor c, Solenoid grab, Solenoid fold, Solenoid push, Joystick grabj, Talon il, Talon ir ) {
		compressor = c;
		grabPiston = grab;
		foldPiston = fold;
		pushPiston = push;
		grabJoy = grabj;
		intakeL = il;
		intakeR = ir;
	}
	
	public void GrabberInit() {
		compressor.setClosedLoopControl( true );
		compressor.stop();
		isDown = false;
	}

	public void autonomousInit() {
		compressor.stop();
	}

	public void autonomousPeriodic() {
		foldPiston.set( true );
		grabPiston.set( true );
		
	}

	public void teleopInit() {
		compressor.start();
		grabPiston.set( false );
		foldPiston.set( false );
		pushPiston.set( false );
	}

	/*public void teleopPeriodic() {
		grabPiston.set( grabJoy.getTrigger() );
	}
	*/
	public void Grab(){
		grabPiston.set( grabJoy.getTrigger() );
		//foldPiston.set(  grabJoy.getRawButton( FOLD_BUTTON));
		//pushPiston.set( grabJoy.getRawButton( PUSH_BUTTON));
		
		 if (grabJoy.getRawButton( FOLD_UP_BUTTON) && isDown){
			foldPiston.set(false);
			isDown = false;
			}
		else if (grabJoy.getRawButton( FOLD_DOWN_BUTTON) && !isDown){
			foldPiston.set(true);
			isDown = true;
		}
		 // Wheel Intake
		 /*
		  if (grabJoy.getRawButton( IN_BUTTON)){
		  	intakeL.set(1.0);
		  	intakeR.set(-1.0);
		  }
		  else if (grabJoy.getRawButton( OUT_BUTTON)){
		  	intakeL.set(-1.0);
		  	intakeR.set(1.0);
		  }
		  */ 
		  
		 
		/*
		if (grabJoy.getRawButton( FOLD_BUTTON)){
			if (!isDown){
				foldPiston.set( true );
				isDown = true;
			}
			else if(isDown){
				foldPiston.set( false );
				isDown = false;
			}
		}
		*/
	}
		
		
}
	





