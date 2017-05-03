package org.usfirst.frc.team1923.robot;

import org.usfirst.frc.team1923.robot.commands.drive.ResetEncoderCommand;
import org.usfirst.frc.team1923.robot.commands.drive.ShiftCommand;
import org.usfirst.frc.team1923.robot.commands.drive.ShiftOmniCommand;
import org.usfirst.frc.team1923.robot.commands.gear.GearCommand;
import org.usfirst.frc.team1923.robot.commands.gear.GearSetHomeCommand;
import org.usfirst.frc.team1923.robot.commands.gear.SlideCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.IndexerOffCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.IndexerOnCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.ShooterSpinUpCommand;
import org.usfirst.frc.team1923.robot.commands.vision.VisionGearAlignCommand;
import org.usfirst.frc.team1923.robot.commands.vision.VisionProcessing;
import org.usfirst.frc.team1923.robot.utils.controller.PS4Controller;
import org.usfirst.frc.team1923.robot.utils.controller.XboxController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public PS4Controller driver;
    public XboxController op;

    public OI() {
        // Creates two ps4 controllers
        driver = new PS4Controller(RobotMap.DRIVER_CONTROLLER_PORT);
        driver.lt.setTriggerSensitivity(0.5);
        driver.rt.setTriggerSensitivity(0.5);

        driver.lb.whenActive(new ShiftCommand(false));
        driver.rb.whenActive(new ShiftCommand(true));

        driver.lt.whenActive(new ShiftOmniCommand(true));
        driver.rt.whenActive(new ShiftOmniCommand(false));

        driver.cross.whenActive(new ResetEncoderCommand());

        op = new XboxController(RobotMap.OP_CONTROLLER_PORT);

        op.x.whenActive(new SlideCommand());
        op.y.whenActive(new GearCommand());
        op.b.whenActive(new GearSetHomeCommand());
        op.rb.whenActive(new ShooterSpinUpCommand(RobotMap.SHOOTER_CENTER_SETPOINT));
        op.lb.whenActive(new ShooterSpinUpCommand(0));
        op.a.whileHeld(new IndexerOnCommand());
        op.back.whenActive(new IndexerOffCommand());

        // For testing in pit
        op.start.whenActive(new ShooterSpinUpCommand(3500)); // 9750

        // Vision Commands
        Command pegAlign = new VisionGearAlignCommand(false);
        driver.square.whileHeld(pegAlign);
        Command feederAlign = new VisionGearAlignCommand(true);
        driver.triangle.whileHeld(feederAlign);
        Command refresh = new VisionProcessing();
        driver.circle.whileHeld(refresh);
    }
}
