package com.mazes.model.dungeon.allocator;

public class TileMasks {

    /**
     No tile
     1 0 0    1 1 1    0 0 1    1 1 0    0 1 1
     1 X 1    1 X 1    1 X 1    1 X 1    1 X 1
     1 1 1    1 1 1    1 1 1    1 1 1    1 1 1
     */
    public static final int[] NO_TILE = {
            CellMasls.W_111_1X1_111,
            CellMasls.W_001_1X1_111,
            CellMasls.W_100_1X1_111,
            CellMasls.W_110_1X1_111,
            CellMasls.W_011_1X1_111,
            CellMasls.W_000_1X1_111
    };

    //----------------------------------------

    /**
     Corner top left
     1 1 1    1 1 0    1 1 1    1 1 0
     1 X 0    1 X 0    1 X 0    1 X 0
     1 0 0    1 0 0    0 0 0    0 0 0
     */
    public static final int[] CORNER_TL = {
            0b110_110_000,
            0b111_110_000,
            0b111_110_100,
            0b110_110_100,
    };

    /**
     Corner top right
     1 1 1    0 1 1    1 1 1    0 1 1
     0 X 1    0 X 1    0 X 1    0 X 1
     0 0 1    0 0 1    0 0 0    0 0 0
     */
    public static final int[] CORNER_TR = {
            0b111_011_001,
            0b111_011_000,
            0b011_011_000,
            0b011_011_001,
    };

    /**
     Corner bottom left
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
     Corner bottom right
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
    Wall on top
    1 1 1    1 1 1    1 1 1
    1 X 1    1 X 1    1 X 1
    1 0 0    0 0 0    0 0 1
     */
    public static final int[] WALL_T = {
            0b111_111_000,
            0b111_111_001,
            0b111_111_100
    };

    /**
    Wall on bottom
    0 0 0
    0 X 0
    1 1 1
     */
    public static final int[] WALL_B = {
            0b000_111_111
    };

    /**
    Wall left
    1 1 0    1 1 1
    1 X 0    1 X 0
    1 1 0    1 1 0
     */
    public static final int[] WALL_L = {
            0b110_110_110,
            0b111_110_110
    };

    /**
    Wall right
    0 1 1    1 1 1
    0 X 1    0 X 1
    0 1 1    0 1 1
     */
    public static final int[] WALL_R = {
            0b011_011_011,
            0b111_011_011
    };

    //----------------------------------------

    /**
    Wall corner top left
     1 1 1
     1 X 1
     1 1 0
    */
    public static final int[] WALL_CORNER_TL = {
            0b111_111_110
    };

    /**
    Wall corner top right
     1 1 1
     1 X 1
     0 1 1
    */
    public static final int[] WALL_CORNER_TR = {
            0b111_111_011
    };

    /**
    Wall corner bottom left
     1 1 0
     1 X 1
     1 1 1
    */
    public static final int[] WALL_CORNER_BL = {
            0b110_111_111
    };

    /**
    Wall corner bottom right
     0 1 1
     1 X 1
     1 1 1
    */
    public static final int[] WALL_CORNER_BR = {
            0b011_111_111
    };

    //----------------------------------------

}
