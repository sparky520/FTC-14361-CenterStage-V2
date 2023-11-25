package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.BlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class RightBlue extends OpMode
{
    private BlueDetection bDetection;
    private List<Recognition> blueCurrentRecognitions;

    private String middleTSE, leftTSE;
    private Boolean scoreMiddleTSE, scoreLeftTSE, scoreRightTSE;
    private RightBlueMid rightBlueMid;
    public Robot bot;
    BlueDetection blueDetection;
    OpenCvWebcam camera;
    String webcamName;

    public void init()
    {
        middleTSE = "MiddleBlue";
        leftTSE = "LeftBlue";
        webcamName = "Webcam 1";

        initCam();

        telemetry.addData("STATUS:", "INITIALIZED");
        telemetry.addData("AMOUNT OF BLUE DETECTED IN LEFT POSITION: ",  blueDetection.getLeftBoxBlueReading());
        telemetry.addData("AMOUNT OF BLUE DETECTED IN MIDDLE POSITION: ",  blueDetection.getMiddleBoxBlueReading());
        telemetry.addData("CURRENT BLUE THRESHOLD SET AT: ", blueDetection.getBlueThreshold());
        telemetry.addData("CUBE IS (Right is default): ", blueDetection.getPosition());

        telemetry.update();
    }

    public void loop()
    {
        if(blueDetection.getPosition() == BlueDetection.TSEPosition.LEFT)
        {
            Pose2d myPose = new Pose2d(-36, 63, Math.toRadians(90));

            SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

            drive.setPoseEstimate(myPose);

            Trajectory Traj1 = drive.trajectoryBuilder(myPose)
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

            drive.followTrajectory(Traj1);
            drive.followTrajectory(Traj2);
            drive.followTrajectory(Traj3);
            drive.followTrajectory(Traj4);
            drive.followTrajectory(Traj5);
            drive.followTrajectory(Traj6);
            drive.followTrajectory(Traj7);
            drive.followTrajectory(Traj8);
        }
//        else if(blueDetection.getPosition() == BlueDetection.TSEPosition.MIDDLE)
//        {
//
//        }
//        else
//        {
//
//        }
    }


    private void initCam() {
        //This line retrieves the resource identifier for the camera monitor view. The camera monitor view is typically used to display the camera feed
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // This line creates a webcam instance using the OpenCvCameraFactor with the webcam name (webcamName) and the camera monitor view ID.
        // The camera instance is stored in the camera variable that we can use later
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);

        // initializing our Detection class (details on how it works at the top)
        blueDetection = new BlueDetection();

        // yeah what this does is it gets the thing which uses the thing so we can get the thing
        /*
        (fr tho idk what pipeline does, but from what I gathered,
         we basically passthrough our detection into the camera
         and we feed the streaming camera frames into our Detection algorithm)
         */
        camera.setPipeline(blueDetection);

        /*
        this starts the camera streaming, with 2 possible combinations
        it starts streaming at a chosen res, or if something goes wrong it throws an error
         */
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.SENSOR_NATIVE);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine("Unspecified Error Occurred During Camera Opening");
            }
        });
    }
}