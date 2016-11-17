package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;

public class Floor extends CellMatcherElement {

    @Override
    public int[] getIds() {
        return new int[]{FLOOR};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] == FLOOR;
    }

}