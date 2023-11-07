package org.firstinspires.ftc.teamcode.Subsystems;


import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingRight;

import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.initRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.intakingRight;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingLeft;
import static org.firstinspires.ftc.teamcode.util.robotConstants.virtualFourBar.outtakingRight;


import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarExtensionState;
import org.firstinspires.ftc.teamcode.Commands.virtualFourBarState;

public class virtualFourBar
{
    private ServoEx leftvirtualFourBar, rightvirtualFourBar;
    public virtualFourBarExtensionState virtualFourBarExtension;
    double minAngle = 0, maxAngle= 360;

    public virtualFourBar(HardwareMap hardwareMap)
    {
        rightvirtualFourBar = new SimpleServo(hardwareMap, "rightvirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);
        leftvirtualFourBar = new SimpleServo(hardwareMap, "leftvirtualFourBar", minAngle, maxAngle, AngleUnit.DEGREES);

        leftvirtualFourBar.setInverted(true);
    }

    public void setvirtualFourBarPosition(virtualFourBarState virtualFourBarState, virtualFourBarExtensionState virtualFourBarExtensionState)
    {
        switch (virtualFourBarState)
        {
            case outtaking:
                leftvirtualFourBar.setPosition(outtakingLeft);
                rightvirtualFourBar.setPosition(outtakingRight);

                virtualFourBarExtension = virtualFourBarExtensionState.station;

                break;
            case intaking:
                leftvirtualFourBar.setPosition(intakingLeft);
                rightvirtualFourBar.setPosition(intakingRight);

                virtualFourBarExtension = virtualFourBarExtensionState.station;
                break;
            default:
                leftvirtualFourBar.setPosition(initLeft);
                rightvirtualFourBar.setPosition(initRight);
        }



    /*    public boolean virtualFourBarExtending (ServoEx v4b, double target) {
            double marginOfError = Math.abs(v4b.getPosition()+5-target);
            if(marginOfError > .05){
                return true;
            }
            else{
                return false;
            }
        }
*/

    }

    public virtualFourBarExtensionState getvirtualFourBarExtensionState()
    {
        return virtualFourBarExtension;
    }

    public boolean virtualFourBarExtending(ServoEx v4b, double target)
    {
        double marginOfError = Math.abs(v4b.getPosition() + 5 - target);

        if(marginOfError > .05)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}