package frc.robot.drivers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;
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

    @Override
    public boolean isConnected() {
        return m_motor.getFaults() == 0 && m_motor.getStickyFaults() == 0;
    }

    /** 
     * Gets the position of the motor.
     *
     * @return The distance in meters if this is a drive motor or the rotation in CCW
     postive degrees if this is a turning motor.
     */
    @Override
    public double getEncoderPosition() {
        if (m_config.m_motorType == SwerveDriverConfig.MotorType.TURNING) {
            return m_quadEncoder.getDistance() + m_offset;
        } else {
            return m_neoEncoder.getPosition();
        }
    }
    
    /** 
     * Gets the velocity of the motor.
     *
     * @return The velocity in meters per second if this is a drive motor or the velocity in CCW
     postive degrees per second if this is a turning motor.
     */
    @Override
    public double getEncoderVelocity() {
        if (m_config.m_motorType == SwerveDriverConfig.MotorType.TURNING) {
            return m_quadEncoder.getRate();
        } else {
            return m_neoEncoder.getVelocity();
        }
    }

    @Override
    public void setEncoder(double target) {
        m_neoEncoder.setPosition(target);
    }

    /**
     * Sets the target of the motor to a setpoint.
     *
     * @param target The target to set the motor to. Will be in meters per second for the drive
     motor and degrees or the turning motor.
     */
    @Override
    public void setTarget(double target, double ffVolts) {
        if (m_config.m_motorType == SwerveDriverConfig.MotorType.TURNING) {
            m_pidController.setReference(target,
                ControlType.kPosition, 0, ffVolts, ArbFFUnits.kVoltage);
        } else {
            m_pidController.setReference(target,
                ControlType.kVelocity, 0, ffVolts, ArbFFUnits.kVoltage);
        }
    }
}
