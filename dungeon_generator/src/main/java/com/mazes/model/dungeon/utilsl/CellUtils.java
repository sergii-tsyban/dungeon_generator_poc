package com.mazes.model.dungeon.utilsl;

import static com.mazes.model.dungeon.allocator.TilesIds.FLOOR;

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

    public static boolean hasMask(int[] masks, int maskToSerach){
        for (int mask : masks) {
            if(mask == maskToSerach){
                return true;
            }
        }
        return false;
    }
}
