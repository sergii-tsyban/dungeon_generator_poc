package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.allocator.TileType;
import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TileType.SIDE_LEFT_WITH_WALL_CONNECTOR;
import static com.mazes.model.dungeon.allocator.TileType.arr;

/**
 * Created by sergii.tsyban on 11/28/2016.
 */
public class SideLeftWithWallConnMatcher extends CellMatcher {

    private WallCornerTopLeftMatcher wallCornerTopLeftMatcher = new WallCornerTopLeftMatcher();
    private WallFrontBottomMatcher wallFrontBottomMatcher = new WallFrontBottomMatcher();

    @Override
    public TileType[] getTiles() {
        return arr(SIDE_LEFT_WITH_WALL_CONNECTOR);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == cave[0].length - 1){
            return false;
        }
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.SIDE_LEFT_WITH_WALL_CONN, mask);
        return hasMask && (wallFrontBottomMatcher.matched(cave, i + 1, j + 1) || wallCornerTopLeftMatcher.matched(cave, i + 1, j + 1));
    }
}