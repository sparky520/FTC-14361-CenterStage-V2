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
    public intakeSlide intakeSlide;
    public org.firstinspires.ftc.teamcode.Subsystems.outtakeSlide outtakeSlide;
    public Mecanum driveTrain;
    public Wrist wrist;
    public Claw claw;
    public org.firstinspires.ftc.teamcode.Subsystems.virtualFourBar virtualFourBar;

    public intakeSlidesState intakeSlidesState;
    public outtakeSlidesState outtakeSlidesState;
    public wristState wristState;
    public clawState clawState, leftclawState, rightclawState;
    public virtualFourBarState virtualFourBarState;
    public virtualFourBarExtensionState virtualFourBarExtensionState;
    public extensionState extensionState;
    public ActiveIntake activeIntake;
    public activeIntakeState activeIntakeState;

    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry)
    {
        this.telemetry = telemetry;

        driveTrain = new Mecanum(hardwareMap);
        claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        virtualFourBar = new virtualFourBar(hardwareMap);
        outtakeSlide = new outtakeSlide(hardwareMap);
        intakeSlide = new intakeSlide(hardwareMap);
        activeIntake = new ActiveIntake(hardwareMap);
    }

    public void setintakeSlidePosition(intakeSlidesState intakeSlidesState, extensionState extensionState)
    {
        intakeSlide.setPosition(extensionState,intakeSlidesState);
    }



    public intakeSlidesState getintakeSlideState()
    {
        return intakeSlidesState;
    }


    public void setintakeSlideState(intakeSlidesState intakeSlidesState)
    {

        this.intakeSlidesState = intakeSlidesState;
    }

    public double getintakeSlidePosition()
    {
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

    public void setOuttakeSlideState(outtakeSlidesState outtakeSlidesState)
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

    public double getOuttakeLeftSlidePosition()
    {
        return outtakeSlide.getLeftouttakeSlideMotorPosition();
    }

    public double getOuttakeRightSlidePosition()
    {
        return outtakeSlide.getRightouttakeSlideMotorPosition();
    }

    public void setWristPosition(wristState wristState)
    {
        wrist.setWristPosition(wristState);
    }


    public void setWristState(wristState wristState)

    {
        this.wristState = wristState;
    }



    public wristState getWristState()

    {
        return wristState;
    }

    public double getWristPosition()
    {
        return wrist.getWristPosition();
    }

    public void setvirtualFourBarPosition(virtualFourBarState virtualFourBarState, virtualFourBarExtensionState virtualFourBarExtensionState)
    {
        virtualFourBar.setvirtualFourBarPosition(virtualFourBarState, virtualFourBarExtensionState);
    }

    public void setvirtualFourBarState(virtualFourBarState virtualFourBarState)
    {
        this.virtualFourBarState = virtualFourBarState;
    }

    public virtualFourBarState getvirtualFourBarState()
    {
        return virtualFourBarState;
    }


    public virtualFourBarExtensionState getvirtualFourBarExtensionState()

    {
        return virtualFourBarExtensionState;
    }

    public void setvirtualFourBarExtensionState(virtualFourBarExtensionState virtualFourBarExtensionState)

    {
        this.virtualFourBarExtensionState = virtualFourBarExtensionState;
    }

    public void setClawPosition(clawState clawState)
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

    public void setclawState(clawState clawState)
    {
        this.clawState = clawState;
    }

    public void setLeftclawState(clawState leftclawState)
    {
        this.leftclawState = leftclawState;
    }

    public void setRightclawState(clawState rightclawState)
    {
        this.rightclawState = rightclawState;
    }

    public clawState getclawState()
    {
        return clawState;
    }

    public clawState getLeftclawState()
    {
        return leftclawState;
    }

    public clawState getRightclawState()
    {
        return rightclawState;
    }

    public void setActiveIntakePosition(activeIntakeState activeIntakeState)
    {
        activeIntake.setActiveIntakePosition(activeIntakeState);
    }


    public activeIntakeState getactiveIntakeState()

    {
        return activeIntakeState;
    }



    public void setactiveIntakeState(activeIntakeState activeIntakeState)

    {
        this.activeIntakeState = activeIntakeState;
    }



}



