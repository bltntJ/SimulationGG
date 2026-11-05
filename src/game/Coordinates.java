package game;

public record Coordinates(Integer row, Integer column) {

    public Coordinates shift(Coordinates shift) {
        return new Coordinates(row + shift.row(), column + shift.column());
    }

    public boolean canShift(Coordinates shift, Integer height, Integer width) {
        int r = row + shift.row();
        int c = column + shift.column();

        return (r >= 0 && r <= height) && (c >= 0 && c <= width);
    }
}
