package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class Floor extends TileMatcher {

    @Override
    public TerrainTileType[] getTiles() {
        return arr(FLOOR);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] == FLOOR.getId();
    }

}