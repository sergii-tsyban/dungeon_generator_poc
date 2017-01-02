package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_CONNECTOR_BOTTOM_TO_LEFT;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class WallConnectorBottomToLeftMatcher extends TileMatcher {

    private WallSideLeft wallSideLeft = new WallSideLeft();
    private WallTopWallSideRightMatcher wallTopWallSideRightMatcher = new WallTopWallSideRightMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{WALL_CONNECTOR_BOTTOM_TO_LEFT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1){
            return false;
        }
        return wallSideLeft.matched(cave, i + 1, j) ||
                wallTopWallSideRightMatcher.matched(cave, i + 1, j);
    }
}
