package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.allocator.TerrainTileAllocator;

public class TerrainTileAllocatorChain implements LevelConstructionChain{

    private LevelConstructionChain nextChain;
    private TerrainTileAllocator terrainTileAllocator;

    public TerrainTileAllocatorChain(LevelConstructionChain nextChain, TerrainTileAllocator terrainTileAllocator) {
        this.nextChain = nextChain;
        this.terrainTileAllocator = terrainTileAllocator;
    }

    @Override
    public void process(LevelConstructionContext context) {
        context.allocatedIds = terrainTileAllocator.allocateIds(context.topology);
        context.allocatedShadowIds = terrainTileAllocator.allocateShadowIds(context.topology);
        nextChain.process(context);
    }
}
