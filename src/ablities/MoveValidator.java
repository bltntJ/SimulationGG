package ablities;

import elements.objects.Grass;
import elements.creatures.Herbivore;
import elements.creatures.Predator;
import game.Coordinates;
import game.SimulationMap;

import java.util.Set;

public interface MoveValidator {
     static <T> boolean isValidMove(Class<? extends T> searcherClass, Coordinates neighbor, SimulationMap simulationMap, Set<Coordinates> visited) {
        boolean isVisited = !visited.contains(neighbor);
        boolean isInBounds = (neighbor.row() >= 0 && neighbor.row() <= simulationMap.getHeight()) && (neighbor.column() >= 0 && neighbor.column() <= simulationMap.getWidth());
        boolean isCellEmpty = !simulationMap.getEntities().containsKey(neighbor) && !simulationMap.getObjects().containsKey(neighbor);
        boolean isTarget = false;

        if (searcherClass == Herbivore.class) {
            isTarget = simulationMap.getObjects().get(neighbor) instanceof Grass;
        } else if (searcherClass == Predator.class) {
            isTarget = simulationMap.getEntities().get(neighbor) instanceof Herbivore;
        }

        return isVisited && isInBounds && (isCellEmpty || isTarget);
    }
}
