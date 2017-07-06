package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.allocator.TerrainTileAllocator;
import com.mazes.model.dungeon.topology.TopologyManager;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.*;

public class RoomsVisualizerFX extends Application {

    private Map<Integer, Image> tileIdToImage = new HashMap<>();

    {
        tileIdToImage.put(NO_TILE, new Image("terrain/wall_solid.png"));
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

    public static final int CELL_SIDE_PIXELS = 16;

    public static final int CAVE_WIDTH = 70;
    public static final int CAVE_HEIGHT = 70;

    private static final Font LABEL_FONT = new Font("Verdana", 15);
    private static final Font TILE_ID_FONT = new Font("Verdana", 10);
    private static final Insets DEFAULT_INSETS = new Insets(10, 7, 10, 7);
    private static final int DEFAULT_SPACING = 10;
    private static final String DEFAULT_CSS = "-fx-background-color: #66b3ff;";

//    private TopologyGenerator generator;
    private TerrainTileAllocator allocator;
    private TopologyManager topologyManager;

    private int[][] cave;

    private List<Integer> ids = new ArrayList<>();

    private Group root;
    private Canvas canvas;
    private Label statusLabel;

    public static void main(String[] args) {
        RoomsVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        canvas = new Canvas(CAVE_WIDTH * CELL_SIDE_PIXELS, CAVE_HEIGHT * CELL_SIDE_PIXELS);
        statusLabel = new Label();

        Scene scene = new Scene(root, Color.WHITE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1500, 900);
        scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        allocator = new TerrainTileAllocator();
//        generator = new RoomsGenerator();
        topologyManager = new TopologyManager();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(TILE_ID_FONT);

//        cave = generator.treeLeafs(CAVE_WIDTH, CAVE_HEIGHT);

        draw(gc);

        addCanvasMouseListener(gc);

        scrollPane.setContent(canvas);

        VBox vbox = new VBox();
        vbox.setPadding(DEFAULT_INSETS);
        vbox.setSpacing(DEFAULT_SPACING);
        vbox.getChildren().addAll(createControlPanel(), scrollPane, statusLabel);

        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createControlPanel() {
        HBox hbox = new HBox();
        hbox.setPadding(DEFAULT_INSETS);
        hbox.setSpacing(DEFAULT_SPACING);
        hbox.setStyle(DEFAULT_CSS);

        Button generateCave = new Button("Generate Cave");
        generateCave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statusLabel.setText("Generate cave ...");
                long millisBefore = System.currentTimeMillis();

//                cave = generator.treeLeafs(CAVE_WIDTH, CAVE_HEIGHT);

                long generationTime = System.currentTimeMillis() - millisBefore;
                draw(canvas.getGraphicsContext2D());
                statusLabel.setText(String.format("Generated in %d millis", generationTime));
            }
        });

        Button allocateIds = new Button("Allocate Ids");
        allocateIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statusLabel.setText("Status: processing");
                long millisBefore = System.currentTimeMillis();
                int[][][] cells = allocator.allocateIds(cave);
                long generationTime = System.currentTimeMillis() - millisBefore;
                draw(canvas.getGraphicsContext2D());
                drawCells(canvas.getGraphicsContext2D(), cells);
                statusLabel.setText(String.format("Allocated in in %d millis", generationTime));
            }
        });

        Button adjustTopology = new Button("Adjust Topology");
        adjustTopology.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int steps = topologyManager.adjustTopology(cave);
                statusLabel.setText(String.format("Adjusted steps made %d", steps));
                draw(canvas.getGraphicsContext2D());
            }
        });

        Button generateImages = new Button("Generate Images");
        generateImages.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WritableImage wi = new WritableImage(CAVE_WIDTH * CELL_SIDE_PIXELS, CAVE_HEIGHT * CELL_SIDE_PIXELS);
                int iter = 0;
                while (iter < 100) {
                    ++iter;
                    wi = generateImages(wi);
                    writeImages(wi, iter);
                    System.out.println("Image created: " + iter);
                }
            }
        });

        hbox.getChildren().addAll(generateCave, allocateIds, adjustTopology, generateImages);
        return hbox;
    }

    private WritableImage generateImages(WritableImage wi) {
//        int[][] c = generator.treeLeafs(CAVE_WIDTH, CAVE_HEIGHT);
//        topologyManager.adjustTopology(c);
//        int[][][] cells = allocator.allocateIds(c);
//        drawCells(canvas.getGraphicsContext2D(), cells);
        return canvas.snapshot(new SnapshotParameters(), wi);
    }

    private void writeImages(WritableImage imageToSave, int iter) {
        File output = new File("D:\\workspaces\\out\\" + iter++ + ".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(imageToSave, null), "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCanvasMouseListener(final GraphicsContext gc) {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        MouseButton mouseButton = e.getButton();
                        int i = (int) (e.getY() / CELL_SIDE_PIXELS);
                        int j = (int) (e.getX() / CELL_SIDE_PIXELS);
                        switch (mouseButton) {
                            case PRIMARY:
                                cave[i][j] = 1;
                                draw(gc);
                                break;
                            case SECONDARY:
                                cave[i][j] = 0;
                                draw(gc);
                                break;
                            case MIDDLE:
                                allocator.chooseMatcher(cave, i, j);
                                gc.setFill(Color.BLUE);
                                gc.strokeRect(j * CELL_SIDE_PIXELS, i * CELL_SIDE_PIXELS, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
                                ids.add(cave[i][j]);
                                if (ids.size() == 9) {
                                    for (int k = 0; k < ids.size(); k++) {
                                            if(k % 3 == 0){
                                                System.out.println();
                                            } else {
                                                System.out.print(ids.get(k) + " ");
                                            }
                                    }
                                    System.out.println();
                                    ids.clear();
                                }
                                System.out.println("y = " + e.getY() + ", x = " + e.getX());
                        }
                    }
                });
    }

    private void drawCells(GraphicsContext gc, int[][][] cells) {
        int x = 0;
        int y = 0;
        for (int[][] row : cells) {
            for (int[] tiles : row) {
//                if (NO_TILE == tiles[0]) {
//                    gc.setFill(Color.RED);
//                    gc.fillRect(x, y, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
//                } else {
                    for (int tile : tiles) {
                        if (WALL_SOLID == tile) {
                            continue;
                        }
                        drawTile(gc, tile, x, y);
                    }
//                }
                x += CELL_SIDE_PIXELS;
            }
            x = 0;
            y += CELL_SIDE_PIXELS;
        }
    }

    private void drawTile(GraphicsContext gc, int tile, int x, int y) {
        Image i = tileIdToImage.get(tile);
        if (i != null) {
            gc.drawImage(i, x, y, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
        } else {
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
        }
    }

    private void draw(GraphicsContext gc) {
        int x = 0;
        int y = 0;
        for (int[] row : cave) {
            for (int cell : row) {
                gc.setFill(cell == FLOOR ? Color.WHITE : Color.BROWN);
                gc.fillRect(x, y, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
                x += CELL_SIDE_PIXELS;
            }
            x = 0;
            y += CELL_SIDE_PIXELS;
        }
    }

}
