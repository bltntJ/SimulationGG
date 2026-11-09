package actions.turnactions;

import elements.objects.Grass;
import game.Coordinates;
import game.SimulationMap;

import java.util.Random;

public class GrassGenerator {
    private final SimulationMap simulationMap;
    private final Random random = new Random();

    public GrassGenerator(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public void generateGrass() {
        if (simulationMap.getObjects().values().stream().filter(entity -> entity instanceof Grass).count() >= 3) return;
        int i = 0;
        while (i < new Random().nextInt(7) + 1) {
            int randomRow = random.nextInt(simulationMap.getHeight() + 1);
            int randomColumn = random.nextInt(simulationMap.getWidth() + 1);
            Coordinates coordinates = new Coordinates(randomRow, randomColumn);

            if (!simulationMap.getEntities().containsKey(coordinates) && !simulationMap.getObjects().containsKey(coordinates)) {
                simulationMap.setObject(coordinates, new Grass(coordinates));
                i++;
            }
        }
    }
}
