package path;

import entity.Grass;
import game.Coordinates;
import game.SimulationMap;

import java.util.*;

public class HerbivorePathFinder {
    public static Coordinates findNextStep(Coordinates start, Coordinates target, SimulationMap simulationMap) {
        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);
        Coordinates currentCoordinates;
        while (!queue.isEmpty()) {
            currentCoordinates = queue.poll();
            if (currentCoordinates.equals(target)) break;

            // SOLVE
            List<Coordinates> currentCreatureMoves = simulationMap.getEntities().get(start).getEntityMoves();
            for (Coordinates move : currentCreatureMoves) {
                int shiftedRow = currentCoordinates.row() + move.row();
                int shiftedColumn = currentCoordinates.column() + move.column();

                Coordinates neighbor = new Coordinates(shiftedRow, shiftedColumn);

                if (isValidMove(neighbor, simulationMap, visited)) {
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

    private static boolean isValidMove(Coordinates neighbor, SimulationMap simulationMap, Set<Coordinates> visited) {
        if (!visited.contains(neighbor)) {
            boolean isInBounds = (neighbor.row() >= 0 && neighbor.row() <= simulationMap.getHeight()) && (neighbor.column() >= 0 && neighbor.column() <= simulationMap.getWidth());
            boolean isCellAppropriate = !simulationMap.getEntities().containsKey(neighbor) && (!simulationMap.getStaticEntities().containsKey(neighbor) || simulationMap.getStaticEntities().get(neighbor) instanceof Grass);

            return isInBounds && isCellAppropriate;
        }
        return false;
    }
}