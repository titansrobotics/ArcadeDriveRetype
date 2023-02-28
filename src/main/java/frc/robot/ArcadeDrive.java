package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;

public class ArcadeDrive {

    private static WPI_VictorSPX leftMotor = new WPI_VictorSPX(1);
    private static WPI_VictorSPX rightMotor = new WPI_VictorSPX(3);

    private static Joystick driveJoy = new Joystick(0);

    public static void arcadeDrive(){
        double speed = -driveJoy.getRawAxis(1)*0.6;
        double turn = driveJoy.getRawAxis(2)*0.3;

        double left = speed + turn;
        double right = speed - turn;

        leftMotor.set(left);
        rightMotor.set(-right);
    }
}