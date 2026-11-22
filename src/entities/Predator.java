package entities;

import elements.Coordinates;
import elements.SimulationMap;
import pathfinding.BfsPathFinder;

import java.util.List;

public class Predator extends Creature {
    private static final Class<? extends Entity> FOOD = Herbivore.class;
    private final int attackPower;

    public Predator(int hp, int speed, int attackPower, Coordinates coordinates) {
        super(hp, speed, coordinates);
        this.attackPower = attackPower;
    }

    public void makeMove(SimulationMap simulationMap) {
        List<Coordinates> path = BfsPathFinder.find(simulationMap, coordinates, FOOD);
        if (path.isEmpty()) {
            return;
        }

        Coordinates newCoordinates = path.get(1);
        attackIfHerbivore(simulationMap, newCoordinates);

        moveIfFinishedHerbivore(simulationMap, coordinates, newCoordinates);
    }

    public void attackIfHerbivore(SimulationMap simulationMap, Coordinates coordinates) {
        if (simulationMap.getEntity(coordinates) instanceof Herbivore herbivore) {
            int currentHerbivoreHp = herbivore.getHp();
            herbivore.setHp(currentHerbivoreHp - attackPower);
            setHp(getHp() - 1);

            if (herbivore.getHp() == 0) {
                simulationMap.removeEntity(coordinates);
                setHp(getHp() + 5);
            }
        }
    }

    public void moveIfFinishedHerbivore(SimulationMap simulationMap, Coordinates oldCoordinates, Coordinates newCoordinates) {
        if (simulationMap.isCellEmpty(newCoordinates)) {
            simulationMap.removeEntity(oldCoordinates);
            simulationMap.putEntity(newCoordinates, this);
            setHp(getHp() - 1);
        }
    }
}

