package config;

public class SimulationConfig {
    public static final int MAP_MIN_HEIGHT = 10;
    public static final int MAP_MIN_WIDTH = 10;

    public static final int HERBIVORE_HEALTH = 10;
    public static final int PREDATOR_HEALTH = 10;

    public static final int HERBIVORE_SPEED = 1;
    public static final int PREDATOR_SPEED = 2;

    public static final int PREDATOR_ATTACK = 5;

    public static final int HERBIVORE_START_COUNT = 7;
    public static final int HERBIVORE_MAX_COUNT = 10;

    public static final int PREDATOR_START_COUNT = 3;
    public static final int PREDATOR_MAX_COUNT = 5;

    public static final int GRASS_START_COUNT = 5;
    public static final int GRASS_MAX_COUNT = 7;
    public static final int ROCK_START_COUNT = 10;
    public static final int TREE_START_COUNT = 10;

    public static final String EMPTY_CELL = "\uD83D\uDFEB";
    public static final String ROCK = "\uD83E\uDEA8";
    public static final String GRASS = "\uD83C\uDF40";
    public static final String TREE  = "\uD83C\uDF34";
    public static final String HERBIVORE = "\uD83E\uDD8C";
    public static final String PREDATOR = "\uD83D\uDC3A";

    private SimulationConfig() {}
}
