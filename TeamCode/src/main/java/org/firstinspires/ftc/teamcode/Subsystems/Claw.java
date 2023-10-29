package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Utilities.robotConstants;

public class Claw {
    private Servo leftHand, rightHand;

    public Claw(HardwareMap hardwareMap)
    {
        leftHand = hardwareMap.servo.get("leftHand");
        rightHand = hardwareMap.servo.get("rightHand");
    }

    public void leftOpen()
    {
        leftHand.setPosition(robotConstants.Claw.leftOpen);
    }

    public void rightOpen()
    {
        rightHand.setPosition(robotConstants.Claw.rightOpen);
    }

    public void open()
    {
        leftHand.setPosition(robotConstants.Claw.leftOpen);
        rightHand.setPosition(robotConstants.Claw.rightOpen);
    }

    public void leftClose()
    {
        leftHand.setPosition(robotConstants.Claw.leftClose);
    }

    public void rightClose()
    {
        rightHand.setPosition(robotConstants.Claw.rightClose);
    }

    public void close()
    {
        leftHand.setPosition(robotConstants.Claw.leftClose);
        rightHand.setPosition(robotConstants.Claw.rightClose);
    }

    public double getLeftHandPosition()
    {
        return leftHand.getPosition();
    }

    public double getRightHandPosition()
    {
        return rightHand.getPosition();
    }


    public void setClawPosition(clawState clawState){
        switch(clawState){
            case open:
                open();
                break;
            case close:
                close();
                break;
            case leftclose:
                leftClose();
                break;
            case rightClose:
                rightClose();
                break;
            case leftOpen:
                leftOpen();
                break;
            case rightOpen:
                rightOpen();
                break;
            default:
                close();
        }

    }
}
