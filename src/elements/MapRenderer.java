package elements;

import entities.Entity;

public class MapRenderer {
    private static final String HERBIVORE = "\ud83d\udc07";
    private static final String PREDATOR = "\ud83d\udc3a";
    private static final String GRASS = "\ud83c\udf3f";
    private static final String TREE = "\ud83c\udf33";
    private static final String ROCK = "\ud83e\udea8";
    private static final String EMPTY_CELL = "\ud83d\udfe9";

    public void render(SimulationMap simulationMap) {
        int height = simulationMap.getHeight();
        int width = simulationMap.getWidth();

        for (int row = 1; row <= height; row++) {
            for (int column = 1; column <= width; column++) {
                Coordinates coordinates = new Coordinates(row, column);

                if(simulationMap.isCellEmpty(coordinates)) {
                    System.out.print(EMPTY_CELL + " ");
                }  else {
                    Entity entity = simulationMap.getEntity(coordinates);
                    String sprite = toSprite(entity);
                    System.out.print(sprite + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private String toSprite(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Herbivore" -> HERBIVORE;
            case "Predator"  -> PREDATOR;
            case "Grass"     -> GRASS;
            case "Tree"      -> TREE;
            case "Rock"      -> ROCK;
            default -> throw new IllegalArgumentException("Undefined entity: " + entity.getClass().getSimpleName());
        };
    }
}
