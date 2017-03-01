package org.usfirst.frc.team1923.robot.commands.driveCommands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *This command takes in a parameter distance and drives straight for the specified distance,
 *and takes a parameter of the power that the robot will drive at using the set method
 */
public class DriveStraightDistanceCommand extends Command {
	private double leftDistance, rightDistance;
	private double power;
	/**
	 * @param distance is the distance in inches that the robot will drive
	 * @param power is the power at which the robot will drive
	 */
    public DriveStraightDistanceCommand(double distance, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubSys);
    	this.leftDistance = distance;
    	this.rightDistance = distance;
    	this.power = power;
    }

    // Called just before this Command runs the first time
    /**
     * The encoder positions are reset when initialize is called
     */
    protected void initialize() {
    	Robot.driveSubSys.resetEncPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * calls set() with the power from the constructor and displays
     * the encoder positions and velocities of the left and right encoders on the SmartDashboard.
     */
    protected void execute() {
    	Robot.driveSubSys.set(power, power);
    	SmartDashboard.putNumber("Left Encoder Position", 
    							Robot.driveSubSys.leftTalons[0].getEncPosition());
    	SmartDashboard.putNumber("Right Encoder Position", 
    							Robot.driveSubSys.rightTalons[0].getEncPosition());
    	SmartDashboard.putNumber("Left Encoder Speed", 
    							Robot.driveSubSys.leftTalons[0].getEncVelocity());
    	SmartDashboard.putNumber("Right Encoder Speed", 
    							Robot.driveSubSys.rightTalons[0].getEncVelocity());
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * isFinished only returns true if the robot has reached both its leftDistance and rightDistance
     * as specified in the parameters of the constructor
     */
    protected boolean isFinished() {
        return (Robot.driveSubSys.hasReachedLeftDistance(leftDistance) && 
        		Robot.driveSubSys.hasReachedLeftDistance(rightDistance));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubSys.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
