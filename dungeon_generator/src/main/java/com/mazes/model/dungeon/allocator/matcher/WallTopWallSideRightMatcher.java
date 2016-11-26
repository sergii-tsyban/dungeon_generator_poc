package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.common.TilesIds.WALL_SOLID;
import static com.mazes.model.dungeon.common.TilesIds.WALL_TOP_WALL_SIDE_RIGHT;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class WallTopWallSideRightMatcher extends CellMatcher {

    private WallCornerTopRightMatcher bottomMatcher = new WallCornerTopRightMatcher();

    @Override
    public int[] getIds() {
        return new int[]{WALL_TOP_WALL_SIDE_RIGHT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == 0){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j - 1] == WALL_SOLID;
    }

}
