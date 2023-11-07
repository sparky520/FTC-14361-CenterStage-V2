package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode {
    private ElapsedTime runTime;
    private GamepadEx driver, operator;

    private Robot bot;

    @Override
    public void init() {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);

        telemetry.addLine("It's goobin time");
        telemetry.addLine("Time taken: " + getRuntime() + " seconds.");
        telemetry.update();

        bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
        bot.setVirtualFourBarState(virtualFourBarState.init);

        bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
        bot.setintakeSlideState(intakeSlidesState.STATION);

        bot.setClawPosition(clawState.open);
        bot.setClawState(clawState.open);

        bot.setLeftClawState(clawState.leftOpen);
        bot.setRightClawState(clawState.rightOpen);
    }

    // ---------------------------- LOOPING ---------------------------- //

    @Override
    public void loop() {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Left Slide Position: " + bot.getOuttakeLeftSlidePosition() + " ticks");
        telemetry.addLine("Right Slide Position: " + bot.getOuttakeRightSlidePosition() + " ticks");
        telemetry.addLine("Intake Slide Position" + bot.getIntakeSlidePosition());
        telemetry.addLine("Wrist Position: " + bot.wrist.getWristPosition());
        telemetry.addLine("State of V4B" + bot.virtualFourBar.getVirtualFourBarextensionState());
        telemetry.addLine("Right Claw Position" + bot.claw.getRightClawPosition());
        telemetry.addLine("Left Claw Position" + bot.claw.getLeftClawPosition());
        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        bot.drivetrain.drive(driver);
        bot.drivetrain.setMotorPower();

        // ---------------------------- DRIVER CODE ---------------------------- //

        if (operator.wasJustPressed(GamepadKeys.Button.START))
        {
            bot.drivetrain.resetIMU();
        }

//        if (driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1)
//        {
//            bot.driveTrain.setSlowDownMotorPower();
//        }
//
//        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1)
//        {
//            bot.driveTrain.setFullPower();
//        {

        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_UP))
        {
            bot.setintakeSlideState(intakeSlidesState.HIGHIN);
            bot.setIntakeSlidePosition(intakeSlidesState.HIGHIN, extensionState.extending);
        }

        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT))
        {
            bot.setintakeSlideState(intakeSlidesState.MEDIUMIN);
            bot.setIntakeSlidePosition(intakeSlidesState.MEDIUMIN, extensionState.extending);
        }

        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN))
        {
            bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
            bot.setintakeSlideState(intakeSlidesState.STATION);
        }

        if (driver.wasJustPressed(GamepadKeys.Button.A))
        {
            if (bot.getactiveIntakeState() != null && bot.getactiveIntakeState().equals(activeIntakeState.active))
            {
                bot.setActiveIntakePosition(activeIntakeState.inactive);
                bot.setactiveIntakeState(activeIntakeState.inactive);
            }
            else
            {
                bot.setActiveIntakePosition(activeIntakeState.active);
                bot.setactiveIntakeState(activeIntakeState.active);
            }
        }

        // --------------------------- OPERATOR CODE --------------------------- //

        if(operator.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON))
        {
            if (bot.getClawState() != null && bot.getClawState().equals(clawState.open))
            {
                bot.setClawPosition(clawState.close);
                bot.setClawState(clawState.close);
            }
            else
            {
                bot.setClawPosition(clawState.open);
                bot.setClawState(clawState.open);
            }
        }

        if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON))
        {
            if (bot.getwristState() != null && bot.getwristState().equals(wristState.normal))
            {
                bot.setWristPosition(wristState.sideways);
                bot.setwristState(wristState.sideways);
            }
            else
            {
                bot.setWristPosition(wristState.normal);
                bot.setwristState(wristState.normal);
            }
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_UP))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.HIGHOUT, extensionState.extended);
            bot.setOuttakeSlidesState(outtakeSlidesState.HIGHOUT);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
            bot.setOuttakeSlidesState(outtakeSlidesState.MEDIUMOUT);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN))
        {
            bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
            bot.setOuttakeSlidesState(outtakeSlidesState.STATION);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
        {
            if(bot.getLeftClawState() != null && bot.getLeftClawState().equals(clawState.leftOpen))
            {
                bot.setOpenLeftClawPosition();
                bot.setLeftClawState(clawState.leftClose);
            }
            else
            {
                bot.setOpenLeftClawPosition();
                bot.setLeftClawState(clawState.leftClose);
            }
        }

        if(operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER))
        {
            if(bot.getRightClawState() != null && bot.getRightClawState().equals(clawState.rightOpen))
            {
                bot.setOpenRightClawPosition();
                bot.setRightClawState(clawState.rightClose);
            }
            else
            {
                bot.setOpenRightClawPosition();
                bot.setRightClawState(clawState.rightOpen);
            }
        }
    }
}

