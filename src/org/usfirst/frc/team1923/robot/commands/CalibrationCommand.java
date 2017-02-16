package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import com.ctre.PigeonImu.CalibrationMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrationCommand extends Command {

	private int  calibrationStatus = Integer.MIN_VALUE;
    public CalibrationCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubSys);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    calibrationStatus =	Robot.driveSubSys.pigeonImu.EnterCalibrationMode(CalibrationMode.Magnetometer12Pt);
    }
    
    public int getCalibrationStatus() {
		return calibrationStatus;
	}

	public void calibration360(){
    	Robot.driveSubSys.pigeonImu.EnterCalibrationMode(CalibrationMode.Magnetometer360);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
