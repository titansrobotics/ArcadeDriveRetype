package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ElevatorMotion {
    /***********CHANGE CHANNEL VALUE********/
    //Motors
    private static CANSparkMax elevatorLeft = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax elevatorRight = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax armRotate = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax clawLeft = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax clawRight = new CANSparkMax(0, MotorType.kBrushless);
    
    //Inputs
    private static Joystick elevatorJoy = new Joystick(1);
    private static JoystickButton raiseArmButton = new JoystickButton(elevatorJoy, 5);
    private static JoystickButton lowerArmButton = new JoystickButton(elevatorJoy, 3);
    private static JoystickButton clawClose = new JoystickButton(elevatorJoy, 1);
    private static JoystickButton clawOpen = new JoystickButton(elevatorJoy, 2);

    public static void moveElevator(){
        //adjust depending on how speed is dictated
        //currently just based on 80% raw input
        double elevatorSpeed = -elevatorJoy.getRawAxis(1)*0.3;
        elevatorLeft.set(elevatorSpeed);
        elevatorRight.set(-elevatorSpeed);
    }

    public static void moveArm(){
        if(raiseArmButton.getAsBoolean()){
            armRotate.set(0.5);
        } else if(lowerArmButton.getAsBoolean()){
            armRotate.set(-0.5);
        } else {
            armRotate.set(0);
        }
    }

    public static void moveClaw(){
        if(clawClose.getAsBoolean()){
            clawLeft.set(0.2);
            clawRight.set(-0.2);
        } else if(clawOpen.getAsBoolean()){
            clawLeft.set(-0.2);
            clawRight.set(0.2);
        } else {
            clawLeft.set(0.05);
            clawRight.set(-0.05);
        }
    }
}
