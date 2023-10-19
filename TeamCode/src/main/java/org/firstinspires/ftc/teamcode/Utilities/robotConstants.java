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

        public static int HIGH = 0;
        public static int MEDIUM = 0;
        public static int LOW = 0;
        public static int GROUND = 0;
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
        public static double close = 0.0;
        public static double open = 0.0;
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
}