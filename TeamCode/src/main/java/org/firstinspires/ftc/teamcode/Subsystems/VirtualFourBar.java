package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.teamcode.Utilities.RobotConstants.VirtualFourBar.initLeft;
import static org.firstinspires.ftc.teamcode.Utilities.RobotConstants.VirtualFourBar.initRight;
import static org.firstinspires.ftc.teamcode.Utilities.RobotConstants.VirtualFourBar.intakingLeft;
import static org.firstinspires.ftc.teamcode.Utilities.RobotConstants.VirtualFourBar.intakingRight;
import static org.firstinspires.ftc.teamcode.Utilities.RobotConstants.VirtualFourBar.outtakingLeft;
import static org.firstinspires.ftc.teamcode.Utilities.RobotConstants.VirtualFourBar.outtakingRight;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;

public class VirtualFourBar {
    private ServoEx leftVirtualFourBar, rightVirtualFourBar;

    public virtualFourBarExtensionState virtualFourBarExtension;
    double minAngle = 0, maxAngle= 360;

    public VirtualFourBar(HardwareMap hardwareMap) {
      //  leftVirtualFourBar = hardwareMap.servo.get("leftVirtualFourBar");
       // rightVirtualFourBar = hardwareMap.servo.get("rightVirtualFourBar");

        rightVirtualFourBar = new SimpleServo(hardwareMap, "rightVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);
        leftVirtualFourBar = new SimpleServo(hardwareMap, "leftVirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);
    }

    public void setVirtualFourBarPosition(virtualFourBarState virtualFourBarState, virtualFourBarExtensionState virtualFourBarExtensionState) {

        switch (virtualFourBarState) {
            case outtaking:
                leftVirtualFourBar.setPosition(outtakingLeft);
                rightVirtualFourBar.setPosition(outtakingRight);
               /*if(virtualFourBarExtending(leftVirtualFourBar,outtakingLeft) || virtualFourBarExtending(rightVirtualFourBar,outtakingRight)){
                    virtualFourBarExtension = virtualFourBarExtensionState.extending;
                }*/

                virtualFourBarExtension = virtualFourBarExtensionState.station;

                break;
            case intaking:
                leftVirtualFourBar.setPosition(intakingLeft);
                rightVirtualFourBar.setPosition(intakingRight);
               /*if(virtualFourBarExtending(leftVirtualFourBar,intakingLeft) || virtualFourBarExtending(rightVirtualFourBar,intakingRight)){
                   virtualFourBarExtension = virtualFourBarExtensionState.extending;
               }*/
                virtualFourBarExtension = virtualFourBarExtensionState.station;
                    break;
                    default:
                        leftVirtualFourBar.setPosition(initLeft);
                        rightVirtualFourBar.setPosition(initRight);
                }
        }
        public virtualFourBarExtensionState getVirtualFourBarExtensionState () {
            return virtualFourBarExtension;
        }

        public boolean virtualFourBarExtending (ServoEx v4b, double target) {
            double marginOfError = Math.abs(v4b.getPosition()+5-target);
            if(marginOfError > .05){
                return true;
            }
            else{
                return false;
            }
        }

    }







