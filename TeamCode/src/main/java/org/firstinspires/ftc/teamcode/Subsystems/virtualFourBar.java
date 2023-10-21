package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;
import org.firstinspires.ftc.teamcode.Utilities.robotConstants;

public class virtualFourBar {
    private Servo leftVirtualFourBar, rightVirtualFourBar;
    public  virtualFourBar(HardwareMap hardwareMap){
        leftVirtualFourBar = hardwareMap.get(Servo.class, "leftVirtualFourBar");
        rightVirtualFourBar = hardwareMap.get(Servo.class, "rightVirtualFourBar");
    }
    public void setVirtualFourBarPosition(virtualFourBarState virtualFourBarState){
        switch(virtualFourBarState){
            case outtaking:
                leftVirtualFourBar.setPosition(robotConstants.VirtualFourBar.outtaking);
                rightVirtualFourBar.setPosition(robotConstants.VirtualFourBar.outtaking);
                break;
            case intaking:
              leftVirtualFourBar.setPosition(robotConstants.VirtualFourBar.intaking);
              rightVirtualFourBar.setPosition(robotConstants.VirtualFourBar.intaking);
              break;
            default:
                leftVirtualFourBar.setPosition(robotConstants.VirtualFourBar.intaking);
                rightVirtualFourBar.setPosition(robotConstants.VirtualFourBar.intaking);


            }
        }
    }

