package org.usfirst.frc.team2458.robot;

import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.*;

public class Camera {
	private UsbCamera cam1;
	private CameraServer cs;
	
	public Camera() {
		cs = CameraServer.getInstance();
		cam1 = cs.startAutomaticCapture("cam0", 0);
		cam1.setResolution(320, 240);
		cam1.setFPS(15);
	}
}