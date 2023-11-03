package org.firstinspires.ftc.teamcode.Utilities;

public class robotConstants
{
    public static class OuttakeSlide
    {
        public static double P = 0.0;
        public static double I = 0.0;
        public static double D = 0.0;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;

        public static int HIGHLEFT = -1700;
        public static int MEDIUMLEFT = -1100;
        public static int LOWLEFT = 0;
        public static int GROUNDLEFT = 0;
        public static int HIGHRIGHT = 1700;
        public static int MEDIUMRIGHT = 1100;
        public static int LOWRIGHT = 0;
        public static int GROUNDRIGHT = 0;
    }

    public static class IntakeSlide
    {
        public static double P = 0.5;
        public static double I = 0.5;
        public static double D = 0.5;

        public static double pulleyCircumference = 0.0;
        public static double ticksPerRevolution = 0.0;

        public static int fullExtension = -3800;
        public static int mediumExtension = -2200;
        public static int shortExtension = 0;
        public static int retracted = 0;
    }

    public static class Claw
    {
        public static double intakeAuto = 0.0;
        public static double intakeTeleOp = 0.0;

        public static double leftClose = .3;
        public static double rightClose = .7;
        public static double leftOpen = 0;
        public static double rightOpen = 1;

        public static double outWristRotation = 0.0;
        public static double sideWristRotation = 0.0;
    }

    public static class Arm
    {
        public static double outtake = 0.0;
        public static double intake = 0.0;
    }

    public static class ActiveIntake
    {
        public static int active = 0;
        public static int deactivate = 0;
    }

    public static class Drone
    {
        public static double launchPosition = 0.0;
        public static  double loadedPosition = 0.0;
    }

    public static class Climb
    {
        public static int climbPosition = 0;
    }
    public static class Wrist{
        public static double wristSideways = .35;
        public static double wristNormal = 0;
    }
    public static class VirtualFourBar{
        public static double intakingLeft = 0;
        public static double intakingRight = 0;

        public static double outtakingRight = .5;

        public static double outtakingLeft = .5;

        public static double initLeft = .7;
        public static double initRight = .8;


        //.1 AND 0 are lined up
    }
}