package com.mazes.model.dungeon.generator;

import java.util.*;

public class RoomsGenerator implements TopologyGenerator {

    public static final int ATTEMPTS_PER_ROOM = 10000;
    public static final int DEFAULT_X_PADDING = 3;
    public static final int DEFAULT_Y_PADDING = 3;

    public static final int ROOM_MAX_SIDE= 8;
    public static final int ROOM_MIN_SIDE= 4;

    private int width;
    private int height;

    private List<Room> rooms;
    private Map<Room, LinkedList<Room>> edges;
    private List<Corridor> corridors;

    public RoomsGenerator(int w, int h) {
        width = w;
        height = h;
    }

    private void generateRandom(){
        rooms = new ArrayList<>();
        Random random  = new Random();
        Room room;
        boolean overlaps;
        int attempts = 0;
        boolean wasPlaced = true;
        while(wasPlaced){
            if(attempts >= ATTEMPTS_PER_ROOM){
                wasPlaced = false;
            }
            overlaps = false;
            int w = random.nextInt(ROOM_MAX_SIDE - ROOM_MIN_SIDE) + ROOM_MIN_SIDE;
            int h = random.nextInt(ROOM_MAX_SIDE - ROOM_MIN_SIDE) + ROOM_MIN_SIDE;

            int x = random.nextInt(width - DEFAULT_X_PADDING * 2 - w) + DEFAULT_X_PADDING;
            int y = random.nextInt(height - DEFAULT_Y_PADDING * 2 - h) + DEFAULT_Y_PADDING;

            room = new Room(x,y,w,h);

            for (Room r : rooms) {
                if(r.touches(room, DEFAULT_X_PADDING, DEFAULT_Y_PADDING)){
                    overlaps = true;
                    attempts++;
                    break;
                }
            }
            if(!overlaps) {
                rooms.add(room);
                attempts = 0;
            }
        }
    }

    private void buildCorridors(){
        if(corridors != null){
            corridors.clear();
        } else {
            corridors = new ArrayList<>();
        }
        Room from;
        Room to;
        for (Map.Entry<Room, LinkedList<Room>> e : edges.entrySet()) {
            from = e.getKey();
            for (Room room : e.getValue()) {
                to = room;
                corridors.add(connect(from, to));
            }
        }
    }

    private Corridor connect(Room from, Room to) {
        Point fromCenter = new Point(from.getCenterX(), from.getCenterY());
        Point toCenter = new Point(to.getCenterX(), to.getCenterY());

        int dx = fromCenter.x - toCenter.x;
        int dy = fromCenter.y - toCenter.y;

        int xStepsLeft = Math.abs(dx);
        int yStepsLeft = Math.abs(dy);

        Corridor c = new Corridor();
        Point current = fromCenter;

        Random r = new Random();
        boolean xDirection;

        while(!current.equals(toCenter)){
            if(xStepsLeft == 0){
                xDirection = false;
            } else if (yStepsLeft == 0){
                xDirection = true;
            } else {
                xDirection = r.nextBoolean();
            }
            if(xDirection){
                xStepsLeft--;
                current = new Point(current.x - (dx/Math.abs(dx)), current.y);
            } else {
                yStepsLeft--;
                current = new Point(current.x, current.y  - (dy/Math.abs(dy)));
            }
            c.addPoint(current);
        }
        return c;
    }

    private void connectRoomsToGraph(){
        if(edges != null){
            edges.clear();
        } else {
            edges = new HashMap<>();
        }
        Room a, b, c; // Rooms to check between
        double abDist, acDist, bcDist; // squared distance between each of the 3 rooms
        boolean skip;
        for(int i = 0;i < rooms.size();i++) {
            a = rooms.get(i);
            for(int j = i+1;j < rooms.size();j++) { // for each pair of rooms
                skip = false;
                b = rooms.get(j);
                // get the sqrd distance between a and b
                abDist = Math.pow(a.getCenterX()-b.getCenterX(), 2) + Math.pow(a.getCenterY()-b.getCenterY(), 2);
                for(int k = 0;k < rooms.size();k++) { // loop through all other rooms
                    if(k == i || k == j) // that are not a or b
                        continue;
                    c = rooms.get(k);
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
                    if(edges.get(a) == null) {
                        edges.put(a, new LinkedList());
                    }
                    edges.get(a).add(b);
                }
            }
        }
    }

    public int[][] generate(){
        generateRandom();
        connectRoomsToGraph();
        buildCorridors();
        return topology();
    }

    private int[][] topology(){
        int[][] cave = new int[width][height];
        for (int y = 0; y < cave.length; y++) {
            for (int x = 0; x < cave[y].length; x++) {
                for (Room room : rooms) {
                    if(room.containPoint(x ,y)){
                        cave[cave.length - 1 - y][x] = 0;
                        break;
                    } else {
                        cave[cave.length - 1 - y][x] = 1;
                    }
                }
            }
        }
        for (Corridor corridor : corridors) {
            for (Point point : corridor.getPoints()) {
                cave[cave.length - 1 - point.y][point.x] = 0;
            }
        }
        return cave;
    }

    private static class Corridor{

        private List<Point> points = new ArrayList<>();

        public void addPoint(Point p){
            points.add(p);
        }

        public List<Point> getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return "Corridor{" +
                    "points=" + points +
                    '}';
        }
    }

    private static class Point{

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static class Room{

        private final int id;
        private int x;
        private int y;
        private final int w;
        private final int h;

        Room(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            id = UUID.randomUUID().hashCode();
        }

        boolean touches(Room b, int xPadding, int yPadding) {

            boolean xOverlaps = (x - xPadding <= b.right()) && (right() + xPadding >= b.x);
            boolean yOverlaps = (y - yPadding <= b.top()) && (top() + yPadding >= b.y);

            return xOverlaps && yOverlaps;
        }

        int right(){
            return x + w - 1;
        }

        int left(){
            return x;
        }

        int top(){
            return y + h - 1;
        }

        int bottom(){
            return y;
        }

        void shift(int dx, int dy) {
            x += dx;
            y += dy;
        }

        public int id() {
            return id;
        }

        boolean containPoint(int px, int py) {
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

            Room room = (Room) o;

            if (id != room.id) return false;
            if (x != room.x) return false;
            if (y != room.y) return false;
            if (w != room.w) return false;
            return h == room.h;

        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + x;
            result = 31 * result + y;
            result = 31 * result + w;
            result = 31 * result + h;
            return result;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    ", w=" + w +
                    ", h=" + h +
                    '}';
        }
    }
}
