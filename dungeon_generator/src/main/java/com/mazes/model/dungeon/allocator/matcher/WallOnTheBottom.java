package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;
import static com.mazes.model.dungeon.common.TilesIds.WALL_B;

public class WallOnTheBottom extends CellMatcherElement {

    @Override
    public int getId() {
        return WALL_B;
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] != FLOOR &&
//                CellUtils.countWallsAround(cave, i, j) >= 5 &&
                i != 0 &&
                cave[i - 1][j] == FLOOR &&
                cave[i][j - 1] != FLOOR &&
                cave[i][j + 1] != FLOOR;
    }

}
