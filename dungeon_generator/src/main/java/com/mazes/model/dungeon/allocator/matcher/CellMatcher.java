package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileType;

public abstract class CellMatcher {

    public abstract TileType[] getTiles();

    public abstract boolean matched(int[][] cave, int i, int j);

}
