package com.mazes.model.dungeon.layers;

import com.mazes.model.dungeon.common.TilesIds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by serg on 07/11/2016.
 */
public class TileLayers {

    private static Map<String, List<Integer>> tileLayer = new HashMap<String, List<Integer>>();

    static {
        addLayer(TerrainLayersNames.FLOOR_LAYER,
                TilesIds.FLOOR,
                TilesIds.WALL_CORNER_TL,
                TilesIds.WALL_CORNER_TR,
                TilesIds.WALL_FRONT_BOTTOM);

        addLayer(TerrainLayersNames.WALLS_ON_TOP,
                TilesIds.CORNER_BL,
                TilesIds.CORNER_BR,
                TilesIds.SIDE_BOTTOM,
                TilesIds.SIDE_LEFT,
                TilesIds.SIDE_RIGHT);
    }

    public static void addLayer(String name, Integer ... tileIds){
        tileLayer.put(name, Arrays.asList(tileIds));
    }

    public static List<Integer> getTileIdsForLayer(String name){
        return tileLayer.get(name);
    }

    public static boolean contains(String name, int tileId){
        return tileLayer.get(name).contains(tileId);
    }

}
