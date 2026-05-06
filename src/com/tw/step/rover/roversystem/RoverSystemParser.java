package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final Boundary boundary;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, Boundary boundary, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.boundary = boundary;
        this.commandCreator = commandCreator;
    }

    private Rover parseRover() {
        String id = scanner.consume();
        Coordinate coordinate = scanner.scanCoordinate();
        Direction heading = scanner.scanDirection();
        return new Rover(id, coordinate, heading);
    }

    public RoverSystem parse() throws IllegalArgumentException {
        RoverSystem roverSystem = new RoverSystem();

        while (!(scanner.peek().contains(":"))) {
            String id = scanner.peek();
            Rover rover = parseRover();
            roverSystem.addRover(id, rover);
        }

        while (scanner.peek() != null) {
            String id = scanner.consume().replace(":", "");
            RoverCommands roverCommands = parseRoverCommands();
            roverSystem.addCommands(id, roverCommands);
        }

        return roverSystem;
    }

    private RoverCommands parseRoverCommands() throws IllegalArgumentException {
        RoverCommands roverCommands = new RoverCommands();
        String instructions = scanner.consume();
        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(instructions.charAt(i), navigator, boundary);
            roverCommands.add(roverCommand);
        }

        return roverCommands;
    }
}
