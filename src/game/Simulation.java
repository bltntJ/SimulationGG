package game;

import actions.InitActions;
import actions.TurnActions;

public class Simulation {
    private final InitActions initActions;
    private final MapRenderer mapRenderer;
    private final TurnActions turnActions;
    private final Statistics statistics;
    private final MoveCounter moveCounter;
    private boolean isRunning = true;

    public Simulation(SimulationMap simulationMap) {
        initActions = new InitActions(simulationMap);
        mapRenderer = new MapRenderer(simulationMap);
        turnActions = new TurnActions(simulationMap);
        statistics = new Statistics(simulationMap);
        moveCounter = new MoveCounter();
        initActions.setDefaultPositionsForObjects();
    }

    private void nextTurn() throws InterruptedException {
        callMovePredators();
        if (moveCounter.getCount() % 15 == 0) {
            isRunning = moveCounter.askToContinue();
        }

        if (turnActions.stopGameIfZeroHerbivores()) {
            System.out.println("All herbivores died");
            isRunning = false;
            return;
        }

        callMoveHerbivores();
        if (moveCounter.getCount() % 15 == 0) {
            isRunning = moveCounter.askToContinue();
        }
    }

    public void callMoveHerbivores() throws InterruptedException {
        Thread.sleep(2000);

        turnActions.moveHerbivores();
        turnActions.removeEntityIfHPZero();
        mapRenderer.render();

        statistics.printStatistics();
        moveCounter.increaseCount();
    }

    public void callMovePredators() throws InterruptedException {
        Thread.sleep(2000);

        turnActions.movePredators();
        turnActions.removeEntityIfHPZero();
        mapRenderer.render();

        statistics.printStatistics();
        moveCounter.increaseCount();
    }

    public void startSimulation() throws InterruptedException {
        initActions.startingMessage(mapRenderer);
        while (isRunning) {
            nextTurn();
        }
    }
}