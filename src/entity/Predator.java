package entity;

import actions.CoordinatesShift;
import config.SimulationConfig;
import game.Coordinates;
import game.SimulationMap;
import utils.CellsGetter;

import java.util.*;

public class Predator extends Creature {
    private final Integer attackPower;
    private final Random random = new Random();
    private final CellsGetter cellsGetter;

    public Predator(Coordinates coordinates) {
        super(coordinates, SimulationConfig.PREDATOR_HEALTH, SimulationConfig.PREDATOR_SPEED);
        attackPower = SimulationConfig.PREDATOR_ATTACK;
        cellsGetter = new CellsGetter();
    }

    public void makeMove(SimulationMap simulationMap) {
        List<Coordinates> moves = cellsGetter.getAvailableMoveCells(simulationMap, getEntityMoves(), coordinates);
        if (!moves.isEmpty()) {
            int randomMoveNumber = random.nextInt(moves.size());
            Coordinates nextCoordinate = moves.get(randomMoveNumber);
            if (simulationMap.getEntities().get(nextCoordinate) instanceof Herbivore targetEntity) {
                attackHerbivore(targetEntity, nextCoordinate);
            }
            changeCoordinates(coordinates, nextCoordinate, simulationMap);
        }
    }

    public void attackHerbivore(Herbivore herbivore, Coordinates coordinates) {
        System.out.printf("The predator at %d %d attacked the herbivore\n", coordinates.row() + 1, coordinates.column() + 1);
        setHP(getHP() + 3);
        herbivore.decreaseHP(attackPower);
    }
}