package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.allocator.TileType;
import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TileType.*;

public class WallCornerTopRightMatcher extends CellMatcher {

    @Override
    public TileType[] getTiles() {
//        return new int[]{FLOOR, CORNER_TR_MASKS};
        return arr(WALL_CORNER_TOP_RIGHT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TileMasks.CORNER_TR_MASKS, mask);
        return hasMask;
    }

}