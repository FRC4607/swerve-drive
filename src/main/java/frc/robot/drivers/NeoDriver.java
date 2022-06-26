package frc.robot.drivers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.AccelStrategy;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import org.frc4607.common.swerve.SwerveDriverConfig;
import org.frc4607.common.swerve.SwerveMotorBase;

/**
 * Extends the {@link org.frc4607.common.swerve.SwerveMotorBase} for REV Neo brushless motors.
 */
public class NeoDriver extends SwerveMotorBase {
    private final CANSparkMax m_motor;
    private final RelativeEncoder m_neoEncoder;
    private final SparkMaxPIDController m_pidController;

    /**
     * Constructs a new instance of this class.
     *
     * @param config The settings for this module.
     */
    public NeoDriver(SwerveDriverConfig config) {
        super(config);

        m_motor = new CANSparkMax(m_config.m_id, MotorType.kBrushless);
        m_motor.setInverted(m_config.m_invertMotor);

        m_neoEncoder = m_motor.getEncoder();
        m_neoEncoder.setPositionConversionFactor(config.m_positionCoefficient);
        m_neoEncoder.setVelocityConversionFactor(config.m_velocityCoefficient);

        m_pidController = m_motor.getPIDController();
        m_pidController.setP(m_config.m_kp, 0);
        m_pidController.setI(m_config.m_ki, 0);
        m_pidController.setD(m_config.m_kd, 0);
        m_pidController.setIMaxAccum(m_config.m_maxI, 0);
        m_pidController.setIZone(m_config.m_kiZone, 0);
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
