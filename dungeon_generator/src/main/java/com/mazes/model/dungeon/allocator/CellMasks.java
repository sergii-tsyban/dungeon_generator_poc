package com.mazes.model.dungeon.allocator;

/**
 * Created by serg on 20/11/2016.
 */
public class CellMasks {

    /**
     1 1 1
     1 X 1
     1 1 1
     */
    public static final int W_111_1X1_111 = 0b111_111_111;

    //----------------------------------------------------

    /*
    . . . . . . .
    . . . X . . .
    . .       . .
    . X       X .
    . .       . .
    . . . X . . .
    . . . . . . .
     */

    /**
     0 0 0
     1 X 1
     1 1 1
     */
    public static final int W_000_1X1_111 = 0b000_111_111;

    /**
     1 1 1
     1 X 1
     0 0 0
     */
    public static final int W_111_1X1_000 = 0b111_111_000;

    /**
     1 1 0
     1 X 0
     1 1 0
     */
    public static final int W_110_1X0_110 = 0b110_110_110;

    /**
     0 1 1
     0 X 1
     0 1 1
     */
    public static final int W_011_0X1_011 = 0b011_011_011;

    //----------------------------------------------------

    /*
    . . . . . . .
    . . X . X . .
    . X       X .
    . .       . .
    . X       X .
    . . X . X . .
    . . . . . . .
     */

    /**
     1 1 1
     0 X 1
     0 1 1
     */
    public static final int W_111_0X1_011 = 0b111_011_011;

    /**
     0 1 1
     0 X 1
     1 1 1
     */
    public static final int W_011_0X1_111 = 0b011_011_111;

    /**
     0 0 1
     1 X 1
     1 1 1
     */
    public static final int W_001_1X1_111 = 0b001_111_111;

    /**
     1 0 0
     1 X 1
     1 1 1
     */
    public static final int W_100_1X1_111 = 0b100_111_111;

    /**
     1 1 0
     1 X 0
     1 1 1
     */
    public static final int W_110_1X0_111 = 0b110_110_111;

    /**
     1 1 1
     1 X 0
     1 1 0
     */
    public static final int W_111_1X0_110 = 0b111_110_110;

    /**
     1 1 1
     1 X 1
     1 0 0
     */
    public static final int W_111_1X1_100 = 0b111_111_100;

    /**
     1 1 1
     1 X 1
     0 0 1
     */
    public static final int W_111_1X1_001 = 0b111_111_001;

    //----------------------------------------------------

    /*
    . . . . . . .
    . X . . . X .
    . .       . .
    . .       . .
    . .       . .
    . X . . . X .
    . . . . . . .
     */

    /**
     1 1 1
     1 X 1
     0 1 1
     */
    public static final int W_111_1X1_011 = 0b111_111_011;

    /**
     0 1 1
     1 X 1
     1 1 1
     */
    public static final int W_011_1X1_111 = 0b011_111_111;

    /**
     1 1 0
     1 X 1
     1 1 1
     */
    public static final int W_110_1X1_111 = 0b110_111_111;

    /**
     1 1 1
     1 X 1
     1 1 0
     */
    public static final int W_111_1X1_110 = 0b111_111_110;

    //----------------------------------------------------

    /*
    . . . . . . . .
    . . . . . . . .
    . . X     X . .
    . .         . .
    . . X     X . .
    . . . . . . . .
    . . . . . . . .
     */

    /**
     1 1 1
     1 X 0
     1 0 0
     */
    public static final int W_111_1X0_100 = 0b111_110_100;

    /**
     1 0 0
     1 X 0
     1 1 1
     */
    public static final int W_100_1X0_111 = 0b100_110_111;

    /**
     0 0 1
     0 X 1
     1 1 1
     */
    public static final int W_001_0X1_111 = 0b001_011_111;

    /**
     1 1 1
     0 X 1
     0 0 1
     */
    public static final int W_111_0X1_001 = 0b111_011_001;

    //----------------------------------------------------

    /*
    . . . . . . . .
    . . . . . . . .
    . . X     X . .
    .             .
     */

    /**
     1 1 1
     1 X 0
     0 0 0
     */
    public static final int W_111_1X0_000 = 0b111_110_000;

    /**
     1 1 1
     0 X 1
     0 0 0
     */
    public static final int W_111_0X1_000 = 0b111_011_000;

    //----------------------------------------------------

    /*
    . . . . . . . .
    . . .     . . .
    . . X     X . .
    .             .
     */

    /**
     1 1 0
     1 X 0
     0 0 0
     */
    public static final int W_110_1X0_000 = 0b110_110_000;

    /**
     0 1 1
     0 X 1
     0 0 0
     */
    public static final int W_011_0X1_000 = 0b011_011_000;

    //----------------------------------------------------

    /*
    . . . . . . . .
    . . .     . . .
    . . X     X . .
    . .         . .
     */

    /**
     1 1 0
     1 X 0
     1 0 0
     */
    public static final int W_110_1X0_100 = 0b110_110_100;

