package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;
import static com.mazes.model.dungeon.common.TilesIds.WALL_T;

public class WallOnTheTop extends CellMatcherElement {

    @Override
    public int[] getIds() {
        return new int[]{FLOOR, WALL_T};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] != FLOOR &&
//                CellUtils.countWallsAround(cave, i, j) >= 5 &&
                i != cave.length - 1 &&
                cave[i + 1][j] == FLOOR &&
                cave[i][j - 1] != FLOOR &&
                cave[i][j + 1] != FLOOR;
    }

}
