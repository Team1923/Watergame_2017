package org.usfirst.frc.team1923.robot.commands.auton;

import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.shooter.IndexerOffCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.IndexerOnCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.ShooterSetCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.ShooterSpinUpCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootGearAutonFarRight extends CommandGroup {

    public ShootGearAutonFarRight() {
        // Shoots Balls
        addSequential(new ShooterSpinUpCommand(RobotMap.SHOOTER_FAR_SETPOINT));
        addSequential(new WaitCommand(0.5));
        addSequential(new IndexerOnCommand());
        addSequential(new WaitCommand(4));
        addParallel(new IndexerOffCommand());
        addSequential(new ShooterSetCommand(0));

        addSequential(new VisionAutonRight());
        // Drops of gear on center peg
        // Robot.visionSubSys.refreshGear();
        // addSequential(new ShiftCommand(false));
        // addSequential(new WaitCommand(0.2));
        //
        // addSequential(new VisionGearAlignCommand());// Aligns Gear
        // addSequential(new WaitCommand(0.2));
        //
        // addSequential(new SlideCommand(true));
        // addSequential(new WaitCommand(0.2));
        // addSequential(new AutonGearCommand(true));
        // addSequential(new WaitCommand(0.2));
        // addSequential(new DriveTimeCommand(-0.5, 1));
    }
}
