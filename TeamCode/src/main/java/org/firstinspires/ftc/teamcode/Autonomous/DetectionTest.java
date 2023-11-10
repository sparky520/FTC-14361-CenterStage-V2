package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.BlueDetection;

import java.util.List;

@Autonomous(name="test tha detect on the blu")
public class DetectionTest extends OpMode {

    private BlueDetection bDetection;
    private List<Recognition> blueCurrentRecognitions;

    private String middleTSE, leftTSE;
    private Boolean scoreMiddleTSE, scoreLeftTSE, scoreRightTSE;

    @Override
    public void init() {

        middleTSE = "MiddleBlue";
        leftTSE = "LeftBlue";

        scoreLeftTSE = false;
        scoreMiddleTSE = false;
        scoreRightTSE = false;

        bDetection = new BlueDetection();


    }

        public void init_loop() {

            blueCurrentRecognitions = bDetection.tfod.getRecognitions();

            if (!blueCurrentRecognitions.isEmpty()) {
                bDetection.telemetryTfod(telemetry);
                telemetry.update();

                for (Recognition recognition : blueCurrentRecognitions) {

                    if (recognition.getLabel().equals(middleTSE))
                        scoreMiddleTSE = true;

                    else if (recognition.getLabel().equals(leftTSE))
                        scoreLeftTSE = true;

                }

            } else {
                // if the model doesn't detect middle or left, ong we're just gambling
             telemetry.addLine("No Objects Detected. Right Blue is selected by default");
              telemetry.update();
                scoreRightTSE = true;
            }

            telemetry.update();

            if(!blueCurrentRecognitions.isEmpty())
                blueCurrentRecognitions.clear();
        }


    public void loop() {


    }
}


