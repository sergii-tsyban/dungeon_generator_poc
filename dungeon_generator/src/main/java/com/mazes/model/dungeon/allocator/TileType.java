package com.mazes.model.dungeon.allocator;

/**
 * Created by serg on 21/11/2016.
 */
public enum TileType {

    NO_TILE(-1),
    WALL_SOLID(1),
    FLOOR(0),
    SIDE_RIGHT(11),
    SIDE_LEFT(12),
    WALL_FRONT_BOTTOM(13),
    WALL_FRONT_TOP(15),
    SIDE_BOTTOM(14),
    WALL_TOP_WALL_SIDE_LEFT(16),
    WALL_TOP_WALL_SIDE_RIGHT(17),
    WALL_TOP_SIDE_RIGHT(10),
    WALL_TOP_SIDE_LEFT(20),
    WALL_SIDE_LEFT(18),
    WALL_SIDE_RIGHT(19),
    SIDE_CONNECTOR_TOP_TO_LEFT(21),
    SIDE_CONNECTOR_TOP_TO_RIGHT(22),
    SIDE_CONNECTOR_BOTTOM_TO_RIGHT(23),
    SIDE_CONNECTOR_BOTTOM_TO_LEFT(24),
    WALL_CONNECTOR_BOTTOM_TO_LEFT(25),
    WALL_CONNECTOR_BOTTOM_TO_RIGHT(26),
    SIDE_LEFT_WITH_WALL_CONNECTOR(27),
    SIDE_RIGHT_WITH_WALL_CONNECTOR(28),
    SIDE_CONNECTOR_TR_WITH_WALL_CONN(29),
    SIDE_CONNECTOR_TL_WITH_WALL_CONN(30),
    WALL_CORNER_TOP_RIGHT(31),
    WALL_CORNER_TOP_LEFT(32),
    CORNER_BR(33),
    CORNER_BL(34),
    SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT(35),
    SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT(36);

    private int id;

    TileType(int id) {
        this.id  = id;
    }

    public int getId() {
        return id;
    }

    public static TileType[] arr(TileType... tiles){
        return tiles;
    }

    public static TileType fromId(int id){
        for(TileType type: TileType.values()){
            if(type.getId() == id)
                return type;
        }
        throw new IllegalArgumentException();
    }
}
