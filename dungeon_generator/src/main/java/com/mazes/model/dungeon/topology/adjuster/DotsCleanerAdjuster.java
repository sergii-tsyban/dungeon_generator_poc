package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.cell.CellMasks.*;

public class DotsCleanerAdjuster implements TopologyAdjuster {

    private static final int[] DOTS_MASKS = {
            W_000_0X0_000,
            W_100_0X0_000,
            W_001_0X0_000,
            W_000_0X0_100,
            W_000_0X0_001,
            W_100_0X0_001,
            W_001_0X0_100,
            W_000_0X0_010,
            W_010_0X0_000
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.maskMatched(DOTS_MASKS, mask)){
                    cave[i][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }

}
