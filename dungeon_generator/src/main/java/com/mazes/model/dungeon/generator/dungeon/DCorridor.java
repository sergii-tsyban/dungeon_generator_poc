package com.mazes.model.dungeon.generator.dungeon;

import java.util.ArrayList;
import java.util.List;

public class DCorridor {

    private List<DCell> cells = new ArrayList<>();

    public void addPoint(DCell cell){
        cells.add(cell);
    }

    public List<DCell> getPoints() {
        return cells;
    }

    public boolean containCell(DCell doorCell) {
        for (DCell cell : cells) {
            if(cell.equals(doorCell)){
                return true;
            }
        }
        return false;
    }
}
