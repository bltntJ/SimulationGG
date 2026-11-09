package actions;

import actions.turnactions.CreaturesGenerator;
import actions.turnactions.CreaturesMover;
import actions.turnactions.CreaturesRemover;
import actions.turnactions.GrassGenerator;
import elements.Creature;
import game.SimulationMap;

public class TurnActionsMain {
    private final CreaturesGenerator creaturesGenerator;
    private final CreaturesMover creaturesMover;
    private final CreaturesRemover creaturesRemover;
    private final GrassGenerator grassGenerator;

    public TurnActionsMain(SimulationMap simulationMap) {
        this.creaturesGenerator = new CreaturesGenerator(simulationMap);
        this.creaturesMover = new CreaturesMover(simulationMap);
        this.creaturesRemover = new CreaturesRemover(simulationMap);
        this.grassGenerator = new GrassGenerator(simulationMap);
    }

    public <T extends Creature> void callCreaturesGenerator(Class<T> creatureClass) {
        creaturesGenerator.generateCreatures(creatureClass);
    }

    public void callCreaturesMover() {
        creaturesMover.moveCreatures();
    }

    public void callCreaturesRemover() {
        creaturesRemover.removeCreature();
    }

    public void callGrassGenerator() {
        grassGenerator.generateGrass();
    }
}