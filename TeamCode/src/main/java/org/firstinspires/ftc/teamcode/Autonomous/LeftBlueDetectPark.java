package org.firstinspires.ftc.teamcode.Autonomous;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.BlueDetection;

import java.util.List;

@Autonomous
public class LeftBlueDetectPark extends OpMode {
    private BlueDetection bDetection;
    private List<Recognition> blueCurrentRecognitions;

    private String middleTSE, leftTSE;
    private Boolean scoreMiddleTSE, scoreLeftTSE, scoreRightTSE;
    private DcMotorEx frontRight, frontLeft, backRight, backLeft;
    double mult = 1.0;
    double batteryVoltage;
    String voltageCategory;
    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotorEx.class, "leftFront");
        backLeft = hardwareMap.get(DcMotorEx.class, "leftRear");
        backRight = hardwareMap.get(DcMotorEx.class, "rightRear");
        frontRight = hardwareMap.get(DcMotorEx.class, "rightFront");

        

        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        backRight.setDirection(DcMotorEx.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void init_loop() {

    }



    @Override
    public void loop() {


    }


    private void forward(double mult, double sec){
        frontRight.setPower(mult);
        frontLeft.setPower(mult); //mult changes the speed the motors go. Slower is more consistent
        backRight.setPower(mult);
        backLeft.setPower(mult);
        runFor(sec); //runs for this amount of time
        /* Mecanum forward (+ means forward, - means backwards)
         + +
         + +
        */
    }

    private void backwards(double mult, double sec) {
        frontRight.setPower(mult * -1);
        frontLeft.setPower(mult * -1);
        backRight.setPower(mult * -1);
        backLeft.setPower(mult * -1);
        runFor(sec);
    }
    // drive left
    private void strafeLeft(double mult, double sec) {
        frontRight.setPower(mult * 1);
        frontLeft.setPower(mult * -1);
        backRight.setPower(mult * -1);
        backLeft.setPower(mult * 1);
        runFor(sec);
    }

    // drive back

    // drive right
    private void strafeRight(double mult, double sec) {
        frontRight.setPower(mult * -1);
        frontLeft.setPower(mult * 1);
        backRight.setPower(mult * 1);
        backLeft.setPower(mult * -1);
        runFor(sec);
    }

    private void runFor(double sec){
        //sleeps for given time, so the program can run. FTC sleep means keep doing what you are doing, not stop everything
        sleep((long) (1000 * sec));

    }
    public double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }

    /*
    The method below, when called, should:
    get the current voltage & filter it into a category,
    store the category in a String
    place it in the right Switch Case
    which will change the speed multiplier
    and make consistent speed across all voltages
    (in theory)
     */
    public void voltageTelem(){

        batteryVoltage = getBatteryVoltage();
        boolean voltNotFound = false;

        if (batteryVoltage >= 14) {
            voltageCategory = "Above 14V";

        } else if (batteryVoltage >= 13) {
            voltageCategory = "13-14V";

        } else if (batteryVoltage >= 12) {
            voltageCategory = "12-13V";

        } else if (batteryVoltage >= 11) {
            voltageCategory = "11-12V";

        } else {

            voltageCategory = "Below 11V";
        }

        switch (voltageCategory) {
            case "Above 14V":
                mult = 0.8;
                break;
            case "13-14V":
                mult = 0.85;
                break;
            case "12-13V":
                mult = 0.9;
                break;
            case "11-12V":
                mult = 1;
                break;
            default:
                telemetry.addLine("Cannot obtain voltage / Voltage too weak");
                voltNotFound = true;
        }

        if(!voltNotFound){
            telemetry.addData("Current battery voltage: ", batteryVoltage);
            telemetry.addData("Current speed multiplier: ", mult);
        }

        telemetry.update();
    }
}
