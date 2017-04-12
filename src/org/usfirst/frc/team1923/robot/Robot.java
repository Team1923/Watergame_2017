
package org.usfirst.frc.team1923.robot;

import org.usfirst.frc.team1923.robot.commands.auton.DoNothingAuton;
import org.usfirst.frc.team1923.robot.commands.auton.VisionAutonCenter;
import org.usfirst.frc.team1923.robot.commands.auton.VisionAutonLeft;
import org.usfirst.frc.team1923.robot.commands.auton.VisionAutonRight;
import org.usfirst.frc.team1923.robot.commands.drive.DriveDistanceCommand;
import org.usfirst.frc.team1923.robot.commands.drive.DriveTimeCommand;
import org.usfirst.frc.team1923.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.DebugSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the start of the program. This class is run automatically when the
 * robot is activated. The rio then calls the appropriate methods.
 */
public class Robot extends IterativeRobot {

    public static ClimberSubsystem climbSubSys;
    public static GearSubsystem gearSubSys;
    public static VisionSubsystem visionSubSys;
    public static DrivetrainSubsystem driveSubSys;
    public static OI oi;
    public static DriverStation dstation = DriverStation.getInstance();
    public static DebugSubsystem debug;

    private Command autonomousCommand;
    private SendableChooser<Command> autonChooser = new SendableChooser<Command>();

    /**
     * Called once when the robot first starts up.
     */
    @Override
    public void robotInit() {
        gearSubSys = new GearSubsystem();
        driveSubSys = new DrivetrainSubsystem();
        climbSubSys = new ClimberSubsystem();
        visionSubSys = new VisionSubsystem();
        debug = new DebugSubsystem();

        oi = new OI();

        // add the autons to the drivers station.
        this.autonChooser.addDefault("Do Nothing Auto", new DoNothingAuton());
        // this.autonChooser.addObject("Log", new LogDataCommand("LOGGED"));
        this.autonChooser.addObject("Drive 2 seconds", new DriveTimeCommand(1.0, 2, true));
        this.autonChooser.addObject("Vision Auton Right", new VisionAutonRight());
        this.autonChooser.addObject("Vision Auton Center", new VisionAutonCenter());
        this.autonChooser.addObject("Vision Auton Left", new VisionAutonLeft());
        this.autonChooser.addObject("Drive 100 inches", new DriveDistanceCommand(100));

        // SmartDashboard.putData("Motion Magic SRX", new
        // DriveMotionMagicCommand(100));

        SmartDashboard.putData("Auto Mode", this.autonChooser);
    }

    /**
     * Called once when the robot is disabled.
     */
    @Override
    public void disabledInit() {
        debug.stopLog();
    }

    /**
     * Called every 20ms when the robot is disabled.
     */
    @Override
    public void disabledPeriodic() {

    }

    /**
     * Called once at the start of autonomous mode.
     */
    @Override
    public void autonomousInit() {
        visionSubSys.refresh();
        // get the auton the drivers selected
        this.autonomousCommand = this.autonChooser.getSelected();

        if (this.autonomousCommand != null)
            this.autonomousCommand.start(); // start the auton
    }

    /**
     * Called every 20ms during autonomous mode.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Called once at the start of tele-op mode.
     */
    @Override
    public void teleopInit() {
        if (this.autonomousCommand != null)
            this.autonomousCommand.cancel(); // stop auton

    }

    /**
     * Called every 20ms during tele-op mode.
     */
    @Override
    public void teleopPeriodic() {
        if (RobotMap.DEBUG)
            SmartDashboard.putNumber("Ultrasonic", Robot.visionSubSys.getDistance());

        Scheduler.getInstance().run();
    }

    /**
     * Called every 20ms during testing mode.
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }

}
