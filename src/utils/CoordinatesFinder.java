package utils;

import elements.Creature;
import elements.Entity;
import elements.objects.Grass;
import elements.creatures.Herbivore;
import game.Coordinates;
import game.SimulationMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoordinatesFinder {
    private <T> List<Coordinates> getAllTargetsCoordinates(Class<T> targetClass, SimulationMap simulationMap) {
        List<Coordinates> targetsList = new ArrayList<>();

        if (targetClass == Grass.class) {
            for (Map.Entry<Coordinates, Entity> entity: simulationMap.getObjects().entrySet()) {
                if (targetClass.isInstance(entity.getValue())) targetsList.add(entity.getKey());
            }
        } else if (targetClass == Herbivore.class) {
            for (Map.Entry<Coordinates, Creature> creature: simulationMap.getEntities().entrySet()) {
                if (targetClass.isInstance(creature.getValue())) targetsList.add(creature.getKey());
            }
        }

        return targetsList;
    }

    public <T> Coordinates findNearestTarget(Class<T> targetClass, Coordinates herbivorePos, SimulationMap simulationMap) {
        List<Coordinates> allGrassList = getAllTargetsCoordinates(targetClass, simulationMap);
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

