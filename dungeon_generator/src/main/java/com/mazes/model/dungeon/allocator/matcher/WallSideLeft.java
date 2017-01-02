package com.mazes.model.dungeon.allocator.matcher;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SIDE_LEFT;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class WallSideLeft extends TileMatcher {

    private WallFrontBottomMatcher bottomMatcher = new WallFrontBottomMatcher();
    private WallCornerTopRightMatcher cornerMatcher = new WallCornerTopRightMatcher ();

    @Override
    public int[] getTiles() {
        return new int[]{WALL_SIDE_LEFT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(j == 0){
            return false;
        }
        return bottomMatcher.matched(cave, i, j - 1) || cornerMatcher.matched(cave, i, j - 1);
    }

}
