package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;
import org.firstinspires.ftc.teamcode.util.robotConstants;

public class activeIntake
{
   DcMotorEx activeIntake;
    public activeIntake(HardwareMap hardwareMap)
    {
        activeIntake = hardwareMap.get(DcMotorEx.class, "activeIntake");
    }

    public void setActiveIntakePosition(activeIntakeState activeIntakeState)
    {
        switch (activeIntakeState)
        {
            case active:
                activeIntake.setPower(robotConstants.ActiveIntake.active);
                break;
            case activeReverse:
                activeIntake.setPower(robotConstants.ActiveIntake.reverseActive);
                break;
            case inactive:
                activeIntake.setPower(0);
        }
    }
}
