package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import static com.mazes.model.dungeon.allocator.TerrainTileType.*;

public class WallFrontTopMatcher extends TileMatcher {

    private WallFrontBottomMatcher bottomMatcher = new WallFrontBottomMatcher();

    @Override
    public TerrainTileType[] getTiles() {
        return arr(WALL_FRONT_TOP);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j);
    }

}
