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
        public static int REGION_WIDTH = 50;
        public static int REGION_HEIGHT = 40;

        // Color definitions
        private final Scalar WHITE = new Scalar(255, 255, 255);

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

    Scalar leftSumColors = new Scalar(0, 0, 0); // Initialize with default values
    Scalar middleSumColors = new Scalar(0, 0, 0); // Initialize with default values

    @Override
    public Mat processFrame(Mat input) {

        // Convert the input Mat to HSV color space
        Imgproc.cvtColor(input, input, Imgproc.COLOR_BGR2HSV);

        // Get the submat frame, and then sum all the values
        Mat leftAreaMat = input.submat(new Rect(LEFT_left_pointA, LEFT_left_pointB));
        leftSumColors = Core.sumElems(leftAreaMat);

        Mat middleAreaMat = input.submat(new Rect(MIDDLE_left_pointA, MIDDLE_left_pointB));
        middleSumColors = Core.sumElems(middleAreaMat);

        // Assuming HSV format, adjust the blue color range accordingly
        double blueComponentLeft = leftSumColors.val[0];
        double blueComponentMiddle = middleSumColors.val[0];

        // Adjust the blue color range in HSV space as needed
        double lowerBlueHue = 90; // Example lower hue value for blue
        double upperBlueHue = 120; // Example upper hue value for blue

        // Check if the blue hue is within the specified range
        if (blueComponentLeft >= lowerBlueHue && blueComponentLeft <= upperBlueHue) {
            position = TSEPosition.MIDDLE;
            Imgproc.rectangle(input, LEFT_left_pointA, LEFT_left_pointB, WHITE, 3);

        } else {
            Imgproc.rectangle(input, LEFT_left_pointA, LEFT_left_pointB, BLUE, 3);
        }

        // Check if the blue hue is within the specified range for the middle rectangle
        if (blueComponentMiddle >= lowerBlueHue && blueComponentMiddle <= upperBlueHue) {
            position = TSEPosition.LEFT;
            Imgproc.rectangle(input, MIDDLE_left_pointA, MIDDLE_left_pointB, WHITE, 3);

        } else {
            Imgproc.rectangle(input, MIDDLE_left_pointA, MIDDLE_left_pointB, BLUE, 3);
        }

        // Release Mat objects
        leftAreaMat.release();
        middleAreaMat.release();

        // Return the modified input Mat
        return input;
    }


    // Returns an enum being the current position where the robot will park
        public TSEPosition getPosition() {
            return position;
        }

        public double getLeftBoxBlueReading(){
            return leftSumColors.val[0];
        }

        public double getMiddleBoxBlueReading(){
            return middleSumColors.val[0];
        }



}
