package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.CORNER_TL;
import static com.mazes.model.dungeon.common.TilesIds.FLOOR;

public class WallCornerTopLeft extends CellMatcherElement {


    @Override
    public int[] getIds() {
        return new int[]{FLOOR, CORNER_TL};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] != FLOOR &&
                !CellUtils.onEdge(cave, i, j) &&
                cave[i + 1][j + 1] == FLOOR &&
                cave[i][j + 1] == FLOOR &&
                cave[i + 1][j] == FLOOR;
    }

}
