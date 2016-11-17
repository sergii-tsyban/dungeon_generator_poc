package com.mazes.model.dungeon.cell;

public class Cell {

    private int x;
    private int y;

    private int[] tileLayers;

    public Cell(int x, int y, int[] tileLayers) {
        this.x = x;
        this.y = y;
        this.tileLayers = tileLayers;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getTileLayers() {
        return tileLayers;
    }
}
