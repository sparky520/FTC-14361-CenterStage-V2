package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.ClawState;
import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.IntakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.VirtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.WristState;
import org.firstinspires.ftc.teamcode.Commands.VirtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.ActiveIntakeState;



public class Robot {
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum driveTrain;
    public Wrist wrist;
    public Claw claw;
    public VirtualFourBar virtualFourBar;

    public IntakeSlidesState intakeSlidesState;
    public OuttakeSlidesState outtakeSlidesState;
    public WristState wristState;
    public ClawState clawState, leftClawState, rightClawState;
    public VirtualFourBarState virtualFourBarState;
    public VirtualFourBarExtensionState virtualFourBarExtensionState;
    public ExtensionState extensionState;
    public ActiveIntake activeIntake;
    public ActiveIntakeState activeIntakeState;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;

        driveTrain = new Mecanum(hardwareMap);
        claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        virtualFourBar = new VirtualFourBar(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);
        activeIntake = new ActiveIntake(hardwareMap);
    }

    public void setIntakeSlidePosition(IntakeSlidesState intakeSlidesState, ExtensionState extensionState)
    {
        intakeSlide.setPosition(extensionState,intakeSlidesState);
    }
    public IntakeSlidesState getIntakeSlideState()
    {
        return intakeSlidesState;
    }

    public void setIntakeSlideState(IntakeSlidesState intakeSlidesState)
    {
        this.intakeSlidesState = intakeSlidesState;
    }

    public double getIntakeSlidePosition()
    {
        return intakeSlide.getIntakeSlidePosition();
    }

    public void setOuttakeSlidePosition(OuttakeSlidesState outtakeSlidesState, ExtensionState extensionState)
    {
        outtakeSlide.setOuttakeSlidePosition(extensionState,outtakeSlidesState);
    }

    public OuttakeSlidesState getOuttakeState()
    {
        return outtakeSlidesState;
    }

    public void setOuttakeSlidesState(OuttakeSlidesState outtakeSlidesState)
    {
        this.outtakeSlidesState = outtakeSlidesState;
    }

    public void setExtensionState(ExtensionState extensionState)
    {
        this.extensionState = extensionState;
    }

    public ExtensionState getExtensionState()
    {
        return extensionState;
    }

    public double getOuttakeLeftSlidePosition()
    {
        return outtakeSlide.getLeftOuttakeSlideMotorPosition();
    }

    public double getOuttakeRightSlidePosition()
    {
        return outtakeSlide.getRightOuttakeSlideMotorPosition();
    }

    public void setWristPosition(WristState wristState)
    {
        wrist.setWristPosition(wristState);
    }

    public void setWristState(WristState wristState)
    {
        this.wristState = wristState;
    }

    public WristState getWristState()
    {
        return wristState;
    }

    public double getWristPosition()
    {
        return wrist.getWristPosition();
    }

    public void setVirtualFourBarPosition(VirtualFourBarState virtualFourBarState, VirtualFourBarExtensionState virtualFourBarExtensionState)
    {
        virtualFourBar.setVirtualFourBarPosition(virtualFourBarState, virtualFourBarExtensionState);
    }

    public void setVirtualFourBarState(VirtualFourBarState virtualFourBarState)
    {
        this.virtualFourBarState = virtualFourBarState;
    }

    public VirtualFourBarState getVirtualFourBarState()
    {
        return virtualFourBarState;
    }

    public VirtualFourBarExtensionState getVirtualFourBarExtensionState()
    {
        return virtualFourBarExtensionState;
    }

    public void setVirtualFourBarExtensionState(VirtualFourBarExtensionState virtualFourBarExtensionState)
    {
        this.virtualFourBarExtensionState = virtualFourBarExtensionState;
    }

    public void setClawPosition(ClawState clawState)
    {
        claw.setClawPosition(clawState);
    }

    public void setOpenLeftClawPosition()
    {
        claw.leftOpen();
    }

    public void setOpenRightClawPosition()
    {
        claw.rightOpen();
    }

    public void setCloseLeftClawPosition()
    {
        claw.leftClose();
    }

    public void setCloseRightClawPosition()
    {
        claw.rightClose();
    }

    public void setClawState(ClawState clawState)
    {
        this.clawState = clawState;
    }

    public void setLeftClawState(ClawState leftClawState)
    {
        this.leftClawState = leftClawState;
    }

    public void setRightClawState(ClawState rightClawState)
    {
        this.rightClawState = rightClawState;
    }

    public ClawState getClawState()
    {
        return clawState;
    }

    public ClawState getLeftClawState()
    {
        return leftClawState;
    }

    public ClawState getRightClawState()
    {
        return rightClawState;
    }

    public void setActiveIntakePosition(ActiveIntakeState activeIntakeState)
    {
        activeIntake.setActiveIntakePosition(activeIntakeState);
    }

    public ActiveIntakeState getActiveIntakeState()
    {
        return activeIntakeState;
    }

    public void setActiveIntakeState(ActiveIntakeState activeIntakeState)
    {
        this.activeIntakeState = activeIntakeState;
    }



}



