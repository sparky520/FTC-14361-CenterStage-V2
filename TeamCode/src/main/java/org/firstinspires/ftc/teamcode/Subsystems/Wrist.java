package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.WristState;
import org.firstinspires.ftc.teamcode.Utilities.RobotConstants;

public class Wrist {

    private Servo servo;
    public Wrist(HardwareMap hardwareMap){

        servo = hardwareMap.get(Servo.class, "wristServo");

    }
    public void setWristPosition(WristState wristState){
        switch(wristState){
            case normal:
                servo.setPosition(RobotConstants.Wrist.wristNormal);
                break;
            case sideways:
                servo.setPosition(RobotConstants.Wrist.wristSideways);
                break;
            default:
                servo.setPosition(RobotConstants.Wrist.wristNormal);
        }
    }
    public double getWristPosition(){
        return servo.getPosition();
    }

}
