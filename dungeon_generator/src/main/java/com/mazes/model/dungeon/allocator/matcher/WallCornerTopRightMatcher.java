package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.TerrainTileType;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class WallCornerTopRightMatcher extends TileMatcher {

    @Override
    public TerrainTileType[] getTiles() {
//        return new int[]{FLOOR, CORNER_TR_MASKS};
        return arr(WALL_CORNER_TOP_RIGHT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TerrainTileMasks.CORNER_TR_MASKS, mask);
        return hasMask;
    }

}