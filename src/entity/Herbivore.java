package entity;

import config.SimulationConfig;
import game.Coordinates;
import game.SimulationMap;
import path.HerbivorePathFinder;
import utils.CellsGetter;
import utils.GrassCoordinatesFinder;

import java.util.*;

public class Herbivore extends Creature {
    private final CellsGetter cellsGetter;
    private final GrassCoordinatesFinder grassCoordinatesFinder;

    public Herbivore(Coordinates coordinates, SimulationMap simulationMap) {
        super(coordinates, SimulationConfig.HERBIVORE_HEALTH, SimulationConfig.HERBIVORE_SPEED);
        cellsGetter = new CellsGetter();
        grassCoordinatesFinder = new GrassCoordinatesFinder(simulationMap);
    }

    public void makeMove(SimulationMap simulationMap) {
        List<Coordinates> moves = cellsGetter.getAvailableMoveCells(simulationMap, getEntityMoves(), coordinates);

        if (!moves.isEmpty()) {
            Coordinates nearestGrassCoordinates = grassCoordinatesFinder.findNearestGrass(coordinates);

            Coordinates nextStep = HerbivorePathFinder.findNextStep(coordinates, nearestGrassCoordinates, simulationMap);
            if (nextStep == null) return;

            Objects.requireNonNull(nextStep, "Next step can not be null");

            if (simulationMap.getStaticEntities().get(nextStep) instanceof Grass) {
                eatGrass(nextStep);
                simulationMap.getStaticEntities().remove(nextStep);
            }
            changeCoordinates(coordinates, nextStep, simulationMap);
        }
    }

    public void eatGrass(Coordinates coordinates) {
        setHP(getHP() + 4);
        System.out.println("-".repeat(20));
        System.out.printf("The herbivore at %d %d ate the grass\n", coordinates.row() + 1, coordinates.column() + 1);
        System.out.println("-".repeat(20));
    }

    public void decreaseHP(int attackPower) {
        int currentHP = getHP() - attackPower;
        setHP(Math.max(currentHP, 0));
    }
}