package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Autonomous {
    /**************CHANGE VALUES**********************/
    private static Encoder armEncoder = new Encoder(0, 1);
    private static Encoder elevatorEncoder = new Encoder(0, 1);
    private static Encoder driveEncoder = new Encoder(0,1);
    private static int ppr = 2048;
    private static double wheelD = 0.5; //in feet
    private static double elevD = 0;
    
    public static void setUpEncoder(){
        driveEncoder.reset();
        driveEncoder.setDistancePerPulse(360/ppr*Math.PI*wheelD);
        armEncoder.reset();
        armEncoder.setDistancePerPulse(360/ppr); //only need rotation
        elevatorEncoder.reset();
        elevatorEncoder.setDistancePerPulse(360/ppr*Math.PI*elevD);
    }

    public static void drive15ft(){
        if(driveEncoder.getDistance() < 15){
            ArcadeDrive.startDriveAuto();
        } else {
            ArcadeDrive.stopDriveAuto();
        }
    }

    public static double getDriveDistance(){
        return driveEncoder.getDistance();
    }

    public static double getArmRotation(){
        return armEncoder.getDistance();
    }

    public static double getElevatorDistance(){
        return elevatorEncoder.getDistance();
    }
}
