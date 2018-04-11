package com.mazes.model.dungeon.generator;

import com.mazes.model.dungeon.generator.chain.*;
import com.mazes.model.dungeon.generator.dungeon.Dungeon;
import com.mazes.model.dungeon.topology.TopologyManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonConstructionFlow {

    public static final int DUNGEON_DEFAULT_WIDTH = 70;
    public static final int DUNGEON_DEFAULT_HEIGHT = 70;

    public static final int CAVE_DEFAULT_WIDTH = 40;
    public static final int CAVE_DEFAULT_HEIGHT = 40;

    public enum Type{
        DUNGEON,
        CAVE,
    }

    private Map<Type, List<LevelConstructionChain>> constructionChains = new HashMap<>();

    public DungeonConstructionFlow(){
        TopologyManager topologyManager = new TopologyManager();
        LevelConstructionChain createDungeonTopology = new CreateDungeonTopology(topologyManager);
        LevelConstructionChain createCave = new CreateCaveTopology(topologyManager);
        LevelConstructionChain randomRooms = new CreateRandomRoomsAndCorridors();
        LevelConstructionChain allocateTileIds = new TerrainTileAllocatorChain();

        List<LevelConstructionChain> cellularDungeon = new ArrayList<>();
        cellularDungeon.add(createCave);
        cellularDungeon.add(allocateTileIds);
        constructionChains.put(Type.CAVE, cellularDungeon);

        List<LevelConstructionChain> randomRoomsDungeon = new ArrayList<>();
        randomRoomsDungeon.add(randomRooms);
        randomRoomsDungeon.add(createDungeonTopology);
        randomRoomsDungeon.add(allocateTileIds);
        constructionChains.put(Type.DUNGEON, randomRoomsDungeon);
    }

    public Dungeon constructDefaultCave(){
        return construct(CAVE_DEFAULT_WIDTH, CAVE_DEFAULT_HEIGHT, Type.CAVE);
    }

    public Dungeon constructDefaultDungeon(){
        return construct(DUNGEON_DEFAULT_WIDTH, DUNGEON_DEFAULT_HEIGHT, Type.DUNGEON);
    }

    private Dungeon construct(int width, int height, Type type){
        LevelConstructionContext constructionContext = new LevelConstructionContext();
        constructionContext.levelWidth = width;
        constructionContext.levelHeight = height;
        for (LevelConstructionChain chain : constructionChains.get(type)) {
            chain.process(constructionContext);
        }
        return new Dungeon(constructionContext);
    }

}
