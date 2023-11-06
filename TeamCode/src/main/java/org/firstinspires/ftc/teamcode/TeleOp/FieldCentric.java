package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.ActiveIntakeState;
import org.firstinspires.ftc.teamcode.Commands.ClawState;
import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.IntakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.VirtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.VirtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.WristState;
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

        bot.setVirtualFourBarPosition(VirtualFourBarState.init, VirtualFourBarExtensionState.extending);
        bot.setVirtualFourBarState(VirtualFourBarState.init);

        bot.setIntakeSlidePosition(IntakeSlidesState.STATION, ExtensionState.extending);
        bot.setIntakeSlideState(IntakeSlidesState.STATION);

        bot.setClawPosition(ClawState.open);
        bot.setClawState(ClawState.open);

        bot.setLeftClawState(ClawState.leftOpen);
        bot.setRightClawState(ClawState.rightOpen);
    }

    // ---------------------------- LOOPING ---------------------------- //

    @Override
    public void loop() {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Left Slide Position: " + bot.getOuttakeLeftSlidePosition() + " ticks");
        telemetry.addLine("Right Slide Position: " + bot.getOuttakeRightSlidePosition() + " ticks");
        telemetry.addLine("Intake Slide Position" + bot.getIntakeSlidePosition());
        telemetry.addLine("Wrist Position: " + bot.wrist.getWristPosition());
        telemetry.addLine("State of V4B" + bot.virtualFourBar.getVirtualFourBarExtensionState());
        telemetry.addLine("Right Claw Position" + bot.claw.getRightClawPosition());
        telemetry.addLine("Left Claw Position" + bot.claw.getLeftClawPosition());
        telemetry.update();

        driver.readButtons();
        operator.readButtons();

        bot.driveTrain.drive(driver);
        bot.driveTrain.setMotorPower();

        // ---------------------------- DRIVER CODE ---------------------------- //

        if (driver.wasJustPressed(GamepadKeys.Button.START)) {
            bot.driveTrain.resetIMU();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1) {
            bot.driveTrain.setSlowDownMotorPower();
        }

        if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1) {
            bot.driveTrain.setFullPower();
            {
                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
                    bot.setIntakeSlideState(IntakeSlidesState.HIGHIN);
                    bot.setIntakeSlidePosition(IntakeSlidesState.HIGHIN, ExtensionState.extending);
                }

                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
                    bot.setIntakeSlideState(IntakeSlidesState.MEDIUMIN);
                    bot.setIntakeSlidePosition(IntakeSlidesState.MEDIUMIN, ExtensionState.extending);
                }

                if (driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
                    bot.setIntakeSlidePosition(IntakeSlidesState.STATION, ExtensionState.extending);
                    bot.setIntakeSlideState(IntakeSlidesState.STATION);
                }

                if (driver.wasJustPressed(GamepadKeys.Button.A)) {
                    if (bot.getActiveIntakeState() != null && bot.getActiveIntakeState().equals(ActiveIntakeState.active)) {
                        bot.setActiveIntakePosition(ActiveIntakeState.inactive);
                        bot.setActiveIntakeState(ActiveIntakeState.inactive);
                    } else {
                        bot.setActiveIntakePosition(ActiveIntakeState.active);
                        bot.setActiveIntakeState(ActiveIntakeState.active);
                    }
                }

                // --------------------------- OPERATOR CODE --------------------------- //

                if (operator.wasJustPressed(GamepadKeys.Button.LEFT_STICK_BUTTON)) {
                    if (bot.getClawState() != null && bot.getClawState().equals(ClawState.open)) {
                        bot.setClawPosition(ClawState.close);
                        bot.setClawState(ClawState.close);
                    } else {
                        bot.setClawPosition(ClawState.open);
                        bot.setClawState(ClawState.open);
                    }
                }

                if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON)) {
                    if (bot.getWristState() != null && bot.getWristState().equals(WristState.normal)) {
                        bot.setWristPosition(WristState.sideways);
                        bot.setWristState(WristState.sideways);
                    } else {
                        bot.setWristPosition(WristState.normal);
                        bot.setWristState(WristState.normal);
                    }
                }

                if (operator.wasJustPressed(GamepadKeys.Button.DPAD_UP)) {
                    bot.setOuttakeSlidePosition(OuttakeSlidesState.HIGHOUT, ExtensionState.extended);
                    bot.setOuttakeSlidesState(OuttakeSlidesState.HIGHOUT);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
                    bot.setOuttakeSlidePosition(OuttakeSlidesState.MEDIUMOUT, ExtensionState.extending);
                    bot.setOuttakeSlidesState(OuttakeSlidesState.MEDIUMOUT);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
                    bot.setOuttakeSlidePosition(OuttakeSlidesState.STATION, ExtensionState.extending);
                    bot.setOuttakeSlidesState(OuttakeSlidesState.STATION);
                }

                if (operator.wasJustPressed(GamepadKeys.Button.A)) {
                    if (bot.virtualFourBarState != null && bot.getVirtualFourBarState().equals(VirtualFourBarState.outtaking)) {
                        bot.setVirtualFourBarPosition(VirtualFourBarState.init, VirtualFourBarExtensionState.extending);
                        bot.setVirtualFourBarState(VirtualFourBarState.init);

                        if (bot.getVirtualFourBarExtensionState() != null && bot.getVirtualFourBarExtensionState().equals(VirtualFourBarExtensionState.extending)) {
                            bot.setWristState(WristState.normal);
                            bot.setWristPosition(WristState.normal);
                        }

                        bot.setWristState(WristState.sideways);
                        bot.setWristPosition(WristState.sideways);
                    } else if (bot.virtualFourBarState != null && bot.getVirtualFourBarState().equals(VirtualFourBarState.intaking)) {
                        bot.setVirtualFourBarPosition(VirtualFourBarState.outtaking, VirtualFourBarExtensionState.extending);
                        bot.setVirtualFourBarState(VirtualFourBarState.outtaking);

                        if (bot.getVirtualFourBarExtensionState() != null && bot.getVirtualFourBarExtensionState().equals(VirtualFourBarExtensionState.extending)) {
                            bot.setWristState(WristState.normal);
                            bot.setWristPosition(WristState.normal);
                        }

                        bot.setWristState(WristState.sideways);
                        bot.setWristPosition(WristState.sideways);
                    } else if (bot.virtualFourBarState != null && bot.getVirtualFourBarState().equals(VirtualFourBarState.init)) {
                        bot.setWristState(WristState.normal);
                        bot.setWristPosition(WristState.normal);

                        bot.setVirtualFourBarPosition(VirtualFourBarState.intaking, VirtualFourBarExtensionState.extending);
                        bot.setVirtualFourBarState(VirtualFourBarState.intaking);
                    } else {
                        bot.setVirtualFourBarPosition(VirtualFourBarState.init, VirtualFourBarExtensionState.extending);
                        bot.setVirtualFourBarExtensionState(VirtualFourBarExtensionState.extending);
                        bot.setVirtualFourBarState(VirtualFourBarState.init);

                        if (bot.getVirtualFourBarExtensionState() != null && bot.getVirtualFourBarExtensionState().equals(VirtualFourBarExtensionState.extending)) {
                            bot.setWristState(WristState.normal);
                            bot.setWristPosition(WristState.normal);
                        }

                        bot.setWristState(WristState.sideways);
                        bot.setWristPosition(WristState.sideways);
                    }
                }

                if (operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
                    if (bot.getLeftClawState() != null && bot.getLeftClawState().equals(ClawState.leftOpen)) {
                        bot.setOpenLeftClawPosition();
                        bot.setLeftClawState(ClawState.leftClose);
                    } else {
                        bot.setOpenLeftClawPosition();
                        bot.setLeftClawState(ClawState.leftClose);
                    }
                }

                if (operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
                    if (bot.getRightClawState() != null && bot.getRightClawState().equals(ClawState.rightOpen)) {
                        bot.setOpenRightClawPosition();
                        bot.setRightClawState(ClawState.rightClose);
                    } else {
                        bot.setOpenRightClawPosition();
                        bot.setRightClawState(ClawState.rightOpen);
                    }
                }
            }
        }
    }
}