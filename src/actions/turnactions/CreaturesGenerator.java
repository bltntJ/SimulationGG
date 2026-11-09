package actions.turnactions;

import actions.RandomCellGetter;
import elements.Creature;
import game.Coordinates;
import game.SimulationMap;

import java.util.Random;

public class CreaturesGenerator {
    private final SimulationMap simulationMap;
    private final RandomCellGetter randomCellGetter;

    public CreaturesGenerator(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
        randomCellGetter = new RandomCellGetter(simulationMap);
    }

    public <T extends Creature> void generateCreatures(Class<T> creatureClass) {
        if (simulationMap.getEntities().values().stream().filter(creatureClass::isInstance).count() > 2) return;
        int i = 0;
        while (i < new Random().nextInt(9) + 1) {
            Coordinates randomCell = randomCellGetter.getRandomFreeCell();
            T creature;
            try {
                creature = creatureClass
                        .getDeclaredConstructor(Coordinates.class, SimulationMap.class)
                        .newInstance(randomCell, simulationMap);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            creature.setCoordinates(randomCell);
            simulationMap.setEntity(randomCell, creature);
            i++;
        }
    }
}
