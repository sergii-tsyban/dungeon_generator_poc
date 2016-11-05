package com.mazes.model.dungeon.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CellularAutomatonCaveGeneration {

    public static final int GRID_STEP = 4;

    public static final int MARK = 5;
    public static final int WALL = 1;
    public static final int FLOOR = 0;

    private int minimumOpenPercentage = 35;
    private int initWallBirthProb = 51;
    private int birthLimit = 4;
    private int deathLimit = 4;
    private int[][] cave;

    private List<Room> rooms;

    public CellularAutomatonCaveGeneration(int width, int height) {
        this.cave = new int[height][width];
        this.rooms = new ArrayList<>();
    }

    public void addRoom(int i, int j, int w, int h){
        rooms.add(new Room(i, j ,w, h));
    }

    public void clear(){
        rooms.clear();
    }

    public void generateRooms(){
        rooms.stream().forEach(r -> {
            r.generateCave();
            int[][] room = r.getRoom();
            for (int i = 0; i < room.length; i++) {
                for (int j = 0; j < room[0].length; j++) {
                    cave[r.getI() + i][r.getJ() + j] = room[i][j];
                }
            }
        });
    }

    private class Room {

        private int i;
        private int j;
        private int[][] room;
        private int gridVStep;
        private int gridHStep;

        public Room(int i, int j, int w, int h){
            this.i = i;
            this.j = j;
            this.room = new int[h][w];
            this.gridVStep = room.length / GRID_STEP;
            this.gridHStep = room[0].length / GRID_STEP;
        }

        public int generateCave(){
            int skippedCavesCount = 0;
            boolean sizeReached = false;
            while(!sizeReached && skippedCavesCount < 100){
                initRoomState();
                placeGrid();
                makeGenerationSteps();
                sizeReached = minimumOpenPercentage <= openPercentage();
                if(!sizeReached){
                    skippedCavesCount++;
                }
            }
            return skippedCavesCount;
        }

        private void initRoomState(){
            Random r = new Random();
            for (int i = 0; i < room.length; i++) {
                for (int j = 0; j < room[i].length; j++) {
                    if(i == 0 || j == 0 || i == room.length - 1 || j == room[i].length - 1){
                        room[i][j] = 1;
                    } else {
                        room[i][j] = r.nextInt(100) <= initWallBirthProb ? WALL : FLOOR;
                    }
                }
            }
        }

        private void placeGrid(){
            for (int i = 1; i < room.length - 1; i++) {
                room[i][gridHStep + 1] = 0;
                room[i][gridHStep * (GRID_STEP - 1) + 1] = 0;
            }
            for (int i = 1; i < room[0].length - 1; i++) {
                room[gridVStep + 1][i] = 0;
                room[gridVStep * (GRID_STEP - 1) + 1][i] = 0;
            }
        }

        private int makeGenerationSteps(){
            int generationsCount = 0;
            boolean stateChanged = true;
            int maxGenerationsSteps = 30;
            while (stateChanged && generationsCount < maxGenerationsSteps) {
                int[][] old = room;
                room = produceGeneration(old);
                stateChanged = !Arrays.deepEquals(old, room);
                generationsCount++;
            }
            return generationsCount;
        }

        private double openPercentage() {
            int[][] newRoom = new int[room.length][room[0].length];
            System.arraycopy(room, 0, newRoom, 0, room.length);
            fill(gridVStep + 1, gridHStep*3 + 1, newRoom);
            int openCellsCount = 0;
            int cellCount = room.length * room[0].length;
            for (int i = 0; i < room.length; i++) {
                for (int j = 0; j < room[i].length; j++) {
                    if(newRoom[i][j] == MARK){
                        openCellsCount++;
                        room[i][j] = FLOOR;
                    } else {
                        room[i][j] = WALL;
                    }
                }
            }
            return openCellsCount * 100 / cellCount;
        }

        private void fill(int i, int j, int[][] arr){
            if(arr[i][j] == FLOOR){
                arr[i][j] = MARK;
                fill(i - 1, j, arr);
                fill(i, j - 1, arr);
                fill(i, j + 1, arr);
                fill(i + 1, j, arr);
            }
        }

        private int[][] produceGeneration(int[][] oldRoom){
            int[][] newRoom = new int[oldRoom.length][oldRoom[0].length];
            for (int y = 0; y < oldRoom.length; y++) {
                for (int x = 0; x < oldRoom[y].length; x++) {
                    int aliveNbrs = countAlive(oldRoom, y, x);

                    boolean isWall = oldRoom[y][x] == WALL;
                    if(isWall){
                        if(aliveNbrs < deathLimit){
                            newRoom[y][x] = FLOOR;
                        }
                        else {
                            newRoom[y][x] = WALL;
                        }
                    } else {
                        if(aliveNbrs > birthLimit){
                            newRoom[y][x] = WALL;
                        } else {
                            newRoom[y][x] = FLOOR;
                        }
                    }
                }
            }
            return newRoom;
        }

        private int countAlive(int[][] oldRoom, int y, int x) {
            int count = 0;
            for(int i = -1; i<2; i++){
                for(int j=-1; j<2; j++){
                    int neighbour_x = x + i;
                    int neighbour_y = y + j;
                    if(i == 0 && j == 0){
                        continue;
                    }
                    //In case the index we're looking at it off the edge of the map
                    if(neighbour_x < 0 || neighbour_y < 0 || neighbour_y >= oldRoom.length || neighbour_x >= oldRoom[0].length){
                        count++;
                    }
                    //Otherwise, a normal check of the neighbour
                    else if(oldRoom[neighbour_y][neighbour_x] == WALL){
                        count++;
                    }
                }
            }
            return count;
        }

        public int[][] getRoom() {
            return room;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static void main(String[] args) {
        int[][] dest = {{1,2,3},{1,2,3},{1,2,3}};
        for (int[] ints : dest) {
            System.out.println(Arrays.toString(ints));
        }
        int[][] src = {{0,0},{0,0}};
        for (int[] ints : dest) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] getCave() {
        return cave;
    }

    public void setInitWallBirthProb(int initWallBirthProb) {
        this.initWallBirthProb = initWallBirthProb;
    }

    public void setBirthLimit(int birthLimit) {
        this.birthLimit = birthLimit;
    }

    public void setDeathLimit(int deathLimit) {
        this.deathLimit = deathLimit;
    }

}
