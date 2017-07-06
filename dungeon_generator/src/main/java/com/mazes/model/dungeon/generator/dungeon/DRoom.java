package com.mazes.model.dungeon.generator.dungeon;

import com.mazes.model.dungeon.generator.graph.rng.RNGNode;

import java.util.UUID;

public class DRoom implements RNGNode {

    private final int id;
    private int x;
    private int y;
    private final int w;
    private final int h;

    public DRoom(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        id = UUID.randomUUID().hashCode();
    }

    public boolean touches(DRoom r, int xPadding, int yPadding) {

        boolean xOverlaps = (x - xPadding <= r.right()) && (right() + xPadding >= r.x);
        boolean yOverlaps = (y - yPadding <= r.top()) && (top() + yPadding >= r.y);

        return xOverlaps && yOverlaps;
    }

    public int right(){
        return x + w - 1;
    }

    public int left(){
        return x;
    }

    public int top(){
        return y + h - 1;
    }

    public int bottom(){
        return y;
    }

    public void shift(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int id() {
        return id;
    }

    public boolean containPoint(int px, int py) {
        return (px >= x && px <= right()) && (py >= y && py <= top());
    }

    public int getCenterY() {
        return y + h/2;
    }

    public int getCenterX() {
        return x + w/2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DRoom dRoom = (DRoom) o;

        return id == dRoom.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
