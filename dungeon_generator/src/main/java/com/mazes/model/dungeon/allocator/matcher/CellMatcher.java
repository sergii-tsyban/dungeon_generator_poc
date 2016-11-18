package com.mazes.model.dungeon.allocator.matcher;

public abstract class CellMatcher {

    public abstract int[] getIds();

    public abstract boolean matched(int[][] cave, int i, int j);

}
