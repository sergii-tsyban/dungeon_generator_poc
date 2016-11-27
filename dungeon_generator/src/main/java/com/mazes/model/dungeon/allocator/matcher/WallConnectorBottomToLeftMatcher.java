package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileType;

import static com.mazes.model.dungeon.allocator.TileType.*;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class WallConnectorBottomToLeftMatcher extends CellMatcher {

    private WallSideLeft wallSideLeft = new WallSideLeft();
    private WallTopWallSideRightMatcher wallTopWallSideRightMatcher = new WallTopWallSideRightMatcher();

    @Override
    public TileType[] getTiles() {
        return arr(WALL_CONNECTOR_BOTTOM_TO_LEFT);
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
