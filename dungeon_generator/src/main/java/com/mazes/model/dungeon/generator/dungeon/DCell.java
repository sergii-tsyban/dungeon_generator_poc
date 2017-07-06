package com.mazes.model.dungeon.generator.dungeon;

public class DCell {

    public int x;
    public int y;

    public DCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DCell dCell = (DCell) o;

        if (x != dCell.x) return false;
        return y == dCell.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
