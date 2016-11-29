package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.allocator.TileType;
import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TileType.SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT;
import static com.mazes.model.dungeon.allocator.TileType.arr;

/**
 * Created by sergii.tsyban on 11/29/2016.
 */
public class SideConnectorTRWithWallSideLeftMatcher extends CellMatcher {

    @Override
    public TileType[] getTiles() {
        return arr(SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT_MASKS, mask);
        return hasMask;
    }
}
