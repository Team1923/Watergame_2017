package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the operations of the gear dumping mechanism on the
 * robot.
 */
public class GearSubsystem extends Subsystem {

	private DoubleSolenoid slider;

	public GearSubsystem() {
		slider = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.SLIDE_FORWARD_PORT, RobotMap.SLIDE_BACKWARD_PORT);

	}

	public void slideForward() {
		if (slider.get() != Value.kForward) {
			slider.set(Value.kForward);
		}
	}

	public void slideBackward() {
		if (slider.get() != Value.kReverse) {
			slider.set(Value.kReverse);
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
