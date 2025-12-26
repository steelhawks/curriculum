# Elevator Subsystem – Structure Overview

This document explains the **structure and intent** of the Elevator subsystem, focusing on how the **beginner core** and the **advanced layer** fit together. The goal is to make the codebase readable, teachable, and scalable.

---

## 1. Design Philosophy

This elevator is structured with **two clear layers**:

1. **Beginner Core** – simple, direct motor control that rookies can understand
2. **Advanced Layer** – safety, automation, profiling, tuning, and logging

> Rule: **You should be able to delete the advanced layer and still have a working elevator.**

---

## 2. Beginner Core (What Rookies Should Focus On)

### Responsibilities

The beginner portion answers only four questions:

* How do we move the elevator **up/down manually**?
* How do we move it to a **position**?
* How do we **stop** it?
* How do we read the **current position**?

### Key Concepts

* One **master TalonFX**
* One **follower TalonFX** using `new Follower(...)`
* Open-loop control (`DutyCycleOut`) for manual movement
* Closed-loop position control for presets

### Mental Model

```
Joystick / Button
        ↓
   Elevator Subsystem
        ↓
     Master Motor ───► Follower Motor
```

If a rookie understands this flow, they understand the elevator.

---

## 3. Advanced Layer (Why It Exists)

The advanced section exists to solve **real competition problems**, not to teach basics.

### Problems It Solves

* Elevator slamming into hard stops
* Brownouts or overcurrent
* Oscillation near setpoints
* Driver abuse / accidental commands
* Autonomous precision

---

## 4. Advanced Features Breakdown

### A. IO Abstraction (`ElevatorIO`)

**Purpose:** Separate *what the elevator does* from *how the hardware does it*.

* Allows:

  * Sim vs real robot
  * TalonFX vs other motors
  * Cleaner testing

> Rookies should NOT modify IO classes early on.

---

### B. Motion Profiling (TrapezoidProfile)

**Purpose:** Prevents violent movement by enforcing:

* Max velocity
* Max acceleration

Instead of:

```
Position jumps instantly
```

We get:

```
Smooth ramp → cruise → smooth stop
```

This protects:

* Chain/belt
* Gearboxes
* Motor shafts

---

### C. Feedforward (kS, kG, kA)

**Purpose:** Help the motor *before* PID has to react.

* `kG`: holds the elevator against gravity
* `kS`: overcomes static friction
* `kA`: helps during acceleration

PID corrects error.
Feedforward prevents error.

---

### D. Safety & Limits

Implemented protections include:

* Bottom and top soft limits
* Reverse limit switch homing
* Brake/coast control
* Emergency stop integration

> These are **non-negotiable** on a real robot.

---

### E. Manual Override Mode

**Why:** Drivers and techs need control when automation fails.

Manual mode:

* Disables profiles
* Disables auto goals
* Uses current or duty-cycle control

This is your **"break glass"** mode.

---

### F. SysId & Tuning

**Purpose:** Characterize the elevator to get real constants.

* Measures kS, kV, kA
* Improves auto consistency
* Makes PID easier

This is **post-rookie content**.

---

## 5. State-Based Control

The elevator uses **states** (HOME, L1–L4, etc.) instead of raw numbers.

Why this matters:

* Code reads like intent
* Commands are safer
* Easier autonomous integration

Example:

```
setDesiredState(L3)
```

vs

```
goToPosition(7.42)
```

---

## 6. Logging & Debugging

Advanced logging provides:

* Position
* Velocity
* Goal vs setpoint
* Motor current & temperature

This is critical for:

* Debugging at events
* Detecting mechanical issues early

---

## 7. How Rookies Should Learn This File

### Phase 1 – Required

* Master vs follower
* Manual movement
* Encoder position

### Phase 2 – Introduced

* Position control
* Brake vs coast
* Limit switches

### Phase 3 – Advanced

* Motion profiles
* Feedforward
* SysId
* IO abstraction

> Do NOT skip phases.

---

## 8. Final Rule (Very Important)

**Complexity is earned, not added.**

This elevator works because:

* The beginner layer is simple
* The advanced layer is justified
* Each feature solves a real problem

If you can’t explain why a feature exists, it doesn’t belong.
