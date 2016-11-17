package com.mazes.model.dungeon.allocator;

import com.mazes.model.dungeon.allocator.matcher.*;
import com.mazes.model.dungeon.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class TileIdAllocator {

    private List<CellMatcherElement> matchersChain = new ArrayList<CellMatcherElement>();

    {
        matchersChain.add(new SolidWall());
        matchersChain.add(new Floor());
        matchersChain.add(new WallCornerTopLeft());
        matchersChain.add(new WallCornerTopRight());
        matchersChain.add(new WallCornerBottomRight());
        matchersChain.add(new WallCornerBottomLeft());
        matchersChain.add(new WallOnTheBottom());
        matchersChain.add(new WallOnTheLeft());
        matchersChain.add(new WallOnTheRight());
        matchersChain.add(new WallOnTheTop());
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
        for (CellMatcherElement cellMatcherElement : matchersChain) {
            cell = cellMatcherElement.assignOnMatch(cave, i, j);
            if(cell != null){
                return cell;
            }
        }
        if(cell == null){
            System.out.println(String.format("%d %d %d", cave[i - 1][j - 1], cave[i - 1][j], cave[i - 1][j + 1]));
            System.out.println(String.format("%d %d %d", cave[i][j - 1], cave[i][j], cave[i][j + 1]));
            System.out.println(String.format("%d %d %d", cave[i + 1][j - 1], cave[i + 1][j], cave[i + 1][j + 1]));
        }
        throw new IllegalStateException("No matcher found for cell: x = " + j + ", y = " + i);
    }

}
