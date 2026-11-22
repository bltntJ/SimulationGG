package elements;

import entities.*;

import java.util.HashMap;
import java.util.Map;

public class SimulationMap {
    private final int height;
    private final int width;
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public SimulationMap(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Map<Coordinates, Entity> getEntities() {
        return new HashMap<>(entities);
    }

    public Entity getEntity(Coordinates coordinates) {
        validate(coordinates);
        return entities.get(coordinates);
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        validate(coordinates);
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        validate(coordinates);
        entities.remove(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        validate(coordinates);
        return !entities.containsKey(coordinates);
    }

    public void validate(Coordinates coordinates) {
        if(coordinates.x() < 1 || coordinates.x() > width || coordinates.y() < 1 || coordinates.y() > height) {
            throw new RuntimeException("Invalid coordinates: %d %d".formatted(coordinates.x(), coordinates.y()));
        };
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
