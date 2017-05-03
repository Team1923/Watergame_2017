package org.usfirst.frc.team1923.robot.commands.climb;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command {

    private double speed, leftTrigger, rightTrigger;

    /**
     * When executed, the climber motors will spin based on trigger level, right
     * trigger overrides
     */
    public ClimbCommand() {
        requires(Robot.climbSubSys);
    }

    @Override
    protected void initialize() {
        speed = 0;
        leftTrigger = 0;
        rightTrigger = 0;
    }

    @Override
    protected void execute() {
        rightTrigger = Robot.oi.op.rt.getX();
        leftTrigger = Robot.oi.op.lt.getX();

        speed = rightTrigger > 0.07 ? rightTrigger : -leftTrigger;

        Robot.climbSubSys.set(speed);
    }

    @Override
    protected boolean isFinished() {
        return true; // TODO: Figure out overcurrent situation
    }

    @Override
    protected void end() {
        // Robot.climbSubSys.set(0); //TODO: Add limit switch to top of climber
        // to automatically stop org.usfirst.frc.team1923.robot.commands.climb
    }

    @Override
    protected void interrupted() {
        end();
    }

}
