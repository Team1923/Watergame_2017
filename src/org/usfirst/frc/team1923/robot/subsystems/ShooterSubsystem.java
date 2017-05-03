package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.VelocityMeasurementPeriod;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    private final double P_CONSTANT = 0.1;// 0.63;
    private final double I_CONSTANT = 0;// 0.000005;
    private final double D_CONSTANT = 0.1;// 0.1;
    private final double F_CONSTANT = 0.009;// 1023 / (10000 / 60 / 10 * 4096);
    private CANTalon shooter;
    private CANTalon indexer;
    private double speed;

    public final double allowableError = 150;

    public ShooterSubsystem() {
        shooter = new CANTalon(RobotMap.SHOOTER_PORT);
        indexer = new CANTalon(RobotMap.INDEXER_PORT);

        shooter.configPeakOutputVoltage(12, 0);
        shooter.configNominalOutputVoltage(0, 0);

        shooter.setP(P_CONSTANT);
        shooter.setI(I_CONSTANT);
        shooter.setD(D_CONSTANT);
        shooter.setF(F_CONSTANT);

        shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        shooter.setCurrentLimit(40);
        // this.shooter.setV
        shooter.configEncoderCodesPerRev(4096);
        shooter.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_1Ms);
        shooter.reverseSensor(true);
        shooter.reverseOutput(false);
        shooter.changeControlMode(TalonControlMode.Speed);

    }

    public void set(double power) {
        shooter.changeControlMode(TalonControlMode.PercentVbus);
        shooter.set(power);
        speed = shooter.getSpeed();
        SmartDashboard.putNumber("Shooter Encoder Speed", speed);
    }

    public void setSetpoint(double setpoint) {
        shooter.changeControlMode(TalonControlMode.Speed);
        shooter.setSetpoint(setpoint);
        // shooter.changeControlMode(TalonControlMode.Voltage);
        // shooter.setSetpoint(setpoint);
    }

    public void refresh() {
        speed = shooter.getSpeed();
        SmartDashboard.putNumber("Shooter Encoder Speed", speed);
        SmartDashboard.putNumber("PID Error", shooter.getError());
    }

    public void index(double speed) {
        indexer.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new ShooterCalibrateCommand());
    }

    public double getSpeed() {
        speed = shooter.getSpeed();
        SmartDashboard.putNumber("Shooter Encoder Speed", speed);
        return speed;
    }

    public double getError() {
        return shooter.getError();
    }
}
