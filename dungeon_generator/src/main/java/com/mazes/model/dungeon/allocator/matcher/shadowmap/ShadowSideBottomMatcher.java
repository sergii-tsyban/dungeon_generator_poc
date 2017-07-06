package com.mazes.model.dungeon.allocator.matcher.shadowmap;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.matcher.TileMatcher;
import com.mazes.model.dungeon.cells.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SHADOW_SIDE_BOTTOM;

public class ShadowSideBottomMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{SHADOW_SIDE_BOTTOM};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.Shadow.SHADOW_SIDE_BOTTOM_MASKS, mask);
        return hasMask;
    }
}
