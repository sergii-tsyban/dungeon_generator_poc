package com.mazes.model.dungeon.allocator;

/**
 * Created by serg on 06/11/2016.
 */
public class TilesIds {

    public static final int NO_TILE = -1;

    public static final int WALL_SOLID = 1;
    public static final int FLOOR = 0;

    public static final int SIDE_RIGHT = 11;
    public static final int SIDE_LEFT = 12;
    public static final int WALL_FRONT_BOTTOM = 13;
    public static final int WALL_FRONT_TOP = 15;
    public static final int SIDE_BOTTOM = 14;
    public static final int WALL_TOP_WALL_SIDE_LEFT = 16;
    public static final int WALL_TOP_WALL_SIDE_RIGHT = 17;
    public static final int WALL_TOP_SIDE_RIGHT = 10;
    public static final int WALL_TOP_SIDE_LEFT = 20;
    public static final int WALL_SIDE_LEFT = 18;
    public static final int WALL_SIDE_RIGHT = 19;

    public static final int SIDE_CONNECTOR_TOP_TO_LEFT = 21;
    public static final int SIDE_CONNECTOR_TOP_TO_RIGHT = 22;
    public static final int SIDE_CONNECTOR_BOTTOM_TO_RIGHT = 23;
    public static final int SIDE_CONNECTOR_BOTTOM_TO_LEFT = 24;
    public static final int WALL_CONNECTOR_BOTTOM_TO_LEFT = 25;
    public static final int WALL_CONNECTOR_BOTTOM_TO_RIGHT = 26;

    public static final int SIDE_LEFT_WITH_WALL_CONNECTOR = 27;
    public static final int SIDE_RIGHT_WITH_WALL_CONNECTOR = 28;
    public static final int SIDE_CONNECTOR_TR_WITH_WALL_CONN = 29;
    public static final int SIDE_CONNECTOR_TL_WITH_WALL_CONN = 30;

    public static final int WALL_CORNER_TOP_RIGHT = 31;
    public static final int WALL_CORNER_TOP_LEFT = 32;
    public static final int CORNER_BR = 33;
    public static final int CORNER_BL = 34;

    public static final int SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT = 35;
    public static final int SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT = 36;
}
