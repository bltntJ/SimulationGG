package actions.turnactions;

import elements.Creature;
import elements.creatures.Herbivore;
import elements.creatures.Predator;
import game.SimulationMap;

public class CreaturesMover {
    private final SimulationMap simulationMap;

    public CreaturesMover(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public void moveCreatures() {
        simulationMap.getEntities().values().stream().sorted((c1, c2) -> {
            if (c1 instanceof Herbivore && c2 instanceof Predator) return -1;
            else if (c1 instanceof Predator && c2 instanceof Herbivore) return 1;
            return 0;
        }).toList().forEach(Creature::makeMove);
    }
}
