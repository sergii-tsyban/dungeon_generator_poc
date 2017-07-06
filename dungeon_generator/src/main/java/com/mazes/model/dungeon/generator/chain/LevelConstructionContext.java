package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.dungeon.Dungeon;
import com.mazes.model.dungeon.generator.dungeon.Level;
import com.mazes.model.dungeon.generator.dungeon.SubDungeon;

import java.util.Collection;
import java.util.Map;

public class LevelConstructionContext {

    public int levelWidth;
    public int levelHeight;

    public boolean adjustTopology = true;

    public Map<Integer,SubDungeon> subDungeons;

    public int rootSubDungeonId;

    public Dungeon dungeon;

    public Level level;

    public int[][] topology;
    public int[][][] allocatedIds;
    public int[][][] allocatedShadowIds;
}
