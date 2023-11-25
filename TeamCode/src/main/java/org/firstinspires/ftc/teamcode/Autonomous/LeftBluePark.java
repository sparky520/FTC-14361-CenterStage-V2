package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class LeftBluePark extends LinearOpMode {

    Robot bot;
    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap, telemetry);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d toTape = new Pose2d(-10,-72,0);
        Pose2d rightTape = new Pose2d(-10,-72,0);


        drive.setPoseEstimate(toTape);

        Trajectory toCenterTape = drive.trajectoryBuilder(toTape)
                .lineToConstantHeading(new Vector2d(-10, -36))
                .addTemporalMarker(1, () -> {
                    bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
            bot.setIntakeSlideState(intakeSlidesState.STATION);
            bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
            bot.setVirtualFourBarState(virtualFourBarState.intaking);
            bot.setClawPosition(clawState.close);
            bot.setClawState(clawState.close);
                })
                .build();

        Trajectory toRightTape = drive.trajectoryBuilder(toTape)
                .lineToConstantHeading(new Vector2d(-5,-36))
                .build();

        Trajectory toLeftTape= drive.trajectoryBuilder(toTape)
                .lineToConstantHeading(new Vector2d(-15, -36))
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(toCenterTape.end())
                .lineToLinearHeading(new Pose2d(-48, -48, Math.toRadians(90)))
                .addTemporalMarker(.2, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.init,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);

                })
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(toCenterTape);
        drive.followTrajectory(toBackBoard);
    }


}