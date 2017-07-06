package com.mazes.model.dungeon.generator.dungeon;

import java.util.Map;

public class Dungeon {

    private Map<Integer, SubDungeon> idToSubDungeon;

    private int rootSubDungeonId;

    public Dungeon(Map<Integer, SubDungeon> idToSubDungeon, int rootSubDungeonId) {
        this.idToSubDungeon = idToSubDungeon;
        this.rootSubDungeonId = rootSubDungeonId;
    }

    public Map<Integer, SubDungeon> getIdToSubDungeon() {
        return idToSubDungeon;
    }

    public int getRootSubDungeonId() {
        return rootSubDungeonId;
    }

}
