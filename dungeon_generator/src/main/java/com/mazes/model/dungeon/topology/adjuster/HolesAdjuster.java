package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cells.CellUtils;

import static com.mazes.model.dungeon.cells.CellMasks.*;

public class HolesAdjuster implements TopologyAdjuster {

    private static final int[] HOLES_MASKS = {
            E_111_1X1_000,
            E_111_1X1_101,
            E_111_1X1_001,
            E_111_1X1_100,

            E_000_1X1_111,
            E_001_1X1_111,
            E_100_1X1_111,
            E_101_1X1_111,

            E_011_0X1_011,
            E_111_0X1_011,
            E_011_0X1_111,
            E_111_0X1_111,

            E_110_1X0_110,
            E_111_1X0_110,
            E_110_1X0_111,
            E_111_1X0_111,
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.maskMatched(HOLES_MASKS, mask)){
                    cave[i][j] = 1;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }
}
