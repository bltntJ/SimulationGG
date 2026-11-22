package actions;

import elements.Coordinates;
import entities.Entity;
import entities.Grass;

public class GrassSpawnAction extends SpawnAction {

    public GrassSpawnAction(int initCount) {
        this.initCount = initCount;
    }

    @Override
    public Entity createEntity(Coordinates coordinates) {
        return new Grass(coordinates);
    }
}