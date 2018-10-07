package org.usfirst.frc.team2458.robot;

import edu.wpi.first.wpilibj.*;

//Z I'm not even gonna go near this...
public class TestAuto {
	private Joystick leftDrive, rightDrive;
	private boolean isDone;
	private String gameData;
	 
	 
	private String leftRight = null;
	public void autonomousPeriodic(){
		if (isDone == false){
			Timer time = new Timer();
			time.start(); 
			while (!time.hasPeriodPassed( 4.0 )) {
				
			}
			isDone = true;
		}
		else {
		} 
		
		//Check if autonomous is done
		if (isDone == false){
			//Check if data is received from field
			if (gameData.length() > 0){
				//Check which side of the field we're on based on the joystick z-axis
				if(leftRight.equals("L") && rightDrive.getZ() < -0.6) {
					System.out.println("hello left");
					Timer timeL = new Timer();
					timeL.start();
					
					while (!timeL.hasPeriodPassed(4.5)) { // driving parallel to the switch
						
					}
					while (!timeL.hasPeriodPassed(0.25)) { //turning 90 degrees
						
					}
					while (!timeL.hasPeriodPassed(1.0)) {
						
					}
				}
				if(leftRight.equals("R") && rightDrive.getZ() > 0.6) {
					System.out.println("hello Right");
					Timer timeR = new Timer();
					timeR.start();
					
					while (!timeR.hasPeriodPassed(4.5)) { // driving parallel to the switch
						
					}
					while (!timeR.hasPeriodPassed(0.25)) { //turning 90 degrees
						
					}
					while (!timeR.hasPeriodPassed(1.0)) {
						
					}
				}
				else {
					Timer time = new Timer();
			 		time.start(); 
			 
					while (!time.hasPeriodPassed( 4.0 )) {
					
					}
					isDone = true;
				}
			}
		}
		else {
			
		}
	}
}
