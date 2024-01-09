package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HorizontalSlides {
    private static PIDController controller;
    private static final double p = 0, i = 0, d = 0;
    private static final double f = 0;
    private static final int extendedBound = 0;
    private static final int retractedBound = 0;

    public static DcMotorEx leftHorizontalSlides;
    public static DcMotorEx rightHorizontalSlides;
    public static double current_position;

    public HorizontalSlides(HardwareMap hardwareMap) {
        controller = new PIDController(p, i, d);

        leftHorizontalSlides = hardwareMap.get(DcMotorEx.class, "left_horizontal");
        rightHorizontalSlides = hardwareMap.get(DcMotorEx.class, "right_horizontal");
    }

    public static void set() {
        if (current_position >= extendedBound) {
            current_position = extendedBound;
        }
        if (current_position <= retractedBound) {
            current_position = retractedBound;
        }

        controller.setPID(p, i, d);
        double current_position = leftHorizontalSlides.getCurrentPosition();

        double pid = controller.calculate(current_position, current_position);

        double power = pid + f;

        leftHorizontalSlides.setPower(power);
        rightHorizontalSlides.setPower(power);
    }

    public static void extend() {
        current_position = extendedBound;
    }

    public static void retract() {
        current_position = retractedBound;
    }
}
