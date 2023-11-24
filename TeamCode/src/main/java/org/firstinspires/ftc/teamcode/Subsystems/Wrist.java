package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.Commands.wristState;
import org.firstinspires.ftc.teamcode.util.robotConstants;


public class Wrist
{
    private Servo servo;
    public Wrist(HardwareMap hardwareMap)
    {
        servo = hardwareMap.get(Servo.class, "wristServo");
    }
    public void setWristPosition(wristState wristState)
    {
        switch(wristState)
        {
            case normal:
                servo.setPosition(robotConstants.Wrist.wristNormal);
                break;
            case sideways:
                servo.setPosition(robotConstants.Wrist.wristSideways);
                break;
            default:
                servo.setPosition(robotConstants.Wrist.wristNormal);
        }
    }
    public double getWristPosition()
    {
        return servo.getPosition();
    }

}
