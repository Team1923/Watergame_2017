	package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives Straight for an inputted time at 0.25 speed
 * @param time the number of seconds the robot drives
 */
public class DriveTimeCommand extends Command {

	double left = 0, right = 0;
    public DriveTimeCommand( double leftSpeed, double rightSpeed ,double time ) {
        requires(Robot.driveSubSystem);
        left = leftSpeed;
        right = rightSpeed;
        setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubSystem.drive(left, right, CANTalon.TalonControlMode.PercentVbus);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubSystem.drive(0, 0, CANTalon.TalonControlMode.PercentVbus);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}