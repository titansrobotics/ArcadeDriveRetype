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

    //Inputs
    private static Joystick elevatorJoy = new Joystick(1);
    private static JoystickButton raiseArmButton = new JoystickButton(elevatorJoy, 5);
    private static JoystickButton lowerArmButton = new JoystickButton(elevatorJoy, 3);
    private static JoystickButton clawClose = new JoystickButton(elevatorJoy, 1);
    private static JoystickButton clawOpen = new JoystickButton(elevatorJoy, 2);
    private static JoystickButton mode1 = new JoystickButton(elevatorJoy, 8);
    private static JoystickButton mode2 = new JoystickButton(elevatorJoy, 10);
    private static JoystickButton mode3 = new JoystickButton(elevatorJoy, 12);
    private static JoystickButton eject1 = new JoystickButton(elevatorJoy, 7);
    private static JoystickButton eject2 = new JoystickButton(elevatorJoy, 9);

    //Speeds
    private static double elevatorDefaultSpeed = 0.8;
    private static double armRotateDefaultSpeed = 0.3;
    private static double clawDefaultSpeed = 0.2;

    //Max Movement
    private static double armAngle = 0;
    private static double elevatorDistance = 0; //in feet

    public static void moveElevator(){
        //boolean return describes if a switch is in contact
        double elevatorSpeed = -elevatorJoy.getRawAxis(1)*elevatorDefaultSpeed;
        if(topElevLimit.get() && elevatorSpeed > 0){
            elevLeft.set(0);
            elevRight.set(0);
       } else if(Autonomous.getElevatorDistance() < 1/12.0 && elevatorSpeed < 0){
            elevLeft.set(0);
            elevRight.set(0);
        }else{
            elevLeft.set(elevatorSpeed);
            elevRight.set(elevatorSpeed);
        }
    }

    public static void raiseElevator(){
        elevLeft.set(elevatorDefaultSpeed);
        elevRight.set(elevatorDefaultSpeed);
    }

    public static void lowerElevator(){
        elevLeft.set(-elevatorDefaultSpeed);
        elevRight.set(-elevatorDefaultSpeed);   }

    public static void stopElevator(){
        elevLeft.set(0);
        elevRight.set(0);
    }

    public static void moveArm(){
        if(raiseArmButton.getAsBoolean() && Autonomous.getArmRotation() < armAngle){
            armRotate.set(armRotateDefaultSpeed);
        } else if(lowerArmButton.getAsBoolean() && Autonomous.getArmRotation() > 1){
            armRotate.set(-armRotateDefaultSpeed);
        } else {
            armRotate.set(0);
        }
    }

    public static void raiseArm(){
        armRotate.set(armRotateDefaultSpeed);
    }

    public static void lowerArm(){
        armRotate.set(-armRotateDefaultSpeed);
    }
    
    public static void stopArm(){
        armRotate.set(0);
    }

    public static void moveClaw(){
        if(clawClose.getAsBoolean()){
            clawLeft.set(clawDefaultSpeed);
            clawRight.set(-clawDefaultSpeed);
        } else if(clawOpen.getAsBoolean()){
            clawLeft.set(-clawDefaultSpeed);
            clawRight.set(clawDefaultSpeed);
        } else {
            //auto holding that may be unnecessary
            clawLeft.set(0);
            clawRight.set(0);
        }
    }

    public static void modes(){
        boolean armPositioned = false, elevPositioned = false;
        if(mode1.getAsBoolean() ){
            while(true){
                if(eject1.getAsBoolean() && eject2.getAsBoolean()) break;
                if(Autonomous.getArmRotation() > 1){
                    lowerArm();
                } else {
                    armPositioned = true;
                }
                if(Autonomous.getElevatorDistance() > 1/12.0){
                    lowerElevator();
                } else {
                    elevPositioned = true;
                }
                if(armPositioned && elevPositioned){
                    break;
                }
            }
        } else if(mode2.getAsBoolean() ){
            while(true){
                if(eject1.getAsBoolean() && eject2.getAsBoolean()) break;
                if(Autonomous.getArmRotation() < armAngle){
                    raiseArm();
                } else {
                    armPositioned = true;
                }
                if(Autonomous.getElevatorDistance() > 1/12.0){
                    lowerElevator();
                } else {
                    elevPositioned = true;
                }
                if(armPositioned && elevPositioned){
                    break;
                }
            }
        } else if(mode3.getAsBoolean() ){
            while(true){
                if(eject1.getAsBoolean() && eject2.getAsBoolean()) break;
                if(Autonomous.getArmRotation() < armAngle){
                    raiseArm();
                } else {
                    armPositioned = true;
                }
                if(Autonomous.getElevatorDistance() < elevatorDistance){
                    raiseElevator();
                } else {
                    elevPositioned = true;
                }
                if(armPositioned && elevPositioned){
                    break;
                }
            }
        }

    }
}
