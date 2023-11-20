package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

/*
******* NOTE *******
This detection is used for the FTC 2023-2024 CENTERSTAGE Challenge by the CPHS Robolobos (14361)

This code is heavily inspired and guided by team Wolf Corp Robotics 12525 (I love these people)

here's the vid that step by step saved my sanity & explains how this version of HSV color detection works:
https://www.youtube.com/watch?v=JO7dqzJi8lw&ab_channel=FTCteamWolfCorpRobotics12525

I've added some comments abt stuff- Good luck!
 */
public class HSVBlueDetetction extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location {
        LEFT,
        RIGHT,
        MIDDLE
    }
    private Location location;

    /*
    These create the rectangles that your TSE should be in-
    adjust these values by using the camera stream in the driver station
    to see where your camera points, and adjust the camera or the boxes so the TSE is inside

     */
    static final Rect LEFT_ROI = new Rect(
            new Point(60, 35),
            new Point(120, 75));
    static final Rect MIDDLE_ROI = new Rect(
            new Point(140, 35),
            new Point(200, 75));
    static double PERCENT_COLOR_THRESHOLD = 0.4;

    public HSVBlueDetetction(Telemetry t) { telemetry = t; }

    @Override
    public Mat processFrame(Mat input) {

        // changes the frame captured by the camera from RGB to HSV
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV_FULL); // Imgproc.COLOR_RGB2HSV & Imgproc.COLOR_RGB2HSV_FULL are the same; FULL is the scale 0-360 & normal is 0-180

        /*
        HSV = [ HUE (color), SATURATION (grey), VALUE (brightness) ]
        HSV has a low end & high end of the spectrum, and here we set the range of color we want

        how do i know what values to put in ???
        use this handy dandy link i found (please do not click on any links/ads, it is super sketchy)
        https://www.tydac.ch/color/
         */

        // *** don't forget to divide the values by 2 if you use Imgproc.COLOR_RBG2HSV

        // in this case, we using dark blue to light blue
        Scalar lowHSV = new Scalar(257, 94, 61);
        Scalar highHSV = new Scalar(177, 255, 242);


        // this shows us the stuff in our range (in this case blue)
        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_ROI);
        Mat right = mat.submat(MIDDLE_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / MIDDLE_ROI.area() / 255;

        left.release();
        right.release();

        telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");

        boolean stoneLeft = leftValue > PERCENT_COLOR_THRESHOLD;
        boolean stoneRight = rightValue > PERCENT_COLOR_THRESHOLD;

        if (stoneLeft && stoneRight) {
            location = Location.MIDDLE;
            telemetry.addData("Skystone Location", "not found");
        }
        else if (stoneLeft) {
            location = Location.RIGHT;
            telemetry.addData("Skystone Location", "right");
        }
        else {
            location = Location.LEFT;
            telemetry.addData("Skystone Location", "left");
        }
        telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Scalar colorStone = new Scalar(255, 0, 0);
        Scalar colorSkystone = new Scalar(0, 255, 0);

        Imgproc.rectangle(mat, LEFT_ROI, location == Location.LEFT? colorSkystone:colorStone);
        Imgproc.rectangle(mat, MIDDLE_ROI, location == Location.RIGHT? colorSkystone:colorStone);

        return mat;
    }

    public Location getLocation() {
        return location;
    }
}