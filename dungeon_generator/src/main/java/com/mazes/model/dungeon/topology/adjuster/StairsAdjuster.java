package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cells.CellUtils;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;
import static com.mazes.model.dungeon.cells.CellMasks.*;

public class StairsAdjuster implements TopologyAdjuster {

    private static final int[] STAIRS_MASKS = {
            W_111_1X0_100,
            W_111_0X1_001,
            W_100_1X0_111,
            W_001_0X1_111
    };
    private double removeStepProb = 0.3f;

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                if(isStair(cave, i, j)){
                    wasAdjusted = tryMakeEmpty(cave, i, j);
                }
            }
        }
        return wasAdjusted;
    }

    private boolean isStair(int[][] cave, int i, int j){
        int mask = CellUtils.toMask(cave, i, j);
        return CellUtils.maskMatched(STAIRS_MASKS, mask);
    }

    private boolean tryMakeEmpty(int[][] cave, int i, int j){
        if(Math.random() < removeStepProb){
            cave[i][j] = FLOOR;
            return true;
        }
        return false;
    }
}
