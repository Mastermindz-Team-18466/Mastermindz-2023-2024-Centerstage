package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public static Servo left_claw;
    public static Servo right_claw;
    public static Servo claw_tilt;
    private static final double claw_open_position = 0;
    private static final double claw_close_position = 0;
    private static final double claw_tilt_intake = 0;
    private static final double claw_tilt_deposit = 0;
    public static double left_claw_position;
    public static double right_claw_position;
    public static double claw_tilt_position;

    public Claw(HardwareMap hardwareMap) {
        left_claw = hardwareMap.get(Servo.class, "left_claw");
        right_claw = hardwareMap.get(Servo.class, "right_claw");
        claw_tilt = hardwareMap.get(Servo.class, "claw_tilt");
    }

    public static void open_left_claw() {
        left_claw_position = claw_open_position;
    }

    public static void close_left_claw() {
        left_claw_position = claw_close_position;
    }

    public static void open_right_claw() {
        right_claw_position = claw_open_position;
    }

    public static void close_right_claw() {
        right_claw_position = claw_close_position;
    }

    public static void intake_tilt() {
        claw_tilt_position = claw_tilt_intake;
    }

    public static void deposit_tilt() {
        claw_tilt_position = claw_tilt_deposit;
    }

    public static void set() {
        left_claw.setPosition(left_claw_position);
        right_claw.setPosition(right_claw_position);
        claw_tilt.setPosition(claw_tilt_position);
    }
}
