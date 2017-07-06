package com.mazes.model.dungeon.generator.dungeon;

import com.mazes.model.dungeon.generator.graph.msp.MinimumSpanningTree;
import com.mazes.model.dungeon.generator.graph.rng.Edge;
import com.mazes.model.dungeon.generator.graph.rng.RelativeNeighborhoodGraph;

import java.util.*;

public class DungeonGenerator {

    public static final int ATTEMPTS_PER_ROOM = 10000;
    public static final int DEFAULT_X_PADDING = 3;
    public static final int DEFAULT_Y_PADDING = 3;

    public static final int ROOM_MAX_SIDE= 8;
    public static final int ROOM_MIN_SIDE= 4;

    public static float calculateDistanceBetween(DRoom from, DRoom to) {
        int dx = from.getCenterX() - to.getCenterX();
        int dy = from.getCenterY() - to.getCenterY();
        return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public static DCorridor connect(DRoom from, DRoom to) {
        DCell fromCenter = new DCell(from.getCenterX(), from.getCenterY());
        DCell toCenter = new DCell(to.getCenterX(), to.getCenterY());

        int dx = fromCenter.x - toCenter.x;
        int dy = fromCenter.y - toCenter.y;

        int xStepsLeft = Math.abs(dx);
        int yStepsLeft = Math.abs(dy);

        DCorridor c = new DCorridor();
        DCell current = fromCenter;

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
                current = new DCell(current.x - (dx/Math.abs(dx)), current.y);
            } else {
                yStepsLeft--;
                current = new DCell(current.x, current.y  - (dy/Math.abs(dy)));
            }
            c.addPoint(current);
        }
        return c;
    }

    public void populateDungeon(SubDungeon subDungeon){
        List<DRoom> rooms = generateRandom(subDungeon.getX(), subDungeon.getY(), subDungeon.getWidth(), subDungeon.getHeight());
        subDungeon.setRooms(rooms);

        RelativeNeighborhoodGraph<DRoom> rng = new RelativeNeighborhoodGraph<>();
        List<Edge<DRoom>> edges = rng.createEdges(rooms);

        MinimumSpanningTree msp = new MinimumSpanningTree();
        edges = msp.minimumSpanningTree(edges);

        List<DCorridor> corridors = buildCorridors(edges);
        subDungeon.setCorridors(corridors);

    }
    public void updateTopology(int[][] topology, SubDungeon sd){
        for (int y = 0; y < topology.length; y++) {
            for (int x = 0; x < topology[y].length; x++) {
                for (DRoom room : sd.getRooms()) {
                    if(room.containPoint(x ,y)){
                        topology[y][x] = 0;
                        break;
                    }
                }
                for (DCorridor corridor : sd.getCorridors()) {
                    for (DCell point : corridor.getPoints()) {
                        topology[point.y][point.x] = 0;
                    }
                }
                if(sd.hasNextSubDungeon()){
                    for(DCorridor corridor: sd.getCorridorsToNextSubDungeons()){
                        for (DCell point : corridor.getPoints()) {
                            topology[point.y][point.x] = 0;
                        }
                    }
                }
            }
        }
    }

    private List<DRoom> generateRandom(int baseX, int baseY, int width, int height){
        List<DRoom> rooms = new ArrayList<>();
        Random random  = new Random();
        DRoom room;
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

            int x = random.nextInt(width - DEFAULT_X_PADDING * 2 - w) + DEFAULT_X_PADDING + baseX;
            int y = random.nextInt(height - DEFAULT_Y_PADDING * 2 - h) + DEFAULT_Y_PADDING + baseY;

            room = new DRoom(x,y,w,h);

            for (DRoom r : rooms) {
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
        return rooms;
    }

    private List<DCorridor> buildCorridors(List<Edge<DRoom>> edges){
        Map<Integer, DRoom> rooms = new HashMap<>();
        for (Edge<DRoom> edge : edges) {
            rooms.put(edge.from.id(), edge.from);
            rooms.put(edge.to.id(), edge.to);
        }
        List<DCorridor> corridors = new ArrayList<>();
        DRoom from;
        DRoom to;
        for (Edge<DRoom> edge : edges) {
            from = rooms.get(edge.from.id());
            to = rooms.get(edge.to.id());
            corridors.add(connect(from, to));
        }
        return corridors;
    }

}
