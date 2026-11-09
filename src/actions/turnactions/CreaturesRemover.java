package actions.turnactions;

import game.SimulationMap;

public class CreaturesRemover {
    private final SimulationMap simulationMap;

    public CreaturesRemover(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public void removeCreature() {
        simulationMap.getEntities().values().removeIf(entity -> entity.getHP() <= 0);
    }
}
