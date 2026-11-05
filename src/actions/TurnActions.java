package actions;

import config.SimulationConfig;
import entity.Creature;
import entity.Grass;
import entity.Herbivore;
import entity.Predator;
import game.Coordinates;
import game.SimulationMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TurnActions {
    private final SimulationMap simulationMap;
    private final Random random = new Random();

    public TurnActions(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
    }

    public void moveHerbivores() {
        List<Creature> creatures = new ArrayList<>(simulationMap.getEntities().values());

        for (Creature creature : creatures) {
            if (creature instanceof Herbivore herbivore) {
                herbivore.makeMove(simulationMap);
            }
        }
        System.out.println("\n" + "=".repeat(20));
        System.out.println("Herbivores made a move");
        System.out.println("=".repeat(20));
    }

    public void movePredators() {
        List<Creature> creatures = new ArrayList<>(simulationMap.getEntities().values());

        for (Creature creature : creatures) {
            if (creature instanceof Predator predator) {
                predator.makeMove(simulationMap);
            }
        }
        System.out.println("\n" + "=".repeat(20));
        System.out.println("Predators made a move");
        System.out.println("=".repeat(20) + "\n");
    }

    public void removeEntityIfHPZero() {
        List<Creature> creatures = new ArrayList<>(simulationMap.getEntities().values());

        for (Creature creature : creatures) {
            if (creature.getHP() <= 0) {
                simulationMap.getEntities().values().removeIf(entity -> entity == creature);
            }
        }
    }

    public boolean stopGameIfZeroHerbivores() {
        return simulationMap.getStaticEntities().values().stream().noneMatch(entity -> entity instanceof Herbivore);
    }

    public boolean stopGameIfZeroGrass() {
        return simulationMap.getStaticEntities().values().stream().noneMatch(entity -> entity instanceof Grass);
    }

    public void generateGrass() {
        if (simulationMap.getStaticEntities().values().stream().filter(entity -> entity instanceof Grass).count() >= 2) return;
        int i = 0;
        while (i < 2) {
            int randomRow = random.nextInt(simulationMap.getHeight() + 1);
            int randomColumn = random.nextInt(simulationMap.getWidth() + 1);
            Coordinates coordinates = new Coordinates(randomRow, randomColumn);

            if (!simulationMap.getEntities().containsKey(coordinates) && !simulationMap.getStaticEntities().containsKey(coordinates)) {
                simulationMap.setStaticEntity(coordinates, new Grass(coordinates));
                i++;
            }
        }
        System.out.println("=".repeat(25));
        System.out.println("Additional grass was added");
        System.out.println("=".repeat(25) + "\n");

    }
}