package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_CONNECTOR_BOTTOM_TO_RIGHT;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class WallConnectorBottomToRightMatcher extends TileMatcher {

    private WallSideRight wallSideRight = new WallSideRight();
    private WallTopWallSideLeftMatcher wallTopWallSideLeftMatcher = new WallTopWallSideLeftMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{WALL_CONNECTOR_BOTTOM_TO_RIGHT, WALL_SOLID};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1){
            return false;
        }
        return wallSideRight.matched(cave, i + 1, j) ||
                wallTopWallSideLeftMatcher.matched(cave, i + 1, j);
    }

}
