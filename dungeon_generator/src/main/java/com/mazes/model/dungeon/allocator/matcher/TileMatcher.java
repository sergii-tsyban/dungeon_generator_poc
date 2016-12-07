package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileType;

public abstract class TileMatcher {

    public abstract TerrainTileType[] getTiles();

    public abstract boolean matched(int[][] cave, int i, int j);

}
