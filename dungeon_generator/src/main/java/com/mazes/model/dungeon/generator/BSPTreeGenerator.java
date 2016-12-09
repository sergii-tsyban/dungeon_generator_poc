package com.mazes.model.dungeon.generator;

import java.util.*;
import java.util.List;

public class BSPTreeGenerator {

    private int maxLeafSize = 50;
    private List<Node> nodes = new LinkedList<Node>();
    private int[][] cave;

    public BSPTreeGenerator(int width, int height){
        cave = new int[height][width];
    }

    public void generateLeafs(){
        nodes.clear();
        Node root = new Node(0,0,cave[0].length, cave.length);
        nodes.add(root);
        boolean didSplit = true;
        while(didSplit){
            didSplit=false;
            ListIterator<Node> iter = nodes.listIterator();
            while(iter.hasNext()){
                Node l = iter.next();
                if(l.leftChild == null && l.rightChild == null){
                    if(l.width > maxLeafSize || l.height > maxLeafSize || Math.random() > 0.25){
                        if(l.split()){
                            iter.add(l.leftChild);
                            iter.add(l.rightChild);
                            didSplit = true;
                        }
                    }
                }
            }
        }
    }

    public List<Node> getLeafs() {
        List<Node> leafs = new ArrayList<>();
        for(Node node: nodes){
            if(node.leftChild == null && node.rightChild == null){
                leafs.add(node);
            }
        }
        return leafs;
    }

    public static class Node {

        private static final int MIN_LEAF_SIZE = 60;

        private Random r = new Random();

        private int i;
        private int j;
        private int width;
        private int height;

        private Node leftChild; // the Node's left child Node
        private Node rightChild; // the Node's right child Node
        private LinkedList haals; // hallways to connect this Node to other Leafs

        public Node(int j, int i, int width, int height) {
            this.j = j;
            this.i = i;
            this.width = width;
            this.height = height;
        }

        public boolean split(){
            // begin splitting the leaf into two children
            if (leftChild != null || rightChild != null)
                return false; // we're already split! Abort!

            // determine direction of split
            // if the width is >25% larger than height, we split vertically
            // if the height is >25% larger than the width, we split horizontally
            // otherwise we split randomly

            boolean splitH = Math.random() > 0.5;
            if (width > height && width / height >= 1.5)
                splitH = false;
            else if (height > width && height / width >= 1.5)
                splitH = true;

            int max = (splitH ? height : width) - MIN_LEAF_SIZE; // determine the maximum height or width

            if (max <= MIN_LEAF_SIZE)
                return false; // the area is too small to split any more...

            int split = r.nextInt(max - MIN_LEAF_SIZE) + (int)(MIN_LEAF_SIZE*0.75); // determine where we're going to split

            // create our left and right children based on the direction of the split
            if (splitH) {
                leftChild = new Node(j, i, width, split);
                rightChild = new Node(j, i + split, width, height - split);
            } else {
                leftChild = new Node(j, i, split, height);
                rightChild = new Node(j + split, i, width - split, height);
            }
            return true; // split successful!
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String toString(){
            return String.format("x = %d, y = %d, w = %d, h = %d", j, i, width, height);
        }
    }

}
