package org.usfirst.frc.team1923.robot.commands.driveCommands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.subsystems.DrivetrainSubsystem;

/**
 *
 */
public class TurnAngleCommand extends DriveDistanceCommand {

    public TurnAngleCommand(double angle) {
        super(DrivetrainSubsystem.angleToDistance(angle),
                -DrivetrainSubsystem.angleToDistance(angle));
    }

    @Override
    protected void initialize() {
        //Robot.driveSubSys.setP(0.08);
        super.initialize();
    }

    @Override
    protected void end() {
        //Robot.driveSubSys.resetP();
        super.end();
    }

}
