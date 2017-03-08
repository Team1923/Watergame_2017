package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.driveCommands.RawDriveCommand;
import org.usfirst.frc.team1923.robot.utils.DriveProfile;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class that houses the motors and shifters
 */
public class DrivetrainSubsystem extends Subsystem {

    private final double P_CONSTANT = 0.05; // TODO: Fill in these values
    private final double I_CONSTANT = 0.00005;
    private final double D_CONSTANT = 0.0000;
    private final double F_CONSTANT = 0;
    private final boolean LEFT_REVERSED = true; // Reverse the sensor or
                                                // the motor or both?
    private final boolean RIGHT_REVERSED = false;
    private final int MAX_SAFE_SHIFT_SPEED = 100; // RPM

    public final int ALLOWABLE_ERROR = 200;

    // TODO: Change wheel diameter and drive base width
    private final static double WHEEL_DIAMETER = 4;
    private final static double DRIVE_RATIO = 32.5 / 50.0;
    // Every turn of the encoder equals DRIVE_RATIO turns of the wheel

    private final static double DRIVE_BASE_WIDTH = 22.5;
    // Middle of wheels measurement in inches
    public static double TURNING_CONSTANT = 1.12;
    private static final double DRIVE_CONSTANT = 1;

    // Arrays of talons to group them together
    // The 0th element will always be the master Talon, the subsequent ones will
    // follow
    private CANTalon[] leftTalons, rightTalons;

    private DoubleSolenoid shifter;
    private DoubleSolenoid shiftOmnis;

    public Ultrasonic frontSonar;

    public DriveProfile dprofile = new DriveProfile(RobotMap.DRIVER_PROFILE);

