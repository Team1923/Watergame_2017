package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Takes in distance value and drives using encoders
 */
public class EncoderDriveCommand extends Command {
	private double leftDistance, rightDistance;
//	private double speed;

	/**
	 * Constructor for command
	 * @param distance distance to be traveled in inches
	 * @param power speed to travel at
	 */
    public EncoderDriveCommand(double distance, double power) {
    	requires(Robot.driveSubSys);
    	leftDistance = distance;
    	rightDistance = distance;
//    	speed = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubSys.resetPosition();
    	Robot.driveSubSys.enableControl();
    	Robot.driveSubSys.drive(rightDistance, leftDistance, TalonControlMode.Position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Left Encoder Pos: ", Robot.driveSubSys.leftTalons[0].getEncPosition());
    	SmartDashboard.putNumber("Right Encoder Pos: ", Robot.driveSubSys.rightTalons[0].getEncPosition());
    	
    	SmartDashboard.putNumber("Left Error Value", Robot.driveSubSys.leftTalons[0].getError());

    	SmartDashboard.putNumber("Left Encoder Target ", leftDistance);
    	SmartDashboard.putNumber("Right Encoder Target ", rightDistance);
    	SmartDashboard.putNumber("Right Get: ",Robot.driveSubSys.rightTalons[0].get());
    	SmartDashboard.putNumber("Left Get: ", Robot.driveSubSys.leftTalons[0].get());

    	SmartDashboard.putNumber("Left Motor Speed: ", Robot.driveSubSys.leftTalons[0].getSpeed());
    	SmartDashboard.putNumber("Right Motor Speed: ", Robot.driveSubSys.rightTalons[0].getSpeed());
    	SmartDashboard.putNumber("Right Encoder Position (getPosition): ",Robot.driveSubSys.rightTalons[0].getPosition());
     	SmartDashboard.putNumber("Left Encoder Position (getPosition): ",Robot.driveSubSys.leftTalons[0].getPosition());
    	SmartDashboard.putString("Control Mode", Robot.driveSubSys.rightTalons[0].getControlMode().toString());

  }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
        //return (Robot.driveSubSys.leftTalons[0].getError() < Robot.driveSubSystem.ENCODER_ERROR_MARGIN );
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
