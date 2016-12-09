package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class WallTopWallSideLeftMatcher extends TileMatcher {

    private WallCornerTopLeftMatcher bottomMatcher = new WallCornerTopLeftMatcher();

    @Override
    public TerrainTileType[] getTiles() {
        return arr(WALL_TOP_WALL_SIDE_LEFT, WALL_SOLID);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == cave[0].length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j + 1] == WALL_SOLID.getId();
    }

}
