package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.DigitalInput;

//if errors reload projects (Shift+Alt+U)
public class ElevatorMotion {
    /***********CHANGE CHANNEL VALUE********/
    //Motors
    private static CANSparkMax elevLeft = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax elevRight = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax armRotate = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax clawLeft = new CANSparkMax(0, MotorType.kBrushless);
    private static CANSparkMax clawRight = new CANSparkMax(0, MotorType.kBrushless);

    

    //Limit Switches
    private static DigitalInput topElevLimit = new DigitalInput(0);
    private static DigitalInput bottomElevLimit = new DigitalInput(1);

    //Inputs
    private static Joystick elevatorJoy = new Joystick(1);
    private static JoystickButton raiseArmButton = new JoystickButton(elevatorJoy, 5);
    private static JoystickButton lowerArmButton = new JoystickButton(elevatorJoy, 3);
    private static JoystickButton clawClose = new JoystickButton(elevatorJoy, 1);
    private static JoystickButton clawOpen = new JoystickButton(elevatorJoy, 2);
    // private static JoystickButton mode1 = new JoystickButton(elevatorJoy, 8);
    // private static JoystickButton mode2 = new JoystickButton(elevatorJoy, 10);
    // private static JoystickButton mode3 = new JoystickButton(elevatorJoy, 12);


    public static boolean moveElevator(){
        //adjust depending on how speed is dictated
        //boolean return describes if a switch is in contact
        //currently just based on 80% raw input
        double elevatorSpeed = -elevatorJoy.getRawAxis(1)*0.8;
        if(topElevLimit.get() && elevatorSpeed > 0){
            elevLeft.set(0);
            elevRight.set(0);
            return true;
        } else if(bottomElevLimit.get() && elevatorSpeed < 0){
            elevLeft.set(0);
            elevRight.set(0);
            return true;
        }else{
            elevLeft.set(elevatorSpeed);
            elevRight.set(-elevatorSpeed);
            return false;
        }
    }

    public static void moveArm(){
        if(raiseArmButton.getAsBoolean()){
            armRotate.set(0.3);
        } else if(lowerArmButton.getAsBoolean()){
            armRotate.set(-0.3);
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
            //auto holding that may be unnecessary
            clawLeft.set(0.05);
            clawRight.set(-0.05);
        }
    }
}
