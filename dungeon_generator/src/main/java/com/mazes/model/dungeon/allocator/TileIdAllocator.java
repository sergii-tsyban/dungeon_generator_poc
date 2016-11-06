package com.mazes.model.dungeon.allocator;

import com.mazes.model.dungeon.allocator.matcher.*;

import java.util.ArrayList;
import java.util.List;

public class TileIdAllocator {

    private List<CellMatcherElement> matchersChain = new ArrayList<>();

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

    public void allocateIds(int[][] cave){
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                assignIdForCell(cave, i, j);
            }
        }
    }

    private void assignIdForCell(int[][] cave, int i, int j) {
        for (CellMatcherElement cellMatcherElement : matchersChain) {
            if(cellMatcherElement.assignOnMatch(cave, i, j)){
                return;
            }
        }
    }

}
