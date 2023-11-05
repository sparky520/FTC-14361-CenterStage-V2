package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.armState;
import org.firstinspires.ftc.teamcode.Utilities.RobotConstants;

public class Arm {
    private Servo leftArm, rightArm;
    public Arm (HardwareMap hardwareMap)
    {
        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");



    }

    public void setPosition(armState state){
        switch (state){
            case intaking:
                    leftArm.setPosition(RobotConstants.Arm.intake);
                rightArm.setPosition(RobotConstants.Arm.intake);
                break;
            case outtaking:
                leftArm.setPosition(RobotConstants.Arm.outtake);
                rightArm.setPosition(RobotConstants.Arm.outtake);
                break;
        }
    }


}
