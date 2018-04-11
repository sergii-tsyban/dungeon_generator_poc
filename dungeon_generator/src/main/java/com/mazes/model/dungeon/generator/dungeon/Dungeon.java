package com.mazes.model.dungeon.generator.dungeon;

import com.mazes.model.dungeon.generator.chain.LevelConstructionContext;

import java.util.*;

public class Dungeon {

    private static final Comparator<DRoom> yComparator = new Comparator<DRoom>() {
        @Override
        public int compare(DRoom r1, DRoom r2) {
            return r1.getCenterY() - r2.getCenterY();
        }
    };

    private static final Comparator<DRoom> xComparator = new Comparator<DRoom>() {
        @Override
        public int compare(DRoom r1, DRoom r2) {
            return r1.getCenterX() - r2.getCenterX();
        }
    };

    private int width;
    private int height;
    private Map<Integer, DRoom> roomsIndex;
    private List<DRoom> leafs;
    private Map<Integer, DCorridor> corridorsIndex;
    private int[][] topology;
    private int[][][] allocatedIds;
    private int[][][] allocatedShadowIds;
    private DRoom startRoom;
    private DRoom endRoom;

    public Dungeon(LevelConstructionContext context) {
        this.width = context.levelWidth;
        this.height = context.levelHeight;
        this.roomsIndex = createRoomIndex(context.rooms);
        this.leafs = separateLeafs(context.rooms);
        this.corridorsIndex = createCorridorIndex(context.corridors);
        this.topology = context.topology;
        this.allocatedIds = context.allocatedIds;
        this.allocatedShadowIds = context.allocatedShadowIds;
        assignRooms(this.leafs);
    }

    private List<DRoom> separateLeafs(List<DRoom> rooms) {
        List<DRoom> leafs = new ArrayList<>();
        for (DRoom room : rooms) {
            if(room.isLeaf()){
                leafs.add(room);
            }
        }
        return leafs;
    }

    private void assignRooms(List<DRoom> rooms) {
        if(rooms.size() == 1){
            startRoom = rooms.get(0);
            endRoom = rooms.get(0);
            return;
        }
        Collections.sort(rooms, xComparator);
        DRoom r1x = rooms.get(0);
        DRoom r2x = rooms.get(rooms.size() - 1);
        Collections.sort(rooms, yComparator);
        DRoom r1y = rooms.get(0);
        DRoom r2y = rooms.get(rooms.size() - 1);
        float distance1 = DungeonGenerator.calculateDistanceBetween(r1x, r2x);
        float distance2 = DungeonGenerator.calculateDistanceBetween(r1y, r2y);
        if(distance1 > distance2){
            startRoom = r1x;
            endRoom = r2x;
        } else {
            startRoom = r1y;
            endRoom = r2y;
        }

    }

    private Map<Integer, DCorridor> createCorridorIndex(List<DCorridor> corridors) {
        Map<Integer, DCorridor> index = new HashMap<>(corridors.size());
        for (DCorridor corridor : corridors) {
            index.put(corridor.hashCode(), corridor);
        }
        return index;
    }

    private Map<Integer, DRoom> createRoomIndex(List<DRoom> rooms) {
        Map<Integer, DRoom> index = new HashMap<>(rooms.size());
        for (DRoom room : rooms) {
            index.put(room.id(), room);
        }
        return index;
    }

    public DRoom getStartRoom() {
        return startRoom;
    }

    public DRoom getEndRoom() {
        return endRoom;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<Integer, DRoom> getRoomsIndex() {
        return roomsIndex;
    }

    public Map<Integer, DCorridor> getCorridorsIndex() {
        return corridorsIndex;
    }

    public int[][] getTopology() {
        return topology;
    }

    public int[][][] getAllocatedIds() {
        return allocatedIds;
    }

    public int[][][] getAllocatedShadowIds() {
        return allocatedShadowIds;
    }
}
