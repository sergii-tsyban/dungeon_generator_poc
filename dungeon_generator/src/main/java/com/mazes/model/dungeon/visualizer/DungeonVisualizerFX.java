package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.allocator.TerrainTileAllocator;
import com.mazes.model.dungeon.generator.CellularAutomatonCaveGenerator;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.*;

public class DungeonVisualizerFX extends Application {

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

    public static final int CELL_SIDE_PIXELS = 8;

    public static final int CAVE_WIDTH = 250;
    public static final int CAVE_HEIGHT = 100;

    private static final Font LABEL_FONT = new Font("Verdana", 15);
    private static final Font TILE_ID_FONT = new Font("Verdana", 10);
    private static final Insets DEFAULT_INSETS = new Insets(10, 7, 10, 7);
    private static final int DEFAULT_SPACING = 10;
    private static final String DEFAULT_CSS = "-fx-background-color: #66b3ff;";

    private CellularAutomatonCaveGenerator generator;
    private TerrainTileAllocator allocator;
    private TopologyManager topologyManager;

    private Group root;
    private Canvas canvas;
    private Label statusLabel;

    private TextField initWallBirthProb;
    private TextField birthLimit;
    private TextField deathLimit;
    private TextField minimumOpenPercentage;

    public static void main(String[] args) {
        DungeonVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        canvas = new Canvas(CAVE_WIDTH * CELL_SIDE_PIXELS, CAVE_HEIGHT * CELL_SIDE_PIXELS);
        statusLabel = new Label();

        Scene scene = new Scene(root, Color.WHITE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1500, 600);
        scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        allocator = new TerrainTileAllocator();
        generator = new CellularAutomatonCaveGenerator(CAVE_WIDTH, CAVE_HEIGHT);
        topologyManager = new TopologyManager();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(TILE_ID_FONT);

        addCanvasMouseListener(gc);
        draw(gc);

        scrollPane.setContent(canvas);

        VBox vbox = new VBox();
        vbox.setPadding(DEFAULT_INSETS);
        vbox.setSpacing(DEFAULT_SPACING);
        vbox.getChildren().addAll(createControlPanel(), createInputPanel(), scrollPane, statusLabel);

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

                int initWallBirth = getInt(initWallBirthProb.getText(), generator.getInitWallBirthProb());
                int bLimit = getInt(birthLimit.getText(), generator.getBirthLimit());
                int dLimit = getInt(deathLimit.getText(), generator.getDeathLimit());
                int minOpenPercentage = getInt(minimumOpenPercentage.getText(), generator.getMinimumOpenPercentage());

                generator.setBirthLimit(bLimit);
                generator.setDeathLimit(dLimit);
                generator.setInitWallBirthProb(initWallBirth);
                generator.setMinimumOpenPercentage(minOpenPercentage);

                generator.addRoom(0, 0, CAVE_WIDTH, CAVE_HEIGHT);
                generator.generateRooms();
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
                int[][][] cells = allocator.allocateIds(generator.getCave());
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
//                topologyManager.executeAdjustmentStep(generator.getCave());
                int steps = topologyManager.adjustTopology(generator.getCave());
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
        generator.addRoom(0, 0, CAVE_WIDTH, CAVE_HEIGHT);
        generator.generateRooms();
        topologyManager.adjustTopology(generator.getCave());
        int[][][] cells = allocator.allocateIds(generator.getCave());
        drawCells(canvas.getGraphicsContext2D(), cells);
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

    private int getInt(String str, int def) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }

    private List<Integer> ids = new ArrayList<>();

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
                                generator.getCave()[i][j] = 1;
                                draw(gc);
                                break;
                            case SECONDARY:
                                generator.getCave()[i][j] = 0;
                                draw(gc);
                                break;
                            case MIDDLE:
                                allocator.chooseMatcher(generator.getCave(), i, j);
                                gc.setFill(Color.BLUE);
                                gc.strokeRect(j * CELL_SIDE_PIXELS, i * CELL_SIDE_PIXELS, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
                                ids.add(generator.getCave()[i][j]);
                                if (ids.size() == 9) {
                                    String out = "%d %d %d\n%d %d %d\n%d %d %d";
                                    System.out.println(String.format(out, ids.get(0), ids.get(1), ids.get(2), ids.get(3), ids.get(4), ids.get(5), ids.get(6), ids.get(7), ids.get(8)));
                                    ids.clear();
                                }
                        }
                    }
                });
    }

    private GridPane createInputPanel() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(DEFAULT_INSETS);
        grid.setStyle(DEFAULT_CSS);

        Label initWallBirthProbLabel = new Label("Init wall birth Prob");
        initWallBirthProbLabel.setFont(LABEL_FONT);
        initWallBirthProb = new TextField("" + generator.getInitWallBirthProb());

        Label birthLimitLabel = new Label("Floor become wall if nbr >");
        birthLimitLabel.setFont(LABEL_FONT);
        birthLimit = new TextField("" + generator.getBirthLimit());

        Label deathLimitLabel = new Label("Wall become floor if nbr <");
        deathLimitLabel.setFont(LABEL_FONT);
        deathLimit = new TextField("" + generator.getDeathLimit());

        Label minimumOpenPercentageLabel = new Label("Skip cave if open percentage <");
        minimumOpenPercentageLabel.setFont(LABEL_FONT);
        minimumOpenPercentage = new TextField("" + generator.getMinimumOpenPercentage());

        grid.addRow(0, initWallBirthProbLabel, initWallBirthProb, minimumOpenPercentageLabel, minimumOpenPercentage);
        grid.addRow(1, deathLimitLabel, deathLimit, birthLimitLabel, birthLimit);
        return grid;
    }

    private void drawCells(GraphicsContext gc, int[][][] cells) {
        int x = 0;
        int y = 0;
        for (int[][] row : cells) {
            for (int[] tiles : row) {
                if (NO_TILE == tiles[0]) {
                    gc.setFill(Color.RED);
                    gc.fillRect(x, y, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
                } else {
                    for (int tile : tiles) {
                        if (WALL_SOLID == tile) {
                            continue;
                        }
                        drawTile(gc, tile, x, y);
                    }
                }
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

        for (int[] row : generator.getCave()) {
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
