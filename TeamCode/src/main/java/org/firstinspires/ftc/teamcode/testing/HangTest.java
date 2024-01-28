package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "HangTest", group = "Concept")
@Config
public class HangTest extends LinearOpMode {
    public CRServo left_hang;
    public CRServo right_hang;
    public static double a_power = 0;
    public static double b_power = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        left_hang = hardwareMap.get(CRServo.class, "left_hang");
        right_hang = hardwareMap.get(CRServo.class, "right_hang");

        left_hang.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            Gamepad currentGamepad1 = new Gamepad();
            Gamepad currentGamepad2 = new Gamepad();

            Gamepad previousGamepad1 = new Gamepad();
            Gamepad previousGamepad2 = new Gamepad();

            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            if (currentGamepad1.a && !previousGamepad1.a) {
                left_hang.setPower(a_power);
                right_hang.setPower(a_power);
            }

            else if (currentGamepad1.b && !previousGamepad1.b) {
                left_hang.setPower(b_power);
                right_hang.setPower(b_power);
            }
        }
    }
}
