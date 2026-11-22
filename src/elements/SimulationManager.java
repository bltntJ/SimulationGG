package elements;

import java.util.Scanner;

public class SimulationManager extends Thread {
    private final Simulation simulation;

    public SimulationManager(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String PAUSE_OPTION = "1";
        String UNPAUSE_OPTION = "2";

        System.out.printf("Pause - %s\n", PAUSE_OPTION);
        System.out.printf("Unpause - %s\n", UNPAUSE_OPTION);

        while (true) {
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> simulation.pauseSimulation();
                case "2" -> simulation.startSimulation();
            }
        }
    }
}