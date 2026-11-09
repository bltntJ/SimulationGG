package elements;

import ablities.MovesGetter;
import ablities.StepFinder;
import game.Coordinates;
import game.SimulationMap;

public abstract class Creature extends Entity implements StepFinder, MovesGetter {
    protected Integer HP;
    protected Integer speed;

    public Creature(Coordinates coordinates, Integer HP, Integer speed) {
        super(coordinates);
        this.HP = HP;
        this.speed = speed;
    }

    public abstract void makeMove();

    public Integer getHP() {
        return HP;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    public int getSpeed() {
        return speed;
    }

    public void changeCoordinates(Coordinates coordinates, Coordinates nextCoordinate, SimulationMap simulationMap) {
        setCoordinates(nextCoordinate);
        simulationMap.setEntity(nextCoordinate, this);
        simulationMap.removeEntity(coordinates);
    }
}