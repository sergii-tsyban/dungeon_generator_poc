package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class SideConnectorTRWithWallSideLeftMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR, SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TerrainTileMasks.SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT_MASKS, mask);
        return hasMask;
    }
}
