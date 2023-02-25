package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class ElevatorMotion {
    /***********CHANGE CHANNEL VALUE********/
    //Motors
    private static PWMSparkMax elevatorMotorLeft = new PWMSparkMax(0);
    private static PWMSparkMax elevatorMotorRight = new PWMSparkMax(0);
    private static MotorControllerGroup elevatorMotors = new MotorControllerGroup(elevatorMotorLeft, elevatorMotorRight);
    private static PWMSparkMax armMotor = new PWMSparkMax(0);
    
    //Inputs
    private static Joystick elevatorJoy = new Joystick(1);
    private static JoystickButton raiseButton = new JoystickButton(elevatorJoy, 5);
    private static JoystickButton lowerButton = new JoystickButton(elevatorJoy, 3);

    public static void moveElevator(){
        //adjust depending on how speed is dictated
        //currently just based on 80% raw input
        double joySpeed = -elevatorJoy.getRawAxis(1)*0.8;
        elevatorMotors.set(joySpeed);
    }

    public static void moveArm(){
        if(raiseButton.getAsBoolean()){
            armMotor.set(0.9);
        } else if(lowerButton.getAsBoolean()){
            armMotor.set(-0.9);
        } else {
            armMotor.set(0);
        }
    }
}
