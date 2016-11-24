package com.mazes.model.dungeon.allocator;

public class TileMasks {

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
     1 0 0    1 0 0    0 0 0    0 0 0
     1 X 0    1 X 0    1 X 0    1 X 0
     1 1 0    1 1 0    1 1 1    1 1 0
     */
    public static final int[] CORNER_BL = {
            0b100_110_111,
            0b000_110_111,
            0b000_110_110,
            0b100_110_110,
    };

    /**
     0 0 0    0 0 1    0 0 0    0 0 1
     0 X 1    0 X 1    0 X 1    0 X 1
     0 1 1    1 1 1    1 1 1    0 1 1
     */
    public static final int[] CORNER_BR = {
            0b001_011_111,
            0b001_011_011,
            0b000_011_011,
            0b000_011_111,
    };

    //----------------------------------------

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
    0 0 0
    0 X 0
    1 1 1
     */
    public static final int[] WALL_B = {
            0b000_111_111
    };

    /**
    1 1 0    1 1 1    1 0 0
    1 X 0    1 X 0    1 X 0
    1 1 0    1 1 0    1 1 0
     */
    public static final int[] SIDE_L_MASKS = {
            CellMasks.W_110_1X0_110,
            CellMasks.W_111_1X0_110,
            CellMasks.W_100_1X0_110
    };

    /**
    0 1 1    1 1 1    0 0 1
    0 X 1    0 X 1    0 X 1
    0 1 1    0 1 1    0 1 1
     */
    public static final int[] SIDE_R_MASKS = {
            CellMasks.W_011_0X1_011,
            CellMasks.W_111_0X1_011,
            CellMasks.W_001_0X1_011
    };

    //----------------------------------------

    /**
     1 1 1
     1 X 1
     1 1 0
    */
    public static final int[] WALL_CORNER_TL = {
            0b111_111_110
    };

    /**
     1 1 1
     1 X 1
     0 1 1
    */
    public static final int[] WALL_CORNER_TR = {
            0b111_111_011
    };

    /**
     1 1 0
     1 X 1
     1 1 1
    */
    public static final int[] WALL_CORNER_BL = {
            0b110_111_111
    };

    /**
     0 1 1
     1 X 1
     1 1 1
    */
    public static final int[] WALL_CORNER_BR = {
            0b011_111_111
    };

    //----------------------------------------

    /**
     1 0 0    1 1 0    0 0 0
     1 X 0    1 X 0    1 X 0
     1 1 1    1 1 1    1 1 1
     */
    public static final int[] CONNECTOR_BL_INSIDE_MASKS = {
            CellMasks.W_100_1X0_111,
            CellMasks.W_110_1X0_111,
            CellMasks.W_000_1X0_111,
    };

    /**
     0 0 1    0 1 1    0 0 0
     0 X 1    0 X 1    0 X 1
     1 1 1    1 1 1    1 1 1
     */
    public static final int[] CONNECTOR_BR_INSIDE_MASKS = {
            CellMasks.W_001_0X1_111,
            CellMasks.W_011_0X1_111,
            CellMasks.W_000_0X1_111,
    };

    //----------------------------------------

    /**
     0 0 0    1 0 0    0 0 0    1 1 1    1 1 0    1 0 0    0 1 1    0 0 1
     1 X 0    1 X 0    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0
     1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0    1 1 0
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
    };

    /**
     0 0 0    0 0 1    0 0 0    1 1 1    1 1 0    1 0 0    0 1 1    0 0 1
     0 X 1    0 X 1    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0    0 X 0
     0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1    0 1 1
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
    };
}
