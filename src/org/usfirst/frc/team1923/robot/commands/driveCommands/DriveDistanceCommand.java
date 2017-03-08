package org.usfirst.frc.team1923.robot.commands.driveCommands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.subsystems.DrivetrainSubsystem;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistanceCommand extends Command {

    private double left, right;
    private double leftTarget, rightTarget;

    private final int TOLERANCE = Robot.driveSubSys.ALLOWABLE_ERROR; // Ticks

    /**
     * This command drives the robot a set distance with the left and right
     * encoder targets
     * 
     * @param left
     *            Distance to travel by left wheel in inches.
     * @param right
     *            Distance to travel by right wheel in inches.
     */
    public DriveDistanceCommand(double left, double right) {
        requires(Robot.driveSubSys);
        this.left = left;
        this.right = right;

        double maxDistance = Math.max(Math.abs(left), Math.abs(right));

        this.setTimeout(maxDistance * 0.05 + 2);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.driveSubSys.resetPosition();

        this.leftTarget = DrivetrainSubsystem.distanceToRotation(left);
        this.rightTarget = DrivetrainSubsystem.distanceToRotation(right);

        SmartDashboard.putNumber("Left Target", this.leftTarget);
        SmartDashboard.putNumber("Right target", this.rightTarget);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveSubSys.drive(this.leftTarget, this.rightTarget, TalonControlMode.Position);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        System.out.println(
                Robot.driveSubSys.getLeftError() + " " + Robot.driveSubSys.getRightError());
        return isTimedOut() || ((Math.abs(Robot.driveSubSys.getLeftError()) < TOLERANCE)
                && (Math.abs(Robot.driveSubSys.getRightError()) < TOLERANCE));

        // (Robot.driveSubSys.getLeftTarget() != 0 &&
        // Robot.driveSubSys.getRightTarget() != 0)
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        if (isTimedOut()) {
            System.out.println("TIMED OUT");
        }
        System.out.println("END END END");
        Robot.driveSubSys.resetPosition();
        // Robot.driveSubSys.disable();
        Robot.driveSubSys.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        System.out.println("INTERRUPT");
        end();
    }

}
