package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.generator.DungeonConstructionFlow;
import com.mazes.model.dungeon.generator.dungeon.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.*;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT;
import static com.mazes.model.dungeon.allocator.TerrainTilesIds.SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT;

public class LevelVisualizerFX extends Application {

    private Map<Integer, Image> tileIdToImage = new HashMap<>();

    {
//        tileIdToImage.put(NO_TILE, new Image("terrain/wall_solid.png"));
        tileIdToImage.put(FLOOR, new Image("terrain/floor.png"));
        tileIdToImage.put(WALL_FRONT_BOTTOM, new Image("terrain/wall_front_bottom.png"));
        tileIdToImage.put(WALL_FRONT_TOP, new Image("terrain/wall_front_top.png"));
        tileIdToImage.put(WALL_SIDE_RIGHT, new Image("terrain/wall_side_right.png"));
        tileIdToImage.put(WALL_SIDE_LEFT, new Image("terrain/wall_side_left.png"));
        tileIdToImage.put(WALL_CORNER_TOP_RIGHT, new Image("terrain/wall_corner_tr.png"));
        tileIdToImage.put(WALL_CORNER_TOP_LEFT, new Image("terrain/wall_corner_tl.png"));
        tileIdToImage.put(WALL_TOP_WALL_SIDE_RIGHT, new Image("terrain/wall_top_wall_side_right.png"));
        tileIdToImage.put(WALL_TOP_WALL_SIDE_LEFT, new Image("terrain/wall_top_wall_side_left.png"));
        tileIdToImage.put(WALL_TOP_SIDE_LEFT, new Image("terrain/wall_top_left_side.png"));
        tileIdToImage.put(WALL_TOP_SIDE_RIGHT, new Image("terrain/wall_top_right_side.png"));
        tileIdToImage.put(WALL_CONNECTOR_BOTTOM_TO_RIGHT, new Image("terrain/wall_connector_bottom_to_right.png"));
        tileIdToImage.put(WALL_CONNECTOR_BOTTOM_TO_LEFT, new Image("terrain/wall_connector_bottom_to_left.png"));
        tileIdToImage.put(SIDE_LEFT, new Image("terrain/side_left.png"));
        tileIdToImage.put(SIDE_RIGHT, new Image("terrain/side_right.png"));
        tileIdToImage.put(SIDE_CONNECTOR_TOP_TO_LEFT, new Image("terrain/side_connector_top_to_left.png"));
        tileIdToImage.put(SIDE_CONNECTOR_TOP_TO_RIGHT, new Image("terrain/side_connector_top_to_right.png"));
        tileIdToImage.put(SIDE_BOTTOM, new Image("terrain/side_bottom.png"));
        tileIdToImage.put(SIDE_CONNECTOR_BOTTOM_TO_LEFT, new Image("terrain/side_connector_bottom_to_left.png"));
        tileIdToImage.put(SIDE_CONNECTOR_BOTTOM_TO_RIGHT, new Image("terrain/side_connector_bottom_to_right.png"));
        tileIdToImage.put(SIDE_LEFT_WITH_WALL_CONNECTOR, new Image("terrain/side_right_with_wall_connector.png"));
        tileIdToImage.put(SIDE_RIGHT_WITH_WALL_CONNECTOR, new Image("terrain/side_left_with_wall_connector.png"));
        tileIdToImage.put(SIDE_CONNECTOR_TR_WITH_WALL_CONN, new Image("terrain/side_connector_tr_with_wall_conn.png"));
        tileIdToImage.put(SIDE_CONNECTOR_TL_WITH_WALL_CONN, new Image("terrain/side_connector_tl_with_wall_conn.png"));
        tileIdToImage.put(SIDE_CONNECTOR_TL_WITH_WALL_SIDE_RIGHT, new Image("terrain/side_connector_tl_with_wall_side_right.png"));
        tileIdToImage.put(SIDE_CONNECTOR_TR_WITH_WALL_SIDE_LEFT, new Image("terrain/side_connector_tr_with_wall_side_left.png"));
    }

    public static final int CELL_SIDE = 10;

    private DungeonConstructionFlow dungeonConstructionFlow;

    private Dungeon dungeon;

    private Group root;
    private Label label;
    private Canvas canvas;

    private TextField widthTextField;
    private TextField heightTextField;

    private int width;
    private int height;

