package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;

public class Robot {
    public IntakeSlide intakeSlide;
    public Mecanum drivetrain;

    public intakeSlidesState intakeSlidesState;
    public extensionState extensionState;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        //arm = new Arm(hardwareMap);
        //claw = new Claw(hardwareMap);
        //wrist = new Wrist(hardwareMap);
        //  outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);

    }

    public void setIntakeState(intakeSlidesState intakeState, extensionState extensionState)
    {
        intakeSlide.setPosition(extensionState,intakeState);
    }

    public void setExtensionState(extensionState extensionState)
    {
        this.extensionState = extensionState;
    }

    public intakeSlidesState getIntakeState()
    {
        return intakeSlidesState;
    }

    public extensionState getExtensionState()
    {
        return extensionState;
    }




}



