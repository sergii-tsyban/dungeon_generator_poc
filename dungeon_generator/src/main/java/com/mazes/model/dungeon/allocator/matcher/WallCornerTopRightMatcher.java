package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.WALL_CORNER_TR;

public class WallCornerTopRightMatcher extends CellMatcher {

    @Override
    public int[] getIds() {
//        return new int[]{FLOOR, CORNER_TR_MASKS};
        return new int[]{WALL_CORNER_TR};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.CORNER_TR_MASKS, mask);
        return hasMask;
    }

}