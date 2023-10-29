package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Utilities.robotConstants;

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
        telemetry.addLine("Time taken: " + getRuntime()+ " seconds.");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Left Slide Position: " + bot.getOuttakeLeftSlidePosition() + " ticks");
        telemetry.addLine("Right Slide Position: " + bot.getOuttakeRightSlidePosition() + " ticks");
        telemetry.addLine("Intake Slide Position" + bot.getIntakeSlidePosition() );
        telemetry.addLine("Wrist Position: " + bot.wrist.getWristPosition());
        telemetry.update();
        driver.readButtons();
        operator.readButtons();
        bot.drivetrain.drive(driver);

        bot.drivetrain.setMotorPowerRounded();



        if(operator.wasJustPressed(GamepadKeys.Button.START)){

            bot.drivetrain.resetIMU();
        }

        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){

            bot.setIntakeSlidePosition(intakeSlidesState.HIGHIN, extensionState.extending);
        }

        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){

            bot.setIntakeSlidePosition(intakeSlidesState.MEDIUMIN, extensionState.extending);
        }

        if(driver.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT))
        {
           if(bot.getWristState().equals(wristState.normal)){
               bot.setWristPosition(wristState.sideways);
               bot.setWristState(wristState.sideways);
           }
           else if(bot.getWristState().equals(wristState.sideways)){
               bot.setWristPosition(wristState.normal);
               bot.setWristState(wristState.normal);
           }
           else{
               bot.setWristPosition(wristState.normal);
               bot.setWristState(wristState.normal);
           }
        }


        if(operator.wasJustPressed(GamepadKeys.Button.X)) {
            if (bot.getVirtualFourBarState().equals(virtualFourBarState.outtaking))
            {
                bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.intaking);
                    while(bot.getVirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending))
                    {
                        bot.setWristState(wristState.sideways);
                        bot.setWristPosition(wristState.sideways);
                     }
                bot.setWristState(wristState.normal);
                bot.setWristPosition(wristState.normal);

            }
            else if(bot.getVirtualFourBarState().equals(virtualFourBarState.intaking)){
                bot.setVirtualFourBarPosition(virtualFourBarState.outtaking, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.outtaking);
                while(bot.getVirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending))
                {
                    bot.setWristState(wristState.sideways);
                    bot.setWristPosition(wristState.sideways);
                }
                bot.setWristState(wristState.normal);
                bot.setWristPosition(wristState.normal);

            }
            else{
                bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
                bot.setVirtualFourBarState(virtualFourBarState.intaking);
                while(bot.getVirtualFourBarExtensionState().equals(virtualFourBarExtensionState.extending))
                {
                    bot.setWristState(wristState.sideways);
                    bot.setWristPosition(wristState.sideways);
                }
                bot.setWristState(wristState.normal);
                bot.setWristPosition(wristState.normal);


            }

        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_UP))
        {
            if(bot.getOuttakeState().equals(outtakeSlidesState.STATION)){
                bot.setOuttakeSlidePosition(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
                bot.setOuttakeSlidesState(outtakeSlidesState.MEDIUMOUT);
            }
            else if(bot.getOuttakeState().equals(outtakeSlidesState.MEDIUMOUT)){
                bot.setOuttakeSlidePosition(outtakeSlidesState.HIGHOUT, extensionState.extending);
                bot.setOuttakeSlidesState(outtakeSlidesState.HIGHOUT);
            }
            else if(bot.getOuttakeState().equals(outtakeSlidesState.HIGHOUT)){
                bot.setOuttakeSlidePosition(outtakeSlidesState.HIGHOUT, extensionState.extended);
                bot.setOuttakeSlidesState(outtakeSlidesState.HIGHOUT);
            }
            else{
                bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                bot.setOuttakeSlidesState(outtakeSlidesState.STATION);
            }
        }


        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            if(bot.getOuttakeState().equals(outtakeSlidesState.HIGHOUT)){
                bot.setOuttakeSlidePosition(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
                bot.setOuttakeSlidesState(outtakeSlidesState.MEDIUMOUT);
            }
            else if(bot.getOuttakeState().equals(outtakeSlidesState.MEDIUMOUT)){
                bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                bot.setOuttakeSlidesState(outtakeSlidesState.STATION);
            }
            else if(bot.getOuttakeState().equals(outtakeSlidesState.STATION)){
                bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extended);
                bot.setOuttakeSlidesState(outtakeSlidesState.STATION);
            }
            else{
                bot.setOuttakeSlidePosition(outtakeSlidesState.STATION, extensionState.extending);
                bot.setOuttakeSlidesState(outtakeSlidesState.STATION);
            }
        }

        if(operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
            if(bot.getClawState().equals(clawState.rightClose)){
                bot.setClawPosition(clawState.rightOpen);
                bot.setClawState(clawState.rightOpen);
            }
            else if(bot.getClawState().equals(clawState.rightOpen)){
                bot.setClawPosition(clawState.rightClose);
                bot.setClawState(clawState.rightClose);
            }
            else{
                bot.setClawPosition(clawState.rightClose);
                bot.setClawState(clawState.rightClose);
            }
        }
        if (operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
            if(bot.getClawState().equals(clawState.leftClose)){
                bot.setClawPosition(clawState.leftOpen);
                bot.setClawState(clawState.leftOpen);
            }
            else if(bot.getClawState().equals(clawState.leftOpen)) {
                bot.setClawPosition(clawState.leftClose);
                bot.setClawState(clawState.leftClose);
            }
            else{
                bot.setClawPosition(clawState.leftClose);
                bot.setClawState(clawState.leftClose);
            }
        }

        if(operator.wasJustPressed(GamepadKeys.Button.B)){
            if(bot.getClawState().equals(clawState.close)){
                bot.setClawPosition(clawState.open);
                bot.setClawState(clawState.open);
            }
            else if(bot.getClawState().equals(clawState.open)){
                bot.setClawPosition(clawState.close);
                bot.setClawState(clawState.close);
            }
            else{
                bot.setClawPosition(clawState.close);
                bot.setClawState(clawState.close);
            }
        }


    }

    public void stop()
    {
        //dawg idk how to stop the damn robot
        telemetry.addLine("Robot Stopped.");
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.update();
    }

}
