package entity;

import game.Coordinates;
import actions.CoordinatesShift;
import game.SimulationMap;

import java.util.*;

public abstract class Creature extends Entity {
    protected Integer HP;
    protected Integer speed;

    public Creature(Coordinates coordinates, Integer HP, Integer speed) {
        super(coordinates);
        this.HP = HP;
        this.speed = speed;
    }

    public void changeCoordinates(Coordinates previousCoordinates, Coordinates nextCoordinate, SimulationMap simulationMap) {
        setCoordinates(nextCoordinate);
        simulationMap.setEntity(nextCoordinate, this);
        simulationMap.getEntities().remove(previousCoordinates);
    }

    public abstract void makeMove(SimulationMap simulationMap);

    public Integer getHP() {
        return HP;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    protected Set<CoordinatesShift> getEntityMoves() {
        Set<CoordinatesShift> allPossibleMoves = new HashSet<>();
        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                allPossibleMoves.add(new CoordinatesShift(i, j));
            }
        }
        return allPossibleMoves;
    }
}