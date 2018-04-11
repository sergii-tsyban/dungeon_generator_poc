package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cells.CellUtils;

import static com.mazes.model.dungeon.cells.CellMasks.*;

public class TeethAdjuster implements TopologyAdjuster {

    private static final int[] TEETH_MASKS = {
            W_100_1X0_100,
            W_101_1X0_100,
            W_100_1X0_101,
            W_101_1X0_101,
            W_001_0X1_001,
            W_101_0X1_001,
            W_101_0X1_011,
            W_101_1X0_110,
            W_011_0X1_101,
            W_110_1X0_101,
            W_001_0X1_101,
            W_101_0X1_101,
            W_101_0X0_111,
            W_100_0X0_111,
            W_001_0X0_111,
            W_000_0X0_111,
            W_111_0X0_100,
            W_111_0X0_001,
            W_111_0X0_101,
            W_111_0X0_000,
            W_000_1X0_100,
            W_000_0X1_001,
            W_110_0X0_000,
            W_011_0X0_000,
            W_100_1X0_000,
            W_001_0X1_000,
            W_000_0X0_110,
            W_000_0X0_011,
            W_000_1X0_000,
            W_000_0X1_000
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.maskMatched(TEETH_MASKS, mask)){
                    cave[i][j] = 0;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }
}
