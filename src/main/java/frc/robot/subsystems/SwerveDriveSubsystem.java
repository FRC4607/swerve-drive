// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.frc4607.common.swerve.*;
import org.frc4607.common.swerve.SwerveDrive.ModuleInfo;

public class SwerveDriveSubsystem extends SubsystemBase {
    private final SwerveDrive drive;

    private SwerveDriveSubsystem(ModuleInfo frontLeft, ModuleInfo frontRight, ModuleInfo backLeft, ModuleInfo backRight) {
        drive = new SwerveDrive(1, frontLeft, frontRight, backLeft, backRight);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    public static SwerveDriveSubsystem create()
}
