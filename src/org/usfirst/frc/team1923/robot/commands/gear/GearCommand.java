package org.usfirst.frc.team1923.robot.commands.gear;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GearCommand extends InstantCommand {

    private boolean open;
    private boolean toggle;

    /**
     * This command toggles the current gear placing position
     */
    public GearCommand() {
        super();
        requires(Robot.gearSubSys);
        toggle = true;
    }

    /**
     * This sets the mechanism to a specific position
     *
     * @param open
     *            true if want to eject
     */
    public GearCommand(boolean open) {
        super();
        requires(Robot.gearSubSys);
        this.open = open;
    }

    @Override
    protected void initialize() {
        if (toggle) {
            Robot.gearSubSys.gearShift();
        } else if (open) {
            Robot.gearSubSys.gearOpen();
        } else {
            Robot.gearSubSys.gearClose();
        }
    }

}
