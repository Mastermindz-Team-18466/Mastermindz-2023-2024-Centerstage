package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "SetServo", group = "Concept")
@Config
public class SetServo extends LinearOpMode {
    public static double start_position = 0;
    public static double end_position = 0;
    public static int reversed = 0;
    public Servo servo;

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "servo_test");

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        waitForStart();

        while (opModeIsActive()) {
            if (reversed == 1) {
                servo.setDirection(Servo.Direction.REVERSE);
            }

            if (reversed == 0) {
                servo.setDirection(Servo.Direction.FORWARD);
            }

            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            if (currentGamepad1.a && !previousGamepad1.a) {
                servo.setPosition(start_position);
            }

            else if (currentGamepad1.b && !previousGamepad1.b) {
                servo.setPosition(end_position);
            }
        }
    }
}
