package actions;

import elements.SimulationMap;
import entities.Creature;
import entities.Entity;

public class TurnActions {
    private final SimulationMap simulationMap;

    public TurnActions(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public void move() {
        for (Entity entity: simulationMap.getEntities().values())  {
            if (entity instanceof Creature) {
                ((Creature) entity).makeMove(simulationMap);
            }
        }
    }
}
