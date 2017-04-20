package com.mazes.model.dungeon.allocator;

import com.mazes.model.dungeon.cell.CellMasks;

public class TerrainTileMasks {

    /**
     1 1 1    0 0 1    1 0 0    1 1 0    0 1 1    0 0 0
     1 X 1    1 X 1    1 X 1    1 X 1    1 X 1    1 X 1
     1 1 1    1 1 1    1 1 1    1 1 1    1 1 1    1 1 1
     */
    public static final int[] NO_TILE_MASKS = {
            CellMasks.W_111_1X1_111,
            CellMasks.W_001_1X1_111,
            CellMasks.W_100_1X1_111,
            CellMasks.W_110_1X1_111,
            CellMasks.W_011_1X1_111,
            CellMasks.W_000_1X1_111
    };

    //----------------------------------------

    /**
     1 1 1    1 1 0    1 1 1    1 1 0
     1 X 0    1 X 0    1 X 0    1 X 0
     1 0 0    1 0 0    0 0 0    0 0 0
     */
    public static final int[] CORNER_TL_MASKS = {
            CellMasks.W_111_1X0_100,
            CellMasks.W_110_1X0_000,
            CellMasks.W_111_1X0_000,
            CellMasks.W_110_1X0_100,
    };

    /**
     1 1 1    0 1 1    1 1 1    0 1 1
     0 X 1    0 X 1    0 X 1    0 X 1
     0 0 1    0 0 1    0 0 0    0 0 0
     */
    public static final int[] CORNER_TR_MASKS = {
            CellMasks.W_111_0X1_001,
            CellMasks.W_011_0X1_001,
            CellMasks.W_111_0X1_000,
            CellMasks.W_011_0X1_000,
    };

    /**
    1 1 1    1 1 1    1 1 1
    1 X 1    1 X 1    1 X 1
    1 0 0    0 0 0    0 0 1
     */
    public static final int[] WALL_FRONT_BOTTOM_MASKS = {
            CellMasks.W_111_1X1_000,
            CellMasks.W_111_1X1_001,
            CellMasks.W_111_1X1_100
    };

    /**
    1 1 0    1 1 1    1 0 0    0 0 0    0 1 1    0 0 1
    1 X 0    1 X 0    1 X 0    1 X 0    1 1 0    1 1 0
    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0
     */
    public static final int[] SIDE_L_MASKS = {
            CellMasks.W_110_1X0_110,
            CellMasks.W_111_1X0_110,
            CellMasks.W_100_1X0_110,
            CellMasks.W_000_1X0_110,
            CellMasks.W_011_1X0_110,
            CellMasks.W_001_1X0_110
    };

    /**
    0 1 1    1 1 1    0 0 1    0 0 0    1 0 0    1 1 0
    0 X 1    0 X 1    0 X 1    0 X 1    0 1 1    0 1 1
    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1
     */
    public static final int[] SIDE_R_MASKS = {
            CellMasks.W_011_0X1_011,
            CellMasks.W_111_0X1_011,
            CellMasks.W_001_0X1_011,
            CellMasks.W_000_0X1_011,
            CellMasks.W_100_0X1_011,
            CellMasks.W_110_0X1_011
    };

    //----------------------------------------

    /**
     1 0 0    1 1 0    0 0 0    1 1 1
     1 X 0    1 X 0    1 X 0    1 X 0
     1 1 1    1 1 1    1 1 1    1 1 1
     */
    public static final int[] CONNECTOR_BL_INSIDE_MASKS = {
            CellMasks.W_100_1X0_111,
            CellMasks.W_110_1X0_111,
            CellMasks.W_000_1X0_111,
            CellMasks.W_111_1X0_111
    };

    /**
     0 0 1    0 1 1    0 0 0    1 1 1
     0 X 1    0 X 1    0 X 1    0 X 1
     1 1 1    1 1 1    1 1 1    1 1 1
     */
    public static final int[] CONNECTOR_BR_INSIDE_MASKS = {
            CellMasks.W_001_0X1_111,
            CellMasks.W_011_0X1_111,
            CellMasks.W_000_0X1_111,
            CellMasks.W_111_0X1_111
    };

    //----------------------------------------

    /**
     0 0 0    1 0 0    0 0 0    1 1 1    1 1 0    1 0 0    0 1 1    0 0 1    1 1 0    0 0 1
     1 X 0    1 X 0    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0    1 0 0    1 0 0
     1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0
     */
    public static final int[] CONNECTOR_BL_MASKS = {
            CellMasks.E_000_1X0_110,
            CellMasks.E_100_1X0_110,
            CellMasks.E_000_0X0_110,
            CellMasks.E_111_0X0_110,
            CellMasks.E_110_0X0_110,
            CellMasks.E_100_0X0_110,
            CellMasks.E_011_0X0_110,
            CellMasks.E_001_0X0_110,
            CellMasks.E_110_1X0_110,
            CellMasks.E_001_1X0_110,
            CellMasks.E_101_1X0_110
    };

