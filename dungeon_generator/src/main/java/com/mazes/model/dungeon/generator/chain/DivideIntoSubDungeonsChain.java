package com.mazes.model.dungeon.generator.chain;

import com.mazes.model.dungeon.generator.dungeon.SubDungeon;
import com.mazes.model.dungeon.generator.graph.bsp.BSPNode;
import com.mazes.model.dungeon.generator.graph.bsp.BSPTreeGenerator;
import com.mazes.model.dungeon.generator.graph.msp.MinimumSpanningTree;
import com.mazes.model.dungeon.generator.graph.rng.Edge;
import com.mazes.model.dungeon.generator.graph.rng.RelativeNeighborhoodGraph;

import java.util.*;

public class DivideIntoSubDungeonsChain implements LevelConstructionChain {

    private LevelConstructionChain nextChain;

    public DivideIntoSubDungeonsChain(LevelConstructionChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void process(LevelConstructionContext context) {
        BSPTreeGenerator bspTreeGenerator = new BSPTreeGenerator();
        RelativeNeighborhoodGraph<BSPNode> relativeNeighborhoodGraph = new RelativeNeighborhoodGraph<>();
        MinimumSpanningTree<BSPNode> msp = new MinimumSpanningTree<>();

        List<Edge<BSPNode>> edges = null;
        List<Integer> rootsIds = null;
        boolean created = false;
        while(!created){

            List<BSPNode> leafs = bspTreeGenerator.treeLeafs(context.levelWidth, context.levelHeight);

            edges = relativeNeighborhoodGraph.createEdges(leafs);

            edges = msp.minimumSpanningTree(edges);

            rootsIds = rootsIds(edges);

            created = rootsIds.size() == 1;
        }
        Collection<SubDungeon> subDungeons = connectToSubDungeons(edges);
        Map<Integer, SubDungeon> sdMap = new HashMap<>();
        for (SubDungeon subDungeon : subDungeons) {
            sdMap.put(subDungeon.getId(), subDungeon);
        }
        context.subDungeons = sdMap;
        context.rootSubDungeonId = rootsIds.get(0);
        nextChain.process(context);
    }

    private Collection<SubDungeon> connectToSubDungeons(List<Edge<BSPNode>> edges){
        Map<BSPNode, SubDungeon> subDungeonMap = new HashMap<>();
        for (Edge<BSPNode> edge : edges) {
            SubDungeon from = fromBspNode(edge.from);
            subDungeonMap.put(edge.from, from);
            SubDungeon to = fromBspNode(edge.to);
            subDungeonMap.put(edge.to, to);
        }

        for (Edge<BSPNode> edge : edges) {
            SubDungeon from = subDungeonMap.get(edge.from);
            SubDungeon to = subDungeonMap.get(edge.to);
            from.addNextSubDungeon(to);
        }

        return subDungeonMap.values();
    }

    private SubDungeon fromBspNode(BSPNode bspNode){
        SubDungeon sd = new SubDungeon(bspNode.getId(), bspNode.j, bspNode.i, bspNode.width, bspNode.height);
        return sd;
    }

    private List<Integer> rootsIds(List<Edge<BSPNode>> edges){
        Map<BSPNode, Integer> linkCounts = new HashMap<>();
        for (Edge<BSPNode> edge : edges) {
            linkCounts.put(edge.from, 0);
            linkCounts.put(edge.to, 0);
        }
        for (Edge<BSPNode> edge : edges) {
            Integer count = linkCounts.get(edge.to);
            linkCounts.put(edge.to, ++count);
        }
        List<Integer> rootIds = new ArrayList<>(3);
        for (Map.Entry<BSPNode, Integer> entry : linkCounts.entrySet()) {
            if(entry.getValue() == 0){
                rootIds.add(entry.getKey().getId());
            }
        }
        return rootIds;
    }

}
