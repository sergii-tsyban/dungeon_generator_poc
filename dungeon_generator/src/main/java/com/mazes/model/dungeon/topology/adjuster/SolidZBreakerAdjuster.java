package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cells.CellUtils;

import static com.mazes.model.dungeon.cells.CellMasks.W_001_1X1_110;
import static com.mazes.model.dungeon.cells.CellMasks.W_100_1X1_011;
import static com.mazes.model.dungeon.cells.CellMasks.W_110_1X0_101;

public class SolidZBreakerAdjuster implements TopologyAdjuster {

    private static final int[] Z_MASKS = {
            W_001_1X1_110,
            W_100_1X1_011,
            W_110_1X0_101
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if (CellUtils.maskMatched(Z_MASKS, mask)) {
                    cave[i][j] = 0;
                    cave[i + 1][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }

}
