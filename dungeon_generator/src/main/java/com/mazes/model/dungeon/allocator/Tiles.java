package com.mazes.model.dungeon.allocator;

/**
 * Created by serg on 21/11/2016.
 */
public enum Tiles {

    NO_TILE("no_tile", -1),
    FLOOR("floor", 1),
    CONNECTOR_BOTTOM_LEFT("connector_bottom_left", 1),
    CONNECTOR_BOTTOM_RIGHT("connector_bottom_right", 1);
//    CONNECTOR_OUTSIDE_BOTTOM_RIGHT("connector_outside_bottom_right", 1);
//    CONNECTOR_OUTSIDE_TOP_RIGHT("connector_outside_top_right", 1);
//    CONNECTOR_OUTSIDE_TOP_LEFT("connector_bottom_right", 1);

    Tiles(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private int id;

}
