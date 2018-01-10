/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3863.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team3863.robot.commands.BaselineAuto;
import org.usfirst.frc.team3863.robot.commands.SwitchFarLeftAuto;
import org.usfirst.frc.team3863.robot.commands.SwitchNearLeftAuto;
import org.usfirst.frc.team3863.robot.subsystems.Drivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Drivetrain kDrivetrain = new Drivetrain();
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<Integer>();
	
	public void updateSmartDashboard() {
		DriverStation ds = DriverStation.getInstance();
		String msg = ds.getGameSpecificMessage();
		
        if(msg.charAt(0) == 'L') {
            SmartDashboard.putString("OurSwitchSide", "Left");
        } else if (msg.charAt(0) == 'R'){
        	SmartDashboard.putString("OurSwitchSide", "Right");
        } else {
        	SmartDashboard.putString("OurSwitchSide", "No Data");
        }
        
        if(msg.charAt(1) == 'L') {
            SmartDashboard.putString("EnemySwitchSide", "Left");
        } else if (msg.charAt(0) == 'R'){
        	SmartDashboard.putString("EnemySwitchSide", "Right");
        } else {
        	SmartDashboard.putString("EnemySwitchSide", "No Data");
        }
        
        if(msg.charAt(2) == 'L') {
            SmartDashboard.putString("ScaleSide", "Left");
        } else if (msg.charAt(0) == 'R'){
        	SmartDashboard.putString("ScaleSide", "Right");
        } else {
        	SmartDashboard.putString("ScaleSide", "No Data");
        }
	}
	
	/**
	 * Update the DriverStation and auton init code with the given Auton command
	 */
	public void updateAuton() {
		int ds_choice = m_chooser.getSelected();
		switch(ds_choice) { 
		 	case 1: 						//Determine Auto mode from switch positions
		 		DriverStation ds = DriverStation.getInstance();
				String msg = ds.getGameSpecificMessage();
				int loc = ds.getLocation();
				
				if(msg.charAt(0) == 'L' &&  loc == 1) {		  //Our switch is to the Left, and we are in Slot 1 (Left)
					m_autonomousCommand = new SwitchNearLeftAuto(false);
		        } else if (msg.charAt(0) == 'R' && loc == 1){ //Our switch is to the Right, and we are in Slot 1 (Left)
		        	m_autonomousCommand = new SwitchFarLeftAuto(false);
		        } else if (msg.charAt(0) == 'L' && loc == 3){ //Our switch is to the Left , and we are in Slot 3 (Right)
		        	m_autonomousCommand = new SwitchNearLeftAuto(true);
			    } else if (msg.charAt(0) == 'R' && loc == 3){ //Our switch is to the Right, and we are in Slot 3 (Right)
			    	m_autonomousCommand = new SwitchFarLeftAuto(true);
			    }
		 		 
		 	case 2: 						//Baseline Auto
		 		m_autonomousCommand = new BaselineAuto(); 
		 		break;
		 	default:
		 		m_autonomousCommand = null;
		 		break; 
	 	}
	}
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("None", 0);
		m_chooser.addObject("AutoSelect Switch", 1);
		m_chooser.addObject("Baseline", 2);
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		updateAuton();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		updateAuton();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		updateSmartDashboard();
	}
}