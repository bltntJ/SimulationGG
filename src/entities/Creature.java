package entities;

import elements.Coordinates;
import elements.SimulationMap;

import java.util.*;

public abstract class Creature extends Entity {
    protected int hp;
    protected int speed;

    public Creature(int hp, int speed, Coordinates coordinates) {
        super(coordinates);
        this.hp = hp;
        this.speed = speed;
    }

    public void makeMove(SimulationMap simulationMap) {
        Random random = new Random();

        List<Coordinates> availableCells = getAvailableCells(simulationMap);
        if (availableCells.isEmpty()) {
            System.out.println(123123133);
            return;
        }

        Coordinates newCoordinates = availableCells.get(random.nextInt(availableCells.size()));
        simulationMap.removeEntity(coordinates);
        simulationMap.putEntity(newCoordinates, this);
    }

    public List<Coordinates> getAvailableCells(SimulationMap simulationMap) {
        List<Coordinates> result = new ArrayList<>();

        for (Coordinates shift: getEntityMoves()) {
            if (coordinates.canShift(simulationMap, shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if(simulationMap.isCellEmpty(newCoordinates)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }


    private Set<Coordinates> getEntityMoves() {
        Set<Coordinates> allMoves = new HashSet<>();
        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                allMoves.add(new Coordinates(i, j));
            }
        }
        allMoves.remove(new Coordinates(0, 0));
        return allMoves;
    }

}
