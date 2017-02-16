package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftCommand extends Command {

	boolean isShifted;
    public ShiftCommand() {
    	requires(Robot.gearSubSys);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearSubSys.slideShift();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
