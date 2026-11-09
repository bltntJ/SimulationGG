package path;

import ablities.MoveValidator;
import elements.Creature;
import game.Coordinates;
import game.SimulationMap;

import java.util.*;

public class PathFinder implements MoveValidator {
    public static Coordinates findNextStep(Class<? extends Creature> searcherClass, Coordinates start, Coordinates target, SimulationMap simulationMap) {
        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);
        Coordinates currentCoordinates;
        while (!queue.isEmpty()) {
            currentCoordinates = queue.poll();
            if (currentCoordinates.equals(target)) break;

            Creature creature = simulationMap.getEntities().get(start);
            List<Coordinates> currentCreatureMoves = creature.getEntityMoves(creature.getSpeed());
            for (Coordinates move : currentCreatureMoves) {
                int shiftedRow = currentCoordinates.row() + move.row();
                int shiftedColumn = currentCoordinates.column() + move.column();

                Coordinates neighbor = new Coordinates(shiftedRow, shiftedColumn);

                if (MoveValidator.isValidMove(searcherClass, neighbor, simulationMap, visited)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentCoordinates);
                    queue.add(neighbor);
                }
            }
        }

        if (!parentMap.containsKey(target) && !start.equals(target)) return null;

        Coordinates nextStep = target;
        while (parentMap.containsKey(nextStep) && !parentMap.get(nextStep).equals(start)) {
            nextStep = parentMap.get(nextStep);
        }

        return nextStep;
    }
}
