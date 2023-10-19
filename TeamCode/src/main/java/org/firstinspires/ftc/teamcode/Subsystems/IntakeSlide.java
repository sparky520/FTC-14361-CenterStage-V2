package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Utilities.robotConstants;

public class IntakeSlide {
    DcMotor intakeSlideMotor;
    private final int countsPerRev = 384;
    double power = .7;

    public IntakeSlide(HardwareMap hardwareMap) {

        intakeSlideMotor = hardwareMap.get(DcMotor.class, "intakeSlideMotor");

        intakeSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Stop and reset encoders doesnt work?
        //intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }
    public void setPosition(extensionState extensionState, intakeSlidesState inExtendState)
    {
        switch(inExtendState){
            case retracted:
                intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.retracted);

                break;
            case extending:
                switch(extensionState){
                    case HIGHIN:
                        intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.fullExtension);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                        intakeSlideMotor.setPower(power);
                        // setPIDMotorPower(robotConstants.IntakeSlide.fullExtension);

                        inExtendState = inExtendState.extended;
                        break;
                    case MEDIUMIN:
                        intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.mediumExtension);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                        intakeSlideMotor.setPower(power);

                        //setPIDMotorPower(robotConstants.IntakeSlide.mediumExtension);
                        inExtendState = inExtendState.extended;

                        break;
                    case STATION:


                        intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.retracted);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        //PIDMotorPower(robotConstants.IntakeSlide.retracted);


                        inExtendState = inExtendState.retracted;
                        break;


                }
            case extended:
                break;
        }
    }

}
