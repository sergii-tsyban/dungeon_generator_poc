package com.mazes.model.dungeon.layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileLayersHolder {

    private Map<String,Layer> layers;
    private Map<Integer, List<String>> tileToLayer;

    public TileLayersHolder() {
        layers = new HashMap<String, Layer>();
        tileToLayer = new HashMap<Integer, List<String>>();
    }

    public void addLayer(String name, int priority, int[] tileIds){
        layers.put(name, new Layer(name, priority, tileIds));
        for (int tileId : tileIds) {
            List<String> layer = tileToLayer.get(tileId);
            if(layer == null){
                layer = new ArrayList<String>();
                tileToLayer.put(tileId, layer);
            }
        }
    }

    private class Layer{

        private String name;
        private int priority;
        private int[] tilesIds;

        public Layer(String name, int priority, int[] tilesIds) {
            this.name = name;
            this.priority = priority;
            this.tilesIds = tilesIds;
        }

        public String getName() {
            return name;
        }

        public int getPriority() {
            return priority;
        }

        public int[] getTilesIds() {
            return tilesIds;
        }
    }

}
