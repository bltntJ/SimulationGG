package game;

import config.Config;
import elements.Creature;
import elements.Entity;

import java.util.HashMap;

public class MapRenderer {
    private final HashMap<Coordinates, Creature> entities;
    private final HashMap<Coordinates, Entity> staticEntities;
    private final int height;
    private final int width;

    public MapRenderer(SimulationMap simulationMap) {
        this.entities = simulationMap.getEntities();
        this.staticEntities = simulationMap.getObjects();
        this.height = simulationMap.getHeight();
        this.width = simulationMap.getWidth();
    }

    public void render() {
        for (int r = 0; r <= height; r++) {
            for (int c = 0; c <= width; c++) {
                Coordinates checkedCoordinate = new Coordinates(r, c);
                if (entities.containsKey(checkedCoordinate)) {
                    String creatureName = entities.get(checkedCoordinate).getClass().getSimpleName();
                    switch (creatureName) {
                        case "Herbivore"-> System.out.print(Config.HERBIVORE + " ");
                        case "Predator" -> System.out.print(Config.PREDATOR + " ");
                    }
                } else if (staticEntities.containsKey(checkedCoordinate)) {
                    String entityName = staticEntities.get(checkedCoordinate).getClass().getSimpleName();
                    switch (entityName) {
                        case "Grass" -> System.out.print(Config.GRASS + " ");
                        case "Tree" -> System.out.print(Config.TREE + " ");
                        case "Rock" -> System.out.print(Config.ROCK + " ");
                    }
                } else {
                    System.out.print(Config.EMPTY_CELL + " ");
                }
            }
            System.out.println();
        }
    }
}