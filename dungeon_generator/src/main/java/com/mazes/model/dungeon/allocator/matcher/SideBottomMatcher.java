package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.SIDE_BOTTOM;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class SideBottomMatcher extends CellMatcher {

    @Override
    public int[] getIds() {
        return new int[]{SIDE_BOTTOM};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.SIDE_BOTTOM_MASKS, mask);
        return hasMask;
    }
}
