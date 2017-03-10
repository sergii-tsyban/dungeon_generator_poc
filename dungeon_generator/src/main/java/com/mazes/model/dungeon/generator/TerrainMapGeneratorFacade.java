package com.mazes.model.dungeon.generator;

import com.mazes.model.dungeon.allocator.TerrainTileAllocator;
import com.mazes.model.dungeon.topology.TopologyManager;

public class TerrainMapGeneratorFacade {

    private TopologyManager topologyManager;
    private TerrainTileAllocator tileAllocator;
    private RoomsGenerator dungeonGenerator;

    public TerrainMapGeneratorFacade(int width, int height){
        this.dungeonGenerator = new RoomsGenerator(width, height);
        this.tileAllocator = new TerrainTileAllocator();
        this.topologyManager = new TopologyManager();
    }

    public int[][][] generateCave(){
        int[][] dungeon = dungeonGenerator.generate();
        topologyManager.adjustTopology(dungeon);
        return tileAllocator.allocateIds(dungeon);
    }

}
