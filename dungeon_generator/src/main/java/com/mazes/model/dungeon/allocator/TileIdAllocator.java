package com.mazes.model.dungeon.allocator;

import com.mazes.model.dungeon.allocator.matcher.*;
import com.mazes.model.dungeon.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class TileIdAllocator {

    private List<CellMatcher> matchersChain = new ArrayList<CellMatcher>();

    {
        matchersChain.add(new NoTileMatcher());
//        matchersChain.add(new Floor());
//        matchersChain.add(new WallCornerTopLeft());
//        matchersChain.add(new WallCornerTopRight());
//        matchersChain.add(new WallCornerBottomRight());
//        matchersChain.add(new WallCornerBottomLeft());
//        matchersChain.add(new WallOnTheBottom());
//        matchersChain.add(new WallOnTheLeft());
//        matchersChain.add(new WallOnTheRight());
//        matchersChain.add(new WallOnTheTop());
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
        Cell cell = null;
        for (CellMatcher cellMatcher : matchersChain) {
            if(cellMatcher.matched(cave, i, j)){
                cell = new Cell(j, i, cellMatcher.getIds());
                break;
            }
        }
        if(cell == null){
            cell = new Cell(j, i, new int[] {cave[i][j]});
//            System.out.println(String.format("%d %d %d", cave[i - 1][j - 1], cave[i - 1][j], cave[i - 1][j + 1]));
//            System.out.println(String.format("%d %d %d", cave[i][j - 1], cave[i][j], cave[i][j + 1]));
//            System.out.println(String.format("%d %d %d", cave[i + 1][j - 1], cave[i + 1][j], cave[i + 1][j + 1]));
        }
        return cell;
    }

}
