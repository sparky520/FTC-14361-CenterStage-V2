package org.firstinspires.ftc.teamcode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "RightBlueMid")
public class RightBlueMid extends LinearOpMode
{
    public Robot bot;
    Pose2d myPose = new Pose2d(-36, 63, Math.toRadians(90));
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(myPose);

        Trajectory Traj1 = drive.trajectoryBuilder(myPose)
//                .addTemporalMarker(0.2, () -> {
//                    bot.setClawPosition(clawState.close);
//                    bot.setClawState(clawState.close);
//                    bot.setWristPosition(wristState.normal);
//                    bot.setWristState(wristState.normal);
//                    bot.setVirtualFourBarPosition(virtualFourBarState.init, virtualFourBarExtensionState.extending);
//                    bot.setVirtualFourBarState(virtualFourBarState.init);
//                })
                .back(19)
                .build();

        Trajectory Traj2 = drive.trajectoryBuilder(Traj1.end())
                .forward(6)
                .build();

        Trajectory Traj3 = drive.trajectoryBuilder(Traj2.end())
                .strafeLeft(20)
                .build();

        Trajectory Traj4 = drive.trajectoryBuilder(Traj3.end())
                .back(18)
                .build();

        Trajectory Traj5 = drive.trajectoryBuilder(Traj4.end())
                .strafeRight(90)
                .build();

        Trajectory Traj6 = drive.trajectoryBuilder(Traj5.end())
                .lineToLinearHeading(new Pose2d(48, 36, Math.toRadians(180)))
                .build();

        Trajectory Traj7 = drive.trajectoryBuilder(Traj6.end())
                .strafeLeft(10)
                .build();

        Trajectory Traj8 = drive.trajectoryBuilder(Traj6.end())
                .back(10)
                .build();

        waitForStart();

        if(isStopRequested())
        {
            return;
        }

            drive.followTrajectory(Traj1);
            drive.followTrajectory(Traj2);
            drive.followTrajectory(Traj3);
            drive.followTrajectory(Traj4);
            drive.followTrajectory(Traj5);
            drive.followTrajectory(Traj6);
            drive.followTrajectory(Traj7);
            drive.followTrajectory(Traj8);
    }
}
