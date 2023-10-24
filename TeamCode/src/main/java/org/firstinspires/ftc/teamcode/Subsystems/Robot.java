package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;

public class Robot {
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum drivetrain;
    public Wrist wrist;
    public virtualFourBar virtualFourBar;

    public intakeSlidesState intakeSlidesState;
    public outtakeSlidesState outtakeSlidesState;
    public wristState wristState;
    public virtualFourBarState virtualFourBarState;
    public extensionState extensionState;


    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        //arm = new Arm(hardwareMap);
        //claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        virtualFourBar = new virtualFourBar(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);


    }

    public void setIntakeSlideState(intakeSlidesState intakeSlidesState, extensionState extensionState)
    {
        intakeSlide.setPosition(extensionState,intakeSlidesState);
    }
    public void setOuttakeSlidesState(outtakeSlidesState outtakeSlidesState, extensionState extensionState){
        outtakeSlide.setOuttakeSlidePosition(extensionState,outtakeSlidesState);
    }

    public void setExtensionState(extensionState extensionState)
    {
        this.extensionState = extensionState;
    }
    public void setWristPosition(wristState wristState){
        wrist.setWristPosition(wristState);
    }
    public void setVirtualFourBarPosition(virtualFourBarState virtualFourBarState){
        virtualFourBar.setVirtualFourBarPosition(virtualFourBarState);
    }

    public intakeSlidesState getIntakeState()
    {

        return intakeSlidesState;
    }
    public outtakeSlidesState getOuttakeState(){
        return outtakeSlidesState;
    }

    public extensionState getExtensionState()
    {

        return extensionState;
    }
    public double getIntakeSlidePosition(){
        return intakeSlide.getIntakeSlidePosition();
    }
    public double getOuttakeLeftSlidePosition(){
        return outtakeSlide.getLeftOuttakeSlideMotorPosition();
    }
    public double getOuttakeRightSlidePosition(){
        return outtakeSlide.getRightOuttakeSlideMotorPosition();
    }

    public double getWristPosition(){
        return wrist.getWristPosition();
    }




}



