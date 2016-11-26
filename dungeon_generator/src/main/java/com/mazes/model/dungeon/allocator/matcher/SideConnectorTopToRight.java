package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.*;

public class SideConnectorTopToRight extends CellMatcher {

    @Override
    public int[] getIds() {
        return new int[]{SIDE_CONNECTOR_TOP_TO_RIGHT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.CONNECTOR_BL_INSIDE_MASKS, mask);
        return hasMask;
    }

}
