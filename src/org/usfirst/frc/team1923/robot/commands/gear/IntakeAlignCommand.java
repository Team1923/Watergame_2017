package org.usfirst.frc.team1923.robot.commands.gear;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.utils.PIDController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeAlignCommand extends Command {

    // TODO: Tune PID constants
    private final double P_CONST = 0.0070;
    private final double I_CONST = 0.0018;
    private final double D_CONST = 0.0000;
    private final double I_ZONE = 5;

    private final double TARGET = 10;
    private final double TOLERANCE = 0.5;
    private final double POWER = 11;

    private PIDController leftController;
    private PIDController rightController;
    private Drivetrain leftDrive;
    private Drivetrain rightDrive;
    private Ultrasonic leftUltrasonic;
    private Ultrasonic rightUltrasonic;

    public IntakeAlignCommand() {
        requires(Robot.driveSubSys);
        requires(Robot.visionSubSys);

        leftDrive = new Drivetrain(Side.LEFT);
        rightDrive = new Drivetrain(Side.RIGHT);

        leftUltrasonic = Robot.visionSubSys.getUltrasonic();
        rightUltrasonic = Robot.visionSubSys.getUltrasonic();

        leftController = new PIDController(P_CONST, I_CONST, D_CONST, leftUltrasonic, leftDrive);
        leftController.setContinuous(true);
        leftController.setAbsoluteTolerance(TOLERANCE);
        leftController.setOutputRange(-1, 1);
        leftController.setSetpoint(TARGET);
        leftController.setIZone(I_ZONE);

        rightController = new PIDController(P_CONST, I_CONST, D_CONST, rightUltrasonic, rightDrive);
        rightController.setContinuous(true);
        rightController.setAbsoluteTolerance(TOLERANCE);
        rightController.setOutputRange(-1, 1);
        rightController.setSetpoint(TARGET);
        rightController.setIZone(I_ZONE);

        this.setTimeout(10);
    }

    @Override
    protected void initialize() {
        leftController.enable();
        rightController.enable();
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return (leftController.onTarget() && rightController.onTarget()) || this.isTimedOut();
    }

    @Override
    protected void end() {
        leftController.disable();
        rightController.disable();
    }

    @Override
    protected void interrupted() {
        this.end();
    }

    public class Drivetrain implements PIDOutput {

        private Side side;

        public Drivetrain(Side side) {
            this.side = side;
        }

        @Override
        public void pidWrite(double output) {
            if (side == Side.LEFT) {
                Robot.driveSubSys.setLeft(output * POWER, CANTalon.TalonControlMode.Voltage);
            } else {
                Robot.driveSubSys.setRight(output * POWER, CANTalon.TalonControlMode.Voltage);
            }
        }

    }

    public enum Side {
        LEFT, RIGHT
    }

}