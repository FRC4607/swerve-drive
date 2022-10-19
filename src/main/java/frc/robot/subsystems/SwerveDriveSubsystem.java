// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.threads.SwerveDriveThread;
import org.frc4607.common.swerve.SwerveDrive;

/**
 * Manages the drivetrain of the robot by talking to the thread containing the swerve drive handler.
 */
public class SwerveDriveSubsystem extends SubsystemBase {

    private final SwerveDriveThread m_thread;
    private final Notifier m_notifier;

    /**
     * Creates a new object and sets up the thread it uses.

     * @param swerveDrive The {@link org.frc4607.common.swerve.SwerveDrive} to operate on.
     */
    public SwerveDriveSubsystem(SwerveDrive swerveDrive) {
        m_thread = new SwerveDriveThread(swerveDrive);
        m_notifier = new Notifier(m_thread);
        m_notifier.startPeriodic(0.001);
    }

    /**
     * Sets the target {@link edu.wpi.first.math.kinematics.ChassisSpeeds} of the robot.

     * @param target The speeds for the drivetrain to target.
     */
    public void setSpeeds(ChassisSpeeds target) {
        m_thread.setSpeeds(target);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

}
