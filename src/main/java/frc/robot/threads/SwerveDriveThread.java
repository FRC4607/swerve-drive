package frc.robot.threads;

import org.frc4607.common.swerve.SwerveDrive;

/**
 * Holds a {@link org.frc4607.common.swerve.SwerveDrive} in a separate thread from the main
 * program and updates it.
 */
public class SwerveDriveThread implements Runnable {
    private boolean m_initialized = false;

    private SwerveDrive m_drivetrain;

    private SwerveDriveThread() {

    }

    public void periodic() {

    }

    public void run() {
        m_drivetrain = new SwerveDrive(modules)
    }
}
