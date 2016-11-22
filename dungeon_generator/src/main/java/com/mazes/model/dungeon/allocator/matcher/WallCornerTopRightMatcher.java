package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.CORNER_TR;
import static com.mazes.model.dungeon.common.TilesIds.FLOOR;

public class WallCornerTopRightMatcher extends CellMatcher {

    @Override
    public int[] getIds() {
//        return new int[]{FLOOR, CORNER_TR};
        return new int[]{CORNER_TR};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.CORNER_TR, mask);
        return hasMask;
    }

}