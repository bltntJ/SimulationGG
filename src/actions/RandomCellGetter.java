package actions;

import game.Coordinates;
import game.SimulationMap;

import java.util.Random;

public class RandomCellGetter {
    private final SimulationMap simulationMap;
    private final Random random = new Random();

    public RandomCellGetter(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public Coordinates getRandomFreeCell() {
        Coordinates randomCell;
        while (true) {
            int randomRow = random.nextInt(simulationMap.getHeight() + 1);
            int randomColumn = random.nextInt(simulationMap.getWidth() + 1);
            randomCell = new Coordinates(randomRow, randomColumn);
            if (simulationMap.getEntities().containsKey(randomCell) || simulationMap.getObjects().containsKey(randomCell)) continue;

            return randomCell;
        }
    }
}
