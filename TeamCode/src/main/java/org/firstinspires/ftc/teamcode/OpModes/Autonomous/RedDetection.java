package org.firstinspires.ftc.teamcode.OpModes.Autonomous;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
public class RedDetection extends OpenCvPipeline {

    public enum TSEPosition {
        LEFT,
        MIDDLE,
        RIGHT
    }

    // TOPLEFT anchor point for the bounding box


    // BOTH OF THESE NEED TO BE CALIBRATED FOR RED !!!!!    
    private static Point RIGHTBOX_TOPLEFT_ANCHOR_POINT = new Point(50, 135); //I think this is the actual one we edit! Increase x goes right, increase y goes down.

    private static Point MIDDLEBOX_TOPLEFT_ANCHOR_POINT = new Point(200, 155); //I think this is the actual one we edit! Increase x goes right, increase y goes down.

    // Width and height for the bounding box
    public static int REGION_WIDTH = 40;
    public static int REGION_HEIGHT = 40;

    // Color definitions
    private final Scalar WHITE = new Scalar(255, 255, 255);

    private final Scalar RED = new Scalar(255, 0, 0);

    // Anchor point definitions
    Point RIGHT_left_pointA = new Point(
            RIGHTBOX_TOPLEFT_ANCHOR_POINT.x,
            RIGHTBOX_TOPLEFT_ANCHOR_POINT.y);
    Point RIGHT_left_pointB = new Point(
            RIGHTBOX_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            RIGHTBOX_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    Point MIDDLE_left_pointA = new Point(
            MIDDLEBOX_TOPLEFT_ANCHOR_POINT.x,
            MIDDLEBOX_TOPLEFT_ANCHOR_POINT.y);
    Point MIDDLE_left_pointB = new Point(
            MIDDLEBOX_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            MIDDLEBOX_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    // Running variable storing the parking position
    private volatile TSEPosition position = TSEPosition.LEFT;

    Scalar redSumColors, middleSumColors;

    private double redThreshold;
    // this can be called to show how much
    @Override
    public Mat processFrame(Mat input) {
        // Get the submat frame, and then sum all the values
        Mat rightAreaMat = input.submat(new Rect(RIGHT_left_pointA, RIGHT_left_pointB));
        redSumColors = Core.sumElems(rightAreaMat);

        Mat middleAreaMat = input.submat(new Rect(MIDDLE_left_pointA, MIDDLE_left_pointB));
        middleSumColors = Core.sumElems(middleAreaMat);

        // Threshold for blue color detection
        /*
        LOWERING THIS VALUE WILL LOWER THE THRESHOLD NEEDED TO DETECT THE COLOR BLUE

        too high, it will need a LOT of blue
        too low, it will say everything is blue

        to tune, increase/decrease it by 10 till you are satisfied

        lighting can and will effect this,
         */
        redThreshold = 150;

        // Check if the blue color is present in the left rectangle
        // val[] is an ArrayList, and the way the class passes the values of R G B,
        //
        if (redSumColors.val[0] > redThreshold) {
            position = TSEPosition.RIGHT;
            Imgproc.rectangle(
                    input,
                    RIGHT_left_pointA,
                    RIGHT_left_pointB,
                    RED,
                    3
            );
        }

        else {
            Imgproc.rectangle(
                    input,
                    RIGHT_left_pointA,
                    RIGHT_left_pointB,
                    WHITE,
                    3
            );
        }

        // Check if the blue color is present in the middle rectangle
        if (middleSumColors.val[0] > redThreshold) {
            position = TSEPosition.MIDDLE;
            Imgproc.rectangle(
                    input,
                    MIDDLE_left_pointA,
                    MIDDLE_left_pointB,
                    RED,
                    3
            );
        }

        else {
            // If blue is not detected in spot 2, set the outline to white
            Imgproc.rectangle(
                    input,
                    MIDDLE_left_pointA,
                    MIDDLE_left_pointB,
                    WHITE,
                    3
            );
        }


        // Release Mat objects
        rightAreaMat.release();
        middleAreaMat.release();

        // Return the modified input Mat
        return input;
    }


    // Returns an enum being the current position where the robot will park
    public TSEPosition getPosition() {
        return position;
    }

    public double getRightBoxRedReading(){
        return redSumColors.val[0];
    }

    public double getMiddleBoxRedReading(){
        return middleSumColors.val[0];
    }

    public double getRedThreshold(){
        return redThreshold;
    }

}