package entity;

import config.SimulationConfig;
import game.Coordinates;
import game.SimulationMap;
import utils.CellsGetter;
import java.util.*;

public class Herbivore extends Creature {
    private final Random random = new Random();
    private final CellsGetter cellsGetter;

    public Herbivore(Coordinates coordinates) {
        super(coordinates, SimulationConfig.HERBIVORE_HEALTH, SimulationConfig.HERBIVORE_SPEED);
        cellsGetter = new CellsGetter();
    }

    public void makeMove(SimulationMap simulationMap) {
        List<Coordinates> moves = cellsGetter.getAvailableMoveCells(simulationMap, getEntityMoves(), coordinates);

        if (!moves.isEmpty()) {
            int randomMoveNumber = random.nextInt(moves.size());
            Coordinates nextCoordinate = moves.get(randomMoveNumber);
            if (simulationMap.getStaticEntities().get(nextCoordinate) instanceof Grass) {
                eatGrass(nextCoordinate);
                simulationMap.getStaticEntities().remove(nextCoordinate);
            }
            changeCoordinates(coordinates, nextCoordinate, simulationMap);
        }
    }

    public void eatGrass(Coordinates coordinates) {
        setHP(getHP() + 4);
        System.out.printf("The herbivore at %d %d ate the grass\n", coordinates.row() + 1, coordinates.column() + 1);
    }

    public void decreaseHP(int attackPower) {
        int currentHP = getHP() - attackPower;
        setHP(Math.max(currentHP, 0));
    }
}