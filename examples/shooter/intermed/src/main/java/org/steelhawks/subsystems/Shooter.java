package org.steelhawks.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.steelhawks.Constants;

public class Shooter extends SubsystemBase {

    private final TalonFX master =
        new TalonFX(Constants.ShooterConstants.MOTOR1_ID,
            Constants.ShooterConstants.CAN_NAME);

    private final TalonFX follower =
        new TalonFX(Constants.ShooterConstants.MOTOR2_ID,
            Constants.ShooterConstants.CAN_NAME);

    private final VelocityVoltage velocityRequest = new VelocityVoltage(0.0);

    public Shooter() {
        // PID + Feedforward
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Slot0.kP = 0.12;
        config.Slot0.kI = 0.0;
        config.Slot0.kD = 0.0;
        config.Slot0.kV = 0.12;

        master.getConfigurator().apply(config);

        // Makes the motor follow the master, both motors have the same output as each other.
        follower.setControl(
            new Follower(master.getDeviceID(), true) // true = invert follower
        );
    }

    /** Run shooter at target velocity (RPS) */
    public void runVelocity(double rps) {
        master.setControl(velocityRequest.withVelocity(rps));
    }

    /** Stop shooter */
    public void stop() {
        runVelocity(0);
    }

    /** Current velocity in RPS */
    public double getVelocity() {
        return master.getVelocity().getValueAsDouble();
    }

    @Override
    public void periodic() {
        // Optional logging later
    }
}
