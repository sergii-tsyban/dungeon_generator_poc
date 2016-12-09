package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class WallSideRight extends TileMatcher {

    private WallFrontBottomMatcher bottomMatcher = new WallFrontBottomMatcher();
    private WallCornerTopLeftMatcher cornerMatcher = new WallCornerTopLeftMatcher();

    @Override
    public TerrainTileType[] getTiles() {
        return arr(WALL_SIDE_RIGHT, WALL_SOLID);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(j == cave[0].length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i, j + 1) || cornerMatcher.matched(cave, i, j + 1);
    }

}
