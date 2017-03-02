package org.usfirst.frc.team1923.robot.commands.driveCommands;

import org.usfirst.frc.team1923.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTurnCommand extends DriveDistanceCommand{
	public double angle;
	
	public DriveTurnCommand(){
		this(90);
	}
	
	public DriveTurnCommand(double angle) {
		super(Robot.driveSubSys.angleToDistance(angle), - Robot.driveSubSys.angleToDistance(angle));
		this.setTimeout(2);		
	}

	@Override
	protected boolean isFinished() {
		System.out.println(Robot.driveSubSys.getLeftError() 
				+ " " + Robot.driveSubSys.getRightError());
		return (Robot.driveSubSys.getLeftTarget() != 0 
				|| Robot.driveSubSys.getRightTarget() != 0) 
				&& (Math.abs(Robot.driveSubSys.getLeftError()) < 300) 
				&& (Math.abs(Robot.driveSubSys.getRightError()) < 300) || this.isTimedOut();
	}
	
}
