package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.allocator.TileMasks;
import com.mazes.model.dungeon.utils.CellUtils;

import static com.mazes.model.dungeon.allocator.CellMasks.*;

/**
 * Created by serg on 25/11/2016.
 */
public class TeethAdjuster implements TopologyAdjuster {

    private static final int[] TEETH_MASKS = {
            W_100_1X0_100,
            W_101_1X0_100,
            W_100_1X0_101,
            W_101_1X0_101,
            W_001_0X1_001,
            W_101_0X1_001,
            W_001_0X1_101,
            W_101_0X1_101,
            W_101_0X0_111,
            W_100_0X0_111,
            W_001_0X0_111,
            W_000_0X0_111,
            W_111_0X0_100,
            W_111_0X0_001,
            W_111_0X0_101,
            W_111_0X0_000
    };

    @Override
    public void adjust(int[][] cave) {
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                int mask = CellUtils.toMask(cave, i, j);
                if(CellUtils.hasMask(TEETH_MASKS, mask)){
                    cave[i][j] = 0;
                }
            }
        }
    }
}
