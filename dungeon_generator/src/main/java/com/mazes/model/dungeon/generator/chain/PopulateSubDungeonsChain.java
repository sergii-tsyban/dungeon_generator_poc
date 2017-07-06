package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.dungeon.DungeonGenerator;
import com.mazes.model.dungeon.generator.dungeon.DRoom;
import com.mazes.model.dungeon.generator.dungeon.SubDungeon;

import java.util.Collection;
import java.util.Map;

public class PopulateSubDungeonsChain implements LevelConstructionChain {

    private LevelConstructionChain nextChain;

    public PopulateSubDungeonsChain(LevelConstructionChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void process(LevelConstructionContext context) {
        DungeonGenerator dungeonGenerator = new DungeonGenerator();
        Map<Integer, SubDungeon> subDungeons = context.subDungeons;
        for (SubDungeon sd : subDungeons.values()) {
            dungeonGenerator.populateDungeon(sd);
        }
        for (SubDungeon sd : subDungeons.values()) {
            if(sd.hasNextSubDungeon()){
                for (SubDungeon nextSd : sd.getNextSubDungeons().values()) {
                    connectSubDungeons(sd, nextSd);
                }
            }
            dungeonGenerator.updateTopology(context.topology, sd);
        }
        nextChain.process(context);
    }

    private void connectSubDungeons(SubDungeon fromDungeon, SubDungeon toDungeon){
        DRoom from = null;
        DRoom to = null;
        float distance = Float.MAX_VALUE;
        for (DRoom currentRoom: fromDungeon.getRooms()) {
            for(DRoom otherRoom: toDungeon.getRooms()){
                float d = DungeonGenerator.calculateDistanceBetween(currentRoom, otherRoom);
                if(d < distance){
                    distance = d;
                    from = currentRoom;
                    to = otherRoom;
                }
            }
        }
        fromDungeon.addCorridorToNextSubDungeon(toDungeon, DungeonGenerator.connect(from, to));
    }

}
