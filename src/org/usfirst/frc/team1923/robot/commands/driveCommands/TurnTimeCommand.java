package org.usfirst.frc.team1923.robot.commands.driveCommands;

import org.usfirst.frc.team1923.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class TurnTimeCommand extends Command {

    private double power;

    public TurnTimeCommand(double power, double timeOut) {
        requires(Robot.driveSubSys);
        this.power = power;
        setTimeout(timeOut);
    }

    @Override
    protected void initialize() {
        Robot.driveSubSys.drive(this.power, -this.power, TalonControlMode.PercentVbus);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected void end() {
        Robot.driveSubSys.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
