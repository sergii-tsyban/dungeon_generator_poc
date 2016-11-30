package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.CellMasks.*;

/**
 * Created by sergii.tsyban on 11/30/2016.
 */
public class MBreakerAdjuster implements TopologyAdjuster {

    private static final int[] M_MASKS = {
            W_011_1X0_100,
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.hasMask(M_MASKS, mask)){
                    cave[i][j] = 0;
                    cave[i][j - 1] = 0;
                    cave[i - 1][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }

}
