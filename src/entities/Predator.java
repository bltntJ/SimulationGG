package entities;

import elements.Coordinates;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(int hp, int speed, int attackPower, Coordinates coordinates) {
        super(hp, speed, coordinates);
        this.attackPower = attackPower;
    }

}
