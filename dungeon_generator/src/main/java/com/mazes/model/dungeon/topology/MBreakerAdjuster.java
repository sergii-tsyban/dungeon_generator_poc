package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.cell.CellMasks.*;

public class MBreakerAdjuster implements TopologyAdjuster {

    private static final int[] M_TL_MASKS = {
            W_011_1X0_100,
    };

    private static final int[] M_TR_MASKS = {
            W_110_0X1_001,
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.hasMask(M_TL_MASKS, mask)){
                    cave[i][j] = 0;
                    cave[i][j - 1] = 0;
                    cave[i - 1][j] = 0;
                    wasAdjusted = true;
                } else if(CellUtils.hasMask(M_TR_MASKS, mask)){
                    cave[i][j] = 0;
                    cave[i][j - 1] = 0;
                    cave[i + 1][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }

}
