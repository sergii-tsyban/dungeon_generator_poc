package com.mazes.model.dungeon.allocator.layers;

import com.mazes.model.dungeon.allocator.TerrainTileType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TerrainLayerManager {

    private Map<TerrainLayer, List<TerrainTileType>> tileLayerToTileType = new HashMap<>();

    private Map<TerrainTileType, TerrainLayer> tileTypeToTileLayer = new HashMap<>();

    private TerrainLayer defaultLayer = TerrainLayer.CEIL;

    private static TerrainLayerManager instance = new TerrainLayerManager();

    private TerrainLayerManager(){
        addLayer(TerrainLayer.FLOOR,
                TerrainTileType.FLOOR,
                TerrainTileType.WALL_CORNER_TOP_LEFT,
                TerrainTileType.WALL_CORNER_TOP_RIGHT,
                TerrainTileType.WALL_FRONT_BOTTOM);
    }

    public void addLayer(TerrainLayer layer, TerrainTileType ... tiles){
        tileLayerToTileType.put(layer, Arrays.asList(tiles));
        for (TerrainTileType tile : tiles) {
            tileTypeToTileLayer.put(tile, layer);
        }
    }

    public boolean contains(TerrainLayer layer, TerrainTileType tileType){
        return tileLayerToTileType.get(layer).contains(tileType);
    }

    public TerrainLayer getLayer(TerrainTileType tileType){
        if(tileTypeToTileLayer.containsKey(tileType)){
            tileTypeToTileLayer.get(tileType);
        }
        return defaultLayer;
    }

    public static TerrainLayerManager getInstance() {
        return instance;
    }
}
