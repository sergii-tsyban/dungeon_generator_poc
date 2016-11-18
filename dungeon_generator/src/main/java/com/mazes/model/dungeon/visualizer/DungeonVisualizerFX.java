package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.allocator.TileIdAllocator;
import com.mazes.model.dungeon.cell.Cell;
import com.mazes.model.dungeon.common.TilesIds;
import com.mazes.model.dungeon.generator.CellularAutomatonCaveGeneration;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;
import static com.mazes.model.dungeon.common.TilesIds.WALL_SOLID;

public class DungeonVisualizerFX extends Application{

    private static Map<Integer, Color> tileIdToColor = new HashMap<>();

    static {
        tileIdToColor.put(WALL_SOLID, Color.BROWN);
        tileIdToColor.put(FLOOR, Color.WHITE);

        tileIdToColor.put(TilesIds.NO_TILE, Color.BLACK);
    }

    public static final int CELL_SIDE_PIXELS = 8;

    public static final int CAVE_WIDTH = 100;
    public static final int CAVE_HEIGHT = 100;

    private static final Font LABEL_FONT = new Font("Verdana", 15);
    private static final Font TILE_ID_FONT = new Font("Verdana", 10);
    private static final Insets DEFAULT_INSETS = new Insets(10, 7, 10, 7);
    private static final int DEFAULT_SPACING = 10;
    private static final String DEFAULT_CSS = "-fx-background-color: #66b3ff;";

    private CellularAutomatonCaveGeneration carg;
    private TileIdAllocator allocator;

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
        scrollPane.setPrefSize(300, 600);
        scrollPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        allocator = new TileIdAllocator();
        carg = new CellularAutomatonCaveGeneration(CAVE_WIDTH, CAVE_HEIGHT);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(TILE_ID_FONT);

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

    private HBox createControlPanel(){
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

                int initWallBirth = getInt(initWallBirthProb.getText(), carg.getInitWallBirthProb());
                int bLimit = getInt(birthLimit.getText(), carg.getBirthLimit());
                int dLimit = getInt(deathLimit.getText(), carg.getDeathLimit());
                int minOpenPercentage = getInt(minimumOpenPercentage.getText(), carg.getMinimumOpenPercentage());

                carg.setBirthLimit(bLimit);
                carg.setDeathLimit(dLimit);
                carg.setInitWallBirthProb(initWallBirth);
                carg.setMinimumOpenPercentage(minOpenPercentage);

                carg.addRoom(0,0,CAVE_WIDTH, CAVE_HEIGHT);
                carg.generateRooms();
                long generationTime = System.currentTimeMillis() - millisBefore;
                draw(canvas.getGraphicsContext2D());
                statusLabel.setText(String.format("Generated in %d millis",  generationTime));
            }
        });

        Button allocateIds = new Button("Allocate Ids");
        allocateIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statusLabel.setText("Status: processing");
                long millisBefore = System.currentTimeMillis();
                Cell[][] cells = allocator.allocateIds(carg.getCave());
                long generationTime = System.currentTimeMillis() - millisBefore;
                draw(canvas.getGraphicsContext2D());
                drawCells(canvas.getGraphicsContext2D(), cells);
                statusLabel.setText(String.format("Allocated in in %d millis",  generationTime));
            }
        });

        hbox.getChildren().addAll(generateCave, allocateIds);
        return hbox;
    }

    private int getInt(String str, int def){
        try{
            return Integer.parseInt(str);
        } catch (Exception e){
            return def;
        }
    }

    private GridPane createInputPanel(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(DEFAULT_INSETS);
        grid.setStyle(DEFAULT_CSS);

        Label initWallBirthProbLabel = new Label("Init wall birth Prob");
        initWallBirthProbLabel.setFont(LABEL_FONT);
        initWallBirthProb = new TextField(""+carg.getInitWallBirthProb());

        Label birthLimitLabel = new Label("Floor become wall if nbr >");
        birthLimitLabel.setFont(LABEL_FONT);
        birthLimit = new TextField(""+carg.getBirthLimit());

        Label deathLimitLabel = new Label("Wall become floor if nbr <");
        deathLimitLabel.setFont(LABEL_FONT);
        deathLimit = new TextField(""+carg.getDeathLimit());

        Label minimumOpenPercentageLabel = new Label("Skip cave if open percentage <");
        minimumOpenPercentageLabel.setFont(LABEL_FONT);
        minimumOpenPercentage = new TextField(""+carg.getMinimumOpenPercentage());

        grid.addRow(0, initWallBirthProbLabel, initWallBirthProb, minimumOpenPercentageLabel, minimumOpenPercentage);
        grid.addRow(1, deathLimitLabel, deathLimit, birthLimitLabel, birthLimit);
        return grid;
    }

    private void drawCells(GraphicsContext gc, Cell[][] cells){
        int x = 0;
        int y = 0;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                int[] tiles = cell.getTileLayers();
                Color c = tileIdToColor.get(tiles[0]);
                if(c == null){
                    c = Color.RED;
                }
                gc.setFill(c);
                gc.fillRect(x,y, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
                x += CELL_SIDE_PIXELS;
            }
            x = 0;
            y += CELL_SIDE_PIXELS;
        }
    }

    private void draw(GraphicsContext gc){
        int x = 0;
        int y = 0;

        for (int[] row : carg.getCave()) {
            for (int cell : row) {
                gc.setFill(cell == FLOOR ? Color.WHITE: Color.BROWN);
                gc.fillRect(x,y, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
                x += CELL_SIDE_PIXELS;
            }
            x = 0;
            y += CELL_SIDE_PIXELS;
        }
    }
}
