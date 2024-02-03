package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "TeleOpMode", group = "Concept")
public class TeleOpMode extends LinearOpMode {
    FieldCentric driver;
    IntakeOuttake in_out_take;
    Claw claw;
    VerticalSlides vertical_slides;
    HorizontalSlides horizontal_slides;
    Intake intake;
    Sensors sensors;
    DepositHorizontalSlides deposit_horizontal_slides;

    @Override
    public void runOpMode() {
        driver = new FieldCentric(hardwareMap, new SampleMecanumDrive(hardwareMap), gamepad1);

        claw = new Claw(hardwareMap);
        vertical_slides = new VerticalSlides(hardwareMap);
        horizontal_slides = new HorizontalSlides(hardwareMap);
        intake = new Intake(hardwareMap);
        sensors = new Sensors(hardwareMap);
        deposit_horizontal_slides = new DepositHorizontalSlides(hardwareMap);

        in_out_take = new IntakeOuttake(sensors, claw, deposit_horizontal_slides, horizontal_slides, intake, vertical_slides);

        in_out_take.setInstructions(IntakeOuttake.Instructions.CLOSED);
        in_out_take.setSpecificInstruction(IntakeOuttake.SpecificInstructions.EXTEND_VERTICAL);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();


        while (!isStarted() && !isStopRequested() && !opModeIsActive()) {
            in_out_take.update();
        }

        waitForStart();

        while (opModeIsActive()) {

            driver.move();
            in_out_take.update();

            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {
                in_out_take.setInstructions(IntakeOuttake.Instructions.EXTEND_VERTICAL_FOR_INTAKE);
                in_out_take.setSpecificInstruction(IntakeOuttake.SpecificInstructions.EXTEND_VERTICAL);
                in_out_take.intake.intake();
            }

            if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
                in_out_take.intake.outtake();
            }

            if (currentGamepad2.x && !previousGamepad2.x) {
                in_out_take.intake.intake();
            }

            if (currentGamepad2.y && !previousGamepad2.y) {
                in_out_take.intake.stop();
            }

            if (currentGamepad2.dpad_up && !(previousGamepad2.dpad_up)) {
                in_out_take.setInstructions(IntakeOuttake.Instructions.OPEN_INTAKE);
                in_out_take.setSpecificInstruction(IntakeOuttake.SpecificInstructions.EXTEND_VERTICAL);
            }

            if (currentGamepad2.right_trigger > 0.5 && !(previousGamepad2.right_trigger > 0.5)) {
                in_out_take.setInstructions(IntakeOuttake.Instructions.CLOSED);
                in_out_take.setSpecificInstruction(IntakeOuttake.SpecificInstructions.DROP_PIXEL);
            }

            if (currentGamepad2.b && !previousGamepad2.b) {
                in_out_take.claw.open_left_claw();
                in_out_take.claw.open_right_claw();
            }

            if (currentGamepad2.left_trigger > 0.5 && !(previousGamepad2.left_trigger > 0.5)) {
                in_out_take.setInstructions(IntakeOuttake.Instructions.DEPOSIT);
                in_out_take.setSpecificInstruction(IntakeOuttake.SpecificInstructions.RETRACT_VERTICAL);
            }

            if (currentGamepad2.a && !previousGamepad2.a) {
                in_out_take.claw.close_left_claw();
                in_out_take.claw.close_right_claw();
            }

            if (gamepad1.left_trigger > 0.2) {
                driver.drive.slowMode = 4.5;
            } else {
                driver.drive.slowMode = 1;
            }

            if (Math.abs(gamepad2.left_stick_x) >= 0.5 || Math.abs(gamepad2.right_stick_y) >= 0.5) {
                in_out_take.vertical_slides.vertical_offset -= gamepad2.right_stick_y * 5;
            }

            // telemetry
            telemetry.update();
        }
    }
}