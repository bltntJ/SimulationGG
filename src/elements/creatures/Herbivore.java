package elements.creatures;

import config.Config;
import elements.Creature;
import elements.objects.Grass;
import game.Coordinates;
import game.SimulationMap;

public class Herbivore extends Creature {
    private final SimulationMap simulationMap;

    public Herbivore(Coordinates coordinates, SimulationMap simulationMap) {
        super(coordinates, Config.HERBIVORE_HEALTH, Config.HERBIVORE_SPEED);
        this.simulationMap = simulationMap;
    }

    public void makeMove() {
        Coordinates nextStep = findStep(Grass.class,this, simulationMap, getEntityMoves(speed), coordinates);
        if (nextStep == null) return;

        if (simulationMap.getObjects().get(nextStep) instanceof Grass) eatGrass(simulationMap, nextStep);
        changeCoordinates(coordinates, nextStep, simulationMap);
        setHP(Math.max(getHP() - 2, 0));
    }

    public void eatGrass(SimulationMap simulationMap, Coordinates nextStep) {
        setHP(getHP() + 3);
        simulationMap.removeObject(nextStep);
    }
}