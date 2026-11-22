package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.Entity;
import entities.Herbivore;

import java.util.Random;

public class AdditionalHerbivoreSpawnAction extends HerbivoreSpawnAction {

    public AdditionalHerbivoreSpawnAction(int initCount) {
        super(initCount);
    }

    @Override
    public void execute(SimulationMap simulationMap) {
        int herbivoresCount = (int) simulationMap.getEntities().values().stream().filter(entity -> entity instanceof Herbivore).count();
        if (herbivoresCount >= 3) return;

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
