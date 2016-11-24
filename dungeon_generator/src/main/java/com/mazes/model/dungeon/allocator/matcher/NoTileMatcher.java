package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.NO_TILE;

public class NoTileMatcher extends CellMatcher {

    @Override
    public int[] getIds() {
        return new int[]{NO_TILE};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.NO_TILE_MASKS, mask);
        return hasMask;
    }

}

