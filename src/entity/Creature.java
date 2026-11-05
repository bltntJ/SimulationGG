package entity;

import game.Coordinates;
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

    public abstract void makeMove(SimulationMap simulationMap);

    public Integer getHP() {
        return HP;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    public List<Coordinates> getEntityMoves() {
        List<Coordinates> allPossibleMoves = new ArrayList<>();
        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                allPossibleMoves.add(new Coordinates(i, j));
            }
        }
        allPossibleMoves.remove(new Coordinates(0, 0));
        return allPossibleMoves;
    }

    public void changeCoordinates(Coordinates previousCoordinates, Coordinates nextCoordinate, SimulationMap simulationMap) {
        setCoordinates(nextCoordinate);
        simulationMap.setEntity(nextCoordinate, this);
        simulationMap.getEntities().remove(previousCoordinates);
    }
}