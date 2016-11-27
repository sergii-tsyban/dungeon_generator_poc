package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileType;

import static com.mazes.model.dungeon.allocator.TileType.*;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class WallSideLeft extends CellMatcher {

    private WallFrontBottomMatcher bottomMatcher = new WallFrontBottomMatcher();
    private WallCornerTopRightMatcher cornerMatcher = new WallCornerTopRightMatcher ();

    @Override
    public TileType[] getTiles() {
        return arr(WALL_SIDE_LEFT);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(j == 0){
            return false;
        }
        return bottomMatcher.matched(cave, i, j - 1) || cornerMatcher.matched(cave, i, j - 1);
    }

}
