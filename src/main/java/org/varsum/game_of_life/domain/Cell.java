package org.varsum.game_of_life.domain;

/**
 * @author The Watchmaker
 */
class Cell {
    private final int x;
    private final int y;


    private Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Cell at(int x, int y){
        return new Cell(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        if (y != cell.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;

        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
