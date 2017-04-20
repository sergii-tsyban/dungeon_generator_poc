package com.mazes.model.dungeon.allocator.matcher.shadowmap;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.matcher.TileMatcher;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SHADOW_OUTER_CORNER;

public class ShadowOuterCornerMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{SHADOW_OUTER_CORNER};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.Shadow.SHADOW_OUTER_CORNER_MASKS, mask);
        return hasMask;
    }
}
