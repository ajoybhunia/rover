package com.tw.step.rover.commands;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.position.Navigator;

public class CommandCreator {
    public RoverCommand create(Character instruction, Navigator navigator, Boundary boundary) {
        return switch (instruction) {
            case 'L' -> new TurnLeftCommand(navigator, boundary);
            case 'R' -> new TurnRightCommand(navigator, boundary);
            case 'F' -> new MoveCommand(navigator, boundary);
            default -> null;
        };
    }
}
