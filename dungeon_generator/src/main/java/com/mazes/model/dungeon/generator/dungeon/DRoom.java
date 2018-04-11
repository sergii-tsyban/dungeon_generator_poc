package com.mazes.model.dungeon.generator.dungeon;

import com.mazes.model.dungeon.generator.graph.rng.RNGNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DRoom implements RNGNode {

    public enum Type {
        LEAF, CORRIDOR, PASS_ROOM, CAVE
    }

    private final int id;

    private int x;
    private int y;
    private int w;
    private int h;

    private List<DCell> doorCells;
    private List<DCorridor> corridors;

    private Type type;

    public DRoom(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        id = UUID.randomUUID().hashCode();
        corridors = new ArrayList<>();
        doorCells = new ArrayList<>();
    }

    public DRoom(int x, int y, int w, int h, Type type) {
        this(x, y, w, h);
        this.type = type;
    }

    public void addCorridor(DCorridor corridor) {
        corridors.add(corridor);
    }

    public boolean isLeaf() {
        return Type.LEAF == type;
    }

    public boolean isCorridor() {
        return Type.CORRIDOR == type;
    }

    public boolean isPassRoom() {
        return Type.PASS_ROOM == type;
    }

    public Type getType() {
        return type;
    }

    public void assignRoomType(int[][] topology) {
        for (int x = this.x; x < right() + 1; x++) {
            if (topology[this.y - 1][x] == 0) {
                doorCells.add(new DCell(x, this.y - 1));
            }
        }
        for (int x = this.x; x < right() + 1; x++) {
            if (topology[top() + 1][x] == 0) {
                doorCells.add(new DCell(x, top() + 1));
            }
        }
        for (int y = this.y - 1; y < top() + 2; y++) {
            if (topology[y][this.x - 1] == 0) {
                doorCells.add(new DCell(this.x - 1, y));
            }
        }
        for (int y = this.y - 1; y < top() + 2; y++) {
            if (topology[y][right() + 1] == 0) {
                doorCells.add(new DCell(right() + 1, y));
            }
        }
        if(doorCells.size() == 1){
            type = Type.LEAF;
            return;
        }
        int outCount = doorCells.size();
        label: for (DCell doorCell : doorCells) {
            for (DCorridor corridor : corridors) {
                if(corridor.containCell(doorCell)){
                    outCount--;
                    continue label;
                }
            }
        }
        if(outCount == 0){
            if(corridors.size() >= doorCells.size()){
                type = Type.PASS_ROOM;
            } else {
                type = Type.CORRIDOR;
            }
        } else if (outCount > 0){
            type = Type.CORRIDOR;
        } else if(outCount < 0){
            type = Type.PASS_ROOM;
        }
        if(type == Type.PASS_ROOM){
            DCell prev = null;
            for (DCell doorCell : doorCells) {
                if(prev == null){
                    prev = doorCell;
                    continue;
                }
                if(prev.x == doorCell.x){
                    if(Math.abs(doorCell.y - prev.y) == 1){
                        type = Type.CORRIDOR;
                        return;
                    }
                } else if(prev.y == doorCell.y){
                    if(Math.abs(doorCell.x - prev.x) == 1){
                        type = Type.CORRIDOR;
                        return;
                    }
                }
                prev = doorCell;
            }
        }
    }

    public List<DCell> getDoorCells() {
        return doorCells;
    }

    public boolean touches(DRoom r, int xPadding, int yPadding) {

        boolean xOverlaps = (x - xPadding <= r.right()) && (right() + xPadding >= r.x);
        boolean yOverlaps = (y - yPadding <= r.top()) && (top() + yPadding >= r.y);

        return xOverlaps && yOverlaps;
    }

    public int right() {
        return x + w - 1;
    }

    public int left() {
        return x;
    }

    public int top() {
        return y + h - 1;
    }

    public int bottom() {
        return y;
    }

    public int height() {
        return h;
    }

    public int width() {
        return w;
    }

    public int id() {
        return id;
    }

    public boolean containPoint(int px, int py) {
        return (px >= x && px <= right()) && (py >= y && py <= top());
    }

    public int getCenterY() {
        return y + h / 2;
    }

    public int getCenterX() {
        return x + w / 2;
    }

    public List<DCorridor> getCorridors() {
        return corridors;
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