    /**
     0 1 1
     0 X 1
     0 0 1
     */
    public static final int W_011_0X1_001 = 0b011_011_001;

    //----------------------------------------------------

    /**
     0 0 0
     1 X 0
     1 1 1
     */
    public static final int W_000_1X0_111 = 0b000_110_111;

    /**
     0 0 0
     0 X 1
     1 1 1
     */
    public static final int W_000_0X1_111 = 0b000_011_111;

    //----------------------------------------------------

    /**
     1 0 0
     1 X 0
     1 1 0
     */
    public static final int W_100_1X0_110 = 0b100_110_110;

    /**
     0 0 1
     0 X 1
     0 1 1
     */
    public static final int W_001_0X1_011 = 0b001_011_011;

    //----------------------------------------------------

    /**
     0 0 0
     0 X 1
     0 1 1
     */
    public static final int W_000_0X1_011 = 0b000_011_011;

    /**
     0 0 0
     1 X 0
     1 1 0
     */
    public static final int W_000_1X0_110 = 0b000_110_110;

    //----------------------------------------------------

    /**
     1 0 0
     1 X 0
     1 1 0
     */
    public static final int E_100_1X0_110 = 0b100_100_110;

    /**
     0 0 0
     1 X 0
     1 1 0
     */
    public static final int E_000_1X0_110 = 0b000_100_110;

    /**
     0 0 1
     0 X 1
     0 1 1
     */
    public static final int E_001_0X1_011 = 0b001_001_011;

    /**
     0 0 0
     0 X 1
     0 1 1
     */
    public static final int E_000_0X1_011 = 0b000_001_011;

    /**
     0 0 0
     0 X 0
     1 1 0
     */
    public static final int E_000_0X0_110 = 0b000_000_110;

    /**
     0 0 0
     0 X 0
     0 1 1
     */
    public static final int E_000_0X0_011 = 0b000_000_011;

    /**
     0 0 0
     0 X 0
     1 1 1
     */
    public static final int E_000_0X0_111 = 0b000_000_111;

    /**
     0 0 0
     1 X 0
     1 1 1
     */
    public static final int E_000_1X0_111 = 0b000_100_111;

    /**
     0 0 0
     0 X 1
     1 1 1
     */
    public static final int E_000_0X1_111 = 0b000_001_111;

    /**
     1 0 0
     1 X 0
     1 1 1
     */
    public static final int E_100_1X0_111 = 0b100_100_111;

    /**
     0 0 1
     0 X 1
     1 1 1
     */
    public static final int E_001_0X1_111 = 0b001_001_111;
    public static final int E_111_0X0_111 = 0b111_000_111;
    public static final int E_011_0X0_111 = 0b011_000_111;
    public static final int E_001_0X0_111 = 0b001_000_111;
    public static final int E_110_0X0_111 = 0b110_000_111;
    public static final int E_100_0X0_111 = 0b100_000_111;

    public static final int E_111_0X0_011 = 0b111_000_011;
    public static final int E_011_0X0_011 = 0b011_000_011;
    public static final int E_001_0X0_011 = 0b001_000_011;
    public static final int E_110_0X0_011 = 0b110_000_011;
    public static final int E_100_0X0_011 = 0b100_000_011;

    public static final int E_111_0X0_110 = 0b111_000_110;
    public static final int E_011_0X0_110 = 0b011_000_110;
    public static final int E_001_0X0_110 = 0b001_000_110;
    public static final int E_110_0X0_110 = 0b110_000_110;
    public static final int E_100_0X0_110 = 0b100_000_110;

    public static final int W_111_1X0_111 = 0b111_110_111;

    public static final int W_111_0X1_111 = 0b111_011_111;

    public static final int E_110_1X0_110 = 0b110_100_110;

    public static final int E_011_0X1_011 = 0b011_001_011;

    public static final int E_001_1X0_110 = 0b001_100_110;

    public static final int E_100_0X1_011 = 0b100_001_011;

    public static final int E_000_1X1_111 = 0b000_101_111;

    public static final int E_111_1X1_000 = 0b111_101_000;

    public static final int E_100_1X1_111 = 0b100_101_111;
    public static final int E_001_1X1_111 = 0b001_101_111;

    public static final int E_111_1X0_110 = 0b111_100_110;
    public static final int E_110_1X0_111 = 0b110_100_111;

    //------------------------------------------------------

    /**
     1 0 0
     1 X 0
     1 0 0
     */
    public static final int W_100_1X0_100 = 0b100_110_100;

    /**
     1 0 1
     1 X 0
     1 0 0
     */
    public static final int W_101_1X0_100 = 0b101_110_100;

    /**
     1 0 0
     1 X 0
     1 0 1
     */
    public static final int W_100_1X0_101 = 0b100_110_101;

    /**
     1 0 1
     1 X 0
     1 0 1
     */
    public static final int W_101_1X0_101 = 0b101_110_101;

