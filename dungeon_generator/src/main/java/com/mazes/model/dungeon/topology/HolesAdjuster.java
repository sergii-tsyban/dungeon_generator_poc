package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.cell.CellUtils;

import static com.mazes.model.dungeon.cell.CellMasks.*;

public class HolesAdjuster implements TopologyAdjuster {

    private static final int[] HOLES_MASKS = {
            E_111_1X1_000,
            E_000_1X1_111,
            E_011_0X1_011,
            E_110_1X0_110,
            E_100_1X1_111,
            E_001_1X1_111,
            E_111_1X0_110,
            E_110_1X0_111
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.hasMask(HOLES_MASKS, mask)){
                    cave[i][j] = 1;
                    wasAdjusted = true;
                }
            }
        }
        return wasAdjusted;
    }
}
