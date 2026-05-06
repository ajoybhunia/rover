package com.tw.step.rover;

import com.tw.step.rover.boundary.*;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.roversystem.RoverSystem;
import com.tw.step.rover.roversystem.RoverSystemParser;
import com.tw.step.rover.roversystem.RoverSystemScanner;

public class App {
    static void main() {
        try {

        String text = """
5 5
R1 1 5 N
R1: LFFRFLFFFR
                """;

        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();

        int topX = scanner.scanNumber();
        int topY = scanner.scanNumber();

        Boundary boundary = new Plateau(new Coordinate(0,0), new Coordinate(topX,topY));
        CommandCreator commandCreator = new CommandCreator();
        RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, boundary, commandCreator);
        RoverSystem system = roverSystemParser.parse();
        system.execute();
        System.out.println(system);
        } catch (Exception e) {
            System.err.println("Failed: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
