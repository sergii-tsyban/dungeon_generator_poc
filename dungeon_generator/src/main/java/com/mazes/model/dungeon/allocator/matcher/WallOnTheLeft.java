package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;
import static com.mazes.model.dungeon.common.TilesIds.WALL_L;

public class WallOnTheLeft extends CellMatcherElement {

    @Override
    public int[] getIds() {
        return new int[]{FLOOR, WALL_L};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return cave[i][j] != FLOOR &&
//                CellUtils.countWallsAround(cave, i, j) >= 5 &&
                j != cave[0].length - 1 &&
                cave[i + 1][j] != FLOOR &&
                cave[i - 1][j] != FLOOR &&
                cave[i][j + 1] == FLOOR;
    }
}
