package org.usfirst.frc.team1923.robot.utils;

import org.usfirst.frc.team1923.robot.RobotMap;

/**
 * Based off of Chris's Calculator.java.
 */
public class Smoother {

	public static double ease(double current, double old, double coalesceIncrement) {
		double new;
		if (current < old - coalesceIncrement) {
			new = old - coalesceIncrement;
		} 
    else if (current > old + coalesceIncrement) {
			new = old + coalesceIncrement;
		} 
    else {
			new = current;
		}
		return new;
	}
	
}
