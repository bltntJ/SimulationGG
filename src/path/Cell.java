package path;

import java.util.Objects;

public class Cell {
    private final int r;
    private final int c;

    public Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return r == cell.r && c == cell.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}