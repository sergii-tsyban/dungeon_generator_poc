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
                TilesIds.CORNER_TL,
                TilesIds.CORNER_TR,
                TilesIds.WALL_T);

        addLayer(TerrainLayersNames.WALLS_ON_TOP,
                TilesIds.CORNER_BL,
                TilesIds.CORNER_BR,
                TilesIds.WALL_B,
                TilesIds.WALL_L,
                TilesIds.WALL_R);
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
