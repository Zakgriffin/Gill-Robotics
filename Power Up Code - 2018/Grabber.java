package org.usfirst.frc.team2458.robot;

import edu.wpi.first.wpilibj.*;

public class Grabber {
	private Compressor compressor;
	private Solenoid grabPiston, foldPiston;
	private Joystick grabJoy;
	private boolean isDown = false;
	
	private final static int FOLD_UP_BUTTON = 3;
	private final static int FOLD_DOWN_BUTTON = 5;
	
	public Grabber(Compressor compressor, Solenoid grabPiston, Solenoid foldPiston, Joystick grabJoy) {
		this.compressor = compressor;
		this.grabPiston = grabPiston;
		this.foldPiston = foldPiston;
		this.grabJoy = grabJoy;
		
		this.compressor.setClosedLoopControl(true);
		this.compressor.stop();
	}

	
	public void teleopInit() {
		compressor.start();
		grabPiston.set(false);
		foldPiston.set(false);
	}

	public void teleopPeriodic() {
		// close grabber if trigger pressed
		grabPiston.set(grabJoy.getTrigger());
		
		if(grabJoy.getRawButton(FOLD_UP_BUTTON) && isDown){
			// fold up grabber
			foldPiston.set(false);
			isDown = false;
		}
		else if(grabJoy.getRawButton(FOLD_DOWN_BUTTON) && !isDown) {
			// fold down grabber
			foldPiston.set(true);
			isDown = true;
		}
	}
	
	
	public void autonomousInit() {
		compressor.stop();
	}

	public void autonomousPeriodic() {
		foldPiston.set(true);
		grabPiston.set(true);
	}
}
	





