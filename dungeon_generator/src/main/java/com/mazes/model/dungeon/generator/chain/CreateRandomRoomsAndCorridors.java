package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.dungeon.DCorridor;
import com.mazes.model.dungeon.generator.dungeon.DRoom;
import com.mazes.model.dungeon.generator.dungeon.DungeonGenerator;
import com.mazes.model.dungeon.generator.graph.msp.MinimumSpanningTree;
import com.mazes.model.dungeon.generator.graph.rng.Edge;
import com.mazes.model.dungeon.generator.graph.rng.RelativeNeighborhoodGraph;

import java.util.List;

public class CreateRandomRoomsAndCorridors implements LevelConstructionChain {

    private DungeonGenerator dg = new DungeonGenerator();

    @Override
    public void process(LevelConstructionContext context) {
        List<DRoom> rooms = dg.createRooms(0, 0, context.levelWidth, context.levelHeight);

        RelativeNeighborhoodGraph<DRoom> rng = new RelativeNeighborhoodGraph<>();
        List<Edge<DRoom>> edges = rng.createEdges(rooms);

        MinimumSpanningTree msp = new MinimumSpanningTree();
        List<Edge<DRoom>> mspEdges = msp.minimumSpanningTree(edges);

        List<DCorridor> corridors = dg.buildCorridors(mspEdges);

        context.rooms = rooms;
        context.corridors = corridors;
    }
}
