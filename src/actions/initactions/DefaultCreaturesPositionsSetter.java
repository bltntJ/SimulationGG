package actions.initactions;

import actions.RandomCellGetter;
import elements.Creature;
import game.Coordinates;
import game.SimulationMap;

import java.lang.reflect.InvocationTargetException;

public class DefaultCreaturesPositionsSetter {
    private final SimulationMap simulationMap;
    private final RandomCellGetter randomCellGetter;

    public DefaultCreaturesPositionsSetter(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
        randomCellGetter = new RandomCellGetter(simulationMap);
    }

    public <T extends Creature> void setDefaultPositionsForCreatures(Class<T> entityClass, int creatureCount) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int i = 0;
        while (i < creatureCount) {
            Coordinates randomCell = randomCellGetter.getRandomFreeCell();
            T creature = entityClass
                    .getDeclaredConstructor(Coordinates.class, SimulationMap.class)
                    .newInstance(randomCell, simulationMap);
            creature.setCoordinates(randomCell);
            simulationMap.setEntity(randomCell, creature);
            i++;
        }
    }
}
