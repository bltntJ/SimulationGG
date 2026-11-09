package game;

import actions.InitActionsMain;
import actions.TurnActionsMain;
import elements.creatures.Herbivore;
import elements.creatures.Predator;

public class Simulation {
    private final MapRenderer mapRenderer;
    private final TurnActionsMain turnActions;
    private final MoveCounter moveCounter;
    private final Thread reader;
    private volatile boolean isRunning = true;

    public Simulation(SimulationMap simulationMap) {
        mapRenderer = new MapRenderer(simulationMap);
        turnActions = new TurnActionsMain(simulationMap);
        moveCounter = new MoveCounter();
        reader = new Thread(new InputReader());
        new InitActionsMain(simulationMap).setDefaultPositions();
    }

    private void printData() {
        mapRenderer.render();
        moveCounter.increaseCount();
    }

    private void doActions() {
        turnActions.callCreaturesMover();
        turnActions.callCreaturesRemover();
    }

    private void generateElements() {
        turnActions.callCreaturesGenerator(Herbivore.class);
        turnActions.callCreaturesGenerator(Predator.class);
        turnActions.callGrassGenerator();
    }

    private void startingMessage(MapRenderer mapRenderer) throws InterruptedException {
        mapRenderer.render();
        System.out.println("To exit, enter 0");
        int i = 3;
        while (i > 0) {
            System.out.println(i + "..");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
    }

    private void nextTurn() {
        doActions();
        printData();
        generateElements();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void startSimulation() {
        try {
            startingMessage(mapRenderer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reader.setDaemon(true);
        reader.start();
        while (isRunning) {
            nextTurn();
            if (!reader.isAlive()) isRunning = false;
        }
    }
}