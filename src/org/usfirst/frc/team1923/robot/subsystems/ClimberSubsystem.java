package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.climb.ClimbCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

    private final double P_CONSTANT = 0;
    private final double I_CONSTANT = 0;
    private final double D_CONSTANT = 0;
    public final double OVER_CURRENT_RATIO = 5; // Amps / Volt
    public final double CLIMB_POWER = 0.8;
    private CANTalon leftClimb, rightClimb;

    /**
     * Creates an instance of the Climber subsystem with two talons Creates the
     * climber subsystem in order to add protection features to slowly disable
     * the climber once it reaches the top.
     */
    public ClimberSubsystem() {
        this.leftClimb = new CANTalon(RobotMap.LEFT_CLIMB_PORT);
        this.rightClimb = new CANTalon(RobotMap.RIGHT_CLIMB_PORT);
        // Sets up the follower relationship
        this.rightClimb.changeControlMode(TalonControlMode.Follower);
        this.rightClimb.reverseOutput(true); // Because the motors are against
                                             // each other
        this.rightClimb.set(this.leftClimb.getDeviceID());

        this.leftClimb.configPeakOutputVoltage(12, -12);
        this.leftClimb.configNominalOutputVoltage(0, 0);

        this.leftClimb.setPID(this.P_CONSTANT, this.I_CONSTANT, this.D_CONSTANT);
    }

    /**
     * @param power
     *            the power to climb at
     */
    public void set(double power) {
        this.leftClimb.set(power);
    }

    /**
     * @return current of left climb
     */
    public double getCurrent() {
        return this.leftClimb.getOutputCurrent();
    }

    /**
     * @return greater of left or right current
     */
    public double getMaxCurrent() {
        return Math.max(this.leftClimb.getOutputCurrent(), this.rightClimb.getOutputCurrent());
    }
    // SmartDashboard.putNumber("Max Climber Amerage: " , getMaxCurrent());

    public double getVoltage() {
        return this.leftClimb.getOutputVoltage();
    }

    // default command to call is the climb command
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ClimbCommand());
    }

}
