package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.util.robotConstants;



public class OuttakeSlide {
    DcMotorEx rightOuttakeSlide, leftOuttakeSlide;
    private final int countsPerRev = 384;
    double power = .9;


    public OuttakeSlide(HardwareMap hardwareMap) {
        rightOuttakeSlide = hardwareMap.get(DcMotorEx.class, "rightOuttakeSlide");
        leftOuttakeSlide = hardwareMap.get(DcMotorEx.class, "leftOuttakeSlide");


        rightOuttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftOuttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftOuttakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        rightOuttakeSlide.setTargetPositionTolerance(5);
        leftOuttakeSlide.setTargetPositionTolerance(5);
    }

    public void setOuttakeSlidePosition(extensionState extensionState, outtakeSlidesState outtakeSlidesState) {
        switch (extensionState) {
            case retracted:
                break;
            case extending: {
                switch (outtakeSlidesState) {
                    case HIGHOUT:
                        leftOuttakeSlide.setTargetPosition(robotConstants.OuttakeSlide.HIGHLEFT);
                        rightOuttakeSlide.setTargetPosition(robotConstants.OuttakeSlide.HIGHRIGHT);

                        leftOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftOuttakeSlide.setPower(power);
                        rightOuttakeSlide.setPower(power);

                        extensionState = extensionState.extended;
                        break;
                    case MEDIUMOUT:
                        leftOuttakeSlide.setTargetPosition(robotConstants.OuttakeSlide.MEDIUMLEFT);
                        rightOuttakeSlide.setTargetPosition(robotConstants.OuttakeSlide.MEDIUMRIGHT);

                        leftOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        extensionState = extensionState.extended;

                        leftOuttakeSlide.setPower(power);
                        rightOuttakeSlide.setPower(power);
                        break;
                    case STATION:
                        leftOuttakeSlide.setTargetPosition(robotConstants.OuttakeSlide.GROUNDLEFT);
                        rightOuttakeSlide.setTargetPosition(robotConstants.OuttakeSlide.GROUNDRIGHT);

                        leftOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        extensionState = extensionState.retracted;

                        leftOuttakeSlide.setPower(power);
                        rightOuttakeSlide.setPower(power);
                        break;
                }
            }
            case extended:
                break;
        }
    }
    public double getLeftOuttakeSlideMotorPosition () {
        double position = leftOuttakeSlide.getCurrentPosition();
        return position;
    }
    public double getRightOuttakeSlideMotorPosition(){
        double position = rightOuttakeSlide.getCurrentPosition();
        return position;
    }
}

