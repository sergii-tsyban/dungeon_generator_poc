package com.mazes.model.dungeon.generator.graph.bsp;

import com.mazes.model.dungeon.generator.graph.rng.RNGNode;

import java.util.Random;

public class BSPNode implements RNGNode {

    private static int ID_SEQUENCE;
    private static final Random RAND = new Random();

    private int minLeafSize = 7;

    private final int id;
    public int i;
    public int j;
    public int width;
    public int height;

    public BSPNode leftChild;
    public BSPNode rightChild;

    public BSPNode(int j, int i, int width, int height) {
        this.j = j;
        this.i = i;
        this.width = width;
        this.height = height;
        id = ++ID_SEQUENCE;
    }

    public int getId() {
        return id;
    }

    public boolean split(){
        if (leftChild != null || rightChild != null)
            return false;
        boolean splitH;
        if (width > height)
            splitH = false;
        else
            splitH = true;
        int max = (splitH ? height : width) - minLeafSize;
        if (max <= minLeafSize)
            return false;
        int split = RAND.nextInt(max - minLeafSize) + minLeafSize;
        if (splitH) {
            leftChild = new BSPNode(j, i, width, split);
            rightChild = new BSPNode(j, i + split, width, height - split);
        } else {
            leftChild = new BSPNode(j, i, split, height);
            rightChild = new BSPNode(j + split, i, width - split, height);
        }
        return true;
    }

    public void setMinLeafSize(int minLeafSize) {
        this.minLeafSize = minLeafSize;
    }

    @Override
    public int getCenterX() {
        return j + width/2;
    }

    @Override
    public int getCenterY() {
        return i + height/2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BSPNode bspNode = (BSPNode) o;

        if (i != bspNode.i) return false;
        if (j != bspNode.j) return false;
        if (width != bspNode.width) return false;
        return height == bspNode.height;

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}
