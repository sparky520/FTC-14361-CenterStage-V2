package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.activeIntakeState;

public class ActiveIntake {
    double power = .4;
   DcMotorEx activeIntake;
    public ActiveIntake(HardwareMap hardwareMap) {
        activeIntake = hardwareMap.get(DcMotorEx.class, "activeIntake");

    }

    public void setActiveIntakePosition(activeIntakeState activeIntakeState){
        switch (activeIntakeState){
            case active:
                activeIntake.setPower(power);
                break;
            case activeReverse:
                activeIntake.setPower(-power);
                break;
            case inactive:
                activeIntake.setPower(0);
        }
    }
}
