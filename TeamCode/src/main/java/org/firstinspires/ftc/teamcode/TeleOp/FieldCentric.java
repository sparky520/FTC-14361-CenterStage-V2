package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
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

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)){

            bot.setIntakeSlideState(intakeSlidesState.HIGHIN, extensionState.extending);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){

            bot.setIntakeSlideState(intakeSlidesState.MEDIUMIN, extensionState.extending);
        }

        if(operator.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
            bot.setIntakeSlideState(intakeSlidesState.STATION, extensionState.extending);
        }
        /*
        if(operator.wasJustPressed(GamepadKeys.Button.A)){
            bot.setWristPosition(wristState.normal);
        }
        if(operator.wasJustPressed(GamepadKeys.Button.B)){
            bot.setWristPosition(wristState.sideways);
        }*/
        if(operator.wasJustPressed(GamepadKeys.Button.X)){
            bot.setVirtualFourBarPosition(virtualFourBarState.outtaking);
        }
        if(operator.wasJustPressed(GamepadKeys.Button.Y)){
            bot.setVirtualFourBarPosition(virtualFourBarState.intaking);
        }
        if(operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
            bot.setOuttakeSlidesState(outtakeSlidesState.HIGHOUT, extensionState.extending);
        }
        if(operator.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
            bot.setOuttakeSlidesState(outtakeSlidesState.MEDIUMOUT, extensionState.extending);
        }
        if(operator.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON)){
            bot.setOuttakeSlidesState(outtakeSlidesState.STATION, extensionState.extending);
        }


        if(operator.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
        {
            bot.drivetrain.setMotorSlowDownPower();
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
