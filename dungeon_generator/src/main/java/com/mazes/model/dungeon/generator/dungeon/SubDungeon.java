package com.mazes.model.dungeon.generator.dungeon;

import java.util.*;

public class SubDungeon {

    private final int id;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private List<DRoom> rooms;
    private List<DCorridor> corridors;
    private Map<Integer, DCorridor> corridorsToNextSubDungeons = new HashMap<>();
    private Map<Integer, SubDungeon> nextSubDungeons = new HashMap<>();

    public SubDungeon(int id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isLeaf(){
        return nextSubDungeons.isEmpty();
    }

    public void setRooms(List<DRoom> rooms) {
        this.rooms = rooms;
    }

    public void setCorridors(List<DCorridor> corridors) {
        this.corridors = corridors;
    }

    public int getCenterY() {
        return y + height/2;
    }

    public int getCenterX() {
        return x + width/2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<DRoom> getRooms() {
        return rooms;
    }

    public List<DCorridor> getCorridors() {
        return corridors;
    }

    public void addNextSubDungeon(SubDungeon to) {
        nextSubDungeons.put(to.getId(), to);
    }

    public boolean contains(int x, int y) {
        return (this.x <= x && (this.x + width) > x) &&
                (this.y <= y && (this.y + height) > y);
    }

    public boolean hasNextSubDungeon() {
        return nextSubDungeons != null && !nextSubDungeons.isEmpty();
    }

    public Map<Integer, SubDungeon> getNextSubDungeons() {
        return nextSubDungeons;
    }

    public int getId() {
        return id;
    }

    public void addCorridorToNextSubDungeon(SubDungeon toDungeon, DCorridor corridor) {
        corridorsToNextSubDungeons.put(toDungeon.getId(), corridor);
    }

    public Collection<DCorridor> getCorridorsToNextSubDungeons() {
        return corridorsToNextSubDungeons.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubDungeon that = (SubDungeon) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        if (width != that.width) return false;
        return height == that.height;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

}
