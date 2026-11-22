package elements;

import actions.*;
import settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulationMap simulationMap;
    private final MapRenderer mapRenderer;
    private final MoveCounter moveCounter;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();
    private volatile boolean isRunning;

    public Simulation(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
        this.mapRenderer = new MapRenderer();
        this.moveCounter = new MoveCounter();

        createActions();
        executeActions(initActions);
    }

    private void createActions() {
        initActions.add(new HerbivoreSpawnAction(Settings.HERBIVORE_START_COUNT));
        initActions.add(new PredatorSpawnAction(Settings.PREDATOR_START_COUNT));
        initActions.add(new GrassSpawnAction(Settings.GRASS_START_COUNT));
        initActions.add(new TreeSpawnAction(Settings.TREE_START_COUNT));
        initActions.add(new RockSpawnAction(Settings.ROCK_START_COUNT));

        turnActions.add(new MoveCreaturesAction());
        turnActions.add(new RemoveDeadCreatureAction());
        turnActions.add(new AdditionalHerbivoreSpawnAction(Settings.HERBIVORE_ADDITIONAL_COUNT));
        turnActions.add(new AdditionalPredatorSpawnAction(Settings.PREDATOR_ADDITIONAL_COUNT));
        turnActions.add(new AdditionalGrassSpawnAction(Settings.GRASS_ADDITIONAL_COUNT));
    }

    public void startSimulation() {
        if (isRunning) return;
        isRunning = true;
        System.out.println("Started...");
        sleep();
        new Thread(() -> {
            while (isRunning) {
                nextTurn();
                sleep();
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
        executeActions(turnActions);
        moveCounter.printCount();
    }

    private void executeActions(List<Action> actions) {
        for(Action action : actions) {
            action.execute(simulationMap);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}