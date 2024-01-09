package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private static DcMotorEx intakeMotor;
    private static Servo dropdown;
    public static final double intakeSpeed = 1;
    public static final double outtakeSpeed = -1;
    public static final double dropdown_up_position = 0;
    public static final double dropdown_down_position = 0.72;
    public static double intake_current_speed;
    public static double dropdown_current_position;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake_motor");
        dropdown = hardwareMap.get(Servo.class, "dropdown");
    }
    public static void stop() {
        intake_current_speed = 0;
    }
    public static void intake() {
        intake_current_speed = 1;
    }
    public static void intake(double speed) {
        if (Math.abs(speed) > 1) speed = 1;
        intake_current_speed = Math.abs(speed);
    }
    public static void outtake() {
        intake_current_speed = -1;
    }
    public static void outtake(double speed) {
        if (Math.abs(speed) > 1) speed = -1;
        intake_current_speed = -Math.abs(speed);
    }
    public static void dropdown_down() {
        dropdown_current_position = dropdown_down_position;
    }
    public static void dropdown_down(double position) {
        if (position > dropdown_down_position) position = dropdown_down_position;
        dropdown_current_position = position;
    }
    public static void dropdown_up() {
        dropdown_current_position = dropdown_up_position;
    }
    public static void dropdown_up(double position) {
        if (position < dropdown_up_position) position = dropdown_up_position;
        dropdown_current_position = position;
    }

    public static void setIntake() {
        intakeMotor.setPower(intake_current_speed);
    }

    public static void setDropdown() {
        dropdown.setPosition(dropdown_current_position);
    }
}
