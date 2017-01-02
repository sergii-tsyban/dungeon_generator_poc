package com.mazes.model.dungeon.allocator.matcher;


import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_TOP_WALL_SIDE_RIGHT;

public class WallTopWallSideRightMatcher extends TileMatcher {

    private WallCornerTopRightMatcher bottomMatcher = new WallCornerTopRightMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{WALL_TOP_WALL_SIDE_RIGHT};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == 0){
            return false;
        }
        return bottomMatcher.matched(cave, i + 1, j) && cave[i][j - 1] == WALL_SOLID;
    }

}
