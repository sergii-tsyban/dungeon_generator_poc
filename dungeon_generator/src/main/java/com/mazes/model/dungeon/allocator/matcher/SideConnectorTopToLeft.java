package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.allocator.TileType;
import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TileType.*;

public class SideConnectorTopToLeft extends CellMatcher {

    @Override
    public TileType[] getTiles() {
        return arr(SIDE_CONNECTOR_TOP_TO_LEFT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.CONNECTOR_BR_INSIDE_MASKS, mask);
        return hasMask;
    }

}
