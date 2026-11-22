package elements;

public record Coordinates(int x, int y) {

    public boolean canShift(SimulationMap simulationMap, Coordinates coordinates) {
        int xShift = x() + coordinates.x();
        int yShift = y() + coordinates.y();
        return xShift >= 1 && xShift <= simulationMap.getWidth() && yShift >= 1 && yShift <= simulationMap.getHeight();

    }
}

