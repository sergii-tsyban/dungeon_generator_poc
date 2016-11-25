package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.common.TilesIds.WALL_CONNECTOR_BOTTOM_TO_RIGHT;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class WallConnectorTopLeftMatcher extends CellMatcher {

    private SideLeftWithWallMatcher sideLeftWithWallMatcher = new SideLeftWithWallMatcher();
    private WallTopRightMatcher wallTopRightMatcher = new WallTopRightMatcher();

    @Override
    public int[] getIds() {
        return new int[]{WALL_CONNECTOR_BOTTOM_TO_RIGHT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1){
            return false;
        }
        return sideLeftWithWallMatcher.matched(cave, i + 1, j) ||
                wallTopRightMatcher.matched(cave, i + 1, j);
    }

}
