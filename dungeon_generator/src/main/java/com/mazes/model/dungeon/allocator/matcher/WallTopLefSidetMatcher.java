package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.common.TilesIds.*;

/**
 * Created by sergii.tsyban on 11/24/2016.
 */
public class WallTopLefSidetMatcher extends CellMatcher {

    private WallCornerTopRightMatcher bottomMatcher = new WallCornerTopRightMatcher();

    @Override
    public int[] getIds() {
        return new int[]{WALL_TOP_LEFT_SIDE};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == 0){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j - 1] == FLOOR;
    }

}
