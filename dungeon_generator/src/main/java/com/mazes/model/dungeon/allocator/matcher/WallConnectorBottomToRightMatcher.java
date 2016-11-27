package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileType;

import static com.mazes.model.dungeon.allocator.TileType.*;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class WallConnectorBottomToRightMatcher extends CellMatcher {

    private WallSideRight wallSideRight = new WallSideRight();
    private WallTopWallSideLeftMatcher wallTopWallSideLeftMatcher = new WallTopWallSideLeftMatcher();

    @Override
    public TileType[] getTiles() {
        return arr(WALL_CONNECTOR_BOTTOM_TO_RIGHT);
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
