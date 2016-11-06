package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.CORNER_TR;
import static com.mazes.model.dungeon.common.TilesIds.FLOOR;

public class WallCornerTopRight extends CellMatcherElement {

    @Override
    public int getId() {
        return CORNER_TR;
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] != FLOOR &&
                !CellUtils.onEdge(cave, i, j) &&
                cave[i + 1][j - 1] == FLOOR &&
                cave[i][j - 1] == FLOOR &&
                cave[i + 1][j] == FLOOR;
    }

}