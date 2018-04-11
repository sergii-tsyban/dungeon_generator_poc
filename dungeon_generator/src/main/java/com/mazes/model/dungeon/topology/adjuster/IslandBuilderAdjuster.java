package com.mazes.model.dungeon.topology.adjuster;

import com.mazes.model.dungeon.cells.CellUtils;

import java.util.Random;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class IslandBuilderAdjuster implements TopologyAdjuster {

    private Random r = new Random();

    private int[][][] smallIslandsPatterns = {
            {
                    {0, 1, 1},
                    {1, 1, 1},
                    {1, 1, 0},
            },
            {
                    {1, 1, 1},
                    {1, 1, 1},
                    {0, 1, 1},
            },
            {
                    {1, 1, 0},
                    {1, 1, 0},
                    {1, 1, 0},
            },
            {
                    {0, 1, 1},
                    {1, 1, 1},
                    {1, 1, 0},
            }
    };

    private int[][][] mediumIslandsPatterns = {
            {
                    {0, 0, 0, 0},
                    {0, 1, 1, 0},
                    {1, 1, 1, 0},
                    {1, 1, 0, 0},
            },
            {
                    {1, 1, 1, 0},
                    {1, 1, 1, 1},
                    {0, 0, 1, 1},
                    {0, 0, 1, 1},
            },
            {
                    {0, 0, 1, 1},
                    {0, 1, 1, 1},
                    {1, 1, 1, 0},
                    {1, 1, 0, 0},
            },
            {
                    {0, 0, 0, 0},
                    {0, 1, 1, 1},
                    {0, 1, 1, 1},
                    {0, 0, 1, 1},
            },
            {
                    {0, 1, 1, 0},
                    {1, 1, 1, 0},
                    {1, 1, 0, 0},
                    {1, 1, 0, 0},
            },
            {
                    {0, 0, 0, 0},
                    {0, 0, 1, 1},
                    {0, 1, 1, 1},
                    {0, 1, 1, 0},
            },
    };

    private int[][][] largeIslandsPatterns = {
            {
                    {1, 1, 0, 1, 1, 0},
                    {1, 1, 0, 1, 1, 0},
                    {0, 0, 0, 1, 1, 1},
                    {0, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 0, 0, 0},
            },
            {
                    {0, 0, 1, 1, 1, 0},
                    {0, 1, 1, 1, 1, 0},
                    {1, 1, 1, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1},
                    {0, 0, 1, 1, 1, 1},
            },
            {
                    {0, 0, 1, 1, 1, 0},
                    {0, 0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1},
                    {1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 1, 0, 0},
                    {0, 1, 1, 1, 0, 0},
            },
            {
                    {0, 1, 1, 1, 1, 0},
                    {0, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 1, 1},
                    {1, 1, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1},
                    {0, 1, 1, 1, 1, 1},
            },
    };

    @Override
    public boolean adjust(int[][] cave) {
        boolean large = adjust(cave, largeIslandsPatterns, 1);
        boolean medium = adjust(cave, mediumIslandsPatterns, 1);
        boolean small = adjust(cave, smallIslandsPatterns, 1);
        return large || medium || small;
    }

    private boolean adjust(int[][] cave, int[][][] islandPattern, double prob) {
        int step = islandPattern[0].length + 2;
        for (int i = 1; i < cave.length - 1 - step; i++) {
            for (int j = 1; j < cave[0].length - 1 - step; ) {
                if (isSuitable(cave, i, j, step)) {
                    if (Math.random() <= prob) {
                        allocateIsland(cave, islandPattern, i, j, step);
                        j += step;
                    } else {
                        j++;
                    }
                } else {
                    j++;
                }
            }
        }
        return false;
    }

    private boolean isSuitable(int[][] cave, int h, int w, int step) {
        for (int i = h; i < h + step; i++) {
            for (int j = w; j < w + step; j++) {
                if (cave[i][j] == WALL_SOLID) {
                    return false;
                }
            }
        }
        return true;
    }

    private void allocateIsland(int[][] cave, int[][][] islandPatterns, int h, int w, int step) {
        int[][] selectedIsland = transformRandomly(islandPatterns[r.nextInt(100) % islandPatterns.length]);
        int startI = (step - selectedIsland.length) / 2;
        int startJ = (step - selectedIsland[0].length) / 2;
        for (int i = 0; i < selectedIsland.length; i++) {
            for (int j = 0; j < selectedIsland[0].length; j++) {
                cave[h + startI + i][w + startJ + j] = selectedIsland[i][j];
            }
        }
    }

    private int[][] transformRandomly(int[][] arr) {
        //TODO : fix flip
//        if(r.nextInt(100) < 50){
//            CellUtils.flipHorizontal(arr);
//        } else {
//            CellUtils.flipVertical(arr);
//        }
        int rand = r.nextInt(100);
        boolean rotCW = rand < 50;
        int iter = 0;
        while (iter < rand % 3) {
            if (rotCW) {
                arr = CellUtils.rotateCW(arr);
            } else {
                arr = CellUtils.rotateCCW(arr);
            }
            iter++;
        }
        return arr;
    }

}
