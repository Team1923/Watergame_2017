package org.usfirst.frc.team1923.robot;

import org.usfirst.frc.team1923.robot.utils.DriveProfile.ProfileCurve;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {

	// Driver Stuff:
	public static final ProfileCurve DRIVER_PROFILE = ProfileCurve.LINEAR;
	public static final ProfileCurve CHINMAY_PROFILE = ProfileCurve.LINEAR;
	public static final ProfileCurve ANISH_PROFILE = ProfileCurve.LINEAR;
	public static final ProfileCurve SURAJ_PROFILE = ProfileCurve.LINEAR;
	
	// OI port numbers
	public static final int DRIVER_CONTROLLER_PORT = 0;
	public static final int OP_CONTROLLER_PORT = 1;

	// Drive motor numbers
	public static final int[] LEFT_DRIVE_PORTS = { 8, 7, 6 }; // Master Talon
																// first
	public static final int[] RIGHT_DRIVE_PORTS = { 1, 2, 3 };

	// Talons that have the SRX Mag Enc installed (masters);
	public static final int LEFT_ENCODER_PORT = 8;
	public static final int RIGHT_ENCODER_PORT = 1;

	// Climb motor numbers
	public static final int LEFT_CLIMB_PORT = 4;
	public static final int RIGHT_CLIMB_PORT = 5;

	// Climber slider port numbers
	public static final int SLIDE_FORWARD_PORT = 6;
	public static final int SLIDE_BACKWARD_PORT = 7;

	// Gear box shifting pistons ports on the PCM
	public static final int SHIFT_FORWARD_PORT = 2;
	public static final int SHIFT_BACKWARD_PORT = 3;

	// Mechanism actuator piston ports
	public static final int MECH_FORWARD_PORT = 4;
	public static final int MECH_BACKWARD_PORT = 5;

	// Drop down omniwheel piston ports
	public static final int OMNI_FORWARD_PORT = 0;
	public static final int OMNI_BACKWARD_PORT = 1;

	// PCM Module number
	public static final int PCM_MODULE_NUM = 12;

	/*
	 * 2 controllers 6 drive talons 2 climb talons 1 shift piston 1 drop piston
	 * 1 gear piston TO BE DETERMINED: Ultrasonic? Button? Camera?
	 */

	// Ultrasonic Sensorb DIO ports
	public static final int FRONT_SONAR_PING_PORT = 8;
	public static final int FRONT_SONAR_ECHO_PORT = 9; // UNITS declared in
														// constructor in drive
														// subsystem
	
	//TODO: Figure out the port for PigeonIMU
	private static final int IMU_PORT = 0;
	//Vision Subsystem Constants
	
	public static final String CAMERA_IP="10.19.20.109";
	
	public static final String NEWTORK_TABLE_ADDRESS="GRIP/table";
	public static final int IMG_WIDTH=320;
	public static final int IMG_HEIGHT=240;
	public static final double TURN_CONSTANT=1000;
	public static final double MAX_WIDTH=100;
	public static final double MAX_DIST = 13;

}
