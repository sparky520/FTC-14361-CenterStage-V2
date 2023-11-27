package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.robotConstants;

@Autonomous(name = "Goob")

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
                .lineToConstantHeading(new Vector2d(-32, -72))
                .addDisplacementMarker(0, () -> {

                    bot.setClawPosition(clawState.close);
                    bot.setClawState(clawState.close);



                })
                .addDisplacementMarker(10, () -> {
                    bot.setVirtualFourBarPosition(virtualFourBarState.init,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.init);

                })
//                .addTemporalMarker(1, () -> {
//                    bot.setIntakeSlidePosition(intakeSlidesState.STATION, extensionState.extending);
//            bot.setIntakeSlideState(intakeSlidesState.STATION);
//            bot.setVirtualFourBarPosition(virtualFourBarState.intaking, virtualFourBarExtensionState.extending);
//            bot.setVirtualFourBarState(virtualFourBarState.intaking);
//            bot.setClawPosition(clawState.close);
//            bot.setClawState(clawState.close);
//                })
                .build();
Trajectory dropOnCenterTape = drive.trajectoryBuilder(toCenterTape.end())
        .lineToConstantHeading(new Vector2d(-40, -72))
        .build();
        Trajectory toRightTape = drive.trajectoryBuilder(toCenterTape.end())
                .lineToConstantHeading(new Vector2d(-5,-36))
                .build();

        Trajectory toLeftTape= drive.trajectoryBuilder(toCenterTape.end())
                .lineToConstantHeading(new Vector2d(-15, -36))
                .build();

        Trajectory toBackBoard = drive.trajectoryBuilder(toCenterTape.end())

                .lineToLinearHeading(new Pose2d(-32, -108, Math.toRadians(90)))


                .addDisplacementMarker(0, () -> {
                     bot.setWristPosition(wristState.sideways);
                    bot.setWristState(wristState.sideways);




                })

                .addDisplacementMarker(10, () -> {

                    bot.setVirtualFourBarPosition(virtualFourBarState.outtaking,virtualFourBarExtensionState.extending);
                    bot.setVirtualFourBarState(virtualFourBarState.outtaking);

                    bot.setOuttakeSlideState(outtakeSlidesState.AUTOLOWOUT);
                    bot.setOuttakeSlidePosition(outtakeSlidesState.AUTOLOWOUT, extensionState.extending);





                })
//            //    .addDisplacementMarker(35, () -> {
//                    bot.setClawPosition(clawState.open);
//                    bot.setClawState(clawState.open);
//
//
//
//
//                })

                .build();
        Trajectory leaveBackBoard = drive.trajectoryBuilder(toBackBoard.end())
                .lineToConstantHeading(new Vector2d(-32,-100))
                .addDisplacementMarker(0, () -> {

                    bot.setClawPosition(clawState.open);
                  bot.setClawState(clawState.open);



                })
                        .build();
        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(toCenterTape);

        //drive.followTrajectory(dropOnCenterTape);

        drive.followTrajectory(toBackBoard);


        drive.followTrajectory(leaveBackBoard);
    }


}