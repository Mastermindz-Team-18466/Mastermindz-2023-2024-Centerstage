//package org.firstinspires.ftc.teamcode.auto.Blue;
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
//import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
//import com.acmerobotics.roadrunner.util.Angle;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.opencv.SpikemarkDetection;
//import org.firstinspires.ftc.teamcode.teleop.Claw;
//import org.firstinspires.ftc.teamcode.teleop.DepositHorizontalSlides;
//import org.firstinspires.ftc.teamcode.teleop.FieldCentric;
//import org.firstinspires.ftc.teamcode.teleop.HorizontalSlides;
//import org.firstinspires.ftc.teamcode.teleop.Intake;
//import org.firstinspires.ftc.teamcode.teleop.IntakeOuttake;
//import org.firstinspires.ftc.teamcode.teleop.Sensors;
//import org.firstinspires.ftc.teamcode.teleop.VerticalSlides;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//
//@Autonomous(name = "BlueFar", group = "Concept")
//public class BlueFar extends LinearOpMode {
//
//    OpenCvCamera webcam;
//    IntakeOuttake in_out_take;
//    Claw claw;
//    VerticalSlides vertical_slides;
//    HorizontalSlides horizontal_slides;
//    Intake intake;
//    Sensors sensors;
//    DepositHorizontalSlides deposit_horizontal_slides;
//
//    int LEFT = 1;
//    int MIDDLE = 2;
//    int RIGHT = 3;
//
//    Pose2d startPose = new Pose2d(1.5 * 23.5, -3 * 23.5, Math.PI / 2);
//
//    //    SpikemarkDetection spikemarkDetection = new SpikemarkDetection(telemetry);
//
//    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//    @Override
//    public void runOpMode() {
//        drive.setPoseEstimate(startPose);
//
//        claw = new Claw(hardwareMap);
//        vertical_slides = new VerticalSlides(hardwareMap);
//        horizontal_slides = new HorizontalSlides(hardwareMap);
//        intake = new Intake(hardwareMap);
//        sensors = new Sensors(hardwareMap);
//        deposit_horizontal_slides = new DepositHorizontalSlides(hardwareMap);
//
//        in_out_take = new IntakeOuttake(sensors, claw, deposit_horizontal_slides, horizontal_slides, intake, vertical_slides);
//
//        in_out_take.setInstructions(IntakeOuttake.Instructions.INITIAL_CLOSED);
//        in_out_take.setSpecificInstruction(IntakeOuttake.SpecificInstructions.EXTEND_VERTICAL);
//
//
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//
////        webcam.setPipeline(spikemarkDetection);
//        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                webcam.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int i) {
//
//            }
//        });
//
//
//        while (!isStarted() && !isStopRequested()) {
//            in_out_take.update();
//            telemetry.update();
//
//
//        }
//
//        if () { //One
//            drive.followTrajectorySequenceAsync(drive.trajectorySequenceBuilder(startPose)
//
//                    .UNSTABLE_addTemporalMarkerOffset(3, () -> {
//                        //outtake
//                    })
//                    .build()
//            );
//        } else if () { //two
//            drive.followTrajectorySequenceAsync(drive.trajectorySequenceBuilder(startPose)
//
//                    .UNSTABLE_addTemporalMarkerOffset(3, () -> {
//                        //outtake
//                    })
//                    .build()
//            );
//        } else { //three
//            drive.followTrajectorySequenceAsync(drive.trajectorySequenceBuilder(startPose)
//
//                    .UNSTABLE_addTemporalMarkerOffset(3, () -> {
//                        //outtake
//                    })
//                    .build()
//            );
//        }
//
//        waitForStart();
//
//        double cycles = 1;
//        long startTime = System.currentTimeMillis();
//
//
//        while (opModeIsActive()) {
//            long currentTime = System.currentTimeMillis();
//
//            Pose2d currentPose = drive.getPoseEstimate();
//
//
//            if (currentTime - startTime >= 2400 && !drive.isBusy()) { //grab one pixel
//                drive.followTrajectorySequenceAsync(drive.trajectorySequenceBuilder(currentPose)
//
//                        .UNSTABLE_addTemporalMarkerOffset(3, () -> {
//                            //outtake
//                        })
//                        .build()
//                );
//            }
//
//            //go for deposit
//            if ((currentTime - startTime >= 7000 && !drive.isBusy() && cycles > 0)) {
//
//            }
//
//        }
//    }
//
//    public void lockTo(Pose2d targetPos) {
//        Pose2d currPos = drive.getPoseEstimate();
//        Pose2d difference = targetPos.minus(currPos);
//        Vector2d xy = difference.vec().rotated(-currPos.getHeading());
//
//        double heading = Angle.normDelta(targetPos.getHeading()) - Angle.normDelta(currPos.getHeading());
//        drive.setWeightedDrivePower(new Pose2d(xy, heading));
//    }
//}