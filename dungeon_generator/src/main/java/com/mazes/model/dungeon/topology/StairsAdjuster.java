package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.utilsl.CellUtils;

import static com.mazes.model.dungeon.allocator.TilesIds.FLOOR;

/**
 * Created by serg on 22/11/2016.
 */
public class StairsAdjuster implements TopologyAdjuster {

    private double removeStepProb = 0.3f;

    @Override
    public boolean adjust(int[][] cave) {
        boolean wasAdjusted = false;
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                if(isStair(cave, i, j)){
                    wasAdjusted = tryMakeEmpty(cave, i, j);
                }
            }
        }
        return wasAdjusted;
    }

    private boolean isStair(int[][] cave, int i, int j){
        int mask = CellUtils.toMask(cave, i, j);
        return CellUtils.hasMask(new int[]{
                0b111_110_100,
                0b111_011_001,
                0b100_110_111,
                0b001_011_111
        },mask);
    }

    private boolean tryMakeEmpty(int[][] cave, int i, int j){
        if(Math.random() < removeStepProb){
            cave[i][j] = FLOOR;
            return true;
        }
        return false;
    }
}
