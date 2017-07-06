package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.cells.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_FRONT_BOTTOM;

public class WallFrontBottomMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR, WALL_FRONT_BOTTOM};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.WALL_FRONT_BOTTOM_MASKS, mask);
        return hasMask;
    }

}
