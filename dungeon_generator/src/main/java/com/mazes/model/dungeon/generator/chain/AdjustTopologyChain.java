package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.topology.TopologyManager;

public class AdjustTopologyChain implements LevelConstructionChain {

    private LevelConstructionChain nextChain;
    private TopologyManager topologyManager;

    public AdjustTopologyChain(LevelConstructionChain nextChain, TopologyManager topologyManager) {
        this.nextChain = nextChain;
        this.topologyManager = topologyManager;
    }

    @Override
    public void process(LevelConstructionContext context) {
        if(context.adjustTopology){
            topologyManager.adjustTopology(context.topology);
        }
        nextChain.process(context);
    }

}
