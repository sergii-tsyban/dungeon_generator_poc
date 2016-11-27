package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileType;

import static com.mazes.model.dungeon.allocator.TileType.*;

/**
 * Created by sergii.tsyban on 11/24/2016.
 */
public class WallTopSideRightMatcher extends CellMatcher {

    private WallCornerTopLeftMatcher bottomMatcher = new WallCornerTopLeftMatcher();

    @Override
    public TileType[] getTiles() {
        return arr(WALL_TOP_SIDE_RIGHT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == cave[0].length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j + 1] == FLOOR.getId();
    }

}
