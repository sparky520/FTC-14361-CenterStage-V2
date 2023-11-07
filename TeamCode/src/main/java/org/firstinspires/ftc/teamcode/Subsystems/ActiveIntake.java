package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.ActiveIntakeState;
import org.firstinspires.ftc.teamcode.Utilities.RobotConstants;

public class ActiveIntake
{
   DcMotorEx activeIntake;
    public ActiveIntake(HardwareMap hardwareMap)
    {
        activeIntake = hardwareMap.get(DcMotorEx.class, "activeIntake");
    }

    public void setActiveIntakePosition(ActiveIntakeState activeIntakeState)
    {
        switch (activeIntakeState)
        {
            case active:
                activeIntake.setPower(RobotConstants.ActiveIntake.active);
                break;
            case activeReverse:
                activeIntake.setPower(RobotConstants.ActiveIntake.reverseActive);
                break;
            case inactive:
                activeIntake.setPower(0);
        }
    }
}
