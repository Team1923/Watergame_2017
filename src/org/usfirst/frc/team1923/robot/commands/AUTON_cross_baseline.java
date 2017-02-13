package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AUTON_cross_baseline extends CommandGroup {
	
	public AUTON_cross_baseline()
	{
		this(0, 0);
	}
	
	public AUTON_cross_baseline(double speed, double timeOut)
	{
		addSequential(new DriveTimeCommand(speed, timeOut));
	}
}
