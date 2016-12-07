package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.TerrainTileType;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class WallFrontBottomMatcher extends TileMatcher {

    @Override
    public TerrainTileType[] getTiles() {
        return arr(WALL_FRONT_BOTTOM);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TerrainTileMasks.WALL_FRONT_BOTTOM_MASKS, mask);
        return hasMask;
    }

}
