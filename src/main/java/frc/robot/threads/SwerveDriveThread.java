package frc.robot.threads;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import org.frc4607.common.swerve.SwerveDrive;

/**
 * A class representing a separate thread running the swerve drive controller at a higher update
 * rate than the main loop. Methods in this class should be thread safe.
 */
public class SwerveDriveThread implements Runnable {
    private final SwerveDrive m_drivetrain;

    private volatile ChassisSpeeds m_lastSpeed = new ChassisSpeeds(0, 0, 0);
    private final Object m_lastSpeedLock = new Object();

    public SwerveDriveThread(SwerveDrive drive) {
        m_drivetrain = drive;
    }

    private void periodic() {
        synchronized (m_lastSpeedLock) {
            m_drivetrain.update(m_lastSpeed);
        }
    }

    /**
     * Sets the target {@link edu.wpi.first.math.kinematics.ChassisSpeeds} of the robot.

     * @param target The speeds for the drivetrain to target.
     */
    public void setSpeeds(ChassisSpeeds target) {
        synchronized (m_lastSpeedLock) {
            m_lastSpeed = target;
        }
    }

    /**
     * Runs the periodic loop of the object.
     */
    public void run() {
        periodic();
    }
}
