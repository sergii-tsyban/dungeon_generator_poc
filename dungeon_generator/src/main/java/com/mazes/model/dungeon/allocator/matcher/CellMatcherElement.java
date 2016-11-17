package com.mazes.model.dungeon.allocator.matcher;

import com.mazes.model.dungeon.cell.Cell;

public abstract class CellMatcherElement {

    public Cell assignOnMatch(int[][] cave, int i, int j){
        boolean matched = matched(cave, i, j);
        if(matched){
            return new Cell(j, i, getIds());
        }
        return null;
    }

    public abstract int[] getIds();

    public abstract boolean matched(int[][] cave, int i, int j);

}
