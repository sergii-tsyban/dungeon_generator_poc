package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.cell.CellMasks.*;

public class WBreakerAdjuster implements TopologyAdjuster {

    private static final int[] W_MASKS = {
            W_001_0X1_110,
//            W_100_1X0_011
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.hasMask(W_MASKS, mask)){
                    cave[i+1][j+1] = 1;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }

}
