package org.usfirst.frc.team2458.robot;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.*;

public class Camera {
	private UsbCamera cam1;
	private CameraServer cs;
	
	public Camera() {
		//Open CV additions
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat Frame = new Mat.eye(3,3,CvType.CV_8UC1);
		
		
		
		
		cs = CameraServer.getInstance();
		cam1 = cs.startAutomaticCapture("cam0", 0);
		cam1.setResolution(320, 240);
		cam1.setFPS(15);
	}
}