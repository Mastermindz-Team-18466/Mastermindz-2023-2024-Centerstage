package org.firstinspires.ftc.teamcode.opencv;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class SpikemarkDetection implements VisionProcessor {
    private final Mat mat = new Mat();
    Telemetry telemetry;
    public enum SpikemarkPosition {
        DEFAULT,
        ONE,
        TWO,
        THREE
    }

    public SpikemarkPosition position = SpikemarkPosition.DEFAULT;

    public SpikemarkDetection(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2BGR);

        Mat matLeft = frame.submat(0, 240, 0, 106);
        Mat matCenter = frame.submat(0, 240, 106, 213);
        Mat matRight = frame.submat(0, 240, 213, 320);

        Imgproc.rectangle(frame, new Rect(0, 0, 106, 240), new Scalar(0, 255, 0));
        Imgproc.rectangle(frame, new Rect(106, 0, 107, 240), new Scalar(0, 255, 0));
        Imgproc.rectangle(frame, new Rect(213, 0, 106, 240), new Scalar(0, 255, 0));

        double leftTotal = Core.sumElems(matLeft).val[0];
        double centerTotal = Core.sumElems(matCenter).val[0];
        double rightTotal = Core.sumElems(matRight).val[0];

        if (leftTotal < centerTotal && leftTotal < rightTotal) {
            position = SpikemarkPosition.ONE;
            telemetry.addData("Position", "ONE");
        }

        else if (centerTotal < rightTotal && centerTotal < leftTotal) {
            position = SpikemarkPosition.TWO;
            telemetry.addData("Position", "TWO");
        }

        else if (rightTotal < centerTotal && rightTotal < leftTotal) {
            position = SpikemarkPosition.THREE;
            telemetry.addData("Position", "THREE");
        }

        telemetry.addData("CenterTotal", centerTotal);
        telemetry.addData("RightTotal", rightTotal);
        telemetry.addData("LeftTotal", leftTotal);

        telemetry.update();

        return null;
    }

    public SpikemarkPosition getPosition() {
        return position;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
    }

}