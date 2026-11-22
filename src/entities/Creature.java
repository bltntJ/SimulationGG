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

    public abstract void makeMove(SimulationMap simulationMap);

    public Set<Coordinates> getEntityMoves() {
        Set<Coordinates> allMoves = new HashSet<>();
        for (int i = -speed; i <= speed; i++) {
            for (int j = -speed; j <= speed; j++) {
                allMoves.add(new Coordinates(i, j));
            }
        }
        allMoves.remove(new Coordinates(0, 0));
        return allMoves;
    }

    public boolean isValidMove(Coordinates neighbor, SimulationMap simulationMap, Set<Coordinates> visited, Class<? extends Entity> target) {
        boolean isNotVisited = !visited.contains(neighbor);
        boolean isEmptyCell = !simulationMap.getEntities().containsKey(neighbor);
        boolean isAppropriateCell = target.isInstance(simulationMap.getEntity(neighbor));

        return  isNotVisited && (isEmptyCell || isAppropriateCell);
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }

    public int getHp() {
        return hp;
    }
}
