package actions;

import elements.Coordinates;
import entities.Entity;
import entities.Herbivore;
import settings.Settings;

public class HerbivoreSpawnAction extends SpawnAction {

    public HerbivoreSpawnAction(int initCount) {
        this.initCount = initCount;
    }

    @Override
    public Entity createEntity(Coordinates coordinates) {
        return new Herbivore(Settings.HERBIVORE_HEALTH, Settings.HERBIVORE_SPEED, coordinates);
    }
}
