package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.allocator.TerrainTileMasks;
import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SIDE_CONNECTOR_TR_WITH_WALL_CONN;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class SideConnectorTopRightWithWallConn extends TileMatcher {

    private WallCornerTopRightMatcher wallCornerTopRightMatcher = new WallCornerTopRightMatcher();
    private WallFrontBottomMatcher wallFrontBottomMatcher = new WallFrontBottomMatcher();

    @Override
    public int[] getTiles() {
        return new int[]{FLOOR, SIDE_CONNECTOR_TR_WITH_WALL_CONN, WALL_SOLID};
    }

    @Override
    public boolean matched(int[][] cave, int i, int j) {
        if(i == cave.length - 1 || j == 0){
            return false;
        }
        int mask = CellUtils.toMask(cave, i, j);
        boolean hasMask = CellUtils.hasMask(TerrainTileMasks.SIDE_CONNECTOR_TR_WITH_WALL_CONN, mask);
        return hasMask && (wallFrontBottomMatcher.matched(cave, i + 1, j - 1) || wallCornerTopRightMatcher.matched(cave, i + 1, j - 1));
    }
}
