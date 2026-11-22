package pathfinding;

import elements.Coordinates;
import elements.SimulationMap;
import entities.Creature;
import entities.Entity;

import java.util.*;

public class BfsPathFinder {

    private BfsPathFinder() {}

    public static List<Coordinates> find(SimulationMap simulationMap, Coordinates start, Class<? extends Entity> target) {
        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);

        Creature searcher = (Creature) simulationMap.getEntity(start);
        Coordinates targetCoordinates = null;

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            Entity currentEntity = simulationMap.getEntity(current);
            if (target.isInstance(currentEntity)) {
                targetCoordinates = current;
                break;
            }

            for (Coordinates move : searcher.getEntityMoves()) {
                if (!current.canShift(simulationMap, new Coordinates(move.x(), move.y()))) {
                    continue;
                }

                Coordinates neighbor = new Coordinates(current.x() + move.x(),current.y() + move.y());

                if (searcher.isValidMove(neighbor, simulationMap, visited, target)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (targetCoordinates == null) {
            return List.of();
        }

        return buildOrderedPath(parentMap, start, targetCoordinates);
    }

    private static List<Coordinates> buildOrderedPath(Map<Coordinates, Coordinates> parentMap, Coordinates start, Coordinates target) {
        LinkedList<Coordinates> path = new LinkedList<>();
        Coordinates current = target;

        while (current != null) {
            path.addFirst(current);
            if (current.equals(start)) {
                break;
            }
            current = parentMap.get(current);
        }

        return path;
    }
}