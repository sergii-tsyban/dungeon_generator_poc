package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.automaton.CellularAutomatonCaveGenerator;
import com.mazes.model.dungeon.generator.dungeon.DRoom;
import com.mazes.model.dungeon.topology.TopologyManager;

public class CreateCaveTopology implements LevelConstructionChain {

    private CellularAutomatonCaveGenerator automatonCaveGenerator;
    private TopologyManager topologyManager;

    public CreateCaveTopology(TopologyManager topologyManager) {
        automatonCaveGenerator = new CellularAutomatonCaveGenerator();
        this.topologyManager = topologyManager;
    }

    @Override
    public void process(LevelConstructionContext context) {
        int[][] topology = new int[context.levelHeight][context.levelWidth];
        for (int y = 0; y < topology.length; y++) {
            for (int x = 0; x < topology[y].length; x++) {
                topology[y][x] = 1;
            }
        }

        DRoom room = new DRoom(0, 0, context.levelWidth, context.levelHeight, DRoom.Type.LEAF);
        context.rooms.add(room);
        automatonCaveGenerator.clear();
        automatonCaveGenerator.addRoom(0, 0, context.levelWidth, context.levelHeight);
        automatonCaveGenerator.updateTopology(topology);

        topologyManager.adjustTopologyAddIslands(topology);

        context.topology = topology;
    }

}
