<<<<<<< HEAD:src/org/usfirst/frc/team1923/robot/commands/ShiftGearCommand.java
package org.usfirst.frc.team1923.robot.commands;
=======
package org.usfirst.frc.team1923.robot.commands.driveCommands;
>>>>>>> beta:src/org/usfirst/frc/team1923/robot/commands/driveCommands/ShiftGearCommand.java

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * This command shifts the gears from up to down or down to up if safe.
 */
public class ShiftGearCommand extends InstantCommand {

	private boolean up;

	public ShiftGearCommand() {
		super();
	}

	public ShiftGearCommand(boolean up) {
		this.up = up;
	}

	// Called once when the command executes
	protected void initialize() {
		if (up) {
<<<<<<< HEAD:src/org/usfirst/frc/team1923/robot/commands/ShiftGearCommand.java
			Robot.driveSubSystem.shiftUp();
		} else {
			Robot.driveSubSystem.shiftDown();
=======
			Robot.driveSubSys.shiftUp();
		} else {
			Robot.driveSubSys.shiftDown();
>>>>>>> beta:src/org/usfirst/frc/team1923/robot/commands/driveCommands/ShiftGearCommand.java
		}
	}

}
