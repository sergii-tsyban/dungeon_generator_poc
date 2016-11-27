package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileType;

import static com.mazes.model.dungeon.allocator.TileType.*;

public class Floor extends CellMatcher {

    @Override
    public TileType[] getTiles() {
        return arr(FLOOR);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] == FLOOR.getId();
    }

}