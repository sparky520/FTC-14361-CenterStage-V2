package org.firstinspires.ftc.teamcode.Autonomous;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="LeftBLuePark")
public class LeftBluePark extends LinearOpMode {
    Pose2d myPose = new Pose2d(-63, 12, Math.toRadians(-180));

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(myPose);

        Trajectory Traj1 = drive.trajectoryBuilder(myPose)
                .strafeRight(75)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(Traj1);
    }
}