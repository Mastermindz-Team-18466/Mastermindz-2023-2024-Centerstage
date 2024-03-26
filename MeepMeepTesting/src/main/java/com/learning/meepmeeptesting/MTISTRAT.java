package com.learning.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MTISTRAT {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        final double TILE_SIZE = 23.5;
        int robotx = 0;
        int roboty = 0;

        Pose2d startPose = new Pose2d(12, 60, Math.PI/2);


        Pose2d backboard = new Pose2d(0, 0);
        Pose2d stack = new Pose2d(startPose.getX() - 1 * TILE_SIZE, startPose.getY()- 2.1 * (TILE_SIZE));


        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.4)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)
                                .lineToSplineHeading(new Pose2d(startPose.getX() + (0.5 * TILE_SIZE) + 23, startPose.getY()-(1.4 * TILE_SIZE), Math.toRadians(180)))
                                .waitSeconds(2)
                                //.strafeRight((0.4 * TILE_SIZE))
                                .lineToSplineHeading(new Pose2d(startPose.getX() + 23, startPose.getY()-(1 * TILE_SIZE), Math.toRadians(180)))
                                .lineToSplineHeading(new Pose2d(startPose.getX() - (2.3 * TILE_SIZE) + 23, startPose.getY()-(1 * TILE_SIZE), Math.toRadians(180)))
                                .waitSeconds(3) //intake
                                .lineToSplineHeading(new Pose2d(startPose.getX() + (0.5 * TILE_SIZE) + 23, startPose.getY()-(1 * TILE_SIZE), Math.toRadians(180)))
                                .waitSeconds(1) //depo
                                .lineToSplineHeading(new Pose2d(startPose.getX() - (2.3 * TILE_SIZE) + 23, startPose.getY()-(1 * TILE_SIZE), Math.toRadians(180)))
                                .waitSeconds(3) // intake
                                .lineToSplineHeading(new Pose2d(startPose.getX() + (0.5 * TILE_SIZE) + 23, startPose.getY()-(1 * TILE_SIZE), Math.toRadians(180)))
                                .waitSeconds(1) // depo
                                .lineToSplineHeading(new Pose2d(startPose.getX() - (2.9 * TILE_SIZE) + 23, startPose.getY()-(1 * TILE_SIZE), Math.toRadians(180)))
                                .turn(0.3)
                                .waitSeconds(3) // intake     30.85
                                .turn(-0.3)
                                .lineToSplineHeading(new Pose2d(startPose.getX() + (0.5 * TILE_SIZE), startPose.getY()-(1 * TILE_SIZE), Math.toRadians(180)))
                                .waitSeconds(1) // depo
                                //.lineToSplineHeading(new Pose2d(startPose.getX() - (0.5 * TILE_SIZE) + 23, startPose.getY()-(2 * TILE_SIZE), Math.toRadians(180)))
                                //.lineToSplineHeading(new Pose2d(startPose.getX() - (2.3 * TILE_SIZE) + 23, startPose.getY()-(2 * TILE_SIZE), Math.toRadians(180)))
                                //.waitSeconds(3) // intake
                                //.lineToSplineHeading(new Pose2d(startPose.getX() - (0.5 * TILE_SIZE) + 23, startPose.getY()-(2 * TILE_SIZE), Math.toRadians(180)))
                                //.splineToConstantHeading(new Vector2d(startPose.getX() + (0.5 * TILE_SIZE) + 23, startPose.getY()-(1.3 * TILE_SIZE)), Math.toRadians(0))
                                //.waitSeconds(1) // depo
                                .build()

                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}