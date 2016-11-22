package com.mazes.model.dungeon.allocator;

import com.mazes.model.dungeon.allocator.matcher.*;
import com.mazes.model.dungeon.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class TileIdAllocator {

    private List<CellMatcher> matchersChain = new ArrayList<CellMatcher>();

    {
        matchersChain.add(new NoTileMatcher());
        matchersChain.add(new WallFrontBottomMatcher());
        matchersChain.add(new WallCornerTopLeftMatcher());
        matchersChain.add(new WallCornerTopRightMatcher());
//        matchersChain.add(new Floor());
//        matchersChain.add(new WallCornerTopLeft());
//        matchersChain.add(new WallCornerTopRight());
//        matchersChain.add(new WallOnTheBottom());
//        matchersChain.add(new WallOnTheLeft());
//        matchersChain.add(new WallOnTheRight());
    }

    public Cell[][] allocateIds(int[][] cave){
        Cell[][] cells = new Cell[cave.length][cave[0].length];
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                cells[i][j] = assignIdForCell(cave, i, j);
            }
        }
        return cells;
    }

    private Cell assignIdForCell(int[][] cave, int i, int j) {
        for (CellMatcher cellMatcher : matchersChain) {
            if(cellMatcher.matched(cave, i, j)){
                return new Cell(j, i, cellMatcher.getIds());
            }
        }
        return new Cell(j, i, new int[] {cave[i][j]});
    }

}
