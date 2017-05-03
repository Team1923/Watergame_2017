package org.usfirst.frc.team1923.robot.utils.controller;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends Controller {

    private static final int A_BUTTON_ID = 1;
    private static final int B_BUTTON_ID = 2;
    private static final int X_BUTTON_ID = 3;
    private static final int Y_BUTTON_ID = 4;
    private static final int LB_BUTTON_ID = 5;
    private static final int RB_BUTTON_ID = 6;
    private static final int BACK_BUTTON_ID = 7;
    private static final int START_BUTTON_ID = 8;
    private static final int LEFT_CLICK_ID = 9;
    private static final int RIGHT_CLICK_ID = 10;

    private static final int LEFT_TRIGGER_AXIS_ID = 2;
    private static final int RIGHT_TRIGGER_AXIS_ID = 3;

    public final Trigger lt;
    public final Trigger rt;
    public final DirectionalPad dPad;
    public final Button a;
    public final Button b;
    public final Button x;
    public final Button y;
    public final Button lb;
    public final Button rb;
    public final Button back;
    public final Button start;
    public final Button rightClick;
    public final Button leftClick;

    public XboxController(final int port) {
        super(port);

        dPad = new DirectionalPad(controller);
        lt = new Trigger(controller, LEFT_TRIGGER_AXIS_ID);
        rt = new Trigger(controller, RIGHT_TRIGGER_AXIS_ID);
        a = new JoystickButton(controller, A_BUTTON_ID);
        b = new JoystickButton(controller, B_BUTTON_ID);
        x = new JoystickButton(controller, X_BUTTON_ID);
        y = new JoystickButton(controller, Y_BUTTON_ID);
        lb = new JoystickButton(controller, LB_BUTTON_ID);
        rb = new JoystickButton(controller, RB_BUTTON_ID);
        back = new JoystickButton(controller, BACK_BUTTON_ID);
        start = new JoystickButton(controller, START_BUTTON_ID);
        rightClick = new JoystickButton(controller, RIGHT_CLICK_ID);
        leftClick = new JoystickButton(controller, LEFT_CLICK_ID);
    }

}
