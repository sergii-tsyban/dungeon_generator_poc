package com.mazes.model.dungeon.allocator;

import com.mazes.model.dungeon.allocator.matcher.*;

import java.util.ArrayList;
import java.util.List;

public class TileIdAllocator {

    private List<CellMatcher> matchersChain = new ArrayList<CellMatcher>();

    {
//        matchersChain.add(new Floor());
        matchersChain.add(new WallFrontBottomMatcher());
        matchersChain.add(new WallFrontTopMatcher());
        matchersChain.add(new WallCornerTopLeftMatcher());
        matchersChain.add(new WallCornerTopRightMatcher());
        matchersChain.add(new WallTopWallSideRightMatcher());
        matchersChain.add(new WallTopWallSideLeftMatcher());
        matchersChain.add(new WallTopSideRightMatcher());
        matchersChain.add(new WallTopSideLeftMatcher());
        matchersChain.add(new SideConnectorTRWithWallSideLeftMatcher());
        matchersChain.add(new SideConnectorTLWithWallSideRightMatcher());
        matchersChain.add(new SideRightWithWallConnMatcher());
        matchersChain.add(new SideLeftWithWallConnMatcher());
        matchersChain.add(new SideConnectorTopRightWithWallConn());
        matchersChain.add(new SideConnectorTopLeftWithWallConn());
        matchersChain.add(new WallConnectorBottomToRightMatcher());
        matchersChain.add(new WallConnectorBottomToLeftMatcher());

        matchersChain.add(new SideLeftMatcher());
        matchersChain.add(new SideRightMatcher());
        matchersChain.add(new SideBottomMatcher());

        matchersChain.add(new SideConnectorBottomToRightMatcher());
        matchersChain.add(new SideConnectorBottomToLeftMatcher());

        matchersChain.add(new SideConnectorTopToRight());
        matchersChain.add(new SideConnectorTopToLeft());

        matchersChain.add(new WallSideLeft());
        matchersChain.add(new WallSideRight());

        matchersChain.add(new NoTileMatcher());
    }

    public TileType[][][] allocateIds(int[][] cave){
        TileType[][][] cells = new TileType[cave.length][cave[0].length][];
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                cells[i][j] = assignIdForCell(cave, i, j);
            }
        }
        return cells;
    }

    public void chooseMatcher(int[][] cave, int i, int j){
        for (CellMatcher cellMatcher : matchersChain) {
            if(cellMatcher.matched(cave, i, j)){
                System.out.println(cellMatcher.getClass().getSimpleName());
                return;
            }
        }
        System.out.println("No Matcher found !");
    }

    private TileType[] assignIdForCell(int[][] cave, int i, int j) {
        for (CellMatcher cellMatcher : matchersChain) {
            if(cellMatcher.matched(cave, i, j)){
                return cellMatcher.getTiles();
            }
        }
        return TileType.arr(TileType.fromId(cave[i][j]));
    }

}