    public DrivetrainSubsystem() {
        this.leftTalons = new CANTalon[RobotMap.LEFT_DRIVE_PORTS.length];
        this.rightTalons = new CANTalon[RobotMap.RIGHT_DRIVE_PORTS.length];

        this.frontSonar = new Ultrasonic(RobotMap.FRONT_SONAR_PING_PORT,
                RobotMap.FRONT_SONAR_ECHO_PORT, Unit.kMillimeters);
        this.frontSonar.setAutomaticMode(true);

        for (int i = 0; i < RobotMap.LEFT_DRIVE_PORTS.length; i++) {
            this.leftTalons[i] = new CANTalon(RobotMap.LEFT_DRIVE_PORTS[i]);
        }

        for (int i = 0; i < RobotMap.RIGHT_DRIVE_PORTS.length; i++) {
            this.rightTalons[i] = new CANTalon(RobotMap.RIGHT_DRIVE_PORTS[i]);
        }

        this.shifter = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.SHIFT_FORWARD_PORT,
                RobotMap.SHIFT_BACKWARD_PORT);
        this.shiftOmnis = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.OMNI_FORWARD_PORT,
                RobotMap.OMNI_BACKWARD_PORT);

        setToFollow();
        configPID();
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private void setToFollow() {
        this.leftTalons[1].changeControlMode(TalonControlMode.Follower);
        this.leftTalons[1].set(leftTalons[0].getDeviceID());
        this.leftTalons[2].changeControlMode(TalonControlMode.Follower);
        this.leftTalons[2].set(leftTalons[0].getDeviceID());

        // leftTalons[0].changeControlMode(TalonControlMode.Follower);
        // leftTalons[0].set(rightTalons[0].getDeviceID());

        this.rightTalons[1].changeControlMode(TalonControlMode.Follower);
        this.rightTalons[1].set(this.rightTalons[0].getDeviceID());
        this.rightTalons[2].changeControlMode(TalonControlMode.Follower);
        this.rightTalons[2].set(this.rightTalons[0].getDeviceID());
    }

    /**
     * Sets the two master talons to a certain mode
     * 
     * @param c
     *            TalonControlMode to be used
     */
    private void setMasterToMode(TalonControlMode c) {
        // Speed, Position, Percent VBus etc.
        this.leftTalons[0].changeControlMode(c);
        this.rightTalons[0].changeControlMode(c);
    }

    /**
     * Configures the PID values of the two master talons
     * 
     * Sets the nominal and peak output voltages and sets the sensor and output
     * reversal flags
     */
    private void configPID() {
        this.leftTalons[0].setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        this.leftTalons[0].reverseSensor(LEFT_REVERSED); // Set to true if
                                                         // reverse
        // rotation
        this.leftTalons[0].configNominalOutputVoltage(0, 0);
        this.leftTalons[0].configPeakOutputVoltage(12, -12);
        // TODO: Config higher if necessary

        this.rightTalons[0].setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        this.rightTalons[0].reverseSensor(RIGHT_REVERSED); // Set to true if
                                                           // reverse
        // rotation
        this.rightTalons[0].configNominalOutputVoltage(0, 0);
        this.rightTalons[0].configPeakOutputVoltage(12, -12);

        this.leftTalons[0].setProfile(0);
        this.leftTalons[0].setF(F_CONSTANT);
        this.leftTalons[0].setP(P_CONSTANT);
        this.leftTalons[0].setI(I_CONSTANT);
        this.leftTalons[0].setD(D_CONSTANT);
        this.leftTalons[0].setIZone(10000);
        this.leftTalons[0].setAllowableClosedLoopErr(ALLOWABLE_ERROR);

        this.rightTalons[0].setProfile(0);
        this.rightTalons[0].setF(F_CONSTANT);
        this.rightTalons[0].setP(P_CONSTANT);
        this.rightTalons[0].setI(I_CONSTANT);
        this.rightTalons[0].setD(D_CONSTANT);
        this.rightTalons[0].setIZone(10000);
        this.rightTalons[0].setAllowableClosedLoopErr(ALLOWABLE_ERROR);

        setMasterToMode(TalonControlMode.PercentVbus);
        this.leftTalons[0].set(0.0);
        this.leftTalons[0].reverseOutput(LEFT_REVERSED);
        this.leftTalons[0].setInverted(LEFT_REVERSED);

        this.rightTalons[0].set(0.0);
        this.rightTalons[0].reverseOutput(RIGHT_REVERSED);
        this.rightTalons[0].setInverted(RIGHT_REVERSED);
    }

    // public void setPID(double p, double i, double d, double f) {
    // leftTalons[0].setPID(p, i, d);
    // leftTalons[0].setF(f);
    // rightTalons[0].setPID(p, i, d);
    // rightTalons[0].setF(f);
    // }

    public void resetP() {
        this.setP(P_CONSTANT);
    }

    public void setP(double p) {
        this.leftTalons[0].setP(p);
        this.rightTalons[0].setP(p);
    }

    /**
     * Directly sets the input value of the motors
     * 
     * Use with caution because it doesn't automatically check the control mode
     * 
     * @param left
     *            Left power
     * @param right
     *            Right power
     */
    private void set(double left, double right) {
        this.leftTalons[0].set(left);
        this.rightTalons[0].set(right);
    }

    /**
     * Disables the closed-loop system and allows direct power setting
     */
    public void disable() {
        setMasterToMode(TalonControlMode.Disabled);
    }

    public void disableControl() {
        this.leftTalons[0].disableControl();
        this.rightTalons[0].disableControl();
    }

    public void enable() {
        setMasterToMode(TalonControlMode.PercentVbus);
    }

    /**
     * Sets talon powers with a specific mode
     * 
     * @param left
     *            Left power
     * @param right
     *            Right power
     * @param m
     *            TalonControlMode to be used
     */
    public void drive(double left, double right, TalonControlMode m) {
        if (this.leftTalons[0].getControlMode() != m || this.rightTalons[0].getControlMode() != m) {
            setMasterToMode(m);
        }
        set(left, right);
    }

    /**
     * Resets current position of the encoders.
     * 
     * Since SRX Mag encoders are used in relative mode, this allows us to
     * simplify autons by resetting the home position of the robot
     */
    public void resetPosition() {
        this.leftTalons[0].setPosition(0);
        this.rightTalons[0].setPosition(0);
    }

    public double getLeftPosition() {
        return this.leftTalons[0].getPosition();
    }

    public double getRightPosition() {
        return this.rightTalons[0].getPosition();
    }

    public double getLeftSpeed() {
        return this.leftTalons[0].getSpeed();
    }

    public double getRightSpeed() {
        return this.rightTalons[0].getSpeed();
    }

    public double getLeftTarget() {
        return this.leftTalons[0].get();
    }

    public double getRightTarget() {
        return this.rightTalons[0].get();
    }

    public double getLeftError() {
        return this.leftTalons[0].getError();
    }

    public double getRightError() {
        return this.rightTalons[0].getError();
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new RawDriveCommand());
    }

    public void shiftUp() { // TODO: Find if these orientations are correct
        if (this.shifter.get() != Value.kForward) {
            this.shifter.set(Value.kForward);
        }
    }

    public void shiftDown() {
        if (safeToShift() && this.shifter.get() != Value.kReverse) {
            this.shifter.set(Value.kReverse);
        }
    }

    public void shiftUpOmnis() {
        if (this.shiftOmnis.get() != Value.kForward) {
            this.shiftOmnis.set(Value.kForward);
        }
    }

    public void shiftDownOmnis() {
        if (this.shiftOmnis.get() != Value.kReverse) {
            this.shiftOmnis.set(Value.kReverse);
        }
    }

    private boolean safeToShift() {
        return Math.max(Math.abs(this.leftTalons[0].getEncVelocity()),
                Math.abs(this.rightTalons[0].getEncVelocity())) < MAX_SAFE_SHIFT_SPEED;
    }

    public void stop() {
        drive(0, 0, TalonControlMode.PercentVbus);
    }

    public static double angleToDistance(double angle) {
        return TURNING_CONSTANT * angle * Math.PI * DRIVE_BASE_WIDTH / 360;
    }

    public static double distanceToRotation(double distance) {
        return distance / (Math.PI * WHEEL_DIAMETER * DRIVE_RATIO) * DRIVE_CONSTANT;
    }

}
