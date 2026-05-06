package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.rover.Rover;

import java.util.*;

public class RoverSystem {
    private final Map<String, Rover> rovers = new HashMap<>();
    private final Map<String, RoverCommands> roverCommands = new HashMap<>();

    public void addRover(String id ,Rover rover) {
        rovers.put(id, rover);
    }

    public void addCommands(String id, RoverCommands roverCommands) {
        this.roverCommands.put(id, roverCommands);
    }

    public void execute() throws IllegalArgumentException {
        for (String id : rovers.keySet()) {
            Rover rover = rovers.get(id);
            RoverCommands commands = roverCommands.get(id);

            if (commands == null) {
                throw new IllegalArgumentException("No commands for rover: " + id);
            }

            commands.execute(rover);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<String> results = new ArrayList<>();

        for (String id : rovers.keySet()) {
            String result = sb.append(id).append(" -> ").append(rovers.get(id)).toString();
            results.add(result);
        }

        return String.join("\n", results);
    }
}
