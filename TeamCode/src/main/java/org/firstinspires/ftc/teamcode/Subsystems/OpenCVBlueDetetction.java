package org.firstinspires.ftc.teamcode.Subsystems;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
public class OpenCVBlueDetetction extends OpenCvPipeline {

        public enum TSEPosition {
            LEFT,
            MIDDLE,
            RIGHT
        }

        // TOPLEFT anchor point for the bounding box
        private static Point LEFTBOX_TOPLEFT_ANCHOR_POINT = new Point(50, 135); //I think this is the actual one we edit! Increase x goes right, increase y goes down.

        private static Point MIDDLEBOX_TOPLEFT_ANCHOR_POINT = new Point(200, 155); //I think this is the actual one we edit! Increase x goes right, increase y goes down.

    // Width and height for the bounding box
        public static int REGION_WIDTH = 40;
        public static int REGION_HEIGHT = 40;

        // Color definitions
        private final Scalar WHITE = new Scalar(0, 0, 255);

        private final Scalar BLUE = new Scalar(0, 0, 255);

        // Anchor point definitions
        Point LEFT_left_pointA = new Point(
                LEFTBOX_TOPLEFT_ANCHOR_POINT.x,
                LEFTBOX_TOPLEFT_ANCHOR_POINT.y);
        Point LEFT_left_pointB = new Point(
                LEFTBOX_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                LEFTBOX_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        Point MIDDLE_left_pointA = new Point(
                MIDDLEBOX_TOPLEFT_ANCHOR_POINT.x,
                MIDDLEBOX_TOPLEFT_ANCHOR_POINT.y);
        Point MIDDLE_left_pointB = new Point(
            MIDDLEBOX_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            MIDDLEBOX_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        // Running variable storing the parking position
        private volatile TSEPosition position = TSEPosition.RIGHT;

        @Override
        public Mat processFrame(Mat input) {
            // Get the submat frame, and then sum all the values
            Mat leftAreaMat = input.submat(new Rect(LEFT_left_pointA, LEFT_left_pointB));
            Scalar leftSumColors = Core.sumElems(leftAreaMat);

            Mat middleAreaMat = input.submat(new Rect(MIDDLE_left_pointA, MIDDLE_left_pointB));
            Scalar middleSumColors = Core.sumElems(middleAreaMat);

            // Get the minimum RGB value from every single channel
            double leftMinColor = Math.max(leftSumColors.val[0], 0.3);

            double middleMinColor = Math.max(middleSumColors.val[0], 0.3);

            // Change the bounding box color based on the sleeve color
            if (leftSumColors.val[0] == leftMinColor) {
                position = TSEPosition.LEFT;
                Imgproc.rectangle(
                        input,
                        LEFT_left_pointA,
                        LEFT_left_pointB,
                        BLUE,
                        3
                );

            }
            else{
                position = TSEPosition.LEFT;
                Imgproc.rectangle(
                        input,
                        LEFT_left_pointA,
                        LEFT_left_pointB,
                        WHITE,
                        3
                );
            }

            if (middleSumColors.val[0] == middleMinColor) {
                position = TSEPosition.MIDDLE;
                Imgproc.rectangle(
                        input,
                        MIDDLE_left_pointA,
                        MIDDLE_left_pointB,
                        BLUE,
                        3
                );

            }
            else{
                position = TSEPosition.MIDDLE;
                Imgproc.rectangle(
                        input,
                        MIDDLE_left_pointA,
                        MIDDLE_left_pointB,
                        WHITE,
                        3
                );
            }

            // Release and return input
            leftAreaMat.release();
            return input;
        }

        // Returns an enum being the current position where the robot will park
        public TSEPosition getPosition() {
            return position;
        }

}
