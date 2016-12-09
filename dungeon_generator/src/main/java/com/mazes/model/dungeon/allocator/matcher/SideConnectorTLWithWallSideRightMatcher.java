package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.TerrainTileType;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class SideConnectorTLWithWallSideRightMatcher extends TileMatcher {

    @Override
    public TerrainTileType[] getTiles() {
        return arr(FLOOR, SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT, WALL_SOLID);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TerrainTileMasks.SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT_MASKS, mask);
        return hasMask;
    }
}
