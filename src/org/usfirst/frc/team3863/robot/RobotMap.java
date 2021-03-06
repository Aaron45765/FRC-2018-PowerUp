/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3863.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Drivetrain CAN IDs
	public static int TALON_DRIVE_LEFTA_ID = 13;
	public static int TALON_DRIVE_LEFTB_ID = 12;
	public static int TALON_DRIVE_RIGHTA_ID = 2;
	public static int TALON_DRIVE_RIGHTB_ID = 3;
	
	//Elevator CAN IDs
	public static int ELEVATOR_DRIVE_TALON_ID = 14;
	
	//Intake CAN IDs
	public static int LEFT_INTAKE_TALON_ID = 0;
	public static int RIGHT_INTAKE_TALON_ID = 1;
	public static int INTAKE_PIVOT_TALON_ID = 15;
	
	//PCM and PDP CAN IDs (defaults to 0, IDs only need to be unique across the same type)
	public static int PDP_ID = 0;
	public static int PCM_ID = 0;
	
	//PCM Solenoid output IDs
	public static int PCM_TRANSMISSION_LOW = 0;
	public static int PCM_TRANSMISSION_HIGH = 1;
	
	public static int PCM_INTAKE_RETRACT = 2;
	public static int PCM_INTAKE_EXTEND = 3;
}