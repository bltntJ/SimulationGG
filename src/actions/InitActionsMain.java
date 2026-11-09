package actions;

import actions.initactions.DefaultCreaturesPositionsSetter;
import actions.initactions.DefaultObjectsPositionsSetter;
import config.Config;
import elements.creatures.Herbivore;
import elements.creatures.Predator;
import elements.objects.Grass;
import elements.objects.Rock;
import elements.objects.Tree;
import game.SimulationMap;

public class InitActionsMain {
    private final SimulationMap simulationMap;

    public InitActionsMain(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public void setDefaultPositions() {
        final DefaultCreaturesPositionsSetter defaultCreaturesPositionsSetter = new DefaultCreaturesPositionsSetter(simulationMap);
        final DefaultObjectsPositionsSetter defaultObjectsPositionsSetter = new DefaultObjectsPositionsSetter(simulationMap);
        try {
            defaultCreaturesPositionsSetter.setDefaultPositionsForCreatures(Herbivore.class, Config.HERBIVORE_START_COUNT);
            defaultCreaturesPositionsSetter.setDefaultPositionsForCreatures(Predator.class, Config.PREDATOR_START_COUNT);
            defaultObjectsPositionsSetter.setDefaultPositionsForObjects(Grass.class, Config.GRASS_START_COUNT);
            defaultObjectsPositionsSetter.setDefaultPositionsForObjects(Rock.class, Config.ROCK_START_COUNT);
            defaultObjectsPositionsSetter.setDefaultPositionsForObjects(Tree.class, Config.TREE_START_COUNT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}