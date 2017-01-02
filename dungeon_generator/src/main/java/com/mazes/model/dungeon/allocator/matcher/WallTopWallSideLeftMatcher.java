package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_TOP_WALL_SIDE_LEFT;

public class WallTopWallSideLeftMatcher extends TileMatcher {

    private WallCornerTopLeftMatcher bottomMatcher = new WallCornerTopLeftMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{WALL_TOP_WALL_SIDE_LEFT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == cave[0].length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j + 1] == WALL_SOLID;
    }

}
