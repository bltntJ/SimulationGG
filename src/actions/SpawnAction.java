package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.*;

import java.util.*;

public abstract class SpawnAction implements Action {
    protected int initCount;

    @Override
    public void execute(SimulationMap simulationMap) {
        Random random = new Random();

        int count = 0;
        while (count < initCount) {
            Coordinates coordinates = getRandomCell(simulationMap, random);

            if (simulationMap.isCellEmpty(coordinates)) {
                Entity entity = createEntity(coordinates);
                simulationMap.putEntity(coordinates, entity);
                count++;
            }
        }
    }

    protected Coordinates getRandomCell(SimulationMap simulationMap, Random random) {
        int row = random.nextInt(simulationMap.getHeight()) + 1;
        int column = random.nextInt(simulationMap.getWidth()) + 1;

        Coordinates coordinates = new Coordinates(row, column);
        simulationMap.validate(coordinates);

        return coordinates;
    }

    protected abstract Entity createEntity(Coordinates coordinates);
}

