package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DepositHorizontalSlides {
    public static Servo left_deposit_horizontal_slides;
    public static Servo right_deposit_horizontal_slides;
    public static final double deposit_position = 0.4;
    public static final double intake_position = 0.7;
    static final double INCREMENT = 0.01;
    static final int CYCLE_MS = 50;
    public double TURNING_POINT = 0.85;
    static double previous_action = System.currentTimeMillis();
    public static double current_position;

    public DepositHorizontalSlides(HardwareMap hardwareMap) {
        left_deposit_horizontal_slides = hardwareMap.get(Servo.class, "left_deposit_horizontal");
        right_deposit_horizontal_slides = hardwareMap.get(Servo.class, "right_deposit_horizontal");

        right_deposit_horizontal_slides.setDirection(Servo.Direction.REVERSE);
    }

    public static void set() {
        double position = left_deposit_horizontal_slides.getPosition();

        if (System.currentTimeMillis() - previous_action > CYCLE_MS) {
            previous_action = System.currentTimeMillis();
            if (position < current_position - 0.05) {
                position += INCREMENT;
            } else if (position > current_position + 0.05) {
                position -= INCREMENT;
            } else {
                position = current_position;
            }
        }

        left_deposit_horizontal_slides.setPosition(position);
        right_deposit_horizontal_slides.setPosition(position);
    }

    public static void intake() {
        current_position = intake_position;
    }

    public static void deposit() {
        current_position = deposit_position;
    }
}
