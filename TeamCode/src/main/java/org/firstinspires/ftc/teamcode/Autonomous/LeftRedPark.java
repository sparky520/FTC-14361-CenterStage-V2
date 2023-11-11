package org.firstinspires.ftc.teamcode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "LeftRedPark")
public class LeftRedPark extends LinearOpMode
{
    Pose2d myPose = new Pose2d(63, -36);
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(myPose);

        Trajectory Traj1 = drive.trajectoryBuilder(myPose)
                .back(80)
                .build();
        Trajectory Traj2 = drive.trajectoryBuilder(Traj1.end())
                .strafeLeft(95)
                .build();
        Trajectory Traj3 = drive.trajectoryBuilder(Traj2.end())
                .strafeLeft(95)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(Traj1);
        drive.followTrajectory(Traj2);

        drive.turn(1);
        drive.followTrajectory(Traj3);
    }
}