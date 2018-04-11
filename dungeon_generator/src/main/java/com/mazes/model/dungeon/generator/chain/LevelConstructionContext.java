package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.dungeon.*;

import java.util.ArrayList;
import java.util.List;

public class LevelConstructionContext {

    public int levelWidth;
    public int levelHeight;

    public List<DRoom> rooms = new ArrayList<>();
    public List<DCorridor> corridors = new ArrayList<>();

    public int[][] topology;
    public int[][][] allocatedIds;
    public int[][][] allocatedShadowIds;

}
