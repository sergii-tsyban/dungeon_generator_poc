package com.mazes.model.dungeon.layers;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerrainTileLayers {

    private static Map<String, List<TerrainTileType>> tileLayer = new HashMap<>();

    static {
        addLayer(TerrainLayer.FLOOR.name(),
                TerrainTileType.FLOOR,
                TerrainTileType.WALL_CORNER_TOP_LEFT,
                TerrainTileType.WALL_CORNER_TOP_RIGHT,
                TerrainTileType.WALL_FRONT_BOTTOM);

        addLayer(TerrainLayer.CEIL.name(),
                TerrainTileType.CORNER_BL,
                TerrainTileType.CORNER_BR,
                TerrainTileType.SIDE_BOTTOM,
                TerrainTileType.SIDE_LEFT,
                TerrainTileType.SIDE_RIGHT);
    }

    public static void addLayer(String name, TerrainTileType ... tiles){
        tileLayer.put(name, Arrays.asList(tiles));
    }

    public static List<TerrainTileType> getTileIdsForLayer(String name){
        return tileLayer.get(name);
    }

    public static boolean contains(String name, int tileId){
        return tileLayer.get(name).contains(tileId);
    }

}
