package org.firstinspires.ftc.teamcode.Autonomous;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingLeftAuton;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingRightAuton;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingRight;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.robotConstants;

@Autonomous(name="LeftBLueParkGoobTest")
public class LeftBlueParkGoobTest extends LinearOpMode {
    Pose2d myPose = new Pose2d(-63, 12, Math.toRadians(-180));
    private DcMotorEx frontRight, frontLeft, backRight, backLeft,intakeSlideMotor;;

    double mult = 1.0;
    double batteryVoltage;
    String voltageCategory;
    private ServoEx leftVirtualFourBar, rightVirtualFourBar, leftHand, rightHand;
    private Servo wristServo;
    double minAngle = 0;
    double maxAngle = 360;

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        intakeSlideMotor = hardwareMap.get(DcMotorEx.class, "intakeSlideMotor");

        intakeSlideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightVirtualFourBar = new SimpleServo(hardwareMap, "rightVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);
        leftVirtualFourBar = new SimpleServo(hardwareMap, "leftVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);

        wristServo = hardwareMap.get(Servo.class, "wristServo");

        leftHand = new SimpleServo(hardwareMap, "leftHand",0, 360, AngleUnit.DEGREES);
        rightHand = new SimpleServo(hardwareMap, "rightHand",0, 360, AngleUnit.DEGREES);
        leftHand.setInverted(true);
        rightHand.setInverted(true);



        Trajectory Traj1 = drive.trajectoryBuilder(myPose)
                .strafeRight(45)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(Traj1);
     //   drive.followTrajectory(Traj1);



            setWristNormal(.5);
            openClaw(.25);
            setV4bIntaking(.5);
            closeClaw(.25);
            setV4BInit(.25);

            setWristNormal(.25);
            setV4BOuttaking(.25);






        }



    private void closeClaw(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            leftHand.setPosition(robotConstants.Claw.leftClose);
            rightHand.setPosition(robotConstants.Claw.rightClose);
        }
    }

    private void openClaw(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            leftHand.setPosition(robotConstants.Claw.leftOpen);
            rightHand.setPosition(robotConstants.Claw.rightOpen);
        }
    }

    private void openLeftClaw(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            leftHand.setPosition(robotConstants.Claw.leftOpen);
        }

    }
    private void openRightClaw(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            rightHand.setPosition(robotConstants.Claw.rightOpen);
        }
    }



    private void setV4BInit(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            leftVirtualFourBar.setPosition(initLeft);
            rightVirtualFourBar.setPosition(initRight);
        }
    }

    private void setV4bIntaking(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            leftVirtualFourBar.setPosition(intakingLeftAuton);
            rightVirtualFourBar.setPosition(intakingRightAuton);
        }
    }

    private void setV4BOuttaking(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            leftVirtualFourBar.setPosition(outtakingLeft);
            rightVirtualFourBar.setPosition(outtakingRight);
        }
    }

    private void setWristNormal(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()){
            wristServo.setPosition(robotConstants.Wrist.wristNormal);
        }
    }

    private void setWristSideways(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            wristServo.setPosition(robotConstants.Wrist.wristSideways);
        }
    }

    public void retractSlide(double sec){
        double timer = (getRuntime() + sec);
        while(timer > getRuntime()) {
            intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.retracted);

            intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            intakeSlideMotor.setPower(.5);

        }
    }
    public void stopMotors(){
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

}