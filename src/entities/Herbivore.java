package entities;

import elements.Coordinates;
import elements.SimulationMap;
import pathfinding.BfsPathFinder;

import java.util.List;

public class Herbivore extends Creature {
    private static final Class<? extends Entity> FOOD = Grass.class;

    public Herbivore(int hp, int speed, Coordinates coordinates) {
        super(hp, speed, coordinates);
    }

    public void makeMove(SimulationMap simulationMap) {
        List<Coordinates> path = BfsPathFinder.find(simulationMap, coordinates, FOOD);
        if (path.isEmpty()) {
            return;
        }

        Coordinates newCoordinates = path.get(1);
        eatIfGrass(simulationMap, newCoordinates);

        simulationMap.removeEntity(coordinates);
        simulationMap.putEntity(newCoordinates, this);
        setHp(getHp() - 1);
    }

    public void eatIfGrass(SimulationMap simulationMap, Coordinates coordinates) {
        if (simulationMap.getEntity(coordinates) instanceof Grass) {
            simulationMap.removeEntity(coordinates);
            setHp(getHp() + 3);
        }
    }

}
