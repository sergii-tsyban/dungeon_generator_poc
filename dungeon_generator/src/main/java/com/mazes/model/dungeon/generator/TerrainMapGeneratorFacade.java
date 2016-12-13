package com.mazes.model.dungeon.generator;

import com.mazes.model.dungeon.allocator.TerrainTileAllocator;
import com.mazes.model.dungeon.topology.TopologyManager;

public class TerrainMapGeneratorFacade {

    private CellularAutomatonCaveGenerator cacg;
    private TopologyManager topologyManager;
    private TerrainTileAllocator tileAllocator;

    public TerrainMapGeneratorFacade(int width, int height){
        this.cacg = new CellularAutomatonCaveGenerator(width, height);
        //TODO : temporal solution
        this.cacg.addRoom(0,0,width,height);
        this.tileAllocator = new TerrainTileAllocator();
        this.topologyManager = new TopologyManager();
    }

    public int[][][] generateTerrainMap(){
        int[][] cave = cacg.getCave();
        topologyManager.adjustTopology(cave);
        return tileAllocator.allocateIds(cave);
    }

}
