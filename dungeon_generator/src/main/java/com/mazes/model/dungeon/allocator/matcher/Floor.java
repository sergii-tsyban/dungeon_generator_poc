package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;

public class Floor extends CellMatcher {

    @Override
    public int[] getIds() {
        return new int[]{FLOOR};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] == FLOOR;
    }

}