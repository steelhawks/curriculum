package org.steelhawks.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.steelhawks.Constants;

public class Shooter extends SubsystemBase {

    private final TalonFX master =
        new TalonFX(Constants.ShooterConstants.MOTOR1_ID,
            Constants.ShooterConstants.CAN_NAME);

    private final TalonFX follower =
        new TalonFX(Constants.ShooterConstants.MOTOR2_ID,
            Constants.ShooterConstants.CAN_NAME);

    // Runs a percentage of the motors total speed.
    private final DutyCycleOut shootRequest = new DutyCycleOut(0);

    public Shooter() {
        // Makes the motor follow the master, both motors have the same output as each other.
        follower.setControl(
            new Follower(master.getDeviceID(), false) // invert if needed
        );
    }

    /** Run shooter at a given speed (0.0 to 1.0) */
    public void run(double speed) {
        master.setControl(shootRequest.withOutput(speed));
    }

    /** Stop the shooter */
    public void stop() {
        run(0);
    }

    @Override
    public void periodic() {
        // Nothing needed here for now
    }
}
