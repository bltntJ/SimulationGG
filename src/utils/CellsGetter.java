package utils;

import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import game.Coordinates;
import game.SimulationMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CellsGetter {
    public List<Coordinates> getAvailableMoveCells(SimulationMap simulationMap, List<Coordinates> entityMoves, Coordinates coordinates) {
        List<Coordinates> result = new ArrayList<>();
        for (Coordinates shift: entityMoves) {
            if (coordinates.canShift(shift, simulationMap.getHeight(), simulationMap.getWidth())) {
                addCellInResult(result, simulationMap, coordinates, shift);
            }
        }
        return result;
    }

    private void addCellInResult(List<Coordinates> result, SimulationMap simulationMap, Coordinates coordinates, Coordinates shift) {
        Coordinates newCoordinates = coordinates.shift(shift);
        HashMap<Coordinates, Creature> creatureMap = simulationMap.getEntities();
        HashMap<Coordinates, Entity> entityMap = simulationMap.getStaticEntities();

        if (simulationMap.getEntities().get(coordinates) instanceof Herbivore) {
            if (CellsChecker.isCellAvailableForHerbivoreMove(newCoordinates, creatureMap, entityMap)) result.add(newCoordinates);
        }
        if (simulationMap.getEntities().get(coordinates) instanceof Predator) {
            if (CellsChecker.isCellAvailableForPredatorMove(newCoordinates, creatureMap, entityMap)) result.add(newCoordinates);
        }
    }
}
