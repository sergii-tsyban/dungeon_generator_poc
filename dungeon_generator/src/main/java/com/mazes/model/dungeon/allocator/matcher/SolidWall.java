package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;
import static com.mazes.model.dungeon.common.TilesIds.WALL_SOLID;

public class SolidWall extends CellMatcherElement {

    @Override
    public int[] getIds() {
        return new int[]{WALL_SOLID};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        return (cave[i][j] == WALL_SOLID &&
                CellUtils.countWallsAround(cave, i, j) >=  7 &&
                CellUtils.onEdge(cave, i, j)) ||
                (!CellUtils.onEdge(cave, i, j) &&
                 cave[i][j] == WALL_SOLID &&
                 cave[i][j - 1] != FLOOR &&
                 cave[i][j + 1] != FLOOR &&
                 cave[i + 1][j] != FLOOR &&
                 cave[i - 1][j] != FLOOR);
    }

}

