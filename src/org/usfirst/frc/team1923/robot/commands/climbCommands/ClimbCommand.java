package org.usfirst.frc.team1923.robot.commands.climbCommands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbCommand extends Command {

    private double speed, lt, rt;

    public ClimbCommand() {
        requires(Robot.climbSubSys);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        this.speed = 0;
        this.lt = 0;
        this.rt = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        this.rt = Robot.oi.op.rt.getX() > 0.07 ? Robot.oi.op.rt.getX() : 0;
        this.lt = Robot.oi.op.lt.getX() > 0.07 ? -Robot.oi.op.lt.getX() : 0;
        this.speed = this.rt > 0.07 ? rt : lt;

        Robot.climbSubSys.set(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true; // TODO: Figure out overcurrent situation
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // Robot.climbSubSys.set(0); //TODO: Add limit switch to top of climber
        // to automatically stop climb
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

}
