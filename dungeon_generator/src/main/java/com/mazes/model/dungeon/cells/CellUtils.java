package com.mazes.model.dungeon.cells;

import java.util.Arrays;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.FLOOR;

public class CellUtils {

    public static int countWallsAround(int[][] oldRoom, int y, int x) {
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
                else if(oldRoom[neighbour_y][neighbour_x] != FLOOR){
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean onEdge(int[][] oldRoom, int y, int x){
        return y == 0 || x  == 0 || y == oldRoom.length - 1 || x == oldRoom[0].length - 1;
    }

    public static int toMask(int[][] room, int cell_y, int cell_x){
        int m = 0;
        for(int y = -1; y<2; y++){
            for(int x=-1; x<2; x++){
                int neighbour_x = cell_x + x;
                int neighbour_y = cell_y + y;
                if(neighbour_x < 0 || neighbour_y < 0 || neighbour_y >= room.length || neighbour_x >= room[0].length){
                    m |= 1;
                } else {
                    m |= room[neighbour_y][neighbour_x];
                }
                m <<= 1;
            }
        }
        return m >> 1;
    }

    public static boolean maskMatched(int[] masks, int maskToSearch){
        for (int mask : masks) {
            if(mask == maskToSearch){
                return true;
            }
        }
        return false;
    }

    public static int[][] rotateCW(int[][] arr){
        int[][] res = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                res[i][j] = arr[(arr.length-1) - j][i];
            }
        }
        return res;
    }

    public static int[][] rotateCCW(int[][] arr){
        int[][] res = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                res[i][j] = arr[j][(arr[i].length - 1) - i];
            }
        }
        return res;
    }

    public static void flipHorizontal(int[][] arr){
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0,k = arr[i].length - 1; j < arr[i].length / 2 && k > arr[i].length / 2; j++, k--) {
                temp = arr[i][j];
                arr[i][j] = arr[i][k];
                arr[i][k] = temp;
            }
        }
    }

    public static void flipVertical(int[][] arr){
        int[] temp;
        for (int i = 0,k = arr.length - 1; i < arr.length / 2 && k > arr.length / 2; i++, k--) {
            temp = Arrays.copyOf(arr[i], arr.length);
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }
}
