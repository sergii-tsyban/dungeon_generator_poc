package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.allocator.TileIdAllocator;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.mazes.model.dungeon.common.TilesIds.FLOOR;
import static com.mazes.model.dungeon.common.TilesIds.WALL_SOLID;

public class DungeonVisualizerFX extends Application{

    public static final int CELL_SIDE = 16;

    public static final int CAVE_WIDTH = 50;
    public static final int CAVE_HEIGHT = 50;

    private CellularAutomatonCaveGeneration carg;
    private TileIdAllocator allocator;

    private Group root = new Group();
    private Canvas canvas;
    private Label label;

    public static void main(String[] args) {
        DungeonVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(CAVE_WIDTH * CELL_SIDE, CAVE_HEIGHT * CELL_SIDE);
        allocator = new TileIdAllocator();

        Scene scene = new Scene(root, Color.WHITE);
        label = new Label("");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 7, 10, 7));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(createControlPanel(), canvas, label);

        carg = new CellularAutomatonCaveGeneration(CAVE_WIDTH, CAVE_HEIGHT);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(new Font("Verdana", 10));

        draw(gc);
        
        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createControlPanel(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        Button generateCave = new Button("Generate Cave");
        generateCave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText("Status: processing");
                long millisBefore = System.currentTimeMillis();
                carg.addRoom(0,0,CAVE_WIDTH, CAVE_HEIGHT);
                carg.generateRooms();
                long generationTime = System.currentTimeMillis() - millisBefore;
                draw(canvas.getGraphicsContext2D());
                label.setText(String.format("Generated in %d millis",  generationTime));
            }
        });
        Button allocateIds = new Button("Allocate Ids");
        allocateIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText("Status: processing");
                long millisBefore = System.currentTimeMillis();
                allocator.allocateIds(carg.getCave());
                long generationTime = System.currentTimeMillis() - millisBefore;
                draw(canvas.getGraphicsContext2D());
                label.setText(String.format("Allocated in in %d millis",  generationTime));
            }
        });
        hbox.getChildren().addAll(generateCave, allocateIds);
        return hbox;
    }

    private void draw(GraphicsContext gc){
        int x = 0;
        int y = 0;
        for (int[] row : carg.getCave()) {
            for (int cell : row) {
                gc.setFill(cell == FLOOR ? Color.WHITE: Color.BROWN);
                gc.fillRect(x,y, CELL_SIDE, CELL_SIDE);
                gc.setFill(cell == FLOOR ? Color.BROWN: Color.WHITE);
                if(cell > 10){
                    gc.fillText(""+cell, x, y + 10);
                } else {
                    gc.fillText(""+cell, x + 6, y + 10);
                }
                x += CELL_SIDE;
            }
            x = 0;
            y += CELL_SIDE;
        }
    }
}
