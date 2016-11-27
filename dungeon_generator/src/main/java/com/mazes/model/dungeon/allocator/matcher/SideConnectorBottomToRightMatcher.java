package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.allocator.TileType;
import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TileType.*;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class SideConnectorBottomToRightMatcher extends CellMatcher {

    @Override
    public TileType[] getTiles() {
        return arr(SIDE_CONNECTOR_BOTTOM_TO_RIGHT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.CONNECTOR_BR_MASKS, mask);
        return hasMask;
    }

}
