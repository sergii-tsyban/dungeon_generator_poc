package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class WallConnectorBottomToLeftMatcher extends TileMatcher {

    private WallSideLeft wallSideLeft = new WallSideLeft();
    private WallTopWallSideRightMatcher wallTopWallSideRightMatcher = new WallTopWallSideRightMatcher();

    @Override
    public TerrainTileType[] getTiles() {
        return arr(WALL_CONNECTOR_BOTTOM_TO_LEFT, WALL_SOLID);
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
