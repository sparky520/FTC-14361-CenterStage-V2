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
        scoreLeftTSE = false;
        scoreMiddleTSE = false;
        scoreRightTSE = false;

        bDetection = new BlueDetection();

        OpenCVBlueDetetction blueDetection = new OpenCVBlueDetetction();

        initCam();

        telemetry.addData("STATUS:", "INITIALIZED");
        telemetry.addData("ROTATION (Right is default): ", blueDetection.getPosition());
        telemetry.addData("WHERE IS BLUE: ",  blueDetection.getPosition());
        telemetry.update();

    }

    public void loop() {


    }

    private void initCam() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);
        blueDetection = new OpenCVBlueDetetction();
        camera.setPipeline(blueDetection);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.SENSOR_NATIVE);
            }

            @Override
            public void onError(int errorCode) {
            }
        });
    }
}


