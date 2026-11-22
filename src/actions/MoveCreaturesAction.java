package actions;

import elements.SimulationMap;
import entities.*;

public class MoveCreaturesAction implements Action {
    @Override
    public void execute(SimulationMap simulationMap) {
        for (Entity entity: simulationMap.getEntities().values())  {
            if (entity instanceof Creature) {
                ((Creature) entity).makeMove(simulationMap);
            }
        }
    }
}
