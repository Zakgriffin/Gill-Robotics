package org.usfirst.frc.team2458.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.I2C.Port;

public class Arduino {
	private I2C i2c;
	private byte[] toSend = new byte[1];
	private byte[] toReceive = new byte[1];

	public void ArduinoInit() {
		i2c = new I2C(Port.kMXP, 0xa0);	// 84 on RIOduino
	}

	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		toSend[0] = 74; // off
		toSend[0] = 72; // on
		boolean ret = i2c.transaction(toSend, 1, toReceive, 1);
	}

	public void teleopInit() {
	}

	public void teleopPeriodic() {
	}
}


