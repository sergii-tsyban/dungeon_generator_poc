package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_TOP_SIDE_LEFT;

public class WallTopSideLeftMatcher extends TileMatcher {

    private WallCornerTopRightMatcher bottomMatcher = new WallCornerTopRightMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR, WALL_TOP_SIDE_LEFT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == 0){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j - 1] == FLOOR;
    }

}
