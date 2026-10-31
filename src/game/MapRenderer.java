package game;

import config.SimulationConfig;
import entity.Creature;
import entity.Entity;

import java.util.HashMap;

public class MapRenderer {
    private final HashMap<Coordinates, Creature> entities;
    private final HashMap<Coordinates, Entity> staticEntities;
    private final int height;
    private final int width;

    public MapRenderer(SimulationMap simulationMap) {
        this.entities = simulationMap.getEntities();
        this.staticEntities = simulationMap.getStaticEntities();
        this.height = simulationMap.getHeight();
        this.width = simulationMap.getWidth();
    }

    public void render() {
        System.out.println("=========================");
        for (int r = 0; r <= height; r++) {
            for (int c = 0; c <= width; c++) {
                Coordinates checkedCoordinate = new Coordinates(r, c);
                if (entities.containsKey(checkedCoordinate)) {
                    String creatureName = entities.get(checkedCoordinate).getClass().getSimpleName();
                    switch (creatureName) {
                        case "Herbivore"-> System.out.print(SimulationConfig.HERBIVORE + " ");
                        case "Predator" -> System.out.print(SimulationConfig.PREDATOR + " ");
                    }
                } else if (staticEntities.containsKey(checkedCoordinate)) {
                    String entityName = staticEntities.get(checkedCoordinate).getClass().getSimpleName();
                    switch (entityName) {
                        case "Grass" -> System.out.print(SimulationConfig.GRASS + " ");
                        case "Tree" -> System.out.print(SimulationConfig.TREE + " ");
                        case "Rock" -> System.out.print(SimulationConfig.ROCK + " ");
                    }
                } else {
                    System.out.print(SimulationConfig.EMPTY_CELL + " ");
                }
            }
            System.out.println();
        }
    }
}