import elements.Simulation;
import elements.SimulationManager;
import elements.SimulationMap;

public class MainWithThreads {
    public static void main(String[] args) {
        SimulationMap simulationMap = new SimulationMap(15, 15);
        Simulation simulation = new Simulation(simulationMap);
        SimulationManager managerThread = new SimulationManager(simulation);

        managerThread.start();
        simulation.startSimulation();
    }
}
