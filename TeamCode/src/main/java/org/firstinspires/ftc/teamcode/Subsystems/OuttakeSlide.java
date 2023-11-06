package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeSlidesState;
import org.firstinspires.ftc.teamcode.Utilities.RobotConstants;



public class OuttakeSlide
{
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

    public void setOuttakeSlidePosition(ExtensionState extensionState, OuttakeSlidesState outtakeSlidesState)
    {
        switch(extensionState)
        {
            case retracted:
                break;
            case extending:
            {
                switch (outtakeSlidesState)
                {
                    case HIGHOUT:
                        leftOuttakeSlide.setTargetPosition(RobotConstants.OuttakeSlide.HIGHLEFT);
                        rightOuttakeSlide.setTargetPosition(RobotConstants.OuttakeSlide.HIGHRIGHT);

                        leftOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftOuttakeSlide.setPower(power);
                        rightOuttakeSlide.setPower(power);
                        break;
                    case MEDIUMOUT:
                        leftOuttakeSlide.setTargetPosition(RobotConstants.OuttakeSlide.MEDIUMLEFT);
                        rightOuttakeSlide.setTargetPosition(RobotConstants.OuttakeSlide.MEDIUMRIGHT);

                        leftOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftOuttakeSlide.setPower(power);
                        rightOuttakeSlide.setPower(power);
                        break;
                    case STATION:
                        leftOuttakeSlide.setTargetPosition(RobotConstants.OuttakeSlide.GROUNDLEFT);
                        rightOuttakeSlide.setTargetPosition(RobotConstants.OuttakeSlide.GROUNDRIGHT);

                        leftOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightOuttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftOuttakeSlide.setPower(power);
                        rightOuttakeSlide.setPower(power);
                        break;
                }
            }
            case extended:
                break;
        }
    }
    public double getLeftOuttakeSlideMotorPosition()
    {
        return leftOuttakeSlide.getCurrentPosition();
    }

    public double getRightOuttakeSlideMotorPosition()
    {
        return rightOuttakeSlide.getCurrentPosition();
    }
}

