package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ElevatorMotion {
    /***********CHANGE CHANNEL VALUE********/
    //Motors
    private static CANSparkMax elevatorLeft = new CANSparkMax(6, MotorType.kBrushless);
    private static CANSparkMax elevatorRight = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax armRotate = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax clawLeft = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax clawRight = new CANSparkMax(0, MotorType.kBrushless);
    
    //Inputs
    private static Joystick elevatorJoy = new Joystick(1);
    private static JoystickButton raiseButton = new JoystickButton(elevatorJoy, 5);
    private static JoystickButton lowerButton = new JoystickButton(elevatorJoy, 3);

    public static void moveElevator(){
        //adjust depending on how speed is dictated
        //currently just based on 80% raw input
        double elevatorSpeed = -elevatorJoy.getRawAxis(1)*0.2;
        elevatorLeft.set(elevatorSpeed);
        elevatorRight.set(-elevatorSpeed);
    }

    public static void moveArm(){
        if(raiseButton.getAsBoolean()){
            armRotate.set(0.7);
        } else if(lowerButton.getAsBoolean()){
            armRotate.set(-0.7);
        } else {
            armRotate.set(0);
        }
    }

    public static void moveClaw(){

    }
}
