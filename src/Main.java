import elements.Simulation;
import elements.SimulationMap;

public class Main {
    public static void main(String[] args) {
        SimulationMap simulationMap = new SimulationMap(10, 10);
        Simulation simulation = new Simulation(simulationMap);

        simulation.startSimulation();
    }
}