package game;

import elements.Creature;
import elements.Entity;

import java.util.HashMap;

public class SimulationMap {
    private final HashMap<Coordinates, Creature> entities = new HashMap<>();
    private final HashMap<Coordinates, Entity> objects = new HashMap<>();
    public final Integer height;
    public final Integer width;

    public SimulationMap(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public void setEntity(Coordinates coordinates, Creature creature) {
        creature.setCoordinates(coordinates);
        entities.put(coordinates, creature);
    }

    public void setObject(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        objects.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void removeObject(Coordinates coordinates) {
        objects.remove(coordinates);
    }

    public HashMap<Coordinates, Creature> getEntities() {
        return entities;
    }

    public HashMap<Coordinates, Entity> getObjects() {
        return objects;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}