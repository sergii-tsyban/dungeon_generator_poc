package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.common.TilesIds.WALL_SIDE_RIGHT;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class SideLeftWithWallMatcher extends CellMatcher {

    private WallFrontBottomMatcher bottomMatcher = new WallFrontBottomMatcher();
    private WallCornerTopLeftMatcher cornerMatcher = new WallCornerTopLeftMatcher();

    @Override
    public int[] getIds() {
        return new int[]{WALL_SIDE_RIGHT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(j == cave[0].length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i, j + 1) || cornerMatcher.matched(cave, i, j + 1);
    }

}
