package elements;

public record Coordinates(int x, int y) {

    public Coordinates shift(Coordinates shift) {
        return new Coordinates(x() + shift.x(), y() + shift.y());
    }

    public boolean canShift(SimulationMap simulationMap, Coordinates shift) {
        int xShift = x() + shift.x();
        int yShift = y() + shift.y();
        return xShift >= 1 && xShift <= simulationMap.getWidth() && yShift >= 1 && yShift <= simulationMap.getHeight();
//        return coordinates.x() >= 1 && coordinates.x() <= simulationMap.getWidth() && coordinates.y() >= 1 && coordinates.y() <= simulationMap.getHeight();
    }
}

