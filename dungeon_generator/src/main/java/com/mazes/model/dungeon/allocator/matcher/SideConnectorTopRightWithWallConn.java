package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.SIDE_BOTTOM;
import static com.mazes.model.dungeon.common.TilesIds.SIDE_CONNECTOR_TR_WITH_WALL_CONN;

/**
 * Created by sergii.tsyban on 11/25/2016.
 */
public class SideConnectorTopRightWithWallConn extends CellMatcher {

    private WallCornerTopRightMatcher wallCornerTopRightMatcher = new WallCornerTopRightMatcher();
    private WallFrontBottomMatcher wallFrontBottomMatcher = new WallFrontBottomMatcher();

    @Override
    public int[] getIds() {
        return new int[]{SIDE_CONNECTOR_TR_WITH_WALL_CONN};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == 0){
            return false;
        }
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.SIDE_CONNECTOR_TR_WITH_WALL_CONN, mask);
        return hasMask && (wallFrontBottomMatcher.matched(cave, i + 1, j - 1) || wallCornerTopRightMatcher.matched(cave, i + 1, j - 1));
    }
}