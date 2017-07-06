package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cells.CellUtils;

import java.util.Random;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class IslandBuilderAdjuster implements TopologyAdjuster {

    private static final int STEP = 10;
    private static final double APPEARANCE_PROB = 0.3;

    private Random r = new Random();

    private int[][][] islandPattern = {
            {
                    {0,0,1,1,0,0,0,0},
                    {0,0,1,1,1,0,0,0},
                    {0,1,1,1,1,1,0,0},
                    {1,1,1,1,1,1,1,0},
                    {1,1,1,1,1,1,1,0},
                    {0,1,1,1,1,1,1,0},
                    {0,0,1,1,1,1,0,0},
                    {0,0,0,1,1,0,0,0},
            },
            {
                    {0,0,0,0,1,1,0,0},
                    {0,0,0,1,1,1,0,0},
                    {0,0,1,1,1,0,0,0},
                    {0,1,1,1,1,0,0,0},
                    {0,1,1,1,1,1,0,0},
                    {0,0,1,1,1,1,1,0},
                    {0,0,0,1,1,1,1,0},
                    {0,0,0,0,1,1,0,0},
            },
            {
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,1,1,1,0},
                    {0,0,0,1,1,1,1,1},
                    {0,0,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,0},
                    {1,1,1,1,1,1,0,0},
                    {0,1,1,1,1,0,0,0},
                    {0,0,1,1,0,0,0,0},
            },
            {
                    {0,0,1,1,1,1,0,0},
                    {0,1,1,1,1,1,1,0},
                    {1,1,1,0,0,1,1,1},
                    {1,1,0,0,0,0,1,1},
                    {1,1,1,0,0,0,0,0},
                    {0,1,1,1,0,0,1,1},
                    {0,0,1,1,1,1,1,1},
                    {0,0,0,1,1,1,1,0},
            },
    };

    @Override
    public boolean adjust(int[][] cave) {
        for (int i = 0; i < cave.length - STEP; i++) {
            for (int j = 0; j < cave[0].length  - STEP;) {
                if(isSuitable(cave, i, j)){
                    if(tryToAllocateIsland(cave, i, j)){
                        j += STEP;
                    } else {
                        j++;
                    }
                }
            }
        }
        return false;
    }

    private boolean isSuitable(int[][] cave, int h, int w) {
        for (int i = h; i < h + STEP; i++) {
            for (int j = w; j < w + STEP; j++) {
                if (cave[i][j] == WALL_SOLID) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean tryToAllocateIsland(int[][] cave, int h, int w){
        if (Math.random() <= APPEARANCE_PROB){
            int[][] selectedIsland = transformRandomly(islandPattern[r.nextInt(100) % islandPattern.length]);
            int startI = (STEP - selectedIsland.length) / 2;
            int startJ = (STEP - selectedIsland[0].length) / 2;
            for (int i = 0; i < selectedIsland.length; i++) {
                for (int j = 0; j < selectedIsland[0].length; j++) {
                    cave[h + startI + i][w + startJ + j] = selectedIsland[i][j];
                }
            }
            return true;
        }
        return false;
    }

    private int[][] transformRandomly(int[][] arr){
        //TODO : fix flip
//        if(r.nextInt(100) < 50){
//            CellUtils.flipHorizontal(arr);
//        } else {
//            CellUtils.flipVertical(arr);
//        }
        int rand = r.nextInt(100);
        boolean rotCW = rand < 50;
        int iter = 0;
        while(iter < rand % 5) {
            if(rotCW){
                arr = CellUtils.rotateCW(arr);
            } else {
                arr = CellUtils.rotateCCW(arr);
            }
            iter++;
        }
        return arr;
    }

}
