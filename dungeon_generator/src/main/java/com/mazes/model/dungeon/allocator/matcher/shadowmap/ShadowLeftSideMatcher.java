package com.mazes.model.dungeon.allocator.matcher.shadowmap;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.matcher.TileMatcher;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SHADOW_LEFT_SIDE;

public class ShadowLeftSideMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{SHADOW_LEFT_SIDE};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.Shadow.SHADOW_LEFT_SIDE_MASKS, mask);
        return hasMask;
    }
}
