package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.cells.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SIDE_LEFT_WITH_WALL_CONNECTOR;

public class SideLeftWithWallConnMatcher extends TileMatcher {

    private WallCornerTopLeftMatcher wallCornerTopLeftMatcher = new WallCornerTopLeftMatcher();
    private WallFrontBottomMatcher wallFrontBottomMatcher = new WallFrontBottomMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR, SIDE_LEFT_WITH_WALL_CONNECTOR};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == cave[0].length - 1){
            return false;
        }
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.maskMatched(TerrainTileMasks.SIDE_LEFT_WITH_WALL_CONN, mask);
        return hasMask && (wallFrontBottomMatcher.matched(cave, i + 1, j + 1) || wallCornerTopLeftMatcher.matched(cave, i + 1, j + 1));
    }
}