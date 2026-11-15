package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.*;

import java.util.Random;

public class InitActions {
    private final SimulationMap simulationMap;

    public InitActions(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public void setDefaultEntitiesPositions(Class<? extends Entity> entityClass, int initCount) {
        Random random = new Random();
        int i = 0;
        while (i < initCount) {
            int row = random.nextInt(simulationMap.getHeight()) + 1;
            int column = random.nextInt(simulationMap.getWidth()) + 1;

            Coordinates coordinates = new Coordinates(row, column);
            simulationMap.validate(coordinates);

            if (simulationMap.isCellEmpty(coordinates)) {
                Entity entity = createEntity(entityClass, coordinates);
                simulationMap.putEntity(coordinates, entity);
                i++;
            }
        }
    }

    private Entity createEntity(Class<? extends Entity> entityClass, Coordinates coordinates) {
        return switch (entityClass.getSimpleName()) {
            case "Herbivore" -> new Herbivore(5, 1, coordinates);
            case "Predator" -> new Predator(5, 2, 1, coordinates);
            case "Grass" -> new Grass(coordinates);
            case "Tree" -> new Tree(coordinates);
            case "Rock" -> new Rock(coordinates);
            default -> throw new IllegalArgumentException("Unknown entity class: " + entityClass.getSimpleName());
        };
    }
}