    /**
     0 0 0    0 0 1    0 0 0    1 1 1    1 1 0    1 0 0    0 1 1    0 0 1    0 1 1    1 0 0
     0 X 1    0 X 1    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0    0 0 1    0 0 1
     0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1
     */
    public static final int[] CONNECTOR_BR_MASKS = {
            CellMasks.E_000_0X1_011,
            CellMasks.E_001_0X1_011,
            CellMasks.E_000_0X0_011,
            CellMasks.E_111_0X0_011,
            CellMasks.E_110_0X0_011,
            CellMasks.E_100_0X0_011,
            CellMasks.E_011_0X0_011,
            CellMasks.E_001_0X0_011,
            CellMasks.E_011_0X1_011,
            CellMasks.E_100_0X1_011,
            CellMasks.E_101_0X1_011
    };

    /**
     0 0 0    0 0 0    0 0 0    1 0 0    0 0 1    1 1 1    0 1 1    0 0 1    1 0 0    1 1 0
     0 X 0    1 X 0    0 X 1    1 X 0    0 X 1    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0
     1 1 1    1 1 1    1 1 1    1 1 1    1 1 1    1 1 1    1 1 1    1 1 1    1 1 1    1 1 1
     */

    public static int[] SIDE_BOTTOM_MASKS  = {
            CellMasks.E_000_0X0_111,
            CellMasks.E_000_1X0_111,
            CellMasks.E_000_0X1_111,
            CellMasks.E_100_1X0_111,
            CellMasks.E_001_0X1_111,
            CellMasks.E_111_0X0_111,
            CellMasks.E_011_0X0_111,
            CellMasks.E_001_0X0_111,
            CellMasks.E_100_0X0_111,
            CellMasks.E_110_0X0_111,
            CellMasks.E_101_0X1_111,
            CellMasks.E_101_1X0_111,
            CellMasks.E_100_0X1_111,
            CellMasks.E_001_1X0_111
    };

    //----------------------------------------

    public static int[] SIDE_CONNECTOR_TR_WITH_WALL_CONN = {
            CellMasks.W_110_1X0_111,
            CellMasks.W_100_1X0_111,
            CellMasks.W_000_1X0_111,
    };

    public static int[] SIDE_CONNECTOR_TL_WITH_WALL_CONN = {
            CellMasks.W_011_0X1_111,
            CellMasks.W_001_0X1_111,
            CellMasks.W_000_0X1_111
    };

    public static int[] SIDE_LEFT_WITH_WALL_CONN = {
            CellMasks.W_011_0X1_011,
            CellMasks.W_001_0X1_011,
            CellMasks.W_000_0X1_011,
            CellMasks.W_111_0X1_011
    };

    public static int[] SIDE_RIGHT_WITH_WALL_CONN = {
            CellMasks.W_100_1X0_110,
            CellMasks.W_000_1X0_110,
            CellMasks.W_110_1X0_110,
            CellMasks.W_111_1X0_110
    };

    //--------------------------------------------

    public static int[] SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT_MASKS = {
            CellMasks.W_011_0X1_110
    };

    public static int[] SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT_MASKS = {
            CellMasks.W_110_1X0_011
    };

    public static class Shadow{

        public static int[] SHADOW_SIDE_BOTTOM_MASKS  = {
                CellMasks.E_000_0X0_111,
                CellMasks.E_000_0X1_111,
                CellMasks.E_001_0X1_111,
                CellMasks.E_001_0X0_110,
                CellMasks.E_111_0X0_111,
                CellMasks.E_011_0X0_111,
                CellMasks.E_001_0X0_111,
                CellMasks.E_100_0X0_111,
                CellMasks.E_110_0X0_111,
                CellMasks.E_101_0X1_111,
                CellMasks.E_100_0X1_111,
                CellMasks.E_100_0X0_110,
                CellMasks.E_011_0X0_110,
                CellMasks.E_000_0X0_110,
                CellMasks.E_110_0X0_110,
                CellMasks.E_111_0X0_110,
        };

        /**
         0 0 0    1 0 0    0 0 1    1 0 1    1 0 1
         1 X 0    1 X 0    1 X 0    1 X 0    1 X 0
         1 1 1    1 1 1    1 1 1    1 1 1    1 1 0
         */

        public static int[] SHADOW_INNER_CORNER_MASKS  = {
                CellMasks.E_000_1X0_111,
                CellMasks.E_000_1X0_110,
                CellMasks.E_100_1X0_111,
                CellMasks.E_100_1X0_110,
                CellMasks.E_101_1X0_111,
                CellMasks.E_001_1X0_111,
                CellMasks.E_001_1X0_110,
                CellMasks.E_101_1X0_110
        };

