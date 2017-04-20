package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.NO_TILE;

public class NoTileMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{NO_TILE};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.NO_TILE_MASKS, mask);
        return hasMask;
    }

}

