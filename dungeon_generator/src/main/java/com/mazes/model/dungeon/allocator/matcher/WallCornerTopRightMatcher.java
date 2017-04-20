package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_CORNER_TOP_RIGHT;

public class WallCornerTopRightMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR, WALL_CORNER_TOP_RIGHT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.CORNER_TR_MASKS, mask);
        return hasMask;
    }

}