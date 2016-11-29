package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.allocator.TileType;
import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TileType.SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT;
import static com.mazes.model.dungeon.allocator.TileType.arr;

/**
 * Created by sergii.tsyban on 11/29/2016.
 */
public class SideConnectorTLWithWallSideRightMatcher extends CellMatcher {

    @Override
    public TileType[] getTiles() {
        return arr(SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT_MASKS, mask);
        return hasMask;
    }
}
