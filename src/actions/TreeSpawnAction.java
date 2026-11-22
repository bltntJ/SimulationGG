package actions;

import elements.Coordinates;
import entities.Entity;
import entities.Tree;

public class TreeSpawnAction extends SpawnAction {

    public TreeSpawnAction(int count) {
        initCount = count;
    }

    @Override
    public Entity createEntity(Coordinates coordinates) {
        return new Tree(coordinates);
    }
}
