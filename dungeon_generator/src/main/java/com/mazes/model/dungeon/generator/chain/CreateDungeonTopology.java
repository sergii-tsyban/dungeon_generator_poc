package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.dungeon.DCell;
import com.mazes.model.dungeon.generator.dungeon.DCorridor;
import com.mazes.model.dungeon.generator.dungeon.DRoom;
import com.mazes.model.dungeon.topology.TopologyManager;

public class CreateDungeonTopology implements LevelConstructionChain {

    private TopologyManager topologyManager;

    public CreateDungeonTopology(TopologyManager topologyManager) {
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
        for (DRoom room : context.rooms) {
            for(int y = room.bottom(); y <= room.top(); y++){
                for (int x = room.left(); x <= room.right(); x++) {
                    topology[y][x] = 0;
                }
            }
        }
        for (DCorridor corridor : context.corridors) {
            for (DCell point : corridor.getPoints()) {
                topology[point.y][point.x] = 0;
            }
        }
        topologyManager.adjustTopology(topology);
        for (DRoom room : context.rooms) {
            room.assignRoomType(topology);
        }
        context.topology = topology;
    }

}
