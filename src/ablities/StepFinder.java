package ablities;

import elements.Creature;
import game.Coordinates;
import game.SimulationMap;
import path.PathFinder;
import utils.CellsGetter;
import utils.CoordinatesFinder;

import java.util.List;

public interface StepFinder {
    CellsGetter cellsGetter = new CellsGetter();
    CoordinatesFinder coordinatesFinder = new CoordinatesFinder();

    default <T> Coordinates findStep(Class<T> targetClass, Creature creature , SimulationMap simulationMap, List<Coordinates> entityMoves, Coordinates coordinates) {
        List<Coordinates> moves = cellsGetter.getAvailableMoveCells(simulationMap, entityMoves, coordinates);
        if (moves.isEmpty()) return null;
        Coordinates nearestTarget = coordinatesFinder.findNearestTarget(targetClass, coordinates, simulationMap);
        return PathFinder.findNextStep(creature.getClass(), coordinates, nearestTarget, simulationMap);
    }
}