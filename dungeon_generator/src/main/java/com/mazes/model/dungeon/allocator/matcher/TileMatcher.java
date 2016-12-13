package com.mazes.model.dungeon.allocator.matcher;

public abstract class TileMatcher {

    public abstract int[] getTiles();

    public abstract boolean matched(int[][] cave, int i, int j);

}
