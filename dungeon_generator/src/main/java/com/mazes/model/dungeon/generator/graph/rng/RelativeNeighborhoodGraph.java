package com.mazes.model.dungeon.generator.graph.rng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RelativeNeighborhoodGraph <T extends RNGNode> {

    public List<Edge<T>> createEdges(List<T> nodes){
        Random r = new Random();
        int weight = 1;
        List<Edge<T>> edges = new ArrayList<>();
        RNGNode a, b, c; // Rooms to check between
        double abDist, acDist, bcDist; // squared distance between each of the 3 rooms
        boolean skip;
        for(int i = 0;i < nodes.size();i++) {
            a = nodes.get(i);
            for(int j = i+1;j < nodes.size();j++) { // for each pair of rooms
                skip = false;
                b = nodes.get(j);
                // get the sqrd distance between a and b
                abDist = Math.pow(a.getCenterX()-b.getCenterX(), 2) + Math.pow(a.getCenterY()-b.getCenterY(), 2);
                for(int k = 0;k < nodes.size();k++) { // loop through all other rooms
                    if(k == i || k == j) // that are not a or b
                        continue;
                    c = nodes.get(k);
                    // get the other two applicable distances
                    acDist = Math.pow(a.getCenterX()-c.getCenterX(), 2) + Math.pow(a.getCenterY()-c.getCenterY(), 2);
                    bcDist = Math.pow(b.getCenterX()-c.getCenterX(), 2) + Math.pow(b.getCenterY()-c.getCenterY(), 2);
                    // if the distance between a and c or b and c are smaller than a, the pairing of
                    // a and b is not a edges edge
                    if(acDist < abDist && bcDist < abDist)
                        skip = true;
                    if(skip) // so we break the loop and go to the next a and b paring
                        break;
                }
                if(!skip) { // if this a and b pairing was never skipped, it should be an edge
//                    Edge e = new Edge(a, b, r.nextInt(10));
                    Edge e = new Edge(a, b, weight++);
                    edges.add(e);
                }
            }
        }
        return edges;
    }

}
