package actions.initactions;

import actions.RandomCellGetter;
import elements.Entity;
import game.Coordinates;
import game.SimulationMap;

import java.lang.reflect.InvocationTargetException;
public class DefaultObjectsPositionsSetter {
    private final SimulationMap simulationMap;
    private final RandomCellGetter randomCellGetter;

    public DefaultObjectsPositionsSetter(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
        randomCellGetter = new RandomCellGetter(simulationMap);
    }

    public <T extends Entity> void setDefaultPositionsForObjects(Class<T> entityClass, int objectCount) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int i = 0;
        while (i < objectCount) {
            Coordinates randomCell = randomCellGetter.getRandomFreeCell();
            T entity = entityClass
                    .getDeclaredConstructor(Coordinates.class)
                    .newInstance(randomCell);
            simulationMap.setObject(randomCell, entity);
            i++;
        }
    }
}
