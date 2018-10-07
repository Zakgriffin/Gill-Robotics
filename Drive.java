package org.usfirst.frc.team2458.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {
	 private Joystick leftDrive, rightDrive;
	 //private Joystick driveJoy;
	 private DifferentialDrive drive;
	 private boolean isDone;
	 private String gameData;
	 private Grabber grabber;
	 private Spark elevatorMotor;
	 
	 private final static double DRIVE_SCALE = -0.6;
	 private final static double DRIVE_UP_SCALE = -1;
	 
	private String leftRight = null;
	//tring left = "L";
	 
	 public Drive(Spark left, Spark right,Joystick l, Joystick r){
		 drive = new DifferentialDrive(left, right); 
		 leftDrive = l;
		 rightDrive = r;
	 }
	 public void init() {
			drive.setExpiration( 0.1 );
			drive.setSafetyEnabled( true );
		}
	 public void teleopDrive(){
		 //drive.arcadeDrive( driveJoy.getX(), zRotation );
		 
		 if (leftDrive.getTrigger() || rightDrive.getTrigger()){
			 drive.tankDrive(leftDrive.getY() * DRIVE_SCALE, rightDrive.getY() * DRIVE_SCALE); 
		 }
		 else{
		drive.tankDrive(leftDrive.getY() * DRIVE_UP_SCALE, rightDrive.getY() * DRIVE_UP_SCALE);
		 }// Remember to add for autonomous
		 
		
	 }
	 public void autonomousInit(){
		 isDone = false;
		 
		  gameData = DriverStation.getInstance().getGameSpecificMessage();
		  leftRight = gameData.substring(0, 1);
		 
	 }
	 public void autonomousPeriodic(){
		 if (isDone == false){
			 Timer time = new Timer();
			 time.start(); 
			 
			while (!time.hasPeriodPassed( 4.0 )) {
				drive.tankDrive(0.6, 0.6);
			}
			isDone = true;
		 }
		 else {
			drive.tankDrive( 0, 0 ); 
		 } 
		
		//Check if autonomous is done
		if (isDone == false){
			//Check if data is received from field
			if (gameData.length() > 0){
				//Check which side of the field we're on based on the joystick z-axis
				//if (rightDrive.getZ() ==
				if (leftRight.equals("L") && rightDrive.getZ() < -.6) {
					System.out.println("hello left");
					Timer timeL = new Timer();
					timeL.start();
					
					while (!timeL.hasPeriodPassed( 4.5 )) { // driving parallel to the switch
						drive.tankDrive(0.6, 0.6);
			}
					while (!timeL.hasPeriodPassed( .25 )) { //turning 90 degrees
						drive.tankDrive(0.4, -0.4);
			}
					while (!timeL.hasPeriodPassed( 1.0 )) {
						drive.tankDrive(.25, .25);
			}
					
				}
				if (leftRight.equals("R") && rightDrive.getZ() > .6) {
					System.out.println("hello Right");
					Timer timeR = new Timer();
					timeR.start();
					
					while (!timeR.hasPeriodPassed( 4.5 )) { // driving parallel to the switch
						drive.tankDrive(0.6, 0.6);
			}
					while (!timeR.hasPeriodPassed( .25 )) { //turning 90 degrees
						drive.tankDrive(-0.4, 0.4);
			}
					while (!timeR.hasPeriodPassed( 1.0 )) {
						drive.tankDrive(.25, .25);
			}
				}
				else {
					Timer time = new Timer();
			 		time.start(); 
			 
					while (!time.hasPeriodPassed( 4.0 )) {
					drive.tankDrive(0.6, 0.6);
					}
					isDone = true;
				}
				
			}
			
		}
		else {
			drive.tankDrive(0, 0);
		}
		
	 }
	 
}
