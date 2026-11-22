package actions;

import elements.Coordinates;
import entities.Entity;
import entities.Predator;
import settings.Settings;

public class PredatorSpawnAction extends SpawnAction {

    public PredatorSpawnAction(int initCount) {
        this.initCount = initCount;
    }

    @Override
    public Entity createEntity(Coordinates coordinates) {
        return new Predator(Settings.HERBIVORE_HEALTH, Settings.HERBIVORE_SPEED, Settings.PREDATOR_ATTACK, coordinates);
    }
}
