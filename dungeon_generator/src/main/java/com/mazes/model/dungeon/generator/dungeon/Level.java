package com.mazes.model.dungeon.generator.dungeon;

public class Level {

    public static final int CELL_OPEN = 0;
    public static final int CELL_CLOSED = 1;

    private Dungeon dungeon;
    private int[][] topology;
    private int[][][] allocatedIds;
    private int[][] openTiles;

    public Level(Dungeon dungeon, int[][] topology, int[][][] allocatedIds) {
        this.dungeon = dungeon;
        this.topology = topology;
        this.allocatedIds = allocatedIds;
        this.openTiles = new int[topology.length][topology[0].length];
        closeAllOpenTiles();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public int[][] getTopology() {
        return topology;
    }

    public int[][][] getAllocatedIds() {
        return allocatedIds;
    }

    public int[][] getOpenTiles() {
        return openTiles;
    }

    public void makeRegionVisible(int startI, int startJ, int endI, int endJ){
        startI = Math.max(startI, 0);
        startJ = Math.max(startJ, 0);
        endI = Math.min(endI, openTiles.length);
        endJ = Math.min(endJ, openTiles[0].length);
        if((startI < endI) && (startJ < endJ)){
            for (int i = startI; i < endI; i++) {
                for (int j = startJ; j < endJ ; j++) {
                    openTiles[i][j] = CELL_OPEN;
                }
            }
        }
    }

    public void closeAllOpenTiles(){
        for (int i = 0; i < openTiles.length; i++) {
            for (int j = 0; j < openTiles[i].length ; j++) {
                openTiles[i][j] = CELL_CLOSED;
            }
        }
    }

}
