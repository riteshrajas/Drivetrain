package frc.robot;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveForwardForTime extends SequentialCommandGroup {
		/** Creates a new DriveForwardForTime. */
		private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric()
				.withDriveRequestType(DriveRequestType.OpenLoopVoltage);
		private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();

		public DriveForwardForTime(CommandSwerveDrivetrain swerve, double time) {
				// Add your commands in the addCommands() call, e.g.
				// addCommands(new FooCommand(), new BarCommand());
				addCommands(
						new ParallelDeadlineGroup(
								new WaitCommand(time),
								swerve.applyRequest(
										() -> forwardStraight
												.withVelocityX(0.1)
												.withVelocityY(0)))
								.andThen(new ParallelDeadlineGroup(new WaitCommand(0), swerve.applyRequest(() -> brake))));
		}
}