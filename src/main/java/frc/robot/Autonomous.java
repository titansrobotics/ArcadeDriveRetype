package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Autonomous {
    /**************CHANGE VALUES**********************/
    private static Encoder armEncoder = new Encoder(2, 3);
    private static Encoder driveEncoder = new Encoder(0,1);
    private static double ppr = 512.0;
    private static double wheelD = 0.5; //in feet
    private static int driveGearBox = 9;

    public static void setUpEncoder(){
        driveEncoder.reset();
        driveEncoder.setDistancePerPulse(360/ppr*Math.PI*wheelD*driveGearBox);
        armEncoder.reset();
        armEncoder.setDistancePerPulse(360/ppr); //only need rotation
    }

    // public static void drive15ft(){
    //     if(driveEncoder.getDistance() < 15){
    //         ArcadeDrive.startDriveAuto();
    //     } else {
    //         ArcadeDrive.stopDriveAuto();
    //     }
    // }

    public static double getDriveDistance(){
        return driveEncoder.getDistance();
    }

    public static double getArmRotation(){
        return armEncoder.getDistance();
    }
}