    public static void main(String[] args) {
        DungeonVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        //init fields
        dungeonConstructionFlow = new DungeonConstructionFlow();

        width = 70;
        height = 70;

        root = new Group();
        canvas = new Canvas(width * CELL_SIDE, width * CELL_SIDE);
        label = new Label("Status: ");
        //init java fx
        Scene scene = new Scene(root, Color.WHITE);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 7, 10, 7));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(createDungeonPanel(),
                createCavePanel(),
                createInputPanel(), canvas, label);
        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createInputPanel() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        final Label widthLabel = new Label("Width:");
        final Label heightLabel = new Label("Height:");
        widthTextField = new TextField(String.valueOf(width));
        heightTextField = new TextField(String.valueOf(height));
        hbox.getChildren().addAll(widthLabel, widthTextField, heightLabel, heightTextField);
        return hbox;
    }

    private HBox createCavePanel() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        final Button generateDungeon = new Button("Generate cave");
        generateDungeon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearCanvas(canvas.getGraphicsContext2D());
                dungeon = dungeonConstructionFlow.constructDefaultCave();
                drawTopology(canvas.getGraphicsContext2D());
            }
        });

        final Button allocateTiles = new Button("Allocate Tiles");
        allocateTiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearCanvas(canvas.getGraphicsContext2D());
                drawCells(canvas.getGraphicsContext2D(), dungeon.getAllocatedIds());
            }
        });

        hbox.getChildren().addAll(generateDungeon, allocateTiles);
        return hbox;
    }

    private HBox createDungeonPanel() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        final Button allocateTiles = new Button("Allocate Tiles");
        allocateTiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearCanvas(canvas.getGraphicsContext2D());
                drawCells(canvas.getGraphicsContext2D(), dungeon.getAllocatedIds());
            }
        });

        final Button showRooms = new Button("Show rooms");
        showRooms.setDisable(true);
        showRooms.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawDungeon(canvas.getGraphicsContext2D());
            }
        });

        final Button generateDungeon = new Button("Generate dungeon");
        generateDungeon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearCanvas(canvas.getGraphicsContext2D());
                dungeon = dungeonConstructionFlow.constructDefaultDungeon();
                drawTopology(canvas.getGraphicsContext2D());
                showRooms.setDisable(false);
            }
        });

        hbox.getChildren().addAll(generateDungeon, showRooms, allocateTiles);
        return hbox;
    }

    private void drawTopology(GraphicsContext gc) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < dungeon.getTopology().length; i++) {
            for (int j = 0; j < dungeon.getTopology()[i].length; j++) {
                gc.setFill(dungeon.getTopology()[i][j] == WALL_SOLID ? Color.BROWN : Color.GRAY);
                gc.fillRect(x, y, CELL_SIDE, CELL_SIDE);
                gc.setFill(Color.LIGHTGREY);
                gc.strokeRect(x, y, CELL_SIDE, CELL_SIDE);
                x += CELL_SIDE;
            }
            x = 0;
            y += CELL_SIDE;
        }
    }

    private Map<DRoom.Type, Color> roomsColors = new HashMap(){{
        put(DRoom.Type.CORRIDOR, Color.LIGHTGREEN);
        put(DRoom.Type.LEAF, Color.GREEN);
        put(DRoom.Type.PASS_ROOM, Color.ORANGE);
        put(DRoom.Type.CAVE, Color.WHITE);
    }};

    private void drawDungeon(GraphicsContext gc) {
        Collection<DCorridor> corridors = dungeon.getCorridorsIndex().values();
        Collection<DRoom> rooms = dungeon.getRoomsIndex().values();
        for (DRoom room : rooms) {
            gc.setFill(roomsColors.get(room.getType()));
            gc.fillRect(room.left() * CELL_SIDE, room.bottom() * CELL_SIDE,
                    room.width() * CELL_SIDE, room.height() * CELL_SIDE);
        }
        gc.setFill(Color.GRAY);
        for (DCorridor corridor : corridors) {
            List<DCell> cells = corridor.getPoints();
            for (DCell cell : cells) {
                gc.fillRect(cell.x * CELL_SIDE, cell.y * CELL_SIDE, CELL_SIDE, CELL_SIDE);
            }
        }
        for (DRoom room : rooms) {
            if(room.isCorridor()){
                continue;
            }
            for(DCell cell: room.getDoorCells()){
                gc.setFill(Color.BLACK);
                gc.strokeRect(cell.x * CELL_SIDE, cell.y * CELL_SIDE, CELL_SIDE, CELL_SIDE);
            }
        }
        drawRoom(gc, dungeon.getStartRoom(), Color.BLUE);
        drawRoom(gc, dungeon.getEndRoom(), Color.RED);
        for (DRoom room : rooms) {
            gc.setFill(Color.BLACK);
            gc.fillText(room.getCorridors().size() + "/" + room.getDoorCells().size(),
                    room.left() * CELL_SIDE + (room.width() * CELL_SIDE)/2,
                    room.bottom() * CELL_SIDE + (room.height() * CELL_SIDE)/2);
        }
    }

    private void drawRoom(GraphicsContext gc, DRoom room, Color color){
        gc.setFill(color);
        gc.fillRect(room.left() * CELL_SIDE, room.bottom() * CELL_SIDE,
                room.width() * CELL_SIDE, room.height() * CELL_SIDE);
    }

    private void clearCanvas(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width * CELL_SIDE, height * CELL_SIDE);
    }

    private int getInt(String str, int def) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    private void drawCells(GraphicsContext gc, int[][][] cells) {
        int x = 0;
        int y = 0;
        for (int[][] row : cells) {
            for (int[] tiles : row) {
                if (NO_TILE == tiles[0]) {
                    gc.setFill(Color.GREY);
                    gc.fillRect(x, y, CELL_SIDE, CELL_SIDE);
                } else {
                    for (int tile : tiles) {
//                        if (WALL_SOLID == tile) {
//                            gc.setFill(Color.GRAY);
//                            continue;
//                        }
                        drawTile(gc, tile, x, y);
                    }
                }
                x += CELL_SIDE;
            }
            x = 0;
            y += CELL_SIDE;
        }
    }

    private void drawTile(GraphicsContext gc, int tile, int x, int y) {
        Image i = tileIdToImage.get(tile);
        if (i != null) {
            gc.drawImage(i, x, y, CELL_SIDE, CELL_SIDE);
        } else {
            gc.setFill(Color.RED);
            gc.fillRect(x, y, CELL_SIDE, CELL_SIDE);
        }
    }
}
