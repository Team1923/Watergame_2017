package org.usfirst.frc.team1923.robot.commands.driveCommands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnCommand extends Command{
	
	private double degrees;


	public TurnCommand(double degrees)
	{
		requires(Robot.driveSubSys);
		this.degrees = degrees;
		//setTimeout(timeOut)
	}
	
	protected void initialize()
	{
		Robot.driveSubSys.turn(degrees);
	}
	
	protected void execute()
	{
		
	}
	
	protected void end()
	{
		
	}
	
	protected void interrupted()
	{
		end();
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.driveSubSys.turnIsFinished();
		//isTimedOut();
	}

}
