package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_FRONT_TOP;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class WallFrontTopMatcher extends TileMatcher {

    private WallFrontBottomMatcher bottomMatcher = new WallFrontBottomMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{WALL_FRONT_TOP};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j);
    }

}
