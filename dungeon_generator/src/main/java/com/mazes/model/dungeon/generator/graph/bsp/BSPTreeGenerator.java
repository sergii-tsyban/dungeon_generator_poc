package com.mazes.model.dungeon.generator.graph.bsp;

import java.util.*;
import java.util.List;

public class BSPTreeGenerator {

    private int sizeToSplit = 15;

    public List<BSPNode> treeLeafs(int width, int height){
        List<BSPNode> nodes = new LinkedList<>();
        BSPNode root = new BSPNode(0, 0, width, height);
        nodes.add(root);
        boolean didSplit = true;
        while(didSplit){
            didSplit = false;
            ListIterator<BSPNode> iter = nodes.listIterator();
            while(iter.hasNext()){
                BSPNode l = iter.next();
                if(l.leftChild == null && l.rightChild == null){
                    if(l.width > sizeToSplit || l.height > sizeToSplit){
                        if(l.split()){
                            iter.add(l.leftChild);
                            iter.add(l.rightChild);
                            didSplit = true;
                        }
                    }
                }
            }
        }
        return leafs(nodes);
    }

    private List<BSPNode> leafs(List<BSPNode> nodes){
        List<BSPNode> leafs = new ArrayList<>();
        for (BSPNode area : nodes) {
            if(area.leftChild == null && area.rightChild == null){
                leafs.add(area);
            }
        }
        return leafs;
    }

    public void setSizeToSplit(int sizeToSplit) {
        this.sizeToSplit = sizeToSplit;
    }
}
