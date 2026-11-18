package elements;

import actions.InitActions;
import actions.TurnActions;
import entities.*;

public class Simulation {
    private final SimulationMap simulationMap;
    private final MapRenderer mapRenderer = new MapRenderer();
    private final TurnActions turnActions;
    private volatile boolean isRunning;

//    private final List<Action> initActions = List.of(new SpawnAction());
//    private final List<Action> turnActions = List.of(new RemoveDeadEntityAction(), new MoveAction());

    public Simulation(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
        InitActions initActions = new InitActions(simulationMap);
        initActions.setDefaultEntitiesPositions(Herbivore.class, 3);
        initActions.setDefaultEntitiesPositions(Predator.class, 3);
        initActions.setDefaultEntitiesPositions(Grass.class, 3);
        initActions.setDefaultEntitiesPositions(Tree.class, 3);
        initActions.setDefaultEntitiesPositions(Rock.class, 3);
        this.turnActions = new TurnActions(simulationMap);
//        executeActions(initActions);
    }

    public void startSimulation() {
        if (isRunning) return;

        isRunning = true;
        System.out.println("Started...");

        sleep();
        new Thread(() -> {
            while (isRunning) {
                nextTurn();
            }
        }).start();
    }

    public void pauseSimulation() {
        if (!isRunning) return;

        isRunning = false;
        System.out.println("Paused...");
    }

    private void nextTurn() {
        mapRenderer.render(simulationMap);
        turnActions.move();
        sleep();
//        executeActions(turnActions);
    }

//    private void executeActions(List<Action> actions) {
//        for(Action action : actions) {
//            action.execute(simulationMap);
//        }
//    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
