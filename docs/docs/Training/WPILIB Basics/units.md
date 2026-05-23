---
sidebar_position: 5
---

# WPILIB Data Types
In Java Basics, you learned about the main data types that are used for all of programming, but we have some special WPILIB based data types that you should be aware about, so you aren't limited to just the basic data types.

## Why Do We Need These?
In robotics, a lot of math is modeled through coding, so having accurate units for each piece of data is important. WPILIB data types allow for you to account units in the data, so each value is different based on the unit. We also model the positions of certain mechanisms. A mechanism is a 3d object, having an x, y, and z axis. Instead of modeling this with a multidimensional array of doubles, we use a WPILIB data type.

## Data Types
Let's start listing out some basic data types:

**Suppliers** What are suppliers? Suppliers are a class of data types, which are basically containers for certain values. Think of it like a constantly updating value when a data type is put into a supplier, rather than a once given data value. Let's imagine a function takes in a parameter of `DoubleSupplier age`, then we would provide this as the double supplier: `() -> 3`. look familiar? The way to provide a DoubleSupplier is by using a shorthand function (lambda function)! To get the value of `age`, we would use `age.get()`, which would return 3, instead of an object. 

**Poses**
Pose is short for position. In WPILIB we have `Pose2d` and `Pose3d`. Both of these represent a  position in 2d space and in 3d space. Poses are useful for modeling components in a 3d space with a single variable. We intialize a Pose by saying `Pose2d` or `Pose3d`, then the variable name. For example `Pose3d something = new Pose3d(0, 0, 0)` To retrieve data from a Pose2d or Pose3d, we have a variety of methods. 

