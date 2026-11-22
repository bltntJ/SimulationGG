package actions;

import elements.Coordinates;
import entities.Entity;
import entities.Rock;

public class RockSpawnAction extends SpawnAction {

    public RockSpawnAction(int count) {
        initCount = count;
    }

    @Override
    public Entity createEntity(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}