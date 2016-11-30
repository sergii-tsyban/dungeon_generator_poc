package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.CellMasks.*;

/**
 * Created by sergii.tsyban on 11/30/2016.
 */
public class XBreakerAdjuster implements TopologyAdjuster {

    private static final int[] X_MASKS = {
            W_110_1X0_001,
            W_100_0X1_011,
            W_011_0X1_100,
            W_001_1X0_110
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.hasMask(X_MASKS, mask)){
                    cave[i][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }
}
