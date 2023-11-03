package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;



public class Robot {
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum drivetrain;
    public Wrist wrist;
    public Claw claw;
    public virtualFourBar virtualFourBar;

    public intakeSlidesState intakeSlidesState;
    public outtakeSlidesState outtakeSlidesState;
    public wristState wristState;
    public clawState clawState;
    public virtualFourBarState virtualFourBarState;
    public virtualFourBarExtensionState virtualFourBarExtensionState;
    public extensionState extensionState;
    public activeIntake activeIntake;
    public activeIntakeState activeIntakeState;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        //arm = new Arm(hardwareMap);
        claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        virtualFourBar = new virtualFourBar(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);


    }

    public void setIntakeSlidePosition(intakeSlidesState intakeSlidesState, extensionState extensionState)
    {
        intakeSlide.setPosition(extensionState,intakeSlidesState);
    }
    public intakeSlidesState getIntakeSlideState()
    {
        return intakeSlidesState;
    }

    public void setIntakeSlideState(intakeSlidesState intakeSlidesState){
        this.intakeSlidesState = intakeSlidesState;
    }
    public double getIntakeSlidePosition(){

        return intakeSlide.getIntakeSlidePosition();
    }

    public void setOuttakeSlidePosition(outtakeSlidesState outtakeSlidesState, extensionState extensionState)
    {
        outtakeSlide.setOuttakeSlidePosition(extensionState,outtakeSlidesState);
    }

    public outtakeSlidesState getOuttakeState()
    {
        return outtakeSlidesState;
    }
    public void setOuttakeSlidesState(outtakeSlidesState outtakeSlidesState)
    {
        this.outtakeSlidesState = outtakeSlidesState;
    }


    public void setExtensionState(extensionState extensionState)
    {
        this.extensionState = extensionState;
    }
    public extensionState getExtensionState()
    {
        return extensionState;
    }

    public double getOuttakeLeftSlidePosition(){
        return outtakeSlide.getLeftOuttakeSlideMotorPosition();
    }
    public double getOuttakeRightSlidePosition(){
        return outtakeSlide.getRightOuttakeSlideMotorPosition();
    }

    public void setWristPosition(wristState wristState){
        wrist.setWristPosition(wristState);
    }
    public void setWristState(wristState wristState){
        this.wristState = wristState;
    }
    public wristState getWristState(){
        return wristState;
    }
    public double getWristPosition(){
        return wrist.getWristPosition();
    }


    public void setVirtualFourBarPosition(virtualFourBarState virtualFourBarState, virtualFourBarExtensionState virtualFourBarExtensionState){
        virtualFourBar.setVirtualFourBarPosition(virtualFourBarState, virtualFourBarExtensionState);

    }

    public void setVirtualFourBarState(virtualFourBarState virtualFourBarState){
        this.virtualFourBarState = virtualFourBarState;
    }

    public virtualFourBarState getVirtualFourBarState(){
        return virtualFourBarState;
    }
    public virtualFourBarExtensionState getVirtualFourBarExtensionState(){
        return virtualFourBarExtensionState;
    }
    public void setVirtualFourBarExtensionState(virtualFourBarExtensionState virtualFourBarExtensionState){
        this.virtualFourBarExtensionState = virtualFourBarExtensionState;
    }



    public void setClawPosition(clawState clawState){

        claw.setClawPosition(clawState);
    }
    public void setClawState(clawState clawState){
        this.clawState= clawState;
    }

    public clawState getClawState(){
        return clawState;
    }

    public void setActiveIntake(activeIntakeState activeIntakeState){
        activeIntake.setActiveIntakePosition(activeIntakeState);
    }

    public activeIntakeState getActiveIntakeState(){
        return activeIntakeState;
    }

    public void setActiveIntakeState(activeIntakeState activeIntakeState){
        this.activeIntakeState = activeIntakeState;
    }



}



