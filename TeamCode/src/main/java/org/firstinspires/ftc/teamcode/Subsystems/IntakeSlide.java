package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.util.robotConstants;

public class IntakeSlide {
    DcMotorEx intakeSlideMotor;
    private final int countsPerRev = 384;
    double power = .7;

    public IntakeSlide(HardwareMap hardwareMap) {

        intakeSlideMotor = hardwareMap.get(DcMotorEx.class, "intakeSlideMotor");

        intakeSlideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        //Stop and reset encoders doesnt work?
        intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }
    public void setPosition(extensionState extensionState, intakeSlidesState inExtendState)
    {
        switch(extensionState){
            case retracted:

                break;
            case extending:
                switch(inExtendState){
                    case HIGHIN:
                        intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.highExtension);

                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);



                        intakeSlideMotor.setPower(power);
                        // setPIDMotorPower(robotConstants.IntakeSlide.fullExtension);

                        extensionState = extensionState.extended;
                        break;
                    case MEDIUMIN:
                        intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.mediumExtension);

                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                        intakeSlideMotor.setPower(power);

                        //setPIDMotorPower(robotConstants.IntakeSlide.mediumExtension);
                        extensionState = extensionState.extended;

                        break;
                    case STATION:
                        intakeSlideMotor.setTargetPosition(robotConstants.intakeSlide.retracted);

                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        //PIDMotorPower(robotConstants.IntakeSlide.retracted);


                        extensionState = extensionState.retracted;
                        break;


                }
            case extended:
                break;
        }
    }

    public void setPosition(int pos)
    {
        intakeSlideMotor.setTargetPosition(pos);
      
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        intakeSlideMotor.setPower(power);
        // setPIDMotorPower(robotConstants.IntakeSlide.fullExtension);
    }

    public double getIntakeSlidePosition(){
        double position = intakeSlideMotor.getCurrentPosition();
        return position;
    }

    public void forceSlidePosition(){
        intakeSlideMotor.setTargetPosition(-8);
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intakeSlideMotor.setPower(1);
    }



}