        /**
         0 0 0  0 0 0  0 0 0  0 0 1  0 1 0  1 0 0  1 1 0  1 1 1  0 1 1  0 0 0  0 0 1  0 0 1  0 1 1  1 0 0  1 1 1  0 1 1
         0 X 0  0 X 0  0 X 1  0 X 0  0 X 0  0 X 0  0 X 0  0 X 0  0 X 0  0 X 1  0 X 1  0 X 1  0 X 1  0 X 0  0 X 1  0 X 1
         1 0 0  1 0 1  1 0 0  1 0 0  1 0 0  1 0 0  1 0 0  1 0 0  1 0 0  1 0 1  1 0 1  1 0 0  1 0 0  1 0 1  1 0 0  1 0 1
         */

        public static int[] SHADOW_OUTER_CORNER_MASKS  = {
                CellMasks.E_000_0X0_100,
                CellMasks.E_000_0X0_101,
                CellMasks.E_000_0X1_100,
                CellMasks.E_001_0X0_100,
                CellMasks.E_010_0X0_100,
                CellMasks.E_100_0X0_100,
                CellMasks.E_110_0X0_100,
                CellMasks.E_111_0X0_100,
                CellMasks.E_111_0X1_101,
                CellMasks.E_011_0X0_100,
                CellMasks.E_000_0X1_101,
                CellMasks.E_001_0X1_101,
                CellMasks.E_001_0X1_100,
                CellMasks.E_011_0X1_100,
                CellMasks.E_100_0X0_101,
                CellMasks.E_111_0X1_100,
                CellMasks.E_011_0X1_101,


        };

        /**
         1 0 0   1 0 1   1 0 0   1 0 0   1 0 0   1 0 1   1 0 1   1 1 1   1 1 0   0 0 0   0 0 0   0 0 0   0 0 1   0 0 1   0 0 1
         1 X 0   1 X 0   1 X 0   1 X 1   1 X 1   1 X 1   1 X 1   1 X 0   1 X 0   1 X 0   1 X 0   1 X 1   1 X 1   1 X 1   1 X 0
         1 0 0   1 0 0   1 0 1   1 0 0   1 0 1   1 0 0   1 0 1   1 0 0   1 0 0   1 0 0   1 0 1   1 0 1   1 0 1   1 0 0   1 0 0
         */

        public static int[] SHADOW_LEFT_SIDE_MASKS  = {
                CellMasks.E_100_1X0_100,
                CellMasks.E_101_1X0_100,
                CellMasks.E_100_1X0_101,
                CellMasks.E_100_1X1_100,
                CellMasks.E_100_1X1_101,
                CellMasks.E_101_1X1_100,
                CellMasks.E_101_1X1_101,
                CellMasks.E_111_1X0_100,
                CellMasks.E_111_1X0_101,
                CellMasks.E_110_1X0_100,
                CellMasks.E_110_1X0_101,
                CellMasks.E_000_1X0_100,
                CellMasks.E_000_1X0_101,
                CellMasks.E_000_1X1_101,
                CellMasks.E_001_1X1_101,
                CellMasks.E_001_1X1_100,
                CellMasks.E_001_1X0_100,

        };

        /**
         0 0 0    0 0 0    0 0 1    1 0 0    1 1 0    1 1 1    0 1 1    0 0 1
         0 X 0    0 X 1    0 X 1    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0
         0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1
         */

        public static int[] SHADOW_TOP_SIDE_CORNER_MASKS  = {
                CellMasks.E_000_0X0_011,
                CellMasks.E_000_0X1_011,
                CellMasks.E_001_0X1_011,
                CellMasks.E_100_0X0_011,
                CellMasks.E_100_0X1_011,
                CellMasks.E_101_0X1_011,
                CellMasks.E_110_0X0_011,
                CellMasks.E_111_0X0_011,
                CellMasks.E_011_0X0_011,
                CellMasks.E_001_0X0_011,
        };

        /**
         1 0 0    1 0 1    1 0 1    1 0 1    1 0 0    1 0 0    1 1 0    1 1 1    1 1 1    1 0 1   1 0 1
         1 X 0    1 X 0    1 X 1    1 X 1    1 X 1    1 X 0    1 X 0    1 X 0    1 X 0    1 X 0   1 X 1
         0 0 0    0 0 0    0 0 0    0 0 1    0 0 1    0 0 1    0 0 0    0 0 0    0 0 1    0 0 1   0 0 1
         */

        public static int[] SHADOW_BOTTOM_WALL_CORNER_MASKS  = {
                CellMasks.E_100_1X0_000,
                CellMasks.E_101_1X0_000,
                CellMasks.E_101_1X1_000,
                CellMasks.E_101_1X1_001,
                CellMasks.E_100_1X1_001,
                CellMasks.E_100_1X0_001,
                CellMasks.E_110_1X0_000,
                CellMasks.E_110_1X0_001,
                CellMasks.E_111_1X0_000,
                CellMasks.E_111_1X0_001,
                CellMasks.E_101_1X0_001,
        };
    }
}
