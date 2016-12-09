package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.TerrainTileType;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class SideConnectorTopToRightMatcher extends TileMatcher {

    @Override
    public TerrainTileType[] getTiles() {
        return arr(FLOOR, SIDE_CONNECTOR_TOP_TO_RIGHT, WALL_SOLID);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TerrainTileMasks.CONNECTOR_BL_INSIDE_MASKS, mask);
        return hasMask;
    }

}
