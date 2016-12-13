package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;

public class FloorMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] == FLOOR;
    }

}