package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;

/**
 * Default command for the drivetrain that updates it from the controller.
 */
public class TeleOp extends CommandBase {
    private final SwerveDriveSubsystem m_subsystem;

    public TeleOp(SwerveDriveSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

}
