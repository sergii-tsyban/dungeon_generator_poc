package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SIDE_CONNECTOR_BOTTOM_TO_LEFT;


public class SideConnectorBottomToLeftMatcher extends TileMatcher {

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR, SIDE_CONNECTOR_BOTTOM_TO_LEFT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.CONNECTOR_BL_MASKS, mask);
        return hasMask;
    }

}
