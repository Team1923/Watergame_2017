package org.usfirst.frc.team1923.robot.commands.driveCommands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftOmniCommand extends InstantCommand {

    private boolean up;

    public ShiftOmniCommand(boolean up) {
        super();
        this.up = up;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        if (this.up) {
            Robot.driveSubSys.shiftUpOmnis();
        } else {
            Robot.driveSubSys.shiftDownOmnis();
        }
    }

}
