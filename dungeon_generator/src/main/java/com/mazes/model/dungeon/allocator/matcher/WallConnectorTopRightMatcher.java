package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.common.TilesIds.CONNECTOR_TR;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class WallConnectorTopRightMatcher extends CellMatcher {

    private SideRightWithWallMatcher sideRightWithWallMatcher = new SideRightWithWallMatcher();
    private WallTopLeftMatcher wallTopLeftMatcher = new WallTopLeftMatcher();

    @Override
    public int[] getIds() {
        return new int[]{CONNECTOR_TR};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1){
            return false;
        }
        return sideRightWithWallMatcher.matched(cave, i + 1, j) ||
                wallTopLeftMatcher.matched(cave, i + 1, j);
    }

}
