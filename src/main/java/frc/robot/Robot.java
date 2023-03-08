// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
//camera server import

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  private double startTime;

  @Override
  public void robotInit() {
    //Forces the cameras to start. Dunno if we actually need this but I'm adding it
    // just to make sure we get both camera streams up and running.
    //Autonomous.setUpEncoder();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    //Autonomous.setUpEncoder();
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    //Autonomous.drive15ft();
    if(time - startTime < 0.5){
      ElevatorMotion.tossAuto();
    } else if(time - startTime < 2){
      ElevatorMotion.stopTossAuto();
      ArcadeDrive.startDriveBackAuto();
    } else{
      ArcadeDrive.stopDriveBackAuto();
    }
  }

  @Override
  public void teleopInit() {}


  @Override
  public void teleopPeriodic() {
    //test
    
    // if(Autonomous.getDriveDistance() < 10){
    //    ArcadeDrive.startDriveAuto();
    //  } else {
    //    ArcadeDrive.stopDriveAuto();
    //  }
    //ElevatorMotion.modes();
    ArcadeDrive.arcadeDrive();

    ElevatorMotion.moveElevator();
    ElevatorMotion.moveArm();
    ElevatorMotion.moveClaw();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
