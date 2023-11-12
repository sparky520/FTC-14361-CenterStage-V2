package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.BlueDetection;
import org.firstinspires.ftc.teamcode.Subsystems.OpenCVBlueDetetction;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.List;

@Autonomous(name="test tha detect on the blu")
public class DetectionTest extends OpMode {

    private BlueDetection bDetection;
    private List<Recognition> blueCurrentRecognitions;

    private String middleTSE, leftTSE;
    private Boolean scoreMiddleTSE, scoreLeftTSE, scoreRightTSE;
    OpenCVBlueDetetction blueDetection;
    OpenCvWebcam camera;
    String webcamName;

    @Override
    public void init() {

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

    public void loop() {


    }

    private void initCam() {
        //This line retrieves the resource identifier for the camera monitor view. The camera monitor view is typically used to display the camera feed
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // This line creates a webcam instance using the OpenCvCameraFactor with the webcam name (webcamName) and the camera monitor view ID.
        // The camera instance is stored in the camera variable that we can use later
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);

        // initializing our Detection class (details on how it works at the top)
        blueDetection = new OpenCVBlueDetetction();

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


