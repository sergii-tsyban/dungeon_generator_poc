package com.mazes.model.dungeon.generator;

import com.mazes.model.dungeon.allocator.TerrainTileAllocator;
import com.mazes.model.dungeon.generator.chain.*;
import com.mazes.model.dungeon.generator.dungeon.Dungeon;
import com.mazes.model.dungeon.generator.dungeon.Level;
import com.mazes.model.dungeon.topology.TopologyManager;

public class DungeonConstructionFlow {

    private LevelConstructionChain startChain;

    private TopologyManager topologyManager = new TopologyManager();
    private TerrainTileAllocator tileAllocator = new TerrainTileAllocator();

    public DungeonConstructionFlow(){
        CreateDungeonTerminationChain terminationChain = new CreateDungeonTerminationChain();
        TerrainTileAllocatorChain terrainTileAllocatorChain = new TerrainTileAllocatorChain(terminationChain, tileAllocator);
        AdjustTopologyChain adjustTopologyChain = new AdjustTopologyChain(terrainTileAllocatorChain, topologyManager);
        PopulateSubDungeonsChain populateSubDungeonsChainChain = new PopulateSubDungeonsChain(adjustTopologyChain);
        DivideIntoSubDungeonsChain divideIntoSubDungeonsChainChain = new DivideIntoSubDungeonsChain(populateSubDungeonsChainChain);
        startChain = new CreateEmptyTopologyChain(divideIntoSubDungeonsChainChain);
    }

    public Level constructNew(LevelConstructionContext constructionContext){
        startChain.process(constructionContext);
        return constructionContext.level;
    }

}
