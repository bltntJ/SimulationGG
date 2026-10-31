package utils;

import entity.Creature;
import entity.Entity;
import game.Coordinates;

import java.util.HashMap;

public class CellsChecker {
    private CellsChecker() {}

    public static boolean isCellAvailableForHerbivoreMove(Coordinates coordinates, HashMap<Coordinates, Creature> creatureMap, HashMap<Coordinates, Entity> entityMap) {
        return (entityMap.containsKey(coordinates) && entityMap.get(coordinates).getClass().getSimpleName().equals("Grass"))
                ||
                (!entityMap.containsKey(coordinates) && !creatureMap.containsKey(coordinates));
    }

    public static boolean isCellAvailableForPredatorMove(Coordinates coordinates, HashMap<Coordinates, Creature> creatureMap, HashMap<Coordinates, Entity> entityMap) {
        return (creatureMap.containsKey(coordinates) && creatureMap.get(coordinates).getClass().getSimpleName().equals("Herbivore"))
                ||
                (!creatureMap.containsKey(coordinates) && !entityMap.containsKey(coordinates));
    }
}
