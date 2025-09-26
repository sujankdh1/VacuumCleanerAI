# CS 557 - AI Homework 1: Vacuum-Cleaner Agent Simulation

This project is an implementation of a simple vacuum-cleaner agent for **CS 557 (Artificial Intelligence)**. The simulation tests the agent's performance under two different measures against two distinct environmental scenarios (percept sequences), as specified in Problem 2 of the assignment.

---

## 📜 Description

The program, written in **Java**, simulates a vacuum-cleaner agent in a two-location world (Location A and Location B). The agent's logic is simple: if its current location is dirty, it vacuums; otherwise, it moves.

The simulation runs four distinct test cases:
1.  **Measure A, Sequence 1:** Agent is rewarded for cleaning. The world is always dirty.
2.  **Measure A, Sequence 2:** Agent is rewarded for cleaning. The world becomes clean after two steps.
3.  **Measure B, Sequence 1:** Agent is rewarded for clean squares but penalized for moving. The world is always dirty.
4.  **Measure B, Sequence 2:** Agent is rewarded for clean squares but penalized for moving. The world becomes clean after two steps.

The program outputs a detailed log for each of the 100 time steps in every run, showing the agent's percept, action, step reward, and cumulative score.

---

## ⚙️ How to Run

To compile and run this project, you will need a **Java Development Kit (JDK)** installed on your system.

1.  **Clone the repository or download the source code.**

2.  **Navigate to the source code directory in your terminal.**
    * The `VacuumSimulator.java` file is located in the `com/example/demo/loadbalance` package. You should navigate to the parent directory containing the `com` folder (e.g., a `src` or `main` directory).

    ```bash
    cd path/to/your/project/src
    ```

3.  **Compile the Java file.**
    ```bash
    javac com/example/demo/loadbalance/VacuumSimulator.java
    ```

4.  **Run the compiled code.**
    ```bash
    java com.example.demo.loadbalance.VacuumSimulator
    ```

---

## 📊 Expected Output

Running the program will print the full simulation logs for all four runs directly to the console. The output will begin with "Run 1" and end after the final score for "Run 4," matching the logs submitted in the PDF solution.

#### Example output snippet:
```text
t=1 Percept=[A, Dirty]
Action=Vacuum StepReward=+1
Score=+1
t=2 Percept=[B, Dirty]
Action=Vacuum StepReward=+1
Score=+2
...
FINAL SCORE: 100
