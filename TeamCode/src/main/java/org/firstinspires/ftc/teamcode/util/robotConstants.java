package org.firstinspires.ftc.teamcode.util;

public class robotConstants
{
    public static class outtakeSlide
    {
        public static double P = 0.0;
        public static double I = 0.0;
        public static double D = 0.0;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;


        public static int HIGHLEFT = -1200;
        public static int HIGHRIGHT = 1200;
        //sometimes the encoder values go negative so you might have to change those

        public static int MEDIUMLEFT = -900;
        public static int MEDIUMRIGHT = 900;

        public static int LOWLEFT = -450;
        public static int LOWRIGHT = 450;


        public static int GROUNDLEFT = 0;
        public static int GROUNDRIGHT = 0;
    }

    public static class intakeSlide
    {
        public static double P = 0.5;
        public static double I = 0.5;
        public static double D = 0.5;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;


        public static int highExtension = 700;
        public static int mediumExtension = 380;


//        public static int fullExtension = -3600;
//        public static int mediumExtension = -1200;
//        public static int shortExtension = -500;

        // first comp length below

        public static int lowExtension = 0;
        public static int retracted = 0;
    }

    public static class Claw
    {
        public static double intakeAuto = 0.0;
        public static double intakeTeleOp = 0.0;

        public static double leftClose = .7;
        public static double rightClose = .3;
        public static double leftOpen = .53;
        public static double rightOpen = .58;

        public static double outWristRotation = 0.0;
        public static double sideWristRotation = 0.0;
    }

    public static class Arm
    {
        public static double outtake = 0.0;
        public static double intake = 0.0;
    }

    public static class activeIntake
    {

        public static double active = -0.3;
        public static double reverseActive = 0.3;

    }

    public static class Climb
    {
        public static int climbPosition = 0;
    }

    public static class Wrist
    {
        public static double wristSideways = 1;
        public static double wristNormal = 0.67;

        // scale is from .33 (0) to .67 (1) so there no overshoot at all

        // side: .33
        // norm: .67
    }

    public static class virtualFourBar
    {
        public static double intakingLeft = 0.65;
        //.486
        //.588
        public static double intakingRight = .61;

        public static double outtakingLeft = 0.26;
        public static double outtakingRight = 0.22;

        public static double initLeft = .54;
        //.45
        //.55
        public static double initRight = .5;

        public static double intakingLeftAuton = 0.5;
        public static double intakingRightAuton = 0.6;




        // right: .58
        // left: .41

        /*
        Robot is EVEN at both sides at:

        L: .64
        R: .45
         */
    }
}