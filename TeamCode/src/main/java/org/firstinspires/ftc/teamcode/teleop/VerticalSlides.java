package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class VerticalSlides {
    private static PIDController controller;
    private static final double p = 0, i = 0, d = 0;
    private static final double f = 0;
    private static final int extendedBound = 0;
    private static final int retractedBound = 0;
    private static final double ground_position = 0;
    private static final double low_position = 0;
    private static final double medium_position = 0;
    private static final double high_position = 0;

    public static DcMotorEx leftVerticalSlides;
    public static DcMotorEx rightVerticalSlides;
    public static double current_position = 0;

    public VerticalSlides(HardwareMap hardwareMap) {
        controller = new PIDController(p, i, d);

        leftVerticalSlides = hardwareMap.get(DcMotorEx.class, "left_vertical");
        rightVerticalSlides = hardwareMap.get(DcMotorEx.class, "right_vertical");
    }

    public static void set() {
        if (current_position >= extendedBound) {
            current_position = extendedBound;
        }
        if (current_position <= retractedBound) {
            current_position = retractedBound;
        }

        controller.setPID(p, i, d);
        double current_position = leftVerticalSlides.getCurrentPosition();

        double pid = controller.calculate(current_position, current_position);

        double power = pid + f;

        leftVerticalSlides.setPower(power);
        rightVerticalSlides.setPower(power);
    }

    public static void go_to_high() {
        current_position = high_position;
    }

    public static void go_to_medium() {
        current_position = medium_position;
    }

    public static void go_to_low() {
        current_position = low_position;
    }

    public static void go_to_ground() {
        current_position = ground_position;
    }
}
