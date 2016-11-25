package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.common.TilesIds.*;

/**
 * Created by sergii.tsyban on 11/24/2016.
 */
public class WallTopRightSideMatcher extends CellMatcher {

    private WallCornerTopLeftMatcher bottomMatcher = new WallCornerTopLeftMatcher();

    @Override
    public int[] getIds() {
        return new int[]{WALL_TOP_SIDE_RIGHT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == cave[0].length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j + 1] == FLOOR;
    }

}