    /**
     0 0 1
     0 X 1
     0 0 1
     */
    public static final int W_001_0X1_001 = 0b001_011_001;

    /**
     1 0 1
     0 X 1
     0 0 1
     */
    public static final int W_101_0X1_001 = 0b101_011_001;

    /**
     0 0 1
     0 X 1
     1 0 1
     */
    public static final int W_001_0X1_101 = 0b001_011_101;

    /**
     1 0 1
     0 X 1
     1 0 1
     */
    public static final int W_101_0X1_101 = 0b101_011_101;

    /**
     1 0 1
     0 X 0
     1 1 1
     */
    public static final int W_101_0X0_111 = 0b101_010_111;

    /**
     1 0 0
     0 X 0
     1 1 1
     */
    public static final int W_100_0X0_111 = 0b100_010_111;

    /**
     0 0 1
     0 X 0
     1 1 1
     */
    public static final int W_001_0X0_111 = 0b001_010_111;

    /**
     0 0 0
     0 X 0
     1 1 1
     */
    public static final int W_000_0X0_111 = 0b000_010_111;

    //-----------------------------------------------------

    /**
     1 1 1
     0 X 0
     1 0 0
     */
    public static final int W_111_0X0_100 = 0b111_010_100;

    /**
     1 1 1
     0 X 0
     0 0 1
     */
    public static final int W_111_0X0_001 = 0b111_010_001;

    /**
     1 1 1
     0 X 0
     1 0 1
     */
    public static final int W_111_0X0_101 = 0b111_010_101;

    /**
     1 1 1
     0 X 0
     0 0 0
     */
    public static final int W_111_0X0_000 = 0b111_010_000;
    public static final int W_011_1X0_110 = 0b011_110_110;
    public static final int W_100_0X1_011 = 0b100_011_011;

    public static final int W_000_1X0_100 = 0b000_110_100;

    public static final int W_000_0X1_001 = 0b000_011_001;

    public static final int W_110_0X0_000 = 0b110_010_000;
    public static final int W_011_0X0_000 = 0b011_010_000;

    public static final int W_100_1X0_000 = 0b100_110_000;
    public static final int W_001_0X1_000 = 0b001_011_000;

    public static final int W_110_0X0_011 = 0b110_010_011;
    public static final int W_011_0X0_110 = 0b011_010_110;

    public static final int W_100_1X1_001 = 0b100_111_001;
    public static final int W_001_1X1_100 = 0b001_111_100;

    public static final int W_110_0X1_011 = 0b110_011_011;
    public static final int W_001_1X0_110 = 0b001_110_110;

    public static final int W_001_1X1_110 = 0b001_111_110;
    public static final int W_100_1X1_011 = 0b100_111_011;

    public static final int W_000_0X0_000 = 0b000_010_000;
    public static final int W_100_0X0_000 = 0b100_010_000;
    public static final int W_001_0X0_000 = 0b001_010_000;
    public static final int W_000_0X0_100 = 0b000_010_100;
    public static final int W_000_0X0_001 = 0b000_010_001;

    public static final int W_100_0X0_001 = 0b100_010_001;
    public static final int W_001_0X0_100 = 0b001_010_100;

    public static final int W_011_0X1_110 = 0b011_011_110;
    public static final int W_110_1X0_011 = 0b110_110_011;

    public static final int W_000_0X0_110 = 0b000_010_110;
    public static final int W_000_0X0_011 = 0b000_010_011;

    public static final int W_011_0X1_100 = 0b011_011_100;
    public static final int W_110_1X0_001 = 0b110_110_001;

    public static final int W_011_1X0_100 = 0b011_110_100;

    public static final int W_100_1X1_100 = 0b100_111_100;
    public static final int W_001_1X1_001 = 0b001_111_001;

    public static final int W_000_1X1_001 = 0b000_111_001;
    public static final int W_001_1X1_000 = 0b001_111_000;

    public static final int W_100_1X1_000 = 0b100_111_000;
    public static final int W_000_1X1_100 = 0b000_111_100;

    public static final int W_110_0X1_001 = 0b110_011_001;

    public static final int W_010_0X0_111 = 0b010_010_111;
    public static final int W_010_0X0_110 = 0b010_010_110;
    public static final int W_010_0X0_011 = 0b010_010_011;
    public static final int W_111_0X0_010 = 0b111_010_010;
    public static final int W_011_0X0_010 = 0b011_010_010;
    public static final int W_110_0X0_010 = 0b110_010_010;

    public static final int W_100_0X0_010 = 0b100_010_010;
    public static final int W_001_0X0_101 = 0b001_010_010;
    public static final int W_100_0X0_011 = 0b100_010_011;
    public static final int W_001_0X0_110 = 0b001_010_110;

    public static final int W_001_0X0_011 = 0b001_010_011;
    public static final int W_100_0X0_110 = 0b100_010_110;

    public static final int W_000_0X0_010 = 0b000_010_010;
    public static final int W_010_0X0_000 = 0b010_010_000;
}
