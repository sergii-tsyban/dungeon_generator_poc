package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TileType;

import static com.mazes.model.dungeon.allocator.TileType.*;

/**
 * Created by sergii.tsyban on 11/23/2016.
 */
public class WallFrontTopMatcher extends CellMatcher {

    private WallFrontBottomMatcher bottomMatcher = new WallFrontBottomMatcher();

    @Override
    public TileType[] getTiles() {
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
