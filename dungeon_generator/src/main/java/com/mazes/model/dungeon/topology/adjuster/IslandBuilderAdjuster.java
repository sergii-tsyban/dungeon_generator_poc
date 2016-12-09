package com.mazes.model.dungeon.topology.adjuster;

import static com.mazes.model.dungeon.allocator.TerrainTileType.WALL_SOLID;

public class IslandBuilderAdjuster implements TopologyAdjuster {

    private static final int STEP = 10;
    private static final double APPEARANCE_PROB = 0.15;

    private int[][][] islandPattern ={
            {
                    {0,1,1,0},
                    {1,1,1,1},
                    {1,1,1,1},
                    {0,1,1,0},
            },
            {
                    {0,0,1,1},
                    {1,1,1,1},
                    {1,1,1,1},
                    {1,1,0,0},
            },
            {
                    {1,1,0,0},
                    {1,1,1,1},
                    {1,1,1,1},
                    {0,0,1,1},
            },
            {
                    {0,1,1,1},
                    {0,1,1,1},
                    {1,1,1,0},
                    {1,1,1,0},
            },
            {
                    {1,1,1,0},
                    {1,1,1,0},
                    {0,1,1,1},
                    {0,1,1,1},
            },
            {
                    {0,1,1,0},
                    {1,1,1,0},
                    {1,1,1,1},
                    {0,0,1,1},
            },
            {
                    {0,0,1,1},
                    {1,1,1,1},
                    {1,1,1,0},
                    {1,1,0,0},
            },
            {
                    {0,1,1,0},
                    {1,1,1,1},
                    {1,1,1,1},
                    {0,0,1,1},
            },
    };

    @Override
    public boolean adjust(int[][] cave) {
        for (int i = 0; i < cave.length - STEP; i++) {
            for (int j = 0; j < cave[0].length  - STEP; j++) {
                if(isSuitable(cave, i, j)){
                    tryToAllocateIsland(cave, i, j);
                }
            }
        }
        return false;
    }

    private boolean isSuitable(int[][] cave, int h, int w) {
        for (int i = h; i < h + STEP; i++) {
            for (int j = w; j < w + STEP; j++) {
                if (cave[i][j] == WALL_SOLID.getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void tryToAllocateIsland(int[][] cave, int h, int w){
        if (Math.random() <= APPEARANCE_PROB){
            int startI = 3;
            int startJ = 3;
            int[][] selectedIsland = islandPattern[(int)(Math.random()*10) % islandPattern.length];
            for (int i = 0; i < selectedIsland.length; i++) {
                for (int j = 0; j < selectedIsland[0].length; j++) {
                    cave[h + startI + i][w + startJ + j] = selectedIsland[i][j];
                }
            }

        }
    }

}
