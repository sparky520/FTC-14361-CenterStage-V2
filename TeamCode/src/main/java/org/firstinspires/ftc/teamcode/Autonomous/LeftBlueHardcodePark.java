package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Autonomous (name="caveman type parking")
public class LeftBlueHardcodePark extends LinearOpMode {

    /*
    *** NOTE ***
    THIS CODE IS MADE TO PARK LEFT WHEN WE ARE
    LEFT BLUE ALLIANCE, AND THATS IT
    (this is hardcoded and created without Roadrunner)
     */
    private DcMotorEx rightFront, leftFront, rightRear, leftRear;

    double mult = 1.0;
    double batteryVoltage;
    String voltageCategory;

    @Override
    public void runOpMode() throws InterruptedException {

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");

        leftRear.setDirection(DcMotorEx.Direction.REVERSE);
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//        // this will initialize all of our encoders- not useful
        // for this class
//        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
//
//        for (LynxModule hub : allHubs) {
//            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
//        }


        // i dunno if this is even a real thing i can do
//        while(!opModeIsActive()) {
//            // lil bit of experimental code (if this works i am suoper cool)
//            voltageTelem();
//        }


        waitForStart();

        while(opModeIsActive()){


            strafeRight(.5, 3);
            stopMotors();
            break;


        }

    }

    // drive forward
    private void forward(double mult, double sec){
        double timer = ( getRuntime() + sec);

        while (time > getRuntime()) {
            rightFront.setPower(mult);
            leftFront.setPower(mult); //mult changes the speed the motors go. Slower is more consistent
            rightRear.setPower(mult);
            leftRear.setPower(mult);
            //runFor(sec); //runs for this amount of time
        /* Mecanum forward (+ means forward, - means backwards)
         + +
         + +
        */
        }
    }

    private void backwards(double mult, double sec) {
        double timer = ( getRuntime() + sec);

        while (timer > getRuntime()) {
            rightFront.setPower(mult * -1);
            leftFront.setPower(mult * -1);
            rightRear.setPower(mult * -1);
            leftRear.setPower(mult * -1);
        }
    }
    // drive left
    private void strafeLeft(double mult, double sec) {
        rightFront.setPower(mult * 1);
        leftFront.setPower(mult * -1);
        rightRear.setPower(mult * -1);
        leftRear.setPower(mult * 1);
        runFor(sec);
    }

    // drive back

    // drive right
    private void strafeRight(double mult, double sec) {
        rightFront.setPower(mult * -1);
        leftFront.setPower(mult * 1);
        rightRear.setPower(mult * 1);
        leftRear.setPower(mult * -1);
        runFor(sec);
    }
        private void stopMotors() {
            rightFront.setPower(0);
            leftFront.setPower(0);
            rightRear.setPower(0);
            leftRear.setPower(0);
        }

        private void runFor(double sec){
        //sleeps for given time, so the program can run. FTC sleep means keep doing what you are doing, not stop everything
        sleep((long) (1000 * sec));

    }
//    public double getBatteryVoltage() {
//        double result = Double.POSITIVE_INFINITY;
//        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
//            double voltage = sensor.getVoltage();
//            if (voltage > 0) {
//                result = Math.min(result, voltage);
//            }
//        }
//        return result;
//    }
//
//    /*
//    The method below, when called, should:
//    get the current voltage & filter it into a category,
//    store the category in a String
//    place it in the right Switch Case
//    which will change the speed multiplier
//    and make consistent speed across all voltages
//    (in theory)
//     */
//    public void voltageTelem(){
//
//        batteryVoltage = getBatteryVoltage();
//        boolean voltNotFound = false;
//
//        if (batteryVoltage >= 14) {
//            voltageCategory = "Above 14V";
//
//        } else if (batteryVoltage >= 13) {
//            voltageCategory = "13-14V";
//
//        } else if (batteryVoltage >= 12) {
//            voltageCategory = "12-13V";
//
//        } else if (batteryVoltage >= 11) {
//            voltageCategory = "11-12V";
//
//        } else {
//
//            voltageCategory = "Below 11V";
//        }
//
//        switch (voltageCategory) {
//            case "Above 14V":
//                mult = 0.8;
//                break;
//            case "13-14V":
//                mult = 0.85;
//                break;
//            case "12-13V":
//                mult = 0.9;
//                break;
//            case "11-12V":
//                mult = 1;
//                break;
//            default:
//                telemetry.addLine("Cannot obtain voltage / Voltage too weak");
//                voltNotFound = true;
//        }
//
//        if(!voltNotFound){
//            telemetry.addData("Current battery voltage: ", batteryVoltage);
//            telemetry.addData("Current speed multiplier: ", mult);
//        }
//
//        telemetry.update();
//    }

}

