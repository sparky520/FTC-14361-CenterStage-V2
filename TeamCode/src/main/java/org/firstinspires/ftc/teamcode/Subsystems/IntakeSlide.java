package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.IntakeSlidesState;
import org.firstinspires.ftc.teamcode.Utilities.RobotConstants;

public class IntakeSlide
{
    DcMotorEx intakeSlideMotor;
    private final int countsPerRev = 384;
    double power = .7;

    public IntakeSlide(HardwareMap hardwareMap) {

        intakeSlideMotor = hardwareMap.get(DcMotorEx.class, "intakeSlideMotor");

        intakeSlideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeSlideMotor.setTargetPositionTolerance(5);

        intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setPosition(ExtensionState extensionState, IntakeSlidesState inExtendState)
    {
        switch(extensionState)
        {
            case retracted:
                break;
            case extending:
                switch(inExtendState)
                {
                    case HIGHIN:
                        intakeSlideMotor.setTargetPosition(RobotConstants.IntakeSlide.HIGHEXTENSION);
                        intakeSlideMotor.setTargetPositionTolerance(5);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        break;
                    case MEDIUMIN:
                        intakeSlideMotor.setTargetPosition(RobotConstants.IntakeSlide.MEDIUMEXTENSION);
                        intakeSlideMotor.setTargetPositionTolerance(5);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        break;
                    case STATION:
                        intakeSlideMotor.setTargetPosition(RobotConstants.IntakeSlide.RETRACTED);
                        intakeSlideMotor.setTargetPositionTolerance(5);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        break;
                }
            case extended:
                break;
        }
    }

    public double getIntakeSlidePosition()
    {
        return intakeSlideMotor.getCurrentPosition();
    }
}
