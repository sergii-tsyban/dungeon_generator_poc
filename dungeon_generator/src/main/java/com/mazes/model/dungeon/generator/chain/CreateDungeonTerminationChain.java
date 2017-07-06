package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.dungeon.Dungeon;
import com.mazes.model.dungeon.generator.dungeon.Level;

public class CreateDungeonTerminationChain implements LevelConstructionChain {

    @Override
    public void process(LevelConstructionContext context) {
        context.dungeon = new Dungeon(context.subDungeons, context.rootSubDungeonId);
        context.level = new Level(context.dungeon, context.topology, context.allocatedIds);
    }

}
