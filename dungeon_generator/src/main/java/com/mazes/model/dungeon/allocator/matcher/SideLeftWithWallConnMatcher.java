package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.allocator.TerrainTileType;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTileType.SIDE_LEFT_WITH_WALL_CONNECTOR;
import static com.mazes.model.dungeon.allocator.TerrainTileType.arr;

public class SideLeftWithWallConnMatcher extends TileMatcher {

    private WallCornerTopLeftMatcher wallCornerTopLeftMatcher = new WallCornerTopLeftMatcher();
    private WallFrontBottomMatcher wallFrontBottomMatcher = new WallFrontBottomMatcher();

    @Override
    public TerrainTileType[] getTiles() {
        return arr(SIDE_LEFT_WITH_WALL_CONNECTOR);
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == cave[0].length - 1){
            return false;
        }
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TerrainTileMasks.SIDE_LEFT_WITH_WALL_CONN, mask);
        return hasMask && (wallFrontBottomMatcher.matched(cave, i + 1, j + 1) || wallCornerTopLeftMatcher.matched(cave, i + 1, j + 1));
    }
}