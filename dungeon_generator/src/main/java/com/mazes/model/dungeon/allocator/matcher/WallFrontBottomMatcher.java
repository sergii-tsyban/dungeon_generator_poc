package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.WALL_FRONT_BOTTOM;

public class WallFrontBottomMatcher extends CellMatcher {

    @Override
    public int[] getIds() {
        return new int[]{WALL_FRONT_BOTTOM};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.WALL_FRONT_BOTTOM_MASKS, mask);
        return hasMask;
    }

}