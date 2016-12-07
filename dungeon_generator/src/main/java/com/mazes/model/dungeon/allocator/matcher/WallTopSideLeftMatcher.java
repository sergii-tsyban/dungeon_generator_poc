package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class WallTopSideLeftMatcher extends TileMatcher {

    private WallCornerTopRightMatcher bottomMatcher = new WallCornerTopRightMatcher();

    @Override
    public TerrainTileType[] getTiles() {
        return arr(WALL_TOP_SIDE_LEFT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == 0){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j - 1] == FLOOR.getId();
    }

}
