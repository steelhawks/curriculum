package org.steelhawks.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.MathUtil;
import org.steelhawks.Constants;

public class Elevator extends SubsystemBase {

    private final TalonFX master =
        new TalonFX(Constants.Elevator.LEFT_MOTOR_ID, Constants.getCANBus());

    private final TalonFX follower =
        new TalonFX(Constants.Elevator.RIGHT_MOTOR_ID, Constants.getCANBus());

    private final DutyCycleOut dutyRequest = new DutyCycleOut(0);
    private final PositionVoltage positionRequest = new PositionVoltage(0);

    public Elevator() {
        // Basic config
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.NeutralMode =
            com.ctre.phoenix6.signals.NeutralModeValue.Brake;

        // VERY simple PID (position)
        config.Slot0.kP = 2.0;
        config.Slot0.kI = 0.0;
        config.Slot0.kD = 0.0;

        master.getConfigurator().apply(config);

        // Proper Phoenix 6 follower
        follower.setControl(
            new Follower(master.getDeviceID(), true) // invert if needed
        );
    }

    /** Manual elevator control (-1.0 to 1.0) */
    public void runManual(double speed) {
        speed = MathUtil.clamp(speed, -0.5, 0.5); // safety
        master.setControl(dutyRequest.withOutput(speed));
    }

    /** Move elevator to a position (rotations) */
    public void goToPosition(double rotations) {
        master.setControl(positionRequest.withPosition(rotations));
    }

    /** Stop elevator */
    public void stop() {
        master.stopMotor();
    }

    /** Zero encoders at bottom */
    public void zero() {
        master.setPosition(0);
    }

    /** Get elevator position (rotations) */
    public double getPosition() {
        return master.getPosition().getValueAsDouble();
    }

    @Override
    public void periodic() {
        // Optional: SmartDashboard logging later
    }
}
