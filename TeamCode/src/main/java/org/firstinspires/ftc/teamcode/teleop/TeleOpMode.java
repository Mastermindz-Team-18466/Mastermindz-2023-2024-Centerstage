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
@Disabled
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
        in_out_take.setSpecificInstruction(IntakeOuttake.SpecificInstructions.CLOSED);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();


        while (!isStarted() && !isStopRequested() && !opModeIsActive()) {
            in_out_take.update();
        }

        waitForStart();

        while (opModeIsActive()) {

            driver.drive.update();
            in_out_take.update();

            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            // add buttons

            // telemetry
            telemetry.update();
        }
    }
}