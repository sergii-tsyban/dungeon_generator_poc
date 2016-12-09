package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.cell.CellMasks.*;

public class ZBreakerAdjuster implements TopologyAdjuster {

    private static final int[] Z_MASKS = {
            W_110_0X0_011,
            W_011_0X0_110,
            W_001_1X1_100,
            W_100_1X1_001
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.hasMask(Z_MASKS, mask)){
                    cave[i][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }

}
