package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.CellMasks.W_001_1X1_110;
import static com.mazes.model.dungeon.allocator.CellMasks.W_100_1X1_011;


/**
 * Created by sergii.tsyban on 11/29/2016.
 */
public class SolidZBreakerAdjuster implements TopologyAdjuster {

    private static final int[] Z_MASKS = {
            W_001_1X1_110,
            W_100_1X1_011
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if (CellUtils.hasMask(Z_MASKS, mask)) {
                    cave[i][j] = 0;
                    cave[i + 1][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }

}