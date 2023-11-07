package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Utilities.robotConstants;




public class outtakeSlide
{
    DcMotorEx rightouttakeSlide, leftouttakeSlide;
    private final int countsPerRev = 384;
    double power = .9;


    public outtakeSlide(HardwareMap hardwareMap) {
        rightouttakeSlide = hardwareMap.get(DcMotorEx.class, "rightouttakeSlide");
        leftouttakeSlide = hardwareMap.get(DcMotorEx.class, "leftouttakeSlide");

        rightouttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftouttakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightouttakeSlide.setTargetPositionTolerance(5);
        leftouttakeSlide.setTargetPositionTolerance(5);
    }

    public void setOuttakeSlidePosition(extensionState extensionState, outtakeSlidesState outtakeSlidesState)
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
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.HIGHLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.HIGHRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                    case MEDIUMOUT:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.MEDIUMLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.MEDIUMRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                    case STATION:
                        leftouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.GROUNDLEFT);
                        rightouttakeSlide.setTargetPosition(robotConstants.outtakeSlide.GROUNDRIGHT);

                        leftouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        rightouttakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        leftouttakeSlide.setPower(power);
                        rightouttakeSlide.setPower(power);
                        break;
                }
            }
            case extended:
                break;
        }
    }
    public double getLeftouttakeSlideMotorPosition()
    {
        return leftouttakeSlide.getCurrentPosition();
    }

    public double getRightouttakeSlideMotorPosition()
    {
        return rightouttakeSlide.getCurrentPosition();
    }
}

