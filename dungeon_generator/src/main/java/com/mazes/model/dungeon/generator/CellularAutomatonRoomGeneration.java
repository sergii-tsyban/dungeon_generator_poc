package com.mazes.model.dungeon.generator;

import java.util.Random;

/**
 * Created by sergii.tsyban on 10/25/2016.
 */
public class CellularAutomatonRoomGeneration {

    public static final int WALL = 1;
    public static final int FLOOR = 0;

    private int initBirthProb = 40;
    private int birthLimit = 4;
    private int deathLimit = 4;
    private int generationsInOneStep = 1;
    private int[][] room;

    public CellularAutomatonRoomGeneration(int width, int heiht) {
        this.room = new int[heiht][width];
    }

    public void initWithRandom(){
        initRoomState();
    }

    public void makeGenerationStep(){
        for (int i = 0; i < generationsInOneStep; i++) {
            room = produceGeneration(room);
        }
    }

    private int[][] produceGeneration(int[][] oldRoom){
        int[][] newRoom = new int[oldRoom.length][oldRoom[0].length];
        for (int y = 0; y < oldRoom.length; y++) {
            for (int x = 0; x < oldRoom[y].length; x++) {
                int aliveNbrs = countAlive(oldRoom, y, x);

                boolean isWall = oldRoom[y][x] == WALL;
                if(isWall){
                    if(aliveNbrs >= deathLimit){
                        newRoom[y][x] = WALL;
                    } else if (aliveNbrs < 2){
                        newRoom[y][x] = FLOOR;
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

    private void initRoomState(){
        Random r = new Random();
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                room[i][j] = r.nextInt() <= initBirthProb ? WALL : FLOOR;
            }
        }
    }

    public int[][] getRoom() {
        return room;
    }

    public void setInitBirthProb(int initBirthProb) {
        this.initBirthProb = initBirthProb;
    }

    public void setBirthLimit(int birthLimit) {
        this.birthLimit = birthLimit;
    }

    public void setDeathLimit(int deathLimit) {
        this.deathLimit = deathLimit;
    }

    public void setGenerationsInOneStep(int generationsInOneStep) {
        this.generationsInOneStep = generationsInOneStep;
    }
}
