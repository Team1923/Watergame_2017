package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTimeCommand extends Command {

	private double speed;

	
	public DriveTimeCommand(double speed, double timeOut){
		requires(Robot.driveSubSystem);
		setTimeout(timeOut);
		this.speed = speed;
	
	}

	protected void initialize() {
		
	}

	protected void execute() {
		Robot.driveSubSystem.drive(speed, -speed, CANTalon.TalonControlMode.PercentVbus);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.driveSubSystem.stop();
	}

	protected void interrupted() {
		end();
	}
	
}
