package com.mazes.model.dungeon.topology;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serg on 22/11/2016.
 */
public class TopologyManager {

    private List<TopologyAdjuster> adjusters = new ArrayList<>();

    {
//        registerTopologyAdjuster(StairsAdjuster.class);
        registerTopologyAdjuster(TeethAdjuster.class);
    }

    public <T extends TopologyAdjuster> void registerTopologyAdjuster(Class<T> adjusterClass){
        try {
            adjusters.add(adjusterClass.newInstance());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void adjustCaveTopology(int[][] cave){
        for (TopologyAdjuster adjuster: adjusters){
            adjuster.adjust(cave);
        }
    }

}
