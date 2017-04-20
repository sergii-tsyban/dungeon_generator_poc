package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.cell.CellMasks.*;

public class XBreakerAdjuster implements TopologyAdjuster {

    private static final int[] X_MASKS = {
            W_110_1X0_001,
            W_100_0X1_011,
            W_011_0X1_100,
            W_001_1X0_110,
            W_100_0X0_010,
            W_001_0X0_101,
            W_100_0X0_011,
            W_001_0X0_110,
            W_001_0X0_011,
            W_100_0X0_110
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.maskMatched(X_MASKS, mask)){
                    cave[i][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }
}
