---
sidebar_position: 1
---

# Hardware 101
Welcome to Hardware 101! Congratulations again on completing basic java! I know it was hard, and you're still a bit confused, but with practice you'll get better. You have to have faith in the learning process. In this section we'll cover the different pieces of electrical equipment we program and use, as well as how they are all connected. When you're ready, let's begin!

## Systemcore

Welcome to our first piece of equipment. The systemcore is the 2027 brain of our robot. This is similar to a computer. It contains a cpu, and processes all our code. We then connect everything to the systemcore and then it runs our code on the correct mechanisms. You might be asking how do we send code to systemcore, well, checkout the radio section for information about how we send code.

## Radio
The radio is what we use to deploy code on the robot. It broadcasts a wifi signal, and we connect to it's wifi, then we click deploy robot code. When we deploy, the radio connects to the systemcore and then sends the code, which then get's processed. We do something called radio tethering, to learn more about it go to this link: **[FRC Radio Programming](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/radio-programming.html)**. The radio is extremely important in have a succesful robot, and we will talk more about radio tethering in Hardware 102

## PDP/PDH

The PDP or PDH are used for distributing power everywhere. We connect the battery to a breaker that connects to this device, then we use that power to send power out to the rest of the components that we need. This is like the heart, pumping out power to all the components.

## Motors

We use a variety of motors, mainly from this company called CTRE. CTRE produces motors called Kraken's, which we mainly use. We also use talon srx's, which is just another type of motor. These motors run everything on our robot from intake to wheels to turret to how we actually move on the floor. These all come with something called a motor controller, which we program