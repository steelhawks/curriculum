---
sidebar_position: 3
---

# Programming Motors

So how do we program motors. First we have to go over something called vendordeps, then we can look at how to program motors.

## Vendordeps
As you know, we use a lot of different softwares and programs when we code. To have the robot understand and use these programs we need to install something called vendordeps, which are big libaries that we use to help our robot understand different mechanisms. To install them, go to the bottom-most icon on the left side and click it. In our case, when we're programming motors, so we'll search for something called CTRE. after you click install, it should start running the installation. After 1 min or so, it should be done. Now, you'll be able to control all motors made by CTRE, which include Talon SRX and Kraken's.

## Creating a motor
In your turret.java file in the Curriculum project (you thought we were done with that?), create a new variable like we create strings and doubles, but don't assign a value yet. **Remember to put this outside the constructor**. This time however, your variable should be a data type of TalonFX (make sure you click enter when the autocomplete option comes up). let's name the motor `s_motor`. The final line should be `private TalonFX s_motor;` TalonFX is the type of motor controller, and we're creating a new motor.

Next, we're going to assign a value to this variable in the constructor. 