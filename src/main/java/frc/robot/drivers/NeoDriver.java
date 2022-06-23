package frc.robot.drivers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import org.frc4607.common.swerve.SwerveMotorBase;

/**
 * Extends the {@link org.frc4607.common.swerve.SwerveMotorBase} for REV Neo brushless motors.
 */
public class NeoDriver extends SwerveMotorBase {
    private final CANSparkMax m_motor;
    private RelativeEncoder m_neoEncoder;

    public NeoDriver(int id) {
        m_motor = new CANSparkMax(id, MotorType.kBrushless);
        m_neoEncoder = m_motor.getEncoder();
    }

    public boolean isConnected() {
        return m_motor.getFaults() == 0 && m_motor.getStickyFaults() == 0;
    }

    public double getEncoderPosition() {
        return m_neoEncoder.getPosition();
    }
    
    public double getEncoderVelocity() {
        return m_neoEncoder.getVelocity();
    }

    public void setEncoder(double target) {
        m_neoEncoder.setPosition(target);
    }

    public void setTarget(double target) {

    }
}
