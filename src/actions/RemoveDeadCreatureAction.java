package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.Creature;
import entities.Entity;

public class RemoveDeadCreatureAction implements Action {
    @Override
    public void execute(SimulationMap simulationMap) {
        for (Coordinates coordinates: simulationMap.getEntities().keySet()) {
            Entity entity = simulationMap.getEntity(coordinates);
            if (entity instanceof Creature && ((Creature) entity).getHp() == 0) {
                simulationMap.removeEntity(coordinates);
            }
        }
    }
}
