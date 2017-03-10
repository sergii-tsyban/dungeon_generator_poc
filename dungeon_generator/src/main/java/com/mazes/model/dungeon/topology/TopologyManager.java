package com.mazes.model.dungeon.topology;

import com.mazes.model.dungeon.topology.adjuster.*;

import java.util.ArrayList;
import java.util.List;

public class TopologyManager {

    public static final int DEFAULT_MAX_ADJUSTMENT_STEPS = 10;

    private int maxAdjustmentSteps = DEFAULT_MAX_ADJUSTMENT_STEPS;

    private List<TopologyAdjuster> adjusters = new ArrayList<>();

    {
//        registerTopologyAdjuster(StairsAdjuster.class);
//        registerTopologyAdjuster(IslandBuilderAdjuster.class);
        registerTopologyAdjuster(SolidZBreakerAdjuster.class);
        registerTopologyAdjuster(TBreakerAdjuster.class);
        registerTopologyAdjuster(MBreakerAdjuster.class);
        registerTopologyAdjuster(ZBreakerAdjuster.class);
        registerTopologyAdjuster(XBreakerAdjuster.class);
        registerTopologyAdjuster(DotsCleanerAdjuster.class);
        registerTopologyAdjuster(TeethAdjuster.class);
        registerTopologyAdjuster(HolesAdjuster.class);
//        registerTopologyAdjuster(WBreakerAdjuster.class);
    }

    public <T extends TopologyAdjuster> void registerTopologyAdjuster(Class<T> adjusterClass){
        try {
            adjusters.add(adjusterClass.newInstance());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean executeAdjustmentStep(int[][] cave){
        boolean wasAdjusted = false;
        for (TopologyAdjuster adjuster: adjusters){
            if(adjuster.adjust(cave)){
                wasAdjusted = true;
            }
        }
        return wasAdjusted;
    }

    public int adjustTopology(int[][] cave){
        int stepsMade = 1;
        while(executeAdjustmentStep(cave) && stepsMade <= maxAdjustmentSteps){
            stepsMade++;
        }
        return stepsMade;
    }

    public void setMaxAdjustmentSteps(int maxAdjustmentSteps) {
        this.maxAdjustmentSteps = maxAdjustmentSteps;
    }
}
