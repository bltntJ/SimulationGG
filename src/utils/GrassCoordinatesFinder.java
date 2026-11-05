package utils;

import entity.Entity;
import entity.Grass;
import game.Coordinates;
import game.SimulationMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GrassCoordinatesFinder {
    private final SimulationMap simulationMap;

    public GrassCoordinatesFinder(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    private List<Coordinates> getAllGrassCoordinates() {
        List<Coordinates> allGrassList = new ArrayList<>();

        for (Map.Entry<Coordinates, Entity> entity: simulationMap.getStaticEntities().entrySet()) {
            if (entity.getValue() instanceof Grass) {
                allGrassList.add(entity.getKey());
            }
        }

        return allGrassList;
    }

    public Coordinates findNearestGrass(Coordinates herbivorePos) {
        List<Coordinates> allGrassList = Objects.requireNonNull(getAllGrassCoordinates(), "List of grass locations must not be null");

        if (allGrassList.isEmpty()) return null;

        Coordinates closestGrass = null;
        int shortestDistance = Integer.MAX_VALUE;

        for (Coordinates grass: allGrassList) {
            int distance = Math.abs(herbivorePos.column() - grass.column()) + Math.abs(herbivorePos.row() - grass.row());
            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestGrass = grass;
            }
        }

        return closestGrass;
    }
}

