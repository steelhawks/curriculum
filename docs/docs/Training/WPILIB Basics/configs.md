---
sidebar: 7
---

import Quiz from '@site/src/components/Quiz.jsx'


# Motor Configs
Now there are a lot of configs we apply to motors based on specific properties of the mechanism we're programming

## Initializing Motor Configs
Let's go back into our Turret file, and create a new variable called `turretConfigs`. This time, I'm not going give you the code for the variable, all I'm going to tell you is that the data type is `TalonFXConfiguration`, you decide everything else. After creating the variable of the motor, go into the constructor to assign a value. In robot container, type this line: `turretConfigs = new TalonFXConfiguration();` Now for the list of Configurations that can be made.

## Configurations
Here are the configs:

### Neutral Mode
Every motor has two brake modes, one called Coast and one called Brake. Coast means that the motor allows the mechanism to turn, so if you attached a gear to a motor and set the motor to coast mode, when the robot is off or not moving you will still be able to manually move the gear. Brake mode is the complete opposite, where the motor won't let you turn the gear at all. you implement it by saying `turretConfigs.MotorOutput.NeutralMode = NeutralMode.Coast;`

### Inverted
Inverted determines the relationship of the motor to it's orientation. What does this mean? Let's say you have one motor oriented towards you, and one oriented away. if both turn in the same direction, you won't be able to move forward as one motor spins one way and one spins the other. This is why we set one of the motors to be inverted, so we are able to spin the same way. `turretConfigs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;`

### Gear Ratio
If you connect a small gear to a larger one, you trade speed to gain extra lifting power. If you connect a large gear to a smaller one, you trade that power for much faster movement. You essentially adjust the ratio to match whether your robot needs to be a heavy-lifter or a fast sprinter. To add a gear ratio do this: `turretConfigs.Feedback.SensorToMechanismRatio = [whatever you're gear ratio is]`

### Current Limits
Current limits are pretty self explanatory in my opinion. For a motor to run, a certain amount of current needs to be given. We can limit the current in order to reduce the risk of brownouts, which is where our robot battery is giving too much power or doesn't have enough power because of drain. To add current limits we need two lines: `turretConfigs.CurrentLimit.SupplyCurrentLimit = [whatever you're current limit is]` and then `turretConfigs.CurrentLimit.SupplyCurrentLimitEnable = true`

> There are three types of current: Supply, Stator, and Torque. Supply current is the type of current taken **FROM** the battery **TO** the motor controller, stator is the current actually **FLOWING** through the motor, this is slightly different from supply current because some current will get dissapated. Lastly torque current is the current used to generate **TORQUE**, or the reason why things spin. 


### Follower Motor
Let's imagine this scenario, we have this wheel controlled by two motors and we're spinning it. We want both motors to run at the same speed, in the same direction. It would be a lot of work to have us write out the same methods to run both of them, and apply the same configs to both of them, and run them separately, so we create motor followers and leaders. With this kind of config, we can write out code for one motor that will automatically run on the follower. What does the code look like?

```
turretMotor1.setControl(new Follower(turretMotor2));
```

This creates a follower and leader, which simplifies a lot of code. 

### Slots
Slots (not slot machines) are specific values that can be filled with PID values (you'll learn about this in extreme depth later) and used by the motor. There are three slots that all have the PID values: `Slot0`, `Slot1` and the last one is impossible to guess: `Slot2`. These slots allow for many values of PID to be used. We mainly use `Slot0` configs, and have them be applied the same way. You'll learn more about these in the Control Theory section


## Applying These Configs

Now you've learned how to create configs, but how do we apply them to a motor?
To have configs running on a motor there are two steps: *create the configs*, *apply the configs*. 

To apply them you would do `turretMotor.getConfigurator().apply(configs);` But we use a special function that safely attempts to do this applying method x times, then quits if not possible:

 `PhoenixUtil.tryUntilOk(5, () -> turretMotor.getConfigurator().apply(configs));` 
 
This line uses a method called `tryUntilOk` to apply the configs `5` times This also takes in a lambda function, which you learned about in Java Basics as a shorthand function.

## Quiz Time

<Quiz questions={[
{
prompt: "Which data type must be used when initializing the 'turretConfigs' variable to store motor configurations?",
options: [
"TalonFX",
"TalonFXConfiguration",
"MotorOutput",
"PhoenixUtil"
],
correct: 1,
explanation: "The text explicitly states that the data type for the configuration variable is 'TalonFXConfiguration'."
},
{
prompt: "What is the primary physical difference between setting a motor to 'Coast' mode versus 'Brake' mode?",
options: [
"Coast mode makes the motor spin faster, while Brake mode increases its lifting power",
"Coast mode allows you to manually move the mechanism when the robot is off, while Brake mode prevents it from turning",
"Coast mode forces the motor to run counter-clockwise, while Brake mode forces it clockwise",
"Coast mode automatically enables current limits, while Brake mode disables them"
],
correct: 1,
explanation: "Coast mode leaves the motor free to spin manually when unpowered, whereas Brake mode resists movement entirely."
},
{
prompt: "Why must you set 'SupplyCurrentLimitEnable' to true when configuring a current limit?",
options: [
"To switch the motor from Stator current to Supply current",
"Because the specified limit value won't actually be applied unless the limit system is explicitly turned on",
"To completely prevent the robot from ever experiencing a brownout",
"To allow the motor to draw unlimited power from the battery"
],
correct: 1,
explanation: "Setting the limit value isn't enough; you must also explicitly enable the configuration flag for the motor controller to enforce it."
},
{
prompt: "According to the text, which type of current represents the electricity actually flowing *through* the motor itself?",
options: [
"Supply Current",
"Stator Current",
"Torque Current",
"Follower Current"
],
correct: 1,
explanation: "Stator current is the current flowing through the motor, which differs slightly from supply current because some energy is dissipated."
},
{
prompt: "In the line 'turretMotor1.setControl(new Follower(turretMotor2));', which motor automatically copies the actions of the other?",
options: [
"turretMotor1 follows turretMotor2",
"turretMotor2 follows turretMotor1",
"Both motors follow a third hidden leader motor",
"Neither motor; they will fight each other's directions"
],
correct: 0,
explanation: "The motor having its control set (turretMotor1) becomes the follower, and it takes the leader motor (turretMotor2) as its parameter."
},
{
prompt: "Why does the code use 'PhoenixUtil.tryUntilOk(5, ...)' instead of just applying the configurations directly?",
options: [
"It optimizes the code so that the motor moves faster",
"It safely attempts to apply the configurations up to 5 times, stopping if it fails, rather than risking a single failed attempt",
"It clears out old PID configurations from Slot1 and Slot2",
"It is the only way Java allows you to create a follower motor"
],
correct: 1,
explanation: "The 'tryUntilOk' utility function provides safety by repeatedly attempting to apply the configs a set number of times in case of temporary initialization hiccups."
}
]} />