package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.allocator.TileType;
import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TileType.*;

public class SideRightMatcher extends CellMatcher {

    @Override
    public TileType[] getTiles() {
        return arr(SIDE_RIGHT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.SIDE_R_MASKS, mask);
        return hasMask;
    }

}