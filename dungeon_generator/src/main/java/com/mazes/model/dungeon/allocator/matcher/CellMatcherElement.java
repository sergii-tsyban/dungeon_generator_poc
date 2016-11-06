package com.mazes.model.dungeon.allocator.matcher;

public abstract class CellMatcherElement {

    public boolean assignOnMatch(int[][] cave, int i, int j){
        boolean matched = matched(cave, i, j);
        if(matched){
            cave[i][j] = getId();
        }
        return matched;
    }

    public abstract int getId();

    public abstract boolean matched(int[][] cave, int i, int j);

}
