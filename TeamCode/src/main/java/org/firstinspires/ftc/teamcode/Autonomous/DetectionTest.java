package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.BlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.HSVBlueDetetction;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;

@Autonomous(name="test tha detect on the blu")
public class DetectionTest extends OpMode {

    private Boolean scoreMiddleTSE, scoreLeftTSE, scoreRightTSE;
    HSVBlueDetetction blueDetection;
    OpenCvWebcam camera;
    String webcamName;

    @Override
    public void init() {

        telemetry.addData("System Status: ", "Initializing...");
        telemetry.update();

        initCam();

        telemetry.addLine("Camera Successfully Initialized.");
        telemetry.addData("CUBE IS: ", blueDetection.getLocation());
        telemetry.addLine("(right is default position");
        telemetry.update();

    }

    public void init_loop() {

        telemetry.addData("System Status: ", "Currently Looping Initialization...");
        telemetry.addData("CUBE IS: ", blueDetection.getLocation());
        telemetry.update();

    }

    public void loop() {
        telemetry.addData("System Status: ", "Currently Looping OpMode...");
        telemetry.addData("CUBE IS: ", blueDetection.getLocation());
        telemetry.update();
    }



    private void initCam() {
        //This line retrieves the resource identifier for the camera monitor view. The camera monitor view is typically used to display the camera feed
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        webcamName = "Webcam 1";

        // This line creates a webcam instance using the OpenCvCameraFactor with the webcam name (webcamName) and the camera monitor view ID.
        // The camera instance is stored in the camera variable that we can use later
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);

        // initializing our Detection class (details on how it works at the top)
        blueDetection = new HSVBlueDetetction(telemetry);

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
                camera.showFpsMeterOnViewport(true);
                camera.startStreaming(320, 240, OpenCvCameraRotation.SENSOR_NATIVE);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine("Unspecified Error Occurred During Camera Opening");
            }
        });
    }
}


