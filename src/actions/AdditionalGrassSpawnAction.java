package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.Entity;
import entities.Grass;

import java.util.Random;

public class AdditionalGrassSpawnAction extends GrassSpawnAction {

    public AdditionalGrassSpawnAction(int initCount) {
        super(initCount);
    }

    @Override
    public void execute(SimulationMap simulationMap) {
        int grassCount = (int) simulationMap.getEntities().values().stream().filter(entity -> entity instanceof Grass).count();
        if (grassCount >= 5) return;

        int additionalAmount = initCount;
        Random random = new Random();

        int count = 0;
        while (count < additionalAmount) {
            Coordinates coordinates = getRandomCell(simulationMap, random);

            if (simulationMap.isCellEmpty(coordinates)) {
                Entity entity = createEntity(coordinates);
                simulationMap.putEntity(coordinates, entity);
                count++;
            }
        }
    }
}