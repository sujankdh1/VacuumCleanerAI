package com.example.demo.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class simulates a vacuum-cleaner agent in a two-location world (A and B).
 * It runs four different simulations based on two performance measures and two percept sequences,
 * logging the agent's behavior and final score for each run.
 * Problem 2 in the homework assignment.
 */

public class VacuumSimulator {

    // A simple class (or record in modern Java) to hold the percept data.
    // Using a record is a concise way to create an immutable data carrier.
    public record Percept(String location, String status) {}

    public static void runSimulation(int measure, List<Percept> sequence, String runName, String measureDescription) {
        Map<String, String> worldState = new HashMap<>();
        worldState.put("A", "Dirty");
        worldState.put("B", "Dirty");

        int score = 0;

        // --- Print Header ---
        // This section formats the output to match the required log format.
        System.out.println("--- " + runName + " ---");
        System.out.println("Run: Measure = \"" + measureDescription + "\"");
        System.out.println("     Sequence = " + runName.split("Sequence ")[1]);
        System.out.println("-".repeat(25));

        // The main simulation loop, running for exactly 100 time steps (t=0 to t=99).
        for (int t = 0; t < 100; t++) {
            Percept currentPercept = sequence.get(t);
            String location = currentPercept.location();
            String status = currentPercept.status();
            String action;

            // Agent's simple reflex logic
            if ("Dirty".equals(status)) {
                action = "Vacuum";
            } else {
                action = "Move";
            }

            int stepReward = 0;

            if (measure == 1) {
                // Measure 1: +1 for each location cleaned.
                if ("Vacuum".equals(action) && "Dirty".equals(status)) {
                    stepReward = 1;
                }
            } else if (measure == 2) {
                // Measure 2: +1 per clean square per step; -1 per move.

                // First, update the world state based on the agent's action.
                if ("Vacuum".equals(action)) {
                    worldState.put(location, "Clean");
                }

                // Next, calculate the reward based on the *new* state.
                int rewardFromState = 0;
                if ("Clean".equals(worldState.get("A"))) {
                    rewardFromState++;
                }
                if ("Clean".equals(worldState.get("B"))) {
                    rewardFromState++;
                }

                int penaltyFromAction = "Move".equals(action) ? -1 : 0;
                stepReward = rewardFromState + penaltyFromAction;
            }

            score += stepReward;

            // --- Print Log Entry for this Step ---
            System.out.printf("t=%d Percept=[%s, %s]\n", t + 1, location, status);
            System.out.printf("Action=%s StepReward=%+d\n", action, stepReward);
            System.out.printf("Score=%+d\n", score);
        }

        System.out.println("FINAL SCORE: " + score);
        System.out.println("\n" + "=".repeat(40) + "\n");
    }

    public static void main(String[] args) {
        // --- Define the two percept sequences for the 100 time-step simulation ---

        // Sequence 1: The world is always dirty. The agent will always perceive "Dirty".
        List<Percept> sequence1 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            sequence1.add(new Percept("A", "Dirty"));
            sequence1.add(new Percept("B", "Dirty"));
        }

        // Sequence 2: The world is dirty for two steps, then clean.
        List<Percept> sequence2 = new ArrayList<>();
        sequence2.add(new Percept("A", "Dirty"));
        sequence2.add(new Percept("B", "Dirty"));
        for (int i = 0; i < 49; i++) {
            sequence2.add(new Percept("A", "Clean"));
            sequence2.add(new Percept("B", "Clean"));
        }

        // --- Define descriptions for logging ---
        String measure1Desc = "+1 point for each location cleaned";
        String measure2Desc = "+1 per clean square per step; -1 per move";

        // --- Run all four required test cases as specified in the problem ---
        runSimulation(1, sequence1, "Run 1: Perf. Measure 1, Sequence 1", measure1Desc);
        runSimulation(1, sequence2, "Run 2: Perf. Measure 1, Sequence 2", measure1Desc);
        runSimulation(2, sequence1, "Run 3: Perf. Measure 2, Sequence 1", measure2Desc);
        runSimulation(2, sequence2, "Run 4: Perf. Measure 2, Sequence 2", measure2Desc);
    }
}
