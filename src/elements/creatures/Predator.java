package elements.creatures;

import config.Config;
import elements.Creature;
import game.Coordinates;
import game.SimulationMap;

public class Predator extends Creature {
    private final Integer attackPower;
    private final SimulationMap simulationMap;

    public Predator(Coordinates coordinates, SimulationMap simulationMap) {
        super(coordinates, Config.PREDATOR_HEALTH, Config.PREDATOR_SPEED);
        attackPower = Config.PREDATOR_ATTACK;
        this.simulationMap = simulationMap;
    }

    public void makeMove() {
        Coordinates nextStep = findStep(Herbivore.class, this, simulationMap, getEntityMoves(speed), coordinates);
        if (nextStep == null) return;

        if (simulationMap.getEntities().get(nextStep) instanceof Herbivore herbivore) attackIfHerbivore(simulationMap, nextStep, herbivore);
        changeCoordinates(coordinates, nextStep, simulationMap);
        setHP(Math.max(getHP() - 3, 0));
    }

    public void attackIfHerbivore(SimulationMap simulationMap, Coordinates nextStep, Herbivore herbivore) {
        herbivore.setHP(Math.max(herbivore.getHP() - attackPower, 0));
        if (herbivore.getHP() == 0) simulationMap.removeEntity(nextStep);
        setHP(getHP() + 5);
    }
}