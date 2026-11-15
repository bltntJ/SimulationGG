import actions.InitActions;
import actions.TurnActions;
import elements.MapRenderer;
import elements.SimulationMap;
import entities.*;

public class Main {
    public static void main(String[] args) {
        SimulationMap simulationMap = new SimulationMap(10, 10);

        InitActions initActions = new InitActions(simulationMap);
        initActions.setDefaultEntitiesPositions(Herbivore.class, 3);
        initActions.setDefaultEntitiesPositions(Predator.class, 3);
        initActions.setDefaultEntitiesPositions(Grass.class, 3);
        initActions.setDefaultEntitiesPositions(Tree.class, 3);
        initActions.setDefaultEntitiesPositions(Rock.class, 3);

        TurnActions turnActions = new TurnActions(simulationMap);
        MapRenderer mapRenderer = new MapRenderer();
        while (true) {
            mapRenderer.render(simulationMap);
            turnActions.move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}