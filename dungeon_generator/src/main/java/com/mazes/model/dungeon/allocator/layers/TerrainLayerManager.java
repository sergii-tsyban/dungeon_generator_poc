package com.mazes.model.dungeon.allocator.layers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.*;

public final class TerrainLayerManager {

    private Map<TerrainLayer, List<Integer>> tileLayerToTileType = new HashMap<>();

    private Map<Integer, TerrainLayer> tileTypeToTileLayer = new HashMap<>();

    private TerrainLayer defaultLayer = TerrainLayer.PERSPECTIVE;

    private static TerrainLayerManager instance = new TerrainLayerManager();

    private TerrainLayerManager(){
        addLayer(TerrainLayer.FLOOR, FLOOR);
        addLayer(TerrainLayer.WALLS,
                WALL_FRONT_BOTTOM,
                WALL_CORNER_TOP_LEFT,
                WALL_CORNER_TOP_RIGHT,
                SIDE_CONNECTOR_TL_WITH_WALL_CONN,
                SIDE_CONNECTOR_TR_WITH_WALL_CONN,
                SIDE_LEFT_WITH_WALL_CONNECTOR,
                SIDE_RIGHT_WITH_WALL_CONNECTOR,
                WALL_FRONT_TOP,
                SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT,
                SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT);
    }

    public void addLayer(TerrainLayer layer, Integer ... tiles){
        tileLayerToTileType.put(layer, Arrays.asList(tiles));
        for (int tile : tiles) {
            tileTypeToTileLayer.put(tile, layer);
        }
    }

    public boolean contains(TerrainLayer layer, int tileType){
        return tileLayerToTileType.get(layer).contains(tileType);
    }

    public TerrainLayer getLayer(int tileType){
        if(tileTypeToTileLayer.containsKey(tileType)){
            return tileTypeToTileLayer.get(tileType);
        }
        return defaultLayer;
    }

    public static TerrainLayerManager getInstance() {
        return instance;
    }
}
