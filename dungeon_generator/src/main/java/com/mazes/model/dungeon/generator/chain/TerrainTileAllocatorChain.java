package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.allocator.TerrainTileAllocator;

public class TerrainTileAllocatorChain implements LevelConstructionChain{

    private TerrainTileAllocator terrainTileAllocator;

    public TerrainTileAllocatorChain() {
        this.terrainTileAllocator = new TerrainTileAllocator();
    }

    @Override
    public void process(LevelConstructionContext context) {
        context.allocatedIds = terrainTileAllocator.allocateIds(context.topology);
        context.allocatedShadowIds = terrainTileAllocator.allocateShadowIds(context.topology);
    }
}
