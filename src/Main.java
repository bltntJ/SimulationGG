import config.SimulationConfig;
import game.Simulation;
import game.SimulationMap;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        int width;
        int height;

        System.out.printf("Enter the height for the map\nMinimal height: %d\n", SimulationConfig.MAP_MIN_HEIGHT);
        while (true) {
            if (scanner.hasNextInt()) {
                height = scanner.nextInt();
                if (height >= 10) {
                    break;
                } else {
                    System.out.println("Enter a correct number");
                    continue;
                }
            } else {
                scanner.next();
            }
            System.out.println("Enter only numbers");
        }

        System.out.printf("Enter the width for the map\nMinimal width: %d\n", SimulationConfig.MAP_MIN_WIDTH);
        while (true) {
            if (scanner.hasNextInt()) {
                width = scanner.nextInt();
                if (width >= 10) {
                    break;
                } else {
                    System.out.println("Enter a correct number");
                    continue;
                }
            } else {
                scanner.next();
            }
            System.out.println("Enter only numbers");
        }

        Simulation simulation = new Simulation(new SimulationMap(height, width));
        simulation.startSimulation();
    }
}
