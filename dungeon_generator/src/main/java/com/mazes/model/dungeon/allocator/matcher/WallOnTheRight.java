package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;
import static com.mazes.model.dungeon.common.TilesIds.WALL_R;

public class WallOnTheRight extends CellMatcherElement {

    @Override
    public int getId() {
        return WALL_R;
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] != FLOOR &&
//                CellUtils.countWallsAround(cave, i, j) >= 5 &&
                j != 0 &&
                cave[i + 1][j] != FLOOR &&
                cave[i - 1][j] != FLOOR &&
                cave[i][j - 1] == FLOOR;
    }